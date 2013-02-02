/**
 * obullxl@gmail.com
 */
package mplat.uijfx.uiviews;

import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import mplat.mgt.enums.TabDataEnum;
import mplat.uijfx.controls.TopFrameControl;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.uiviews.views.BaseView;
import mplat.uijfx.uiviews.views.CourseWareWebView;
import mplat.uijfx.utils.Alert;
import mplat.utils.UConst;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.UIBtnMsg;
import com.atom.core.uijfx.UIConfig;
import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.UITipMsg;
import com.atom.core.uijfx.event.EventAdapter;

/**
 * @author obullxl@gmail.com
 */
public final class MainViewController {
    /** 组件尺寸 */
    private static final UISize SIZE = UISize.to(1000, 705);

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
        TabDataEnum tabdata = TabDataEnum.MAIN_VIEW;
        this.tabMain.setClosable(false);
        this.tabMain.setUserData(tabdata.code());
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
        this.chargeOpacity(node, UConst.OPACITY_ON);
    }

    @FXML
    private void onMouseOut(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.DEFAULT);
        this.chargeOpacity(node, UConst.OPACITY_OUT);
    }

    @FXML
    private void onMouseClick(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
            LogUtils.info("系统课件");
            this.activeTab(TabDataEnum.COURSE_WARE);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            LogUtils.info("专项技能训练");
            this.activeTab(TabDataEnum.TOPIC_TRAIN);
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            LogUtils.info("专项急救案例训练");
            this.activeTab(TabDataEnum.EMERGE_TRAIN);
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            LogUtils.info("专项急救案例考核");
            this.activeTab(TabDataEnum.EMERGE_EXAM);
        } else if (node == this.imgSystemCfg) {
            // 系统功能
            LogUtils.info("系统功能设置");
            this.activeTab(TabDataEnum.SYSTEM_CFG);
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

    /**
     * 根据用户数据查询TAB
     */
    public Tab findTab(TabDataEnum tabdata) {
        for (Tab tab : this.tabPane.getTabs()) {
            if (StringUtils.equalsIgnoreCase(ObjectUtils.toString(tab.getUserData()), tabdata.code())) {
                return tab;
            }
        }

        return null;
    }

    /**
     * 激活TAB
     */
    public void activeTab(Tab tab) {
        // 保存
        this.tabPane.getTabs().add(tab);
        // 激活
        this.tabPane.getSelectionModel().select(tab);
    }

    /**
     * 激活TAB
     */
    public void activeTab(TabDataEnum tabdata) {
        if (tabdata == TabDataEnum.MAIN_VIEW) {
            this.activeMainViewTab();
        } else if (tabdata == TabDataEnum.COURSE_WARE) {
            this.activeCourseWareTab();
        } else if (tabdata == TabDataEnum.TOPIC_TRAIN) {
            this.activeTopicTrainTab();
        } else if (tabdata == TabDataEnum.EMERGE_TRAIN) {
            this.activeEmergeTrainTab();
        } else if (tabdata == TabDataEnum.EMERGE_EXAM) {
            this.activeEmergeExamTab();
        } else if (tabdata == TabDataEnum.SYSTEM_CFG) {
            this.activeSystemCfgTab();
        }
    }

    /**
     * 激活“欢迎使用”TAB
     */
    private void activeMainViewTab() {
        // 查找
        Tab tab = this.findTab(TabDataEnum.MAIN_VIEW);
        if (tab == null) {
            String msg = "主页面Tab不存在，请重新登录系统!";
            LogUtils.error(msg, new RuntimeException(msg));
            Alert.alert(UIConfig.get().setSize(UISize.to(400, 300)).setTipMsg(UITipMsg.to("系统异常", msg)));
        } else {
            // 激活
            this.tabPane.getSelectionModel().select(tab);
        }
    }

    /**
     * 激活“系统课件”TAB
     */
    private void activeCourseWareTab() {
        TabDataEnum tdata = TabDataEnum.COURSE_WARE;
        // 新建
        Tab tab = this.findTab(tdata);
        if (tab == null) {
            BorderPane border = new BorderPane();

            // TOP
            TopFrameControl topCtrl = new TopFrameControl(this);
            ImageView spec = topCtrl.getImgCourseWare();
            spec.setOpacity(UConst.OPACITY_ON);
            this.initTopFrameControl(topCtrl, spec);
            border.setTop(topCtrl);

            // Center
            BaseView webView = new CourseWareWebView(this, this.findHtmlUrl(tdata.code()));
            border.setCenter((WebView) webView.findView());

            tab = new Tab(tdata.desp());
            tab.setClosable(true);
            tab.setUserData(tdata.code());
            tab.setGraphic(new ImageView(this.findTabCourseWare()));
            tab.setContent(border);

            // 保存
            this.tabPane.getTabs().add(tab);
        }

        // 激活
        this.tabPane.getSelectionModel().select(tab);
    }

    /**
     * 激活“专项技能训练”TAB
     */
    private void activeTopicTrainTab() {
        TabDataEnum tdata = TabDataEnum.TOPIC_TRAIN;
        // 新建
        Tab tab = this.findTab(tdata);
        if (tab == null) {
            BorderPane border = new BorderPane();

            // TOP
            TopFrameControl topCtrl = new TopFrameControl(this);
            ImageView spec = topCtrl.getImgTopicTrain();
            spec.setOpacity(UConst.OPACITY_ON);
            this.initTopFrameControl(topCtrl, spec);
            border.setTop(topCtrl);

            // Center
            BaseView webView = new CourseWareWebView(this, this.findHtmlUrl(tdata.code()));
            border.setCenter((WebView) webView.findView());

            tab = new Tab(tdata.desp());
            tab.setClosable(true);
            tab.setUserData(tdata.code());
            tab.setGraphic(new ImageView(this.findTabTopicTrain()));
            tab.setContent(border);

            // 保存
            this.tabPane.getTabs().add(tab);
        }

        // 激活
        this.tabPane.getSelectionModel().select(tab);
    }

    /**
     * 激活“专业急救训练”TAB
     */
    private void activeEmergeTrainTab() {
        TabDataEnum tdata = TabDataEnum.EMERGE_TRAIN;
        // 新建
        Tab tab = this.findTab(tdata);
        if (tab == null) {
            BorderPane border = new BorderPane();

            // TOP
            TopFrameControl topCtrl = new TopFrameControl(this);
            ImageView spec = topCtrl.getImgEmergeTrain();
            spec.setOpacity(UConst.OPACITY_ON);
            this.initTopFrameControl(topCtrl, spec);
            border.setTop(topCtrl);

            // Center
            BaseView webView = new CourseWareWebView(this, this.findHtmlUrl(tdata.code()));
            border.setCenter((WebView) webView.findView());

            tab = new Tab(tdata.desp());
            tab.setClosable(true);
            tab.setUserData(tdata.code());
            tab.setGraphic(new ImageView(this.findTabEmergeTrain()));
            tab.setContent(border);

            // 保存
            this.tabPane.getTabs().add(tab);
        }

        // 激活
        this.tabPane.getSelectionModel().select(tab);
    }

    /**
     * 激活“专业急救考核”TAB
     */
    private void activeEmergeExamTab() {
        TabDataEnum tdata = TabDataEnum.EMERGE_EXAM;
        // 新建
        Tab tab = this.findTab(tdata);
        if (tab == null) {
            BorderPane border = new BorderPane();

            // TOP
            TopFrameControl topCtrl = new TopFrameControl(this);
            ImageView spec = topCtrl.getImgEmergeExam();
            spec.setOpacity(UConst.OPACITY_ON);
            this.initTopFrameControl(topCtrl, spec);
            border.setTop(topCtrl);

            // Center
            WebView webView = new WebView();
            final WebEngine webEngine = webView.getEngine();
            webEngine.load(this.findHtmlUrl(tdata.code()));
            border.setCenter(webView);

            tab = new Tab(tdata.desp());
            tab.setClosable(true);
            tab.setUserData(tdata.code());
            tab.setGraphic(new ImageView(this.findTabTopicTrain()));
            tab.setContent(border);

            // 保存
            this.tabPane.getTabs().add(tab);
        }

        // 激活
        this.tabPane.getSelectionModel().select(tab);
    }

    /**
     * 激活“系统功能”TAB
     */
    private void activeSystemCfgTab() {
        TabDataEnum tdata = TabDataEnum.SYSTEM_CFG;
        // 新建
        Tab tab = this.findTab(tdata);
        if (tab == null) {
            BorderPane border = new BorderPane();

            // TOP
            TopFrameControl topCtrl = new TopFrameControl(this);
            ImageView spec = topCtrl.getImgSystemCfg();
            spec.setOpacity(UConst.OPACITY_ON);
            this.initTopFrameControl(topCtrl, spec);
            border.setTop(topCtrl);

            // Center
            WebView webView = new WebView();
            final WebEngine webEngine = webView.getEngine();
            webEngine.load(this.findHtmlUrl(tdata.code()));
            border.setCenter(webView);

            tab = new Tab(tdata.desp());
            tab.setClosable(true);
            tab.setUserData(tdata.code());
            tab.setGraphic(new ImageView(this.findTabTopicTrain()));
            tab.setContent(border);

            // 保存
            this.tabPane.getTabs().add(tab);
        }

        // 激活
        this.tabPane.getSelectionModel().select(tab);
    }

    // ~~~~~~~~ getters and setters ~~~~~~~~~ //

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * 初始化Top组件
     */
    private void initTopFrameControl(TopFrameControl ctrl, final ImageView spec) {
        List<ImageView> imgs = Arrays.asList(ctrl.getImgCourseWare(), ctrl.getImgTopicTrain(), ctrl.getImgEmergeTrain(), ctrl.getImgEmergeExam(), ctrl.getImgSystemCfg());
        for (final ImageView img : imgs) {
            // 鼠标ON
            img.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent evt) {
                    img.setCursor(Cursor.HAND);
                    if (img != spec) {
                        img.setOpacity(UConst.OPACITY_ON);
                    }
                }
            });

            // 鼠标OUT
            img.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent evt) {
                    img.setCursor(Cursor.DEFAULT);
                    if (img != spec) {
                        img.setOpacity(UConst.OPACITY_OUT);
                    }
                }
            });

            // 鼠标Click
            img.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent evt) {
                    String udata = ObjectUtils.toString(img.getUserData());
                    activeTab(TabDataEnum.findByCode(udata));
                }
            });
        }
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

    private Image findTabCourseWare() {
        return new Image(IMGS.class.getResourceAsStream("btn-course-ware.png"), 20, 20, false, false);
    }

    private Image findTabTopicTrain() {
        return new Image(IMGS.class.getResourceAsStream("btn-topic-train.jpg"), 20, 20, false, false);
    }
    
    private Image findTabEmergeTrain() {
        return new Image(IMGS.class.getResourceAsStream("btn-emerge-train.jpg"), 20, 20, false, false);
    }

    /**
     * HTML文件URL
     */
    private String findHtmlUrl(String name) {
        // return "http://www.baidu.com";
        return "file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/" + name + ".html");
    }

}
