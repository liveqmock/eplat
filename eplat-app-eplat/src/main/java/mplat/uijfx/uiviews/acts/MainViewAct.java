/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import mplat.mgt.enums.TabDataEnum;
import mplat.uijfx.controls.TopFrameControl;
import mplat.uijfx.controls.TopMenuControl;
import mplat.uijfx.uiviews.events.CourseWareViewEvent;
import mplat.uijfx.uiviews.events.EmergeExamViewEvent;
import mplat.uijfx.uiviews.events.EmergeTrainViewEvent;
import mplat.uijfx.uiviews.events.SystemCfgViewEvent;
import mplat.uijfx.uiviews.events.TopicTrainViewEvent;
import mplat.utils.MViewUtils;
import mplat.utils.UConst;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.utils.TabPaneUtils;
import com.atom.core.uijfx.utils.WebViewUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 应用主窗体
 * 
 * @author obullxl@gmail.com
 * @version $Id: MainViewAct.java, V1.0.1 2013-2-3 下午2:50:11 $
 */
public final class MainViewAct extends BaseXmlAct {

    /** 菜单栏组件 */
    private TopMenuControl topMenuBar;

    @FXML
    private BorderPane     viewRoot;

    @FXML
    private TabPane        tabPane;
    @FXML
    private Tab            tabMain;
    @FXML
    private AnchorPane     content;

    @FXML
    private ImageView      imgCourseWare;
    @FXML
    private ImageView      imgTopicTrain;
    @FXML
    private ImageView      imgEmergeTrain;
    @FXML
    private ImageView      imgEmergeExam;
    @FXML
    private ImageView      imgSystemCfg;

    @FXML
    private ImageView      imgExit;

    /**
     * 默认构造器
     */
    public MainViewAct(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final MainViewAct initXmlViews() {
        // 属性
        this.findGroupViewProperty().set(this.viewRoot);
        this.findSizeProperty().set(new Dimension2D(1000, 705));
        this.findTitleProperty().set("GD/ACLS 8000 高级生命支持急救技能训练软件2013版 - [欢迎使用]");

        // this.content.setStyle("background-image:url('../../images/Welcome-BG.jpg');-fx-background-repeat:no-repeat;-fx-background-position:center center;");

        // 菜单
        this.topMenuBar = new TopMenuControl(this);
        this.viewRoot.setTop(this.topMenuBar);

        // Tab标签
        TabDataEnum tabdata = TabDataEnum.MAIN_VIEW;
        this.tabMain.setClosable(false);
        this.tabMain.setUserData(tabdata.code());
        this.tabMain.setGraphic(new ImageView(MViewUtils.findTabWelcome()));

        // 设置持有器
        MViewUtils.set(this);

        return this;
    }

    /**
     * 获取TabPane面板视图
     */
    public final TabPane findTabPane() {
        return this.tabPane;
    }

    @FXML
    private void onMouseOn(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.HAND);
        MViewUtils.chargeOpacity(node, UConst.OPACITY_ON);
    }

