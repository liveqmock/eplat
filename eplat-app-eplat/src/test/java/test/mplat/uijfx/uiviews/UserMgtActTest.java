/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews;

import javafx.stage.Stage;
import mplat.uijfx.uiviews.acts.UserMgtAct;

import com.atom.core.uijfx.test.BaseTest;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserMgtActTest.java, V1.0.1 2013-2-15 上午11:33:40 $
 */
public class UserMgtActTest extends BaseTest {
    
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
        UserMgtAct act = new UserMgtAct(stage);
        act.show();
    }

}
