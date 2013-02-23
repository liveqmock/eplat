/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mplat.mgt.MgtFactory;
import mplat.mgt.UserMgt;
import mplat.mgt.dto.UserInfoDTO;

import org.apache.commons.lang.StringUtils;

import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 增加用户控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserCreateAct.java, V1.0.1 2013-2-15 下午1:42:00 $
 */
public final class UserCreateAct extends BaseXmlAct {

    /** 用户管理器 */
    private final UserMgt userMgt;

    @FXML
    private AnchorPane    rootView;
    @FXML
    private TextField     txtUserName;
    @FXML
    private PasswordField txtPasswd;
    @FXML
    private PasswordField txtPasswdRepeat;
    @FXML
    private Button        btnSure;
    @FXML
    private Button        btnCancel;

    /**
     * 默认构造函数
     */
    public UserCreateAct(Stage stage, Object rootAct) {
        super(stage);

        this.setRootAct(rootAct);
        this.userMgt = MgtFactory.get().findUserMgt();
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public BaseXmlAct initXmlViews() {
        this.setNewStage(new Stage());

        // 属性
        this.findGroupViewProperty().set(this.rootView);
        this.findSizeProperty().set(new Dimension2D(350.0, 225.0));
        this.findTitleProperty().set("增加新用户");

        // 事件
        this.btnSure.setDisable(true);

        this.txtUserName.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                btnSure.setDisable(!isInputValid());
            }
        });

        this.txtPasswd.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                btnSure.setDisable(!isInputValid());
            }
        });

        this.txtPasswdRepeat.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                btnSure.setDisable(!isInputValid());
            }
        });

        // 清空
        this.clearInput();

        // 返回
        return this;
    }

    /**
     * 清空输入框
     */
    private final void clearInput() {
        String text = StringUtils.EMPTY;
        this.txtUserName.setText(text);
        this.txtPasswd.setText(text);
        this.txtPasswdRepeat.setText(text);
    }

    /**
     * 检查输入信息是否合法
     */
    private final boolean isInputValid() {
        String name = this.txtUserName.getText();
        String passwd = this.txtPasswd.getText();
        String passwd2 = this.txtPasswdRepeat.getText();

        if (StringUtils.isBlank(name) || StringUtils.isBlank(passwd) || StringUtils.isBlank(passwd2)) {
            return false;
        }

        if (!StringUtils.equals(passwd, passwd2)) {
            return false;
        }

        return true;
    }

    @FXML
    private void onSureAction(ActionEvent evt) {
        String name = this.txtUserName.getText();
        UserInfoDTO user = this.userMgt.find(name);
        if (user != null) {
            PopupUtils.alert(this.findNewStage(), "[增加]-用户名(" + name + ")已经存在，请选择其它用户名！");
            return;
        }

        // 保存用户
        UserInfoDTO srcObj = new UserInfoDTO();
        srcObj.setUserName(name);
        srcObj.setUserPasswd(this.txtPasswd.getText());
        this.userMgt.create(srcObj);

        // 更新表格
        UserMgtAct act = this.findRootAct();
        act.refreshUsers();

        // 清空
        this.clearInput();

        // 提示
        PopupUtils.success(this.findNewStage(), "增加新用户成功！");
    }

    @FXML
    private void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }

}
