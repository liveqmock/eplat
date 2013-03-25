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

import com.atom.core.uijfx.popup.PopupBuilder;
import com.atom.core.uijfx.popup.PopupConst;
import com.atom.core.uijfx.popup.PopupEvent;
import com.atom.core.uijfx.utils.StageUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 更新用户信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserUpdateAct.java, V1.0.1 2013-2-3 下午2:50:11 $
 */
public final class UserUpdateAct extends BaseXmlAct {

    /** 用户信息 */
    private final UserInfoDTO user;
    private final UserMgt     userMgt;

    @FXML
    private AnchorPane        rootView;
    @FXML
    private TextField         txtUserName;
    @FXML
    private PasswordField     txtPasswd;
    @FXML
    private PasswordField     txtPasswdRepeat;
    @FXML
    private Button            btnSure;
    @FXML
    private Button            btnCancel;

    /**
     * 默认构造方法
     */
    public UserUpdateAct(Stage stage, UserInfoDTO user) {
        super(stage);

        this.user = user;
        this.userMgt = MgtFactory.get().findUserMgt();
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final UserUpdateAct initXmlViews() {
        // 属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(350.0, 225.0));
        this.findTitleProperty().set("更新用户信息");
        this.findGroupViewProperty().set(this.rootView);

        // 事件
        this.btnSure.setDisable(true);

        this.txtPasswd.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                final String repeat = txtPasswdRepeat.getText();
                if (StringUtils.isBlank(newValue) || !StringUtils.equals(newValue, repeat)) {
                    btnSure.setDisable(true);
                } else {
                    btnSure.setDisable(false);
                }
            }
        });

        this.txtPasswdRepeat.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                final String repeat = txtPasswd.getText();
                if (StringUtils.isBlank(newValue) || !StringUtils.equals(newValue, repeat)) {
                    btnSure.setDisable(true);
                } else {
                    btnSure.setDisable(false);
                }
            }
        });

        // 返回
        return this;
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseViewAct#initViewShown()
     */
    public final void initViewShown() {
        super.initViewShown();

        this.findTitleProperty().set("更新用户(" + this.user.getUserName() + ")信息");
        this.txtUserName.setText(this.user.getUserName());
    }

    @FXML
    private void onSureAction(ActionEvent evt) {
        this.user.setUserPasswd(this.txtPasswd.getText());
        this.userMgt.update(this.user);

        // 弹出提示框
        PopupBuilder builder = PopupBuilder.create(this.findNewStage());
        builder.modal(true).imageValue(PopupConst.IMG_SUCCESS).buttons(PopupConst.BTN_SURE_VALUE);
        builder.title("操作成功").despMsg("[更新]-用户(" + this.user.getUserName() + ")信息修改成功！");
        builder.callback(new PopupEvent() {
            public void callback(Stage stage, Stage newStage, int btnValue) {
                StageUtils.close(newStage);
                StageUtils.close(stage);
            }
        });
        
        builder.build().show();
    }

    @FXML
    private void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }

}
