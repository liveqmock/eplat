/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.controls;

import javafx.application.Application;
import javafx.stage.Stage;
import mplat.uijfx.controls.Alert;
import mplat.uijfx.images.IMGS;

/**
 * Alert测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: AlertTest.java, V1.0.1 2013-2-3 下午10:37:19 $
 */
public class AlertTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        Alert alert = new Alert(stage);
        alert.setIcon(IMGS.findSuccessIcon(0.0));
        alert.setMsg(null);
        alert.setContent("@version $Id: AlertTest.java, V1.0.1 2013-2-3 下午10:37:19 $");
        
        alert.show();
    }

}
