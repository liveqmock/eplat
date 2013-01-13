/**
 * obullxl@gmail.com
 */
package mplat;

import javafx.application.Application;
import javafx.stage.Stage;
import mplat.jfxview.login.LoginController;
import mplat.utils.DataMap;
import mplat.utils.StageHolder;
import mplat.utils.StageUtils;

/**
 * @author obullxl@gmail.com
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        StageHolder.set(stage);
        StageUtils.findController(LoginController.class).initComponents(stage);
    }

    @Override
    public void stop() throws Exception {
        DataMap.remove();
        StageHolder.remove();

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
