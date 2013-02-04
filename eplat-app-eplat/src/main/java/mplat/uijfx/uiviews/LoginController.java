/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mplat.uijfx.uiviews;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.UserInfoDTO;
import mplat.uijfx.utils.Alert;
import mplat.utils.UserHolder;

import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.UIBtnMsg;
import com.atom.core.uijfx.UIConfig;
import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.UITipMsg;
import com.atom.core.uijfx.event.EventAdapter;
import com.atom.core.uijfx.utils.StageUtils;

/**
 *
 * @author Administrator
 */
public class LoginController {
    private static final UISize SIZE = UISize.to(713, 235);

    private Stage               stage;

    @FXML
    private BorderPane          viewRoot;

    @FXML
    private ImageView           imgLogo;
    @FXML
    private TextField           txtUserName;
    @FXML
    private PasswordField       txtUserPasswd;
    @FXML
    private ComboBox<String>    cboxPorts;
    @FXML
    private HBox                hboxTipMsg;
    @FXML
    private Label               lblTipMsg;

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        this.txtUserName.setPromptText("用户名");
        this.txtUserPasswd.setPromptText("密码");

        UserInfoDTO user = MgtFactory.get().getUserMgt().find("admin");
        this.txtUserName.setText(user.getUserName());
        this.txtUserPasswd.setText(user.getUserPasswd());

        this.cboxPorts.getSelectionModel().select(0);

        this.lblTipMsg.setText("请登录系统~");
    }

    /**
     * 页面初始化
     */
    public boolean initViews(Stage stage) {
        this.stage = stage;

        this.stage.setTitle("用户登录");

        this.stage.setScene(new Scene(this.viewRoot, SIZE.getWidth(), SIZE.getHeight()));
        this.stage.sizeToScene();
        this.stage.centerOnScreen();
        this.stage.setResizable(false);
        this.stage.show();

        return true;
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

        UserInfoDTO user = MgtFactory.get().getUserMgt().login(userName, userPasswd);
        if (user == null) {
            this.lblTipMsg.setText("用户不存在或密码错误，请重新输入！");
            LogUtils.warn("[用户登录]-登录失败，UserName[" + userName + "], UserPasswd[" + userPasswd + "].");
            EventAdapter adapter = new EventAdapter() {
                public void onHidden(WindowEvent evt) {
                    lblTipMsg.setText("用户登录");
                }
            };

            Alert.alert(UIConfig.get().setSize(UISize.to(200D, 180D)).setTipMsg(UITipMsg.to("错误提示", "登录失败，用户不存在或密码错误！")).setBtnMsg(UIBtnMsg.get().setSure("确定")).setAdapter(adapter));
        } else {
            UserHolder.set(user);
            LogUtils.warn("[用户登录]-登录成功，UserInfo[" + user + "].");

            StageUtils.findController(MainViewController.class).initViews(this.stage);
        }
    }

}
