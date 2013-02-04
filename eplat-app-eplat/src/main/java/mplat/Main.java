/**
 * obullxl@gmail.com
 */
package mplat;

import javafx.application.Application;
import javafx.stage.Stage;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.uiviews.LoginController;
import mplat.utils.DataMap;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.uijfx.utils.StageHolder;
import com.atom.core.uijfx.utils.StageUtils;
import com.atom.core.xstream.store.StoreFactory;

/**
 * @author obullxl@gmail.com
 */
public class Main extends Application {

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        StageHolder.set(stage);

        CfgUtils.findRootPath();
        CfgUtils.findConfigPath();
        StoreFactory.get().init();

        stage.getIcons().addAll(IMGS.findIconImages());

        StageUtils.findController(LoginController.class).initViews(stage);
        // LoginView.create(stage).initViews().show();
    }

    /** 
     * @see javafx.application.Application#stop()
     */
    public void stop() throws Exception {
        DataMap.remove();
        StageHolder.remove();

        StoreFactory.get().stop();

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

}
