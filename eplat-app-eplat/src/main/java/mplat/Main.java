/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mplat.mgt.msgs.DataMSG;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.uiviews.acts.LoginAct;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.TimerUtils;
import com.atom.core.uijfx.utils.IconsHolder;
import com.atom.core.uijfx.utils.StageHolder;
import com.atom.core.xstream.store.StoreFactory;

/**
 * 应用启动入口
 * 
 * @author obullxl@gmail.com
 * @version $Id: Main.java, V1.0.1 2013-2-3 下午2:50:11 $
 */
public final class Main extends Application {

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        StageHolder.set(stage);

        CfgUtils.findRootPath();
        CfgUtils.findConfigPath();
        StoreFactory.get().init();

        IconsHolder.setIconImages(findIconImages());
        stage.getIcons().addAll(IconsHolder.getIconImages());

        // 显示登录窗口
        LoginAct act = new LoginAct(stage);
        act.findSizeToSceneProperty().set(true);
        act.show();
    }

    /** 
     * @see javafx.application.Application#stop()
     */
    public void stop() throws Exception {
        StageHolder.remove();

        StoreFactory.get().stop();
        
        TimerUtils.stopTimer();
        
        DataMSG.get().closePort();

        super.stop();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the application can not be launched through deployment artifacts, e.g., in IDEs
     * with limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * IconImage信息
     */
    private static List<Image> findIconImages() {
        List<Image> imgs = new ArrayList<Image>();
        
        imgs.add(new Image(IMGS.class.getResourceAsStream("tab-welcome.jpg"), 16, 16, false, false));

        return imgs;
    }

}
