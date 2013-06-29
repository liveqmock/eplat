package com.atom.apps.eplat.views;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

import com.atom.apps.eplat.SWTUtils;

public class EmgeLogDlg extends Dialog implements SelectionListener {
    /** 回传 */
    private final List<String> logs;

    private Text               text;

    private Button             btnSure;
    private Button             btnClose;

    /**
     * Create the dialog
     */
    public EmgeLogDlg(Shell parentShell, List<String> rtnLogs) {
        super(parentShell);

        rtnLogs.clear();
        this.logs = rtnLogs;
    }

    /** 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new BorderLayout(0, 0));

        text = new Text(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        text.setLayoutData(BorderLayout.CENTER);

        return container;
    }

    /** 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        this.btnSure = createButton(parent, IDialogConstants.SKIP_ID, "确定", true);
        btnSure.addSelectionListener(this);

        this.btnClose = createButton(parent, IDialogConstants.SKIP_ID, "关闭", false);
        btnClose.addSelectionListener(this);
    }

    /** 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);

        shell.setText("增加事件日志");
        shell.setImages(SWTUtils.findImgIcons());
    }

    /**
     * Return the initial size of the dialog.
     */
    protected Point getInitialSize() {
        return new Point(400, 350);
    }

    /** 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent evt) {
        Object source = evt.getSource();

        // 关闭
        if (source == this.btnClose) {
            this.close();
        }

        // 增加属性日志
        else if (source == this.btnSure) {
            String data = this.text.getText();
            String[] items = StringUtils.split(data, SystemUtils.LINE_SEPARATOR);
            this.logs.addAll(Arrays.asList(items));
            this.close();
        }
    }

    /** 
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent evt) {
    }

}
