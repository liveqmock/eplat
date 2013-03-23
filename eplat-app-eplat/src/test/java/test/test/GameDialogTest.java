/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: GameDialogTest.java, V1.0.1 2013-3-23 下午3:51:06 $
 */
public class GameDialogTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new GameDialog());
        primaryStage.setScene(scene);
        primaryStage.setTitle("游戏对话显示");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
