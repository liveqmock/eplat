package com.atom.apps.eplat.views;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.utils.LogUtils;

public class EmgeCaseCmpt extends Composite implements ControlListener, DisposeListener {

    private final Composite mainView;
    private final String    caseName;
    private final Browser   browser;
    private BrowserFunction function;

    /**
     * Create the composite.
     */
    public EmgeCaseCmpt(Composite parent, String caseName) {
        super(parent, SWT.NONE);

        this.mainView = parent;
        this.caseName = caseName;
        this.browser = new Browser(this, SWT.NONE);
        this.browser.setBounds(0, 0, 100, 100);

        // this.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));

        this.addControlListener(this);
        this.addDisposeListener(this);
    }

    /** 
     * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
     */
    public void widgetDisposed(DisposeEvent evt) {
        this.function.dispose();
        this.browser.close();
    }

    /** 
     * @see org.eclipse.swt.events.ControlListener#controlMoved(org.eclipse.swt.events.ControlEvent)
     */
    public void controlMoved(ControlEvent evt) {
    }

    /** 
     * @see org.eclipse.swt.events.ControlListener#controlResized(org.eclipse.swt.events.ControlEvent)
     */
    public void controlResized(ControlEvent evt) {
        this.browser.setSize(this.getSize());
    }

    /**
     * 加载Flash动画
     */
    public void startAnimeCase() {
        String html = SWTUtils.findEmgeCaseHtml(this.caseName);
        LogUtils.info("准备加载HTML动画：" + html);

        this.browser.setUrl(html);
        this.browser.addProgressListener(new ProgressAdapter() {
            public void completed(ProgressEvent evt) {
            }
        });

        this.function = new BrowserFunction(this.browser, SWTUtils.FUNC_FIRE_EVENT) {
            public Object function(Object[] args) {
                LogUtils.info("JS回调：" + args);

                if (args == null || args.length < 1) {
                    return null;
                }

                String arg = ObjectUtils.toString(args[0]);

                // 进入场景
                if (StringUtils.equalsIgnoreCase(arg, "1")) {
                    // TODO:
                    //                    setVisible(false);
                }

                return args;
            }
        };
    }
}
