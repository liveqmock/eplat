/**
 * obullxl@gmail.com
 */
package mplat.uijfx.welcome;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import com.atom.core.uijfx.event.EventAdapter;

/**
 * @author obullxl@gmail.com
 */
public class WelcomeController {
    private static final UISize SIZE = UISize.to(1000, 750);
    
    public static final double OPACITY_ON  = 1.0;
    public static final double OPACITY_OUT = 0.5;

    private Stage              stage;
    
    @FXML
    private BorderPane         viewRoot;
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

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        // Tab标签
        this.tabWelcome.setGraphic(new ImageView(this.findTabWelcome()));

        // Logo/Copyright
        this.imgLogo.setImage(this.findImgLogo());
        this.imgCopyRgt.setImage(this.findImgCopy());

        // 系统课件
        this.btnXtkj.setImage(this.findImgXtkj());

        // 专项技能训练
        this.btnZxjnxl.setImage(this.findImgZxjnxl());

        // 专业急救案例训练
        this.btnZyjjalxl.setImage(this.findImgZyjjalxl());

        // 专业急救案例考核
        this.btnZyjjalkh.setImage(this.findImgZyjjalkh());

        // 系统功能
        this.btnXtgn.setImage(this.findImgXtgn());

        // 退出系统
        this.btnExit.setImage(this.findImgExit());
    }

    /**
     * 页面初始化
     */
    public boolean initViews(Stage stage) {
        this.stage = stage;

        // this.welcome.setStyle("-fx-background-color:#232323");
        this.welcome.setStyle("-fx-background-image: url(\"welcome-background.jpg\")");

        this.stage.setTitle("GD/ACLS 8000 高级生命支持急救技能训练软件2013版 - [欢迎使用]");
        
        this.stage.setScene(new Scene(this.viewRoot, SIZE.getWidth(), SIZE.getHeight()));
        // this.stage.sizeToScene();
        this.stage.centerOnScreen();
        this.stage.setResizable(false);
        this.stage.show();

        return true;
    }

    @FXML
    private void onMouseOn(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.HAND);
        this.chargeOpacity(node, OPACITY_ON);
    }

    @FXML
    private void onMouseOut(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.DEFAULT);
        this.chargeOpacity(node, OPACITY_OUT);
    }
    
    @FXML
    private void onMouseClick(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        
        if(node == this.btnXtkj) {
            // 系统课件
            LogUtils.info("系统课件");
        } else if(node == this.btnZxjnxl) {
            // 专项技能训练
            LogUtils.info("专项技能训练");
        } else if(node == this.btnZyjjalxl) {
            // 专项急救案例训练
            LogUtils.info("专项急救案例训练");
        } else if(node == this.btnZyjjalkh) {
            // 专项急救案例考核
            LogUtils.info("专项急救案例考核");
        } else if(node == this.btnXtgn) {
            // 系统功能
            LogUtils.info("系统功能");
        } else {
            // 未知
            LogUtils.error("未知的导航操作", new RuntimeException("未知的导航操作"));
        }
    }

    @FXML
    private void onMenuExit(ActionEvent evt) {
        this.onExitSystem();
    }

    @FXML
    private void onExitAction(MouseEvent evt) {
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
