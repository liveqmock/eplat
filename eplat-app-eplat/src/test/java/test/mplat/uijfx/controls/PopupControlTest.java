/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.controls;

import javafx.application.Application;
import javafx.stage.Stage;
import mplat.uijfx.controls.PopupControl;
import mplat.uijfx.images.IMGS;

/**
 * PopupControl测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupControlTest.java, V1.0.1 2013-2-3 下午10:15:51 $
 */
public class PopupControlTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        PopupControl popup = new PopupControl(stage);
        popup.setIcon(IMGS.findSuccessIcon(30.0));
        
        popup.show();
    }

}
