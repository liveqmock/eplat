/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.atom.apps.SWTUtils;

/**
 * 登录视图
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserLoginView.java, V1.0.1 2013-4-2 下午9:39:28 $
 */
public final class UserLoginView extends ApplicationWindow {

    protected Shell shell;
    private Text    txtUserName;
    private Text    txtPasswd;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            UserLoginView window = new UserLoginView();
            window.setBlockOnOpen(true);
            window.open();

            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 默认构造器
     */
    public UserLoginView() {
        super(null);
    }

    /** 
     * @see org.eclipse.jface.window.Window#initializeBounds()
     */
    protected void initializeBounds() {
        this.getShell().setSize(365, 270);
        this.getShell().setText("用户登录");

        SWTUtils.center(this.getShell());
    }

    /** 
     * @see org.eclipse.jface.window.ApplicationWindow#getLayout()
     */
    protected Layout getLayout() {
        return null;
    }

    /** 
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        Label label = new Label(this.getShell(), SWT.NONE);
        label.setBounds(34, 104, 48, 17);
        label.setText("用户名：");

        txtUserName = new Text(this.getShell(), SWT.BORDER);
        txtUserName.setBounds(87, 101, 228, 23);

        Label label_1 = new Label(this.getShell(), SWT.NONE);
        label_1.setBounds(320, 52, 0, 17);

        Label label_2 = new Label(this.getShell(), SWT.NONE);
        label_2.setBounds(46, 154, 36, 17);
        label_2.setAlignment(SWT.RIGHT);
        label_2.setText("密码：");

        txtPasswd = new Text(this.getShell(), SWT.BORDER | SWT.PASSWORD);
        txtPasswd.setBounds(87, 151, 228, 23);

        Label label_3 = new Label(this.getShell(), SWT.NONE);
        label_3.setBounds(34, 48, 48, 17);
        label_3.setText("下位机：");

        final Combo cboxPorts = new Combo(this.getShell(), SWT.READ_ONLY);
        cboxPorts.setBounds(87, 44, 228, 25);
        cboxPorts.setItems(new String[] { "COM1", "COM2" });
        cboxPorts.select(0);

        Button btnLogin = new Button(this.getShell(), SWT.NONE);
        btnLogin.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String cport = cboxPorts.getItem(cboxPorts.getSelectionIndex());
                System.out.println("用户登录-CPort[" + cport + "], UserName[" + txtUserName.getText() + "], Passwd[" + txtPasswd.getText() + "].");
            }
        });
        btnLogin.setBounds(132, 195, 80, 27);
        btnLogin.setText("登录");

        Button btnReset = new Button(this.getShell(), SWT.NONE);
        btnReset.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                txtUserName.setText(StringUtils.EMPTY);
                txtPasswd.setText(StringUtils.EMPTY);
            }
        });
        btnReset.setBounds(235, 195, 80, 27);
        btnReset.setText("清除");

        Label lblNewLabel = new Label(this.getShell(), SWT.NONE);
        lblNewLabel.setBounds(405, 184, 0, 17);

        return parent;
    }
}
