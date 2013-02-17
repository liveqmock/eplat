/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableViewBuilder;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.stage.Stage;

/**
 * SplitPane测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: SplitPaneTest.java, V1.0.1 2013-2-16 下午1:33:51 $
 */
public class SplitPaneTest extends Application {

    /**
     * 应用入口
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(final Stage stage) throws Exception {
        Group root = new Group();
        stage.setScene(new Scene(root, 600, 500));

        String css = SplitPaneTest.class.getResource("SplitPaneTest.css").toExternalForm();
        final SplitPane splitPane = new SplitPane();
        splitPane.setId("hiddenSplitter");
        splitPane.getStylesheets().add(css);
        
        stage.getScene().widthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                splitPane.setPrefWidth(stage.getScene().getWidth());
            }
        });
        stage.getScene().heightProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                splitPane.setPrefHeight(stage.getScene().getHeight());
            }
        });
        
        splitPane.setPrefWidth(stage.getScene().getWidth());
        splitPane.setPrefHeight(stage.getScene().getHeight());

        TableView<?> rgnLeft = TableViewBuilder.create().build();

        Region rgnCenter = RegionBuilder.create().build();
        rgnCenter.setMinWidth(100);
        rgnCenter.setMaxWidth(100);

        TableView<?> rgnRight = TableViewBuilder.create().build();

        splitPane.getItems().addAll(rgnLeft, rgnCenter, rgnRight);
        splitPane.setDividerPositions(0.33, 0.66);

        // splitPane.getStylesheets().add(hidingSplitPaneCss);

        root.getChildren().add(splitPane);

        stage.show();
    }

}
