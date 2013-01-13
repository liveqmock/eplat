/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mplat.uijfx.login;

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
import mplat.uijfx.UIComponent;
import mplat.utils.UISize;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Administrator
 */
public class LoginController implements Initializable, UIComponent {

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
        System.out.println("You clicked me!");
    }

    public static UISize findSize() {
        return UISize.toSize(290, 210);
    }

    public boolean initComponents(Stage stage) {
        stage.setTitle("用户登录");
        stage.setResizable(false);

        stage.show();

        return true;
    }
}
