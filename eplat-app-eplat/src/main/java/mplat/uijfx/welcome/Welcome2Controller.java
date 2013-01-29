/**
 * obullxl@gmail.com
 */
package mplat.uijfx.welcome;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.utils.Alert;

import org.apache.commons.lang.StringUtils;

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
public class Welcome2Controller implements Initializable, UIView {

    private Stage stage;
    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabWelcome;
    @FXML
    private BorderPane welcome;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnXtkj; // 系统课件
    @FXML
    private Button btnZxjnxl; // 专项技能训练
    @FXML
    private Button btnZyjjalxl; // 专业急救案例训练
    @FXML
    private Button btnZyjjalkh; // 专业急救案例考核
    
    @FXML
    private Button btnXtgn; // 系统功能
    @FXML
    private ImageView imgXtgn;
    
    @FXML
    private ImageView imgCopyRgt;
    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tab标签
        this.tabWelcome.setGraphic(new ImageView(this.findTabWelcome()));

        // Logo/Copyright
        this.imgLogo.setImage(this.findImgLogo());
        this.imgCopyRgt.setImage(this.findImgCopy());

        // 系统课件
        this.btnXtkj.setTooltip(new Tooltip("系统课件"));
        this.btnXtkj.setText(null);
        this.btnXtkj.setGraphicTextGap(0);
        this.btnXtkj.setGraphic(new ImageView(this.findImgXtkj()));
        this.onBtnOpacityOut(this.btnXtkj);

        this.btnXtkj.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityIn(btnXtkj);
            }
        });

        this.btnXtkj.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityOut(btnXtkj);
            }
        });

        // 专项技能训练
        this.btnZxjnxl.setTooltip(new Tooltip("专项技能训练"));
        this.btnZxjnxl.setText(null);
        this.btnZxjnxl.setGraphicTextGap(0);
        this.btnZxjnxl.setGraphic(new ImageView(this.findImgZxjnxl()));
        this.onBtnOpacityOut(this.btnZxjnxl);

        this.btnZxjnxl.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityIn(btnZxjnxl);
            }
        });

        this.btnZxjnxl.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityOut(btnZxjnxl);
            }
        });

        // 专业急救案例训练
        this.btnZyjjalxl.setTooltip(new Tooltip("专业急救案例训练"));
        this.btnZyjjalxl.setText(StringUtils.EMPTY);
        this.btnZyjjalxl.setGraphicTextGap(0);
        this.btnZyjjalxl.setGraphic(new ImageView(this.findImgZyjjalxl()));
        this.onBtnOpacityOut(this.btnZyjjalxl);

        this.btnZyjjalxl.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityIn(btnZyjjalxl);
            }
        });

        this.btnZyjjalxl.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityOut(btnZyjjalxl);
            }
        });

        // 专业急救案例考核
        this.btnZyjjalkh.setTooltip(new Tooltip("专业急救案例考核"));
        this.btnZyjjalkh.setText(StringUtils.EMPTY);
        this.btnZyjjalkh.setGraphicTextGap(0);
        this.btnZyjjalkh.setGraphic(new ImageView(this.findImgZyjjalkh()));
        this.onBtnOpacityOut(this.btnZyjjalkh);

        this.btnZyjjalkh.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityIn(btnZyjjalkh);
            }
        });

        this.btnZyjjalkh.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityOut(btnZyjjalkh);
            }
        });

        // 系统功能
        this.btnXtgn.setTooltip(new Tooltip("系统功能"));
        this.btnXtgn.setText(StringUtils.EMPTY);
        this.btnXtgn.setGraphicTextGap(0);
        this.btnXtgn.setGraphic(new ImageView(this.findImgXtgn()));
        this.onBtnOpacityOut(this.btnXtgn);

        this.btnXtgn.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityIn(btnXtgn);
            }
        });

        this.btnXtgn.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityOut(btnXtgn);
            }
        });
        
        // 系统功能2
        this.imgXtgn.setImage(this.findImgXtgn());
        
        this.imgXtgn.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityIn(imgXtgn);
                imgXtgn.setCursor(Cursor.HAND);
            }
        });

        this.imgXtgn.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                onBtnOpacityOut(imgXtgn);
                imgXtgn.setCursor(Cursor.DEFAULT);
            }
        });
        
        this.imgXtgn.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                Alert.alert(UIConfig.get());
            }
        });
        
        // 退出系统
        this.btnExit.setTooltip(new Tooltip("退出系统"));
        this.btnExit.setText(StringUtils.EMPTY);
        this.btnExit.setGraphicTextGap(0);
        this.btnExit.setGraphic(new ImageView(this.findImgExit()));
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

        EventAdapter adapter = new EventAdapter() {
            public void onSure(ActionEvent evt) {
                Platform.exit();
            }
        };

        Alert.alert(UIConfig.get().setSize(UISize.to(400, 300)).setTipMsg(UITipMsg.to("退出系统", "你确定要退出系统吗？")).setBtnMsg(UIBtnMsg.get().setSure("确定").setCancel("取消")).setAdapter(adapter));
    }

    @FXML
    public void onMenuExit(ActionEvent evt) {
        this.onExitSystem();
    }

    @FXML
    public void onExitAction(ActionEvent evt) {
        this.onExitSystem();
    }

    /**
     * 透明度-1.0
     */
    private void onBtnOpacityIn(Node btn) {
        btn.setOpacity(1.0);
    }

    /**
     * 透明度-0.5
     */
    private void onBtnOpacityOut(Node btn) {
        btn.setOpacity(0.5);
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
