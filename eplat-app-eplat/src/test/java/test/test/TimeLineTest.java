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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: TimeLineTest.java, V1.0.1 2013-3-25 下午10:30:13 $
 */
public class TimeLineTest extends Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        IntegerProperty prop = new SimpleIntegerProperty(0);
        prop.addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(oldValue + "  -->  " + newValue);
            }
        });
        
        KeyFrame begin = new KeyFrame(Duration.ZERO, new KeyValue(prop, 0));
        KeyFrame finish = new KeyFrame(Duration.seconds(3), new KeyValue(prop, 3, Interpolator.EASE_BOTH));
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(begin, finish);
        
        timeline.setAutoReverse(true);
        timeline.setCycleCount(3);
        
        timeline.play();
    }

}
