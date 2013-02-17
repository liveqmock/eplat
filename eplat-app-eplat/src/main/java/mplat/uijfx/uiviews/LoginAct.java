/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.UserInfoDTO;
import mplat.utils.UserHolder;

import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 用户登录
 * 
 * @author obullxl@gmail.com
 * @version $Id: LoginAct.java, V1.0.1 2013-2-3 下午2:50:11 $
 */
public class LoginAct extends BaseXmlAct {

    @FXML
    private BorderPane       viewRoot;

    @FXML
    private ImageView        imgLogo;
    @FXML
    private TextField        txtUserName;
    @FXML
    private PasswordField    txtUserPasswd;
    @FXML
    private Button           btnLogin;
    @FXML
    private ComboBox<String> cboxPorts;
    @FXML
    private HBox             hboxTipMsg;
    @FXML
    private Label            lblTipMsg;

    /**
     * 默认构造器
     */
    public LoginAct(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final LoginAct initXmlViews() {
        // 属性
        this.findGroupViewProperty().set(this.viewRoot);
        this.findSizeProperty().set(new Dimension2D(713, 235));
        this.findTitleProperty().set("用户登录");

        this.txtUserName.setPromptText("用户名");
        this.txtUserPasswd.setPromptText("密码");

        // TODO: 正式上线删除
        UserInfoDTO user = MgtFactory.get().findUserMgt().find("admin");
        this.txtUserName.setText(user.getUserName());
        this.txtUserPasswd.setText(user.getUserPasswd());

        this.txtUserName.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                if (StringUtils.isBlank(newValue)) {
                    btnLogin.setDisable(true);
                } else {
                    btnLogin.setDisable(false);
                }
            }
        });

        this.txtUserPasswd.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                if (StringUtils.isBlank(newValue)) {
                    btnLogin.setDisable(true);
                } else {
                    btnLogin.setDisable(false);
                }
            }
        });

        if (StringUtils.isBlank(this.txtUserName.getText()) || StringUtils.isBlank(this.txtUserPasswd.getText())) {
            this.btnLogin.setDisable(true);
        }

        this.cboxPorts.getSelectionModel().select(0);

        this.lblTipMsg.setText("请登录系统~");

        return this;
    }

    @FXML
    private void onClearAction(ActionEvent event) {
        String empty = StringUtils.EMPTY;
        this.txtUserName.setText(empty);
        this.txtUserPasswd.setText(empty);
    }

    @FXML
    private void onLoginAction(ActionEvent event) {
        String userName = this.txtUserName.getText();
        String userPasswd = this.txtUserPasswd.getText();

        int portNameIdx = this.cboxPorts.getSelectionModel().getSelectedIndex();
        String portName = this.cboxPorts.getSelectionModel().getSelectedItem();
        System.out.println("下位机-" + portNameIdx + ": " + portName);

        UserInfoDTO user = MgtFactory.get().findUserMgt().login(userName, userPasswd);
        if (user == null) {
            this.lblTipMsg.setText("用户不存在或密码错误，请重新输入！");
            LogUtils.warn("[用户登录]-登录失败，UserName[" + userName + "], UserPasswd[" + userPasswd + "].");

            PopupUtils.alert(this.findStage(), "登录失败", "登录失败，用户不存在或密码错误！");
        } else {
            UserHolder.set(user);
            LogUtils.warn("[用户登录]-登录成功，UserInfo[" + user + "].");

            // 主窗口
            new MainViewAct(this.findStage()).show();
        }
    }

}
