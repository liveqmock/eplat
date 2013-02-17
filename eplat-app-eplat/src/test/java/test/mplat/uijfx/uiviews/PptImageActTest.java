/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews;

import javafx.application.Application;
import javafx.stage.Stage;
import mplat.uijfx.uiviews.PptImageAct;

/**
 * PptImageAct测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: PptImageActTest.java, V1.0.1 2013-2-13 下午4:29:30 $
 */
public class PptImageActTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        PptImageAct ppt = new PptImageAct(stage, "CW01", true, 0);
        ppt.show();
    }

}
