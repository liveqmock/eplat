package com.atom.apps.eplat.views;

import mplat.mgt.MgtFactory;
import mplat.mgt.UserMgt;
import mplat.mgt.dto.UserInfoDTO;

import org.apache.commons.lang.StringUtils;
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
import org.eclipse.swt.widgets.Text;

import com.atom.apps.eplat.SWTUtils;

public class UserUpdateView extends Dialog {

    private Text              userName;
    private Text              passwd;
    private Text              passwd2;

    private final UserMgt     umgt = MgtFactory.get().findUserMgt();

    /** 用户信息 */
    private final UserInfoDTO user;
    private final boolean     update;

    /**
     * Create the dialog.
     * @param parentShell
     */
    public UserUpdateView(Shell parentShell, UserInfoDTO user) {
        super(parentShell);

        this.user = user;
        this.update = (this.user.getId() > 0L);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(null);

        Label label = new Label(container, SWT.NONE);
        label.setBounds(11, 15, 48, 17);
        label.setAlignment(SWT.RIGHT);
        label.setBounds(35, 26, 61, 17);
        label.setText("用户名：");

        userName = new Text(container, SWT.BORDER);
        userName.setBounds(11, 41, 73, 23);
        userName.setEditable(!this.update);
        userName.setBounds(102, 23, 172, 23);

        Label label_1 = new Label(container, SWT.NONE);
        label_1.setBounds(11, 73, 36, 17);
        label_1.setAlignment(SWT.RIGHT);
        label_1.setBounds(35, 63, 61, 17);
        label_1.setText("密码：");

        passwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        passwd.setBounds(11, 99, 73, 23);
        passwd.setBounds(102, 60, 172, 23);

        Label label_2 = new Label(container, SWT.NONE);
        label_2.setBounds(11, 131, 60, 17);
        label_2.setAlignment(SWT.RIGHT);
        label_2.setBounds(35, 99, 61, 17);
        label_2.setText("确认密码：");

        passwd2 = new Text(container, SWT.BORDER | SWT.PASSWORD);
        passwd2.setBounds(11, 157, 73, 23);
        passwd2.setBounds(102, 96, 172, 23);

        /*
        Button btnSure = new Button(container, SWT.NONE);
        btnSure.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                String ptxt = passwd.getText();
                if (StringUtils.isBlank(ptxt) || !StringUtils.equals(ptxt, passwd2.getText())) {
                    SWTUtils.alert(getShell(), "密码为空或与确认密码不相等！");
                    return;
                }

                user.setUserPasswd(ptxt);
                MgtFactory.get().findUserMgt().update(user);

                SWTUtils.alert(getShell(), "用户信息修改成功！");
                // 关闭
                close();
            }
        });
        btnSure.setBounds(108, 137, 80, 27);
        btnSure.setText("确认");

        Button btnClear = new Button(container, SWT.NONE);
        btnClear.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                passwd.setText(StringUtils.EMPTY);
                passwd2.setText(StringUtils.EMPTY);
            }
        });
        btnClear.setBounds(194, 137, 80, 27);
        btnClear.setText("清除");
        */

        // 初始化
        if (this.update) {
            userName.setText(this.user.getUserName());
            passwd.setFocus();
        } else {
            userName.setFocus();
        }

        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    protected void createButtonsForButtonBar(Composite parent) {
        Button button = createButton(parent, IDialogConstants.SKIP_ID, "确定", true);
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String ptxt = passwd.getText();
                if (StringUtils.isBlank(ptxt) || !StringUtils.equals(ptxt, passwd2.getText())) {
                    SWTUtils.alert(getShell(), "密码为空或与确认密码不相等！");
                    return;
                }

                user.setUserPasswd(ptxt);
                if (update) {
                    umgt.update(user);
                    SWTUtils.alert(getShell(), "用户信息修改成功！");
                } else {
                    user.setUserName(userName.getText());

                    UserInfoDTO tmpUser = umgt.find(user.getUserName());
                    if (tmpUser != null) {
                        SWTUtils.alert(getShell(), "用户[" + tmpUser.getUserName() + "]已经存在！");
                        userName.setFocus();
                        return;
                    } else {
                        umgt.create(user);
                        SWTUtils.alert(getShell(), "用户信息增加成功！");
                    }
                }

                // 关闭
                close();
            }
        });

        Button button_1 = createButton(parent, IDialogConstants.SKIP_ID, "清除", false);
        button_1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                passwd.setText(StringUtils.EMPTY);
                passwd2.setText(StringUtils.EMPTY);
            }
        });
    }

    /** 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);

        shell.setText("增加/修改用户信息");
        shell.setImages(SWTUtils.findImgIcons());
    }

    /**
     * Return the initial size of the dialog.
     */
    protected Point getInitialSize() {
        return new Point(316, 227);
    }

}
