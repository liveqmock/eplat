/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mplat.jfxview.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mplat.jfxview.ViewComponent;

/**
 *
 * @author Administrator
 */
public class LoginController implements Initializable, ViewComponent {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean initComponents(Stage stage) {
        System.out.println("初始化LoginController.");
        stage.show();
        return true;
    }
}
