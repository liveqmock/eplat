/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import java.util.Arrays;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import mplat.mgt.enums.TabDataEnum;
import mplat.uijfx.controls.TopFrameControl;
import mplat.uijfx.controls.TopMenuControl;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.uiviews.views.BaseView;
import mplat.uijfx.uiviews.views.CourseWareWebView;
import mplat.uijfx.uiviews.views.EmergeExamWebView;
import mplat.uijfx.uiviews.views.EmergeTrainWebView;
import mplat.uijfx.uiviews.views.SystemCfgWebView;
import mplat.uijfx.uiviews.views.TopicTrainWebView;
import mplat.utils.UConst;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ObjectUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseStageView;
import com.atom.core.uijfx.views.BaseTabPane;

/**
 * 应用主窗体
 * 
 * @author obullxl@gmail.com
 * @version $Id: MainViewController.java, V1.0.1 2013-2-3 下午2:50:11 $
 */
public final class MainViewController extends BaseStageView {
    /** 组件尺寸 */
    private final Dimension2D size = new Dimension2D(1000, 705);

    private TopMenuControl    topMenuBar;

    @FXML
    private BorderPane        viewRoot;

    @FXML
    private TabPane           tabPane;
    @FXML
    private Tab               tabMain;
    private BaseTabPane       tabView;

    @FXML
    private ImageView         imgCourseWare;
    @FXML
    private ImageView         imgTopicTrain;
    @FXML
    private ImageView         imgEmergeTrain;
    @FXML
    private ImageView         imgEmergeExam;
    @FXML
    private ImageView         imgSystemCfg;

    @FXML
    private ImageView         imgExit;

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        // 菜单
        this.topMenuBar = new TopMenuControl(this);
        this.viewRoot.setTop(this.topMenuBar);
        // this.viewRoot.getTop().prefHeight(22.0);

        this.tabView = new BaseTabPane() {
            /** 
             * @see com.atom.core.uijfx.views.BaseTabPane#findTabPane()
             */
            public TabPane findTabPane() {
                return tabPane;
            }

            /** 
             * @see com.atom.core.uijfx.views.BaseTabPane#findHomePageTab()
             */
            public Tab findHomePageTab() {
                return tabMain;
            }
        };

        // Tab标签
        TabDataEnum tabdata = TabDataEnum.MAIN_VIEW;
        this.tabMain.setClosable(false);
        this.tabMain.setUserData(tabdata.code());
        this.tabMain.setGraphic(new ImageView(this.findTabWelcome()));
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseStageView#findTitle()
     */
    public String findTitle() {
        return "GD/ACLS 8000 高级生命支持急救技能训练软件2013版 - [欢迎使用]";
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseStageView#findGroupView()
     */
    @SuppressWarnings("unchecked")
    public BorderPane findGroupView() {
        return this.viewRoot;
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseStageView#findSize()
     */
    public Dimension2D findSize() {
        return this.size;
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
    private void onExitAction(MouseEvent evt) {
        this.onExitSystem();
    }

    // ~~~~~~~~~~ 公用方法 ~~~~~~~~~~~~ //
    
    /**
     * 获取TabView对象
     */
    public final BaseTabPane findTabView() {
        return this.tabView;
    }

    /**
     * 激活TAB
     */
    public void activeTab(TabDataEnum tabdata) {
        // 查找
        Tab tab = this.tabView.findTab(tabdata.code());

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
                ImageView spec = topCtrl.getImgCourseWare();
                spec.setOpacity(UConst.OPACITY_ON);
                this.initTopFrameControl(topCtrl, spec);
                border.setTop(topCtrl);

                // Center
                BaseView<?, ?> webView = new CourseWareWebView(this, this.findHtmlUrl(tabdata.code()));
                border.setCenter((WebView) webView.findView());

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(this.findTabCourseWare()));
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
                ImageView spec = topCtrl.getImgTopicTrain();
                spec.setOpacity(UConst.OPACITY_ON);
                this.initTopFrameControl(topCtrl, spec);
                border.setTop(topCtrl);

                // Center
                BaseView<?, ?> webView = new TopicTrainWebView(this, this.findHtmlUrl(tabdata.code()));
                border.setCenter((WebView) webView.findView());

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(this.findTabTopicTrain()));
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
                ImageView spec = topCtrl.getImgEmergeTrain();
                spec.setOpacity(UConst.OPACITY_ON);
                this.initTopFrameControl(topCtrl, spec);
                border.setTop(topCtrl);

                // Center
                BaseView<?, ?> webView = new EmergeTrainWebView(this, this.findHtmlUrl(tabdata.code()));
                border.setCenter((WebView) webView.findView());

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(this.findTabEmergeTrain()));
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
                ImageView spec = topCtrl.getImgEmergeExam();
                spec.setOpacity(UConst.OPACITY_ON);
                this.initTopFrameControl(topCtrl, spec);
                border.setTop(topCtrl);

                // Center
                BaseView<?, ?> webView = new EmergeExamWebView(this, this.findHtmlUrl(tabdata.code()));
                border.setCenter((WebView) webView.findView());

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(this.findTabEmergeExam()));
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
                ImageView spec = topCtrl.getImgSystemCfg();
                spec.setOpacity(UConst.OPACITY_ON);
                this.initTopFrameControl(topCtrl, spec);
                border.setTop(topCtrl);

                // Center
                BaseView<?, ?> webView = new SystemCfgWebView(this, this.findHtmlUrl(tabdata.code()));
                border.setCenter((WebView) webView.findView());

                tab = new Tab(tabdata.desp());
                tab.setClosable(true);
                tab.setUserData(tabdata.code());
                tab.setGraphic(new ImageView(this.findTabSystemCfg()));
                tab.setContent(border);

                // 保存
                this.tabPane.getTabs().add(tab);
            }

            // 激活
            this.tabPane.getSelectionModel().select(tab);
        }
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
        PopupUtils.exitSystem(this.findStage());
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

    private Image findTabEmergeExam() {
        return new Image(IMGS.class.getResourceAsStream("btn-emerge-exam.jpg"), 20, 20, false, false);
    }

    private Image findTabSystemCfg() {
        return new Image(IMGS.class.getResourceAsStream("btn-system-cfg.jpg"), 20, 20, false, false);
    }

    /**
     * HTML文件URL
     */
    private String findHtmlUrl(String name) {
        // return "http://www.baidu.com";
        return "file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/" + name + ".html");
    }

}
