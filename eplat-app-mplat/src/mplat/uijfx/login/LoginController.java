/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mplat.uijfx.login;

import com.atom.core.lang.user.UserHolder;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.UIBtnMsg;
import com.atom.core.uijfx.UIConfig;
import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.UITipMsg;
import com.atom.core.uijfx.UIView;
import com.atom.core.uijfx.event.EventAdapter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.UserInfoDTO;
import mplat.uijfx.utils.Alert;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Administrator
 */
public class LoginController implements Initializable, UIView {

    private Stage stage;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Label lblLoginTop;
    @FXML
    private Label lblLoginTitle;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtUserPasswd;
    @FXML
    private ComboBox cboxPorts;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtUserName.setPromptText("用户名");
        this.txtUserPasswd.setPromptText("密码");
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

        UserInfoDTO user = MgtFactory.get().getUserMgt().tryLogin(userName, userPasswd);
        if (user == null) {
            this.lblLoginTitle.setText("用户不存在或密码错误，请重新输入！");
            LogUtils.warn("[用户登录]-登录失败，UserName[" + userName + "], UserPasswd[" + userPasswd + "].");
            EventAdapter adapter = new EventAdapter() {
                public void onHidden(WindowEvent evt) {
                    lblLoginTitle.setText("用户登录");
                }
            };

            Alert.alert(UIConfig.get().setSize(UISize.to(200D, 180D)).setTipMsg(UITipMsg.to("错误提示", "登录失败，用户不存在或密码错误！")).setBtnMsg(UIBtnMsg.get().setSure("确定")).setAdapter(adapter));
        } else {
            UserHolder.set(user.toUser());
            LogUtils.warn("[用户登录]-登录成功，UserInfo[" + user + "].");
        }
    }

    public static UISize findSize() {
        return UISize.to(290, 210);
    }

    public boolean initViews(Stage stage) {
        this.stage = stage;

        // this.lblLoginTop.setMinWidth(stage.getWidth());
        // this.lblLoginTop.setStyle("-fx-background-color:rgb(255, 255, 255, 0.4)");

        stage.setTitle("用户登录");
        stage.setResizable(false);

        stage.show();

        return true;
    }
}
