package com.atom.apps.eplat.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Composite;

public class EmgeTrainView extends Composite implements ControlListener {

    /** 急救训练 */
    private EmgeMainView cmptMain;

    /**
     * Create the composite.
     */
    public EmgeTrainView(Composite parent) {
        super(parent, SWT.NONE);
        initComponents();
    }

    private void initComponents() {
        this.cmptMain = new EmgeMainView(this, "AnimeCase8");

        this.addControlListener(this);

        this.relocateComposite();
    }

    /**
     * 设置组件居中显示
     */
    private void relocateComposite() {
        int width = EmgeMainView.SIZE.getOne();
        int height = EmgeMainView.SIZE.getTwo();
        
        int x = (this.getSize().x - width) / 2;
        
        this.cmptMain.setBounds(x, 0, width, height);
    }

    /** 
     * @see org.eclipse.swt.widgets.Composite#checkSubclass()
     */
    protected void checkSubclass() {
        super.checkSubclass();
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
        this.relocateComposite();
    }

}
