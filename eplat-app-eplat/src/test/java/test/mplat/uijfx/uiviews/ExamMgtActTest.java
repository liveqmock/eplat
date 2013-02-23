/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews;

import mplat.uijfx.uiviews.acts.ExamMgtAct;
import javafx.stage.Stage;

import com.atom.core.uijfx.test.BaseTest;

/**
 * 试题管理测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: ExamMgtActTest.java, V1.0.1 2013-2-15 下午6:32:03 $
 */
public final class ExamMgtActTest extends BaseTest {

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
        ExamMgtAct act = new ExamMgtAct(stage);
        act.show();
    }

}
