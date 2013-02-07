/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.controls;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class MplatJFXML extends Application implements JavaFX {

    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            this.stage = primaryStage;
            StageHolder.set(stage);
            
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(JavaFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void stop() throws Exception {
        DataMap.remove();
        StageHolder.remove();
        
        super.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void onLogin() {
        this.gotoUserMgt();
    }

    private void gotoLogin() {
        try {
            LoginController login = this.findController("Login.fxml");
            stage.setResizable(false);
            stage.setTitle("FXML Login Sample");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(JavaFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoUserMgt() {
        try {
            UserMgtController userMgt = this.findController("UserMgt.fxml");
            stage.setResizable(true);
            stage.setTitle("FXML 表格测试");
            stage.setMinWidth(400.0);
            stage.setMinHeight(300.0);

            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setWidth(primaryScreenBounds.getWidth() * 0.5);
            stage.setHeight(primaryScreenBounds.getHeight() * 0.5);
            stage.centerOnScreen();

            userMgt.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(JavaFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private <T extends Initializable> T findController(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(this.getClass().getResource(fxml));

        Parent page = (Parent) loader.load();
        stage.setScene(new Scene(page, 800, 600));
        stage.sizeToScene();

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        // set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        return loader.getController();
    }
}
