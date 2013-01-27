/**
 * obullxl@gmail.com
 */
package mplat.uijfx;

import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mplat.uijfx.images.IMGS;

/**
 * @author obullxl@gmail.com
 */
public class LoginView {

    private static final int WIDTH = 713;
    private static final int HEIGHT = 400;

    private final Stage primaryStage;
    private Group primaryRoot;
    private Scene primaryScene;
    private BorderPane borderPane;

    private LoginView(final Stage stage) {
        this.primaryStage = stage;
    }

    public static final LoginView create(final Stage stage) {
        return new LoginView(stage);
    }

    /**
     * 显示页面
     */
    public void show() {
        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.sizeToScene();
        this.primaryStage.show();
    }

    /**
     * 初始化组件
     */
    public LoginView initViews() {
        this.primaryRoot = new Group();
        this.primaryScene = new Scene(this.primaryRoot, WIDTH, HEIGHT);
        this.borderPane = new BorderPane();

        // TOP
        Image loginLogo = IMGS.findLoginLogo();
        this.borderPane.setTop(new ImageView(loginLogo));

        // Center
        GridPane gridpane = new GridPane();
        gridpane.setVgap(5);
        gridpane.setHgap(10);
        gridpane.setPadding(new Insets(5, 5, 5, 5));

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        column1.setHalignment(HPos.RIGHT);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(80);
        column2.setHalignment(HPos.LEFT);
        gridpane.getColumnConstraints().addAll(column1, column2);

        gridpane.add(new Label("用户名："), 1, 1);
        gridpane.add(new Label("密码："), 1, 2);
        gridpane.add(new Label("下位机："), 1, 3);

        double itmWidth = column2.getMaxWidth() * 0.8;

        TextField txtUserName = new TextField();
        txtUserName.setPromptText("用户名");
        txtUserName.setPrefWidth(itmWidth);

        PasswordField txtUserPasswd = new PasswordField();
        txtUserPasswd.setPromptText("密码");
        txtUserPasswd.setPrefWidth(itmWidth);

        List<String> ports = Arrays.asList("COM1", "COM2");
        ComboBox cboxPorts = new ComboBox();
        cboxPorts.getItems().addAll(ports);
        cboxPorts.setPrefWidth(itmWidth);

        HBox hboxBtns = new HBox();
        hboxBtns.setPadding(new Insets(0, 0, 0, 10));
        hboxBtns.setAlignment(Pos.CENTER_LEFT);
        Button btnLogin = new Button("登录");
        btnLogin.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
             public void handle(ActionEvent evt) {
                 System.out.println("登录按钮按下！~~");
             }
        });

        Button btnClear = new Button("清除");
        hboxBtns.getChildren().add(btnLogin);
        hboxBtns.getChildren().add(btnClear);

        this.borderPane.setCenter(gridpane);

        // 初始化结束
        this.primaryRoot.getChildren().add(this.borderPane);
        return this;
    }
}
