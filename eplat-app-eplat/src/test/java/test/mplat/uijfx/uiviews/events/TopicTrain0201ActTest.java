/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews.events;

import java.net.URL;

import javafx.stage.Stage;
import mplat.uijfx.uiviews.events.TopicTrainViewEvent;
import mplat.uijfx.uiviews.events.TopicTrainViewEvent.TopicTrain0201Act;

import com.atom.core.uijfx.test.BaseTest;

/**
 * TopicTrain0101Act测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain0201ActTest.java, V1.0.1 2013-2-21 下午9:46:17 $
 */
public class TopicTrain0201ActTest extends BaseTest {

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
        URL fxml = TopicTrainViewEvent.class.getResource("TopicTrain0201Act.fxml");
        new TopicTrain0201Act(stage, fxml).show();
    }

}
