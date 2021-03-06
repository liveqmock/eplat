/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.controls;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.uijfx.controls.TopFrameControl;
import mplat.uijfx.uiviews.acts.MainViewAct;

import com.atom.core.uijfx.utils.StageUtils;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopFrameControlTest.java, V1.0.1 2013-1-29 下午6:36:28 $
 */
public class TopFrameControlTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        TopFrameControl topFrame = new TopFrameControl(StageUtils.findController(MainViewAct.class));

        BorderPane border = new BorderPane();
        border.setTop(topFrame);

        Group root = new Group();
        root.getChildren().add(border);

        stage.setScene(new Scene(root));
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.show();
    }

}
