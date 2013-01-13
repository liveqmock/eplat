/**
 * obullxl@gmail.com
 */
package mplat;

import javafx.application.Application;
import javafx.stage.Stage;
import mplat.store.StoreFactory;
import mplat.uijfx.login.LoginController;
import mplat.utils.CfgUtils;
import mplat.utils.DataMap;
import mplat.utils.StageHolder;
import mplat.utils.StageUtils;
import mplat.utils.UISize;

/**
 * @author obullxl@gmail.com
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        StageHolder.set(stage);

        CfgUtils.findRootPath();
        CfgUtils.findConfigPath();
        StoreFactory.get().initStores();

        StageUtils.findController(LoginController.class, UISize.toMaxSize()).initComponents(stage);
    }

    @Override
    public void stop() throws Exception {
        DataMap.remove();
        StageHolder.remove();

        StoreFactory.get().saveStores();

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
