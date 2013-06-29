package com.atom.apps.eplat.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.sf.feeling.swt.win32.extension.ole.flash.Flash;
import org.sf.feeling.swt.win32.extension.ole.flash.listener.FlashEventListener;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.utils.LogUtils;

public class CmptEmergeCase extends Composite implements FlashEventListener {

    private final Composite mainView;
    private Flash           flash;
    private final String    caseName;

    /**
     * Create the composite.
     */
    public CmptEmergeCase(Composite parent, String caseName) {
        super(parent, SWT.NONE);

        this.mainView = parent;
        this.flash = new Flash(this.mainView, SWT.NONE, this);
        this.caseName = caseName;
        
        this.setSize(500, 350);
    }

    /**
     * @see org.sf.feeling.swt.win32.extension.ole.flash.listener.FlashEventListener#onReadyStateChange(int)
     */
    public void onReadyStateChange(int newState) {
//        MessageBox message = new MessageBox(this.mainView.getShell());
//        message.setText("Receive Event");
//        message.setMessage("Receive a FSCommand Event: NewState = " + newState);
//        message.open();
    }

    /** 
     * @see org.sf.feeling.swt.win32.extension.ole.flash.listener.FlashEventListener#onProgress(int)
     */
    public void onProgress(int percentDone) {
        //        MessageBox message = new MessageBox(this.mainView.getShell());
        //        message.setText("Receive Event");
        //        message.setMessage("Receive a FSCommand Event: PercentDone = " + percentDone);
        //        message.open();
    }

    /** 
     * @see org.sf.feeling.swt.win32.extension.ole.flash.listener.FlashEventListener#onFSCommand(java.lang.String, java.lang.String)
     */
    public void onFSCommand(String command, String args) {
        MessageBox message = new MessageBox(this.mainView.getShell());
        message.setText("Receive Event");
        message.setMessage("Receive a FSCommand Event: command = " + command + ", value = " + args);
        message.open();
    }

    /**
     * 播放Flash动画
     */
    public void playCase() {
        String swf = SWTUtils.findEmgeCaseFlash(this.caseName);
        LogUtils.info("准备加载Flash动画：" + swf);
        this.flash.loadMovie(0, swf);
    }

}
