/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mplat.uijfx.user;

import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.UIView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author obullxl@gmail.com
 */
public class UserController implements Initializable, UIView {

    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static UISize findSize() {
        return UISize.toMaxSize();
    }

    public boolean initViews(Stage stage) {
        this.stage = stage;

        stage.setTitle("用户管理");
        stage.setResizable(false);

        stage.show();

        return true;
    }
}
