/**
 * obullxl@gmail.com
 */
package mplat.uijfx.welcome;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.UIView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author obullxl@gmail.com
 */
public class WelcomeController implements Initializable, UIView {

    private Stage stage;

    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private BorderPane welcome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // todo
    }

    public static UISize findSize() {
        return UISize.toPrefSize();
    }

    @Override
    public boolean initViews(Stage stage) {
        this.stage = stage;

        // this.welcome.setStyle("-fx-background-color:#232323");
        this.welcome.setStyle("-fx-background-image: url(\"welcome-background.jpg\")");

        this.stage.setTitle("GD/ACLS 8000 高级生命支持急救技能训练软件2013版 - [欢迎使用]");
        this.stage.setResizable(false);
        this.stage.centerOnScreen();
        this.stage.show();

        return true;
    }

    /**
     * 退出系统
     */
    private void onExitSystem() {
        LogUtils.warn("退出系统~~~");
    }

    @FXML
    public void onMenuExit(ActionEvent evt) {
        this.onExitSystem();
    }
}
