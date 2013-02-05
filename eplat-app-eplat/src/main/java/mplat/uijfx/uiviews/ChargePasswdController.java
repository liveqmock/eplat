/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mplat.mgt.MgtFactory;
import mplat.mgt.UserMgt;
import mplat.mgt.dto.UserInfoDTO;

import org.apache.commons.lang.StringUtils;

import com.atom.core.uijfx.IconImageHolder;
import com.atom.core.uijfx.UISize;

/**
 * 修改密码
 * 
 * @author obullxl@gmail.com
 * @version $Id: ChargePasswdController.java, V1.0.1 2013-2-3 下午2:50:11 $
 */
public class ChargePasswdController {
    private static final UISize SIZE = UISize.to(350, 225);

    /** 主场景 */
    private Stage               primaryStage;

    /** 用户信息 */
    private UserInfoDTO         user;
    private UserMgt             userMgt;

    @FXML
    private AnchorPane          viewRoot;
    @FXML
    private TextField           txtUserName;
    @FXML
    private PasswordField       txtPasswd;
    @FXML
    private PasswordField       txtPasswdRepeat;
    @FXML
    private Button              btnCharge;
    @FXML
    private Button              btnCancel;

    /** 弹出框场景 */
    private Stage               newStage;

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        this.txtPasswd.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                final String repeat = txtPasswdRepeat.getText();
                if (StringUtils.isBlank(newValue) || !StringUtils.equals(newValue, repeat)) {
                    btnCharge.setDisable(true);
                } else {
                    btnCharge.setDisable(false);
                }
            }
        });

        this.txtPasswdRepeat.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                final String repeat = txtPasswd.getText();
                if (StringUtils.isBlank(newValue) || !StringUtils.equals(newValue, repeat)) {
                    btnCharge.setDisable(true);
                } else {
                    btnCharge.setDisable(false);
                }
            }
        });
    }

    /**
     * 页面初始化
     */
    public boolean initViews(Stage stage, UserInfoDTO user) {
        this.primaryStage = stage;
        this.user = user;
        this.userMgt = MgtFactory.get().getUserMgt();

        this.txtUserName.setText(this.user.getUserName());
        this.txtPasswd.setText(this.user.getUserPasswd());
        this.txtPasswdRepeat.setText(this.txtPasswd.getText());

        this.newStage = new Stage();
        this.newStage.initOwner(this.primaryStage);
        this.newStage.initModality(Modality.APPLICATION_MODAL);

        this.newStage.getIcons().addAll(IconImageHolder.getIconImages());
        this.newStage.setTitle("修改登录密码 [" + user.getUserName() + "]");
        this.newStage.setScene(new Scene(this.viewRoot, SIZE.getWidth(), SIZE.getHeight()));
        // this.stage.sizeToScene();
        this.newStage.setResizable(false);
        this.newStage.show();

        return true;
    }

    @FXML
    private void onChargeAction(ActionEvent evt) {
        this.user.setUserPasswd(this.txtPasswd.getText());
        this.userMgt.update(this.user);

        this.closeChargeStage();
    }

    @FXML
    private void onCancelAction(ActionEvent evt) {
        this.closeChargeStage();
    }

    private void closeChargeStage() {
        Platform.runLater(new Runnable() {
            public void run() {
                if (newStage != null) {
                    newStage.close();
                }
            }
        });
    }

}
