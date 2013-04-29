package com.atom.apps.eplat.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.atom.apps.eplat.SWTResourceManager;
import com.atom.apps.eplat.SWTUtils;

public class SystemInfoView extends Dialog {

    /**
     * Create the dialog.
     * @param parentShell
     */
    public SystemInfoView(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(null);

        Label lblLogo = new Label(container, SWT.NONE);
        lblLogo.setImage(SWTResourceManager.getImage(SystemInfoView.class, "/mplat/uijfx/images/about.gif"));
        lblLogo.setBounds(24, 22, 52, 52);
        lblLogo.setText("");

        Label lblTitle = new Label(container, SWT.NONE);
        lblTitle.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
        lblTitle.setBounds(112, 22, 310, 28);
        lblTitle.setText("高级生命支持急救技能训练软件");
        
        Label lblVersion = new Label(container, SWT.NONE);
        lblVersion.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
        lblVersion.setBounds(112, 96, 310, 17);
        lblVersion.setText("系统版本：V1.0.1");

        Label lblPubDate = new Label(container, SWT.NONE);
        lblPubDate.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
        lblPubDate.setBounds(112, 119, 310, 17);
        lblPubDate.setText("发布日期：2013年07月");

        Label lblCategory = new Label(container, SWT.NONE);
        lblCategory.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
        lblCategory.setBounds(112, 142, 310, 17);
        lblCategory.setText("软件型号：单机版");

        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        Button button = createButton(parent, IDialogConstants.SKIP_ID, "确认", true);
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                close();
            }
        });
    }

    /** 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);

        shell.setText("系统信息");
        shell.setImages(SWTUtils.findImgIcons());
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(450, 254);
    }

}
