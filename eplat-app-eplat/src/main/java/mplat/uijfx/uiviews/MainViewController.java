/**
 * obullxl@gmail.com
 */
package mplat.uijfx.uiviews;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

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
import javafx.stage.Stage;
import mplat.uijfx.controls.TopFrameControl;
import mplat.uijfx.controls.TopFrameEventProxy;
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
public class MainViewController {
    private static final UISize SIZE        = UISize.to(1000, 705);

    public static final double  OPACITY_ON  = 1.0;
    public static final double  OPACITY_OUT = 0.5;

    private Stage               stage;

    @FXML
    private BorderPane          viewRoot;

    @FXML
    private MenuBar             menuBar;

    @FXML
    private TabPane             tabPane;
    @FXML
    private Tab                 tabMain;

    @FXML
    private ImageView           imgCourseWare;
    @FXML
    private ImageView           imgTopicTrain;
    @FXML
    private ImageView           imgEmergeTrain;
    @FXML
    private ImageView           imgEmergeExam;
    @FXML
    private ImageView           imgSystemCfg;

    @FXML
    private ImageView           imgExit;

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        // Tab标签
        this.tabMain.setGraphic(new ImageView(this.findTabWelcome()));
    }

    /**
     * 页面初始化
     */
    public boolean initViews(Stage stage) {
        this.stage = stage;

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

        if (node == this.imgCourseWare) {
            // 系统课件
            LogUtils.info("系统课件");

            Tab tab = this.findTab(TabUserData.COURSE_WARE);
            if(tab == null) {
                tab = this.newCourseWareTab();
            }
            
            this.putAndActiveTab(tab);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            LogUtils.info("专项技能训练");
            
            Tab tab = this.findTab(TabUserData.TOPIC_TRAIN);
            if(tab == null) {
                tab = this.newTopicTrainTab();
            }
            
            this.putAndActiveTab(tab);
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            LogUtils.info("专项急救案例训练");
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            LogUtils.info("专项急救案例考核");
        } else if (node == this.imgSystemCfg) {
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

    // ~~~~~~~~~~ 公用方法 ~~~~~~~~~~~~ //

    public Tab findTab(String userData) {
        for(Tab tab : this.tabPane.getTabs()) {
            if(StringUtils.equalsIgnoreCase(ObjectUtils.toString(tab.getUserData()), userData)) {
                return tab;
            }
        }
        
        return null;
    }
    
    public void putAndActiveTab(Tab tab) {
        Tab tmp = this.findTab(ObjectUtils.toString(tab.getUserData()));
        if(tmp == null) {
            this.tabPane.getTabs().add(tab);
        }
        
        this.tabPane.getSelectionModel().select(tab);
    }
    
    public Tab newCourseWareTab() {
        BorderPane border = new BorderPane();
        border.setTop(new TopFrameControl(TopFrameEventProxy.get()));

        Tab tab = new Tab("系统课件");
        tab.setClosable(true);
        tab.setUserData(TabUserData.COURSE_WARE);
        tab.setGraphic(new ImageView(this.findTabCourseWare()));
        tab.setContent(border);
        
        return tab;
    }
    
    public Tab newTopicTrainTab() {
        BorderPane border = new BorderPane();
        border.setTop(new TopFrameControl(TopFrameEventProxy.get()));

        Tab tab = new Tab("专项技能训练");
        tab.setClosable(true);
        tab.setUserData(TabUserData.TOPIC_TRAIN);
        tab.setGraphic(new ImageView(this.findTabTopicTrain()));
        tab.setContent(border);
        
        return tab;
    }
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

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

    private Image findTabCourseWare() {
        return new Image(IMGS.class.getResourceAsStream("btn-course-ware.png"), 20, 20, false, false);
    }
    
    private Image findTabTopicTrain() {
        return new Image(IMGS.class.getResourceAsStream("btn-topic-train.jpg"), 20, 20, false, false);
    }

}
