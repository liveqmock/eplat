/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Administrator
 */
public class LoginController implements Initializable {

    @FXML
    private Label label;
    private JavaFX application;

    public void setApp(JavaFX application) {
        this.application = application;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        this.application.onLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
