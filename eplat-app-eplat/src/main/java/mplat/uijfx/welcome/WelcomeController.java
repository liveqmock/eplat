/**
 * obullxl@gmail.com
 */
package mplat.uijfx.welcome;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.utils.Alert;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.UIBtnMsg;
import com.atom.core.uijfx.UIConfig;
import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.UITipMsg;
import com.atom.core.uijfx.UIView;
import com.atom.core.uijfx.event.EventAdapter;

/**
 * @author obullxl@gmail.com
 */
public class WelcomeController implements Initializable, UIView {

    public static final double OPACITY_ON  = 1.0;
    public static final double OPACITY_OUT = 0.5;

    private Stage              stage;
    @FXML
    private BorderPane         borderPane;
    @FXML
    private HBox               center;
    @FXML
    private MenuBar            menuBar;
    @FXML
    private TabPane            tabPane;
    @FXML
    private Tab                tabWelcome;
    @FXML
    private BorderPane         welcome;
    @FXML
    private ImageView          imgLogo;
    @FXML
    private ImageView          btnXtkj;          // 系统课件
    @FXML
    private ImageView          btnZxjnxl;        // 专项技能训练
    @FXML
    private ImageView          btnZyjjalxl;      // 专业急救案例训练
    @FXML
    private ImageView          btnZyjjalkh;      // 专业急救案例考核
    @FXML
    private ImageView          btnXtgn;          // 系统功能
    @FXML
    private ImageView          imgCopyRgt;
    @FXML
    private ImageView          btnExit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tab标签
        this.tabWelcome.setGraphic(new ImageView(this.findTabWelcome()));

        // Logo/Copyright
        this.imgLogo.setImage(this.findImgLogo());
        this.imgCopyRgt.setImage(this.findImgCopy());

        // 系统课件
        this.btnXtkj.setImage(this.findImgXtkj());
        this.chargeOpacity(btnXtkj, OPACITY_OUT);

        // 专项技能训练
        this.btnZxjnxl.setImage(this.findImgZxjnxl());
        this.chargeOpacity(btnZxjnxl, OPACITY_OUT);

        // 专业急救案例训练
        this.btnZyjjalxl.setImage(this.findImgZyjjalxl());
        this.chargeOpacity(btnZyjjalxl, OPACITY_OUT);

        // 专业急救案例考核
        this.btnZyjjalkh.setImage(this.findImgZyjjalkh());
        this.chargeOpacity(btnZyjjalkh, OPACITY_OUT);

        // 系统功能
        this.btnXtgn.setImage(this.findImgXtgn());
        this.chargeOpacity(btnXtgn, OPACITY_OUT);

        // 退出系统
        this.btnExit.setImage(this.findImgExit());
        this.chargeOpacity(btnExit, OPACITY_OUT);
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

    @FXML
    public void onMouseOn(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.HAND);
        this.chargeOpacity(node, OPACITY_ON);
    }

    @FXML
    public void onMouseOut(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.DEFAULT);
        this.chargeOpacity(node, OPACITY_OUT);
    }

    @FXML
    public void onMenuExit(ActionEvent evt) {
        this.onExitSystem();
    }

    @FXML
    public void onExitAction(MouseEvent evt) {
        this.onExitSystem();
    }

    /**
     * 退出系统
     */
    private void onExitSystem() {
        LogUtils.warn("退出系统~~~");

        EventAdapter adapter = new EventAdapter() {
            public void onSure(ActionEvent evt) {
                Platform.exit();
            }
        };

        Alert.alert(UIConfig.get().setSize(UISize.to(400, 300)).setTipMsg(UITipMsg.to("退出系统", "你确定要退出系统吗？")).setBtnMsg(UIBtnMsg.get().setSure("确定").setCancel("取消")).setAdapter(adapter));
    }

    /**
     * 设置透明度
     */
    private void chargeOpacity(Node node, double value) {
        node.setOpacity(value);
    }

    // 图片信息
    private Image findTabWelcome() {
        return new Image(IMGS.class.getResourceAsStream("tab-welcome.jpg"), 20, 20, false, false);
    }

    private Image findImgLogo() {
        return new Image(IMGS.class.getResourceAsStream("welcome-logo.jpg"), 808, 0, true, false);
    }

    private Image findImgXtkj() {
        return new Image(IMGS.class.getResourceAsStream("btn-xtkj.png"), 135, 135, false, false);
    }

    private Image findImgZxjnxl() {
        return new Image(IMGS.class.getResourceAsStream("btn-zxjnxl.jpg"), 135, 135, false, false);
    }

    private Image findImgZyjjalxl() {
        return new Image(IMGS.class.getResourceAsStream("btn-zyjjalxl.jpg"), 135, 135, false, false);
    }

    private Image findImgZyjjalkh() {
        return new Image(IMGS.class.getResourceAsStream("btn-zyjjalkh.jpg"), 135, 135, false, false);
    }

    private Image findImgXtgn() {
        return new Image(IMGS.class.getResourceAsStream("btn-xtgn.jpg"), 135, 135, false, false);
    }

    private Image findImgCopy() {
        return new Image(IMGS.class.getResourceAsStream("welcome-copy.jpg"), 480, 0, true, false);
    }

    private Image findImgExit() {
        return new Image(IMGS.class.getResourceAsStream("exit.jpg"), 70, 70, false, false);
    }
}
