/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews;

import javafx.stage.Stage;
import mplat.uijfx.uiviews.EcgtMgtAct;

import com.atom.core.uijfx.test.BaseTest;

/**
 * 心律识别测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtMgtActTest.java, V1.0.1 2013-2-22 下午9:20:47 $
 */
public class EcgtMgtActTest extends BaseTest {

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
        EcgtMgtAct act = new EcgtMgtAct(stage);
        act.show();
    }

}
