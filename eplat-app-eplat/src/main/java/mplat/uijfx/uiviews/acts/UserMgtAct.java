/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.mgt.MgtFactory;
import mplat.mgt.UserMgt;
import mplat.mgt.dto.UserInfoDTO;
import mplat.uijfx.uiviews.beans.UserWO;

import com.atom.core.uijfx.popup.PopupConst;
import com.atom.core.uijfx.popup.PopupEvent;
import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 用户管理控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserMgtAct.java, V1.0.1 2013-2-15 上午11:21:09 $
 */
public final class UserMgtAct extends BaseXmlAct {

    @FXML
    private BorderPane                     viewRoot;
    @FXML
    private Button                         btnCreateUser;
    @FXML
    private Button                         btnUpdateUser;
    @FXML
    private Button                         btnRemoveUser;
    @FXML
    private Button                         btnUnselectUser;
    @FXML
    private TableView<UserWO>              tableView;
    @FXML
    private TableColumn<UserWO, Long>      tcolUserID;
    @FXML
    private TableColumn<UserWO, ImageView> tcolUserAdmin;
    @FXML
    private TableColumn<UserWO, String>    tcolUserName;

    private UserMgt                        userMgt;

    /**
     * 默认构造器
     */
    public UserMgtAct(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final BaseXmlAct initXmlViews() {
        // 用户管理
        this.userMgt = MgtFactory.get().findUserMgt();

        // 舞台属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(600.0, 400.0));
        this.findTitleProperty().set("系统用户管理");
        this.findGroupViewProperty().set(this.viewRoot);
        this.findResizableProperty().set(true);

        // 组件属性
        this.tableView.setPrefWidth(this.findSizeProperty().get().getWidth());
        this.tcolUserID.setPrefWidth(30);
        this.tcolUserAdmin.setPrefWidth(30);
        this.tcolUserAdmin.setSortable(false);
        this.tcolUserName.setPrefWidth(this.tableView.getPrefWidth() - 80);

        // 事件
        this.tableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<UserWO>() {
            public void onChanged(ListChangeListener.Change<? extends UserWO> change) {
                List<UserWO> users = tableView.getSelectionModel().getSelectedItems();
                if (users.isEmpty()) {
                    // 没有选择
                    btnRemoveUser.setDisable(true);
                    btnUpdateUser.setDisable(true);
                    btnUnselectUser.setDisable(true);
                } else {
                    // 选择用户
                    btnRemoveUser.setDisable(false);
                    btnUpdateUser.setDisable(false);
                    btnUnselectUser.setDisable(false);
                }
            }
        });

        // 用户列表
        this.refreshUsers();

        return this;
    }

    /**
     * 更新表格
     */
    public final void refreshUsers() {
        this.tableView.getSelectionModel().clearSelection();
        this.tableView.getItems().clear();

        List<UserWO> users = UserWO.from(this.userMgt.findAll());
        Collections.sort(users);
        this.tableView.getItems().addAll(FXCollections.observableList(users).toArray(new UserWO[] {}));
    }

    @FXML
    private final void onCreateUser(ActionEvent evt) {
        new UserCreateAct(this.findNewStage(), this).show();
    }

    @FXML
    private final void onUpdateUser(ActionEvent evt) {
        UserWO srcObj = this.tableView.getSelectionModel().getSelectedItem();
        if (srcObj == null) {
            PopupUtils.alert(this.findNewStage(), "[更新]-请选择一个用户！");
            return;
        }

        UserInfoDTO dstObj = UserWO.to(srcObj);
        new UserUpdateAct(this.findNewStage(), dstObj).show();
    }

    @FXML
    private final void onRemoveUser(ActionEvent evt) {
        final UserWO srcObj = this.tableView.getSelectionModel().getSelectedItem();
        if (srcObj == null) {
            PopupUtils.alert(this.findNewStage(), "[删除]-请选择一个用户！");
            return;
        }

        if (UserMgt.isSystemAdmin(srcObj.getUserName())) {
            PopupUtils.alert(this.findNewStage(), "[删除]-系统管理员不能被删除！");
            return;
        }

        PopupUtils.confirm(this.findNewStage(), "删除用户", "[删除]-确定删除用户(" + srcObj.getUserName() + ")信息吗？", new PopupEvent() {
            public void callback(Stage newStage, int btnValue) {
                if (btnValue == PopupConst.BTN_SURE_VALUE) {
                    userMgt.remove(UserWO.to(srcObj));
                    tableView.getItems().remove(srcObj);
                }

                // 关闭弹出对话框
                closeStage(newStage);
            }
        });
    }

    @FXML
    private final void onUnselectUser(ActionEvent evt) {
        this.tableView.getSelectionModel().clearSelection();
    }
}
