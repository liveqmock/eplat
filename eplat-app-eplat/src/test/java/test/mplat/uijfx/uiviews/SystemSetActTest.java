/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews;

import javafx.stage.Stage;
import mplat.uijfx.uiviews.SystemSetAct;

import com.atom.core.uijfx.test.BaseTest;

/**
 * SysmteSetAct单元测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemSetActTest.java, V1.0.1 2013-2-18 下午9:22:22 $
 */
public final class SystemSetActTest extends BaseTest {

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
        SystemSetAct act = new SystemSetAct(stage);
        act.show();
    }

}
