package com.atom.apps.eplat.views;

import java.util.List;

import mplat.mgt.MgtFactory;
import mplat.mgt.dto.UserInfoDTO;
import mplat.mgt.msgs.DataMSG;
import mplat.utils.PortUtils;

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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.utils.LogUtils;

public class UserLoginView extends ApplicationWindow {
    private Combo cboxPort;
    private Text  txtUserName;
    private Text  txtPasswd;

    /**
     * Launch the application.
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
        lblLogo.setBounds(0, 0, 350, 50);
        lblLogo.setText("");
        lblLogo.setBackgroundImage(SWTUtils.findImage(parent.getDisplay(), "login-logo.jpg", lblLogo.getBounds()));

        Label label = new Label(container, SWT.NONE);
        label.setAlignment(SWT.RIGHT);
        label.setBounds(21, 69, 70, 17);
        label.setText("下位机：");

        cboxPort = new Combo(container, SWT.READ_ONLY);
        cboxPort.setItems(new String[] { "COM1", "COM2" });
        cboxPort.setBounds(104, 66, 200, 25);
        cboxPort.select(0);

        Label label_1 = new Label(container, SWT.NONE);
        label_1.setAlignment(SWT.RIGHT);
        label_1.setBounds(30, 114, 61, 17);
        label_1.setText("用户名：");

        txtUserName = new Text(container, SWT.BORDER);
        txtUserName.setBounds(104, 111, 200, 23);

        Label label_2 = new Label(container, SWT.NONE);
        label_2.setAlignment(SWT.RIGHT);
        label_2.setBounds(30, 155, 61, 17);
        label_2.setText("密码：");

        txtPasswd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        txtPasswd.setBounds(104, 152, 200, 23);

        Button btnLogin = new Button(container, SWT.NONE);
        btnLogin.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String userName = txtUserName.getText();
                if (StringUtils.isBlank(userName)) {
                    SWTUtils.alert(getShell(), "用户名为空，请重新输入！");
                    txtUserName.setFocus();
                    return;
                }

                String passwd = txtPasswd.getText();
                UserInfoDTO user = MgtFactory.get().findUserMgt().login(userName, passwd);
                if (user == null) {
                    SWTUtils.alert(getShell(), "登录失败，用户不存在或密码错误，请重新输入！");
                    txtUserName.setFocus();
                    return;
                }

                String cport = cboxPort.getText();
                DataMSG dataMsg = DataMSG.get().setPortName(cport);
                if (!dataMsg.openPort()) {
                    SWTUtils.alert(getShell(), "虚拟人体连接失败，请重新选择串口！");
                    initPortNames();
                    return;
                } else {
                    // TODO: 初始化消息
                    dataMsg.writeData(new byte[] { 1, 2, 3, 4, 5, 6 });
                }

                LogUtils.get().info("登录成功:: CPort[{}], UserName[{}], Passwd[{}].", cport, userName, passwd);

                // 显示主窗口
                close();
                MainSetView window = new MainSetView();
                window.setBlockOnOpen(true);
                window.open();
            }
        });
        btnLogin.setBounds(125, 191, 80, 27);
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
        btnClear.setBounds(224, 191, 80, 27);
        btnClear.setText("清除");

        // TODO:测试数据
        UserInfoDTO user = MgtFactory.get().findUserMgt().find("admin");
        this.txtUserName.setText("admin");
        this.txtPasswd.setText(user.getUserPasswd());

        // 初始化串口
        this.initPortNames();

        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
    }

    /**
     * 初始化串口
     */
    private final void initPortNames() {
        List<String> ports = PortUtils.findSerialPortNames();
        this.cboxPort.setItems(ports.toArray(new String[] {}));
        this.cboxPort.select(0);
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
        newShell.setImages(SWTUtils.findImgIcons());
        
        SWTUtils.center(newShell);
    }

    /** 
     * @see org.eclipse.jface.window.Window#getInitialSize()
     */
    protected Point getInitialSize() {
        return new Point(350, 260);
    }
}
