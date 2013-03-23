/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: InterpolatorTest.java, V1.0.1 2013-3-23 下午3:41:41 $
 */
public class InterpolatorTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Rectangle rect = new Rectangle(0, 100, 100, 100);
        rect.setFill(Color.RED);
        root.getChildren().add(rect);
        rect.xProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (oldValue != null) {
                    System.out.println(newValue.doubleValue() - oldValue.doubleValue());
                }
            }
        });
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        
        KeyFrame kf = new KeyFrame(Duration.seconds(1), new KeyValue(rect.xProperty(), 100.0, Interpolator.EASE_BOTH));
        timeline.getKeyFrames().add(kf);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        timeline.play();
    }

    public static void main(String args[]) {
        launch();
    }
}
