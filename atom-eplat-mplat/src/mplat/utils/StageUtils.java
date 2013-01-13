/**
 * obullxl@gmail.com
 */
package mplat.utils;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

/**
 * @author obullxl@gmail.com
 */
public class StageUtils {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static String findFXMLName(Class<?> controller) {
        return StringUtils.substringBefore(controller.getSimpleName(), "Controller");
    }

    public static <T extends Initializable> T findController(Class<T> clazz) throws Exception {
        return findController(clazz, findFXMLName(clazz) + ".fxml");
    }

    public static <T extends Initializable> T findController(Class<T> clazz, String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(clazz.getResource(fxml));

        Parent page = (Parent) loader.load();
        Stage stage = StageHolder.get();
        stage.setScene(new Scene(page, WIDTH, HEIGHT));
        stage.sizeToScene();

        return loader.getController();
    }

    public static Rectangle2D findScreenSize() {
        return Screen.getPrimary().getVisualBounds();
    }

}
