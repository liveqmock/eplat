/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews.events;

import javafx.stage.Stage;
import mplat.mgt.PumpMgt;
import mplat.uijfx.uiviews.events.TopicTrainViewEvent.TopicTrain6800Act;

import com.atom.core.uijfx.test.BaseTest;

/**
 * TopicTrain68Act测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain0601ActTest.java, V1.0.1 2013-2-23 上午11:35:54 $
 */
public class TopicTrain6800ActTest extends BaseTest {

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
        new TopicTrain6800Act(PumpMgt.EJECTOR, stage).show();
        new TopicTrain6800Act(PumpMgt.TRANSFER, stage).show();
    }

}
