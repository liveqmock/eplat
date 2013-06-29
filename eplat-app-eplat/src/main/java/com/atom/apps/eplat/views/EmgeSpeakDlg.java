package com.atom.apps.eplat.views;

import mplat.mgt.MgtFactory;
import mplat.mgt.SpeakMgt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import swing2swt.layout.BorderLayout;

import com.atom.apps.eplat.SWTUtils;

public class EmgeSpeakDlg extends Dialog implements SelectionListener {
    /** 视图 */
    private Button   rbtnSpeakOpen;
    private Button   rbtnSpeakClose;
    private Combo    cmbSpeak;
    private Spinner  spnCount;
    private Spinner  spnSeconds;
    private Button   btnSure;
    private Button   btnCancel;

    protected Object result;
    protected Shell  shell;

    /**
     * 入口-测试
     */
    public static void main(String[] args) {
        EmgeSpeakDlg dialog = new EmgeSpeakDlg(new Shell());
        dialog.open();
    }

    /**
     * Create the dialog.
     */
    public EmgeSpeakDlg(Shell parent) {
        super(parent, SWT.NONE);
    }

    /**
     * Open the dialog.
     */
    public Object open() {
        initComponents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        return result;
    }

    /**
     * Create contents of the dialog.
     */
    private void initComponents() {
        this.shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        this.shell.setSize(350, 252);
        SWTUtils.center(this.getParent(), this.shell);
        this.shell.setImages(SWTUtils.findImgIcons());
        this.shell.setText("模拟人发音设置");
        this.shell.setLayout(new BorderLayout(0, 0));

        Group grpCenter = new Group(shell, SWT.NONE);
        grpCenter.setLayoutData(BorderLayout.CENTER);

        this.rbtnSpeakOpen = new Button(grpCenter, SWT.RADIO);
        this.rbtnSpeakOpen.addSelectionListener(this);
        this.rbtnSpeakOpen.setBounds(10, 28, 110, 17);
        this.rbtnSpeakOpen.setText("启动模拟人发音");

        this.cmbSpeak = new Combo(grpCenter, SWT.READ_ONLY);
        this.cmbSpeak.setBounds(138, 23, 150, 25);
        SpeakMgt mgt = MgtFactory.get().findSpeakMgt();
        this.cmbSpeak.setItems(SpeakMgt.toArray(mgt.findSpeaks()));

        Label label = new Label(grpCenter, SWT.NONE);
        label.setBounds(109, 64, 61, 17);
        label.setText("次数：");

        this.spnCount = new Spinner(grpCenter, SWT.BORDER);
        this.spnCount.setMaximum(1000);
        this.spnCount.setSelection(1);
        this.spnCount.setPageIncrement(1);
        this.spnCount.setBounds(176, 61, 50, 23);

        Label label_1 = new Label(grpCenter, SWT.NONE);
        label_1.setBounds(109, 108, 61, 17);
        label_1.setText("间隔：");

        this.spnSeconds = new Spinner(grpCenter, SWT.BORDER);
        this.spnSeconds.setMaximum(1000);
        this.spnSeconds.setPageIncrement(1);
        this.spnSeconds.setBounds(176, 105, 50, 23);

        this.rbtnSpeakClose = new Button(grpCenter, SWT.RADIO);
        this.rbtnSpeakClose.addSelectionListener(this);
        this.rbtnSpeakClose.setBounds(10, 163, 97, 17);
        this.rbtnSpeakClose.setText("停止发音");

        Label label_2 = new Label(grpCenter, SWT.NONE);
        label_2.setBounds(227, 108, 61, 17);
        label_2.setText("秒");

        Composite cptBottom = new Composite(shell, SWT.NONE);
        cptBottom.setLayoutData(BorderLayout.SOUTH);
        cptBottom.setLayout(null);

        this.btnSure = new Button(cptBottom, SWT.NONE);
        this.btnSure.addSelectionListener(this);
        this.btnSure.setBounds(155, 0, 80, 27);
        this.btnSure.setText("确定");

        this.btnCancel = new Button(cptBottom, SWT.NONE);
        this.btnCancel.addSelectionListener(this);
        this.btnCancel.setBounds(254, 0, 80, 27);
        this.btnCancel.setText("取消");
    }

    /** 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent evt) {
        Object evtObj = evt.getSource();
        if (evtObj == null) {
            return;
        }

        // 启动发音
        if (evtObj == this.rbtnSpeakOpen) {
            boolean enable = this.rbtnSpeakOpen.getSelection();
            this.cmbSpeak.setEnabled(enable);
            this.spnCount.setEnabled(enable);
            this.spnSeconds.setEnabled(enable);
        }

        // 停止发音
        else if (evtObj == this.rbtnSpeakClose) {
            boolean enable = this.rbtnSpeakOpen.getSelection();
            this.cmbSpeak.setEnabled(enable);
            this.spnCount.setEnabled(enable);
            this.spnSeconds.setEnabled(enable);
        }

        // 确定按钮
        else if (evtObj == this.btnSure) {
            // TODO:
            this.shell.dispose();
        }

        // 取消按钮
        else if (evtObj == this.btnCancel) {
            this.shell.dispose();
        }
    }

    /** 
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
    }

}
