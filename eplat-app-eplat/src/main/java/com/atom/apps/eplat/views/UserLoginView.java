package com.atom.apps.eplat.views;

import mplat.mgt.MgtFactory;
import mplat.mgt.dto.UserInfoDTO;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.atom.apps.SWTUtils;
import com.atom.core.lang.utils.LogUtils;

public class UserLoginView extends ApplicationWindow {
    private Combo cboxPort;
    private Text  txtUserName;
    private Text  txtPasswd;

    /**
     * Create the application window.
     */
    public UserLoginView() {
        super(null);
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        // addStatusLine();
    }

    /** 
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(null);

        Label lblLogo = new Label(container, SWT.NONE);
        lblLogo.setBounds(0, 0, 352, 27);
        lblLogo.setText("New Label");

        Label label = new Label(container, SWT.NONE);
        label.setAlignment(SWT.RIGHT);
        label.setBounds(21, 45, 70, 17);
        label.setText("下位机：");

        cboxPort = new Combo(container, SWT.READ_ONLY);
        cboxPort.setItems(new String[] { "COM1", "COM2" });
        cboxPort.setBounds(104, 42, 200, 25);
        cboxPort.select(0);

        Label label_1 = new Label(container, SWT.NONE);
        label_1.setAlignment(SWT.RIGHT);
        label_1.setBounds(30, 99, 61, 17);
        label_1.setText("用户名：");

        txtUserName = new Text(container, SWT.BORDER);
        txtUserName.setBounds(104, 96, 200, 23);

        Label label_2 = new Label(container, SWT.NONE);
        label_2.setAlignment(SWT.RIGHT);
        label_2.setBounds(30, 140, 61, 17);
        label_2.setText("密码：");

        txtPasswd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        txtPasswd.setBounds(104, 137, 200, 23);

        Button btnLogin = new Button(container, SWT.NONE);
        btnLogin.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String userName = txtUserName.getText();
                if (StringUtils.isBlank(userName)) {
                    SWTUtils.alert(getShell(), "用户名为空，请重新输入！");
                    return;
                }

                String passwd = txtPasswd.getText();
                UserInfoDTO user = MgtFactory.get().findUserMgt().login(userName, passwd);
                if (user == null) {
                    SWTUtils.alert(getShell(), "登录失败，用户不存在或密码错误，请重新输入！");
                    return;
                }

                String cport = cboxPort.getText();
                LogUtils.get().info("登录成功:: CPort[{}], UserName[{}], Passwd[{}].", cport, userName, passwd);

                // 显示主窗口
                close();
                HomeMainView window = new HomeMainView();
                window.setBlockOnOpen(true);
                window.open();
            }
        });
        btnLogin.setBounds(125, 183, 80, 27);
        btnLogin.setText("登录");

        Button btnClear = new Button(container, SWT.NONE);
        btnClear.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (cboxPort.getItemCount() > 0) {
                    cboxPort.select(0);
                }
                txtUserName.setText(StringUtils.EMPTY);
                txtPasswd.setText(StringUtils.EMPTY);
            }
        });
        btnClear.setBounds(224, 183, 80, 27);
        btnClear.setText("清除");

        // TODO:测试数据
        UserInfoDTO user = MgtFactory.get().findUserMgt().find("admin");
        this.txtUserName.setText("admin");
        this.txtPasswd.setText(user.getUserPasswd());

        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
    }

    /** 
     * @see org.eclipse.jface.window.Window#getShellStyle()
     */
    protected int getShellStyle() {
        return SWT.CLOSE | SWT.MIN | SWT.SYSTEM_MODAL;
    }

    /** 
     * @see org.eclipse.jface.window.ApplicationWindow#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("用户登录");
        
        SWTUtils.center(newShell);
    }

    /** 
     * @see org.eclipse.jface.window.Window#getInitialSize()
     */
    protected Point getInitialSize() {
        return new Point(360, 260);
    }
}
