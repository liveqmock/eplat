/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 登录视图
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserLoginView.java, V1.0.1 2013-4-2 下午9:39:28 $
 */
public final class UserLoginView {

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
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(365, 270);
        shell.setText("用户登录");
        shell.setLayout(null);

        Label label = new Label(shell, SWT.NONE);
        label.setBounds(34, 104, 48, 17);
        label.setText("用户名：");

        txtUserName = new Text(shell, SWT.BORDER);
        txtUserName.setBounds(87, 101, 228, 23);

        Label label_1 = new Label(shell, SWT.NONE);
        label_1.setBounds(320, 52, 0, 17);

        Label label_2 = new Label(shell, SWT.NONE);
        label_2.setBounds(46, 154, 36, 17);
        label_2.setAlignment(SWT.RIGHT);
        label_2.setText("密码：");

        txtPasswd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
        txtPasswd.setBounds(87, 151, 228, 23);

        Label label_3 = new Label(shell, SWT.NONE);
        label_3.setBounds(34, 48, 48, 17);
        label_3.setText("下位机：");

        Combo cboxPorts = new Combo(shell, SWT.READ_ONLY);
        cboxPorts.setBounds(87, 44, 228, 25);
        cboxPorts.setItems(new String[] { "COM1", "COM2" });
        cboxPorts.select(0);

        Button btnLogin = new Button(shell, SWT.NONE);
        btnLogin.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println("用户登录-UserName[" + txtUserName.getText() + "], Passwd[" + txtPasswd.getText() + "].");
            }
        });
        btnLogin.setBounds(132, 195, 80, 27);
        btnLogin.setText("登录");

        Button btnReset = new Button(shell, SWT.NONE);
        btnReset.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                txtUserName.setText(StringUtils.EMPTY);
                txtPasswd.setText(StringUtils.EMPTY);
            }
        });
        btnReset.setBounds(235, 195, 80, 27);
        btnReset.setText("清除");

        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setBounds(405, 184, 0, 17);
    }
}