    @FXML
    private void onMouseOut(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.DEFAULT);
        MViewUtils.chargeOpacity(node, UConst.OPACITY_OUT);
    }

    @FXML
    private void onMouseClick(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
            this.activeTab(TabDataEnum.COURSE_WARE);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            this.activeTab(TabDataEnum.TOPIC_TRAIN);
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            this.activeTab(TabDataEnum.EMERGE_TRAIN);
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            this.activeTab(TabDataEnum.EMERGE_EXAM);
        } else if (node == this.imgSystemCfg) {
            // 系统功能
            this.activeTab(TabDataEnum.SYSTEM_CFG);
        } else {
            // 未知
            LogUtils.error("未知的导航操作", new RuntimeException("未知的导航操作"));
        }
    }

    @FXML
    private void onExitAction(MouseEvent evt) {
        PopupUtils.exitSystem(this.findStage());
    }

    // ~~~~~~~~~~ 公用方法 ~~~~~~~~~~~~ //

    /**
     * 激活TAB
     */
    public final void activeTab(TabDataEnum tabdata) {
        // 查找
        Tab tab = TabPaneUtils.findTab(this.findTabPane(), tabdata.code());

        if (tabdata == TabDataEnum.MAIN_VIEW) {
            // 欢迎页面
            if (tab == null) {
                String msg = "主页面Tab不存在，请重新登录系统!";
                LogUtils.error(msg, new RuntimeException(msg));
                PopupUtils.alert(this.findStage(), "系统异常", msg);
            } else {
                // 激活
                this.tabPane.getSelectionModel().select(tab);
            }
        } else if (tabdata == TabDataEnum.COURSE_WARE) {
            // 系统课件
            if (tab == null) {
                BorderPane border = new BorderPane();

                // TOP
                TopFrameControl topCtrl = new TopFrameControl(this);
                MViewUtils.initTopFrame(topCtrl, topCtrl.findImgCourseWare());
                border.setTop(topCtrl);

                // Center
                WebView view = WebViewUtils.initWebView(MViewUtils.findHtmlUrl(tabdata.code()));
                WebViewUtils.registWebStateSucceedEvent(view, new CourseWareViewEvent(view));
                border.setCenter(view);

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(MViewUtils.findTabCourseWare()));
                tab.setContent(border);

                // 保存
                this.tabPane.getTabs().add(tab);
            }

            // 激活
            this.tabPane.getSelectionModel().select(tab);
        } else if (tabdata == TabDataEnum.TOPIC_TRAIN) {
            // 专业急救训练
            if (tab == null) {
                BorderPane border = new BorderPane();

                // TOP
                TopFrameControl topCtrl = new TopFrameControl(this);
                MViewUtils.initTopFrame(topCtrl, topCtrl.findImgTopicTrain());
                border.setTop(topCtrl);

                // Center
                WebView view = WebViewUtils.initWebView(MViewUtils.findHtmlUrl(tabdata.code()));
                WebViewUtils.registWebStateSucceedEvent(view, new TopicTrainViewEvent(view));
                border.setCenter(view);

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(MViewUtils.findTabTopicTrain()));
                tab.setContent(border);

                // 保存
                this.tabPane.getTabs().add(tab);
            }

            // 激活
            this.tabPane.getSelectionModel().select(tab);
        } else if (tabdata == TabDataEnum.EMERGE_TRAIN) {
            // 急救案例训练
            if (tab == null) {
                BorderPane border = new BorderPane();

                // TOP
                TopFrameControl topCtrl = new TopFrameControl(this);
                MViewUtils.initTopFrame(topCtrl, topCtrl.findImgEmergeTrain());
                border.setTop(topCtrl);

                // Center
                WebView view = WebViewUtils.initWebView(MViewUtils.findHtmlUrl(tabdata.code()));
                WebViewUtils.registWebStateSucceedEvent(view, new EmergeTrainViewEvent(view));
                border.setCenter(view);

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(MViewUtils.findTabEmergeTrain()));
                tab.setContent(border);

                // 保存
                this.tabPane.getTabs().add(tab);
            }

            // 激活
            this.tabPane.getSelectionModel().select(tab);
        } else if (tabdata == TabDataEnum.EMERGE_EXAM) {
            // 急救案例考核
            if (tab == null) {
                BorderPane border = new BorderPane();

                // TOP
                TopFrameControl topCtrl = new TopFrameControl(this);
                MViewUtils.initTopFrame(topCtrl, topCtrl.findImgEmergeExam());
                border.setTop(topCtrl);

                // Center
                WebView view = WebViewUtils.initWebView(MViewUtils.findHtmlUrl(tabdata.code()));
                WebViewUtils.registWebStateSucceedEvent(view, new EmergeExamViewEvent(view));
                border.setCenter(view);

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(MViewUtils.findTabEmergeExam()));
                tab.setContent(border);

                // 保存
                this.tabPane.getTabs().add(tab);
            }

            // 激活
            this.tabPane.getSelectionModel().select(tab);
        } else if (tabdata == TabDataEnum.SYSTEM_CFG) {
            // 系统功能设置
            if (tab == null) {
                BorderPane border = new BorderPane();

                // TOP
                TopFrameControl topCtrl = new TopFrameControl(this);
                MViewUtils.initTopFrame(topCtrl, topCtrl.findImgSystemCfg());
                border.setTop(topCtrl);

                // Center
                WebView view = WebViewUtils.initWebView(MViewUtils.findHtmlUrl(tabdata.code()));
                WebViewUtils.registWebStateSucceedEvent(view, new SystemCfgViewEvent(view));
                border.setCenter(view);

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(MViewUtils.findTabSystemCfg()));
                tab.setContent(border);

                // 保存
                this.tabPane.getTabs().add(tab);
            }

            // 激活
            this.tabPane.getSelectionModel().select(tab);
        }
    }

}
