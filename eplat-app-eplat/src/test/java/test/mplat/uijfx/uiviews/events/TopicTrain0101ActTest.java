/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews.events;

import java.net.URL;

import javafx.stage.Stage;
import mplat.uijfx.uiviews.events.TopicTrainViewEvent;
import mplat.uijfx.uiviews.events.TopicTrainViewEvent.TopicTrain0101Act;

import com.atom.core.uijfx.test.BaseTest;

/**
 * TopicTrain01Act测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain0101ActTest.java, V1.0.1 2013-2-16 下午12:12:52 $
 */
public class TopicTrain0101ActTest extends BaseTest {

    /**
     * 应用入口
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see com.atom.core.uijfx.test.BaseTest#onTest(javafx.stage.Stage)
     */
    public void onTest(Stage stage) {
        URL fxml = TopicTrainViewEvent.class.getResource("TopicTrain0101Act.fxml");
        new TopicTrain0101Act(stage, fxml).show();
    }

}
