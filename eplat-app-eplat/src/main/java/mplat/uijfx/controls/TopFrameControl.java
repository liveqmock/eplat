/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import mplat.mgt.enums.TabDataEnum;
import mplat.uijfx.uiviews.MainViewController;
import mplat.utils.UConst;

import com.atom.core.lang.utils.LogUtils;

/**
 * 顶部导航栏
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopFrameControl.java, V1.0.1 2013-1-29 下午5:50:34 $
 */
public class TopFrameControl extends HBox {

    @FXML
    private HBox                     topFrame;

    @FXML
    private ImageView                imgCourseWare;
    @FXML
    private ImageView                imgTopicTrain;
    @FXML
    private ImageView                imgEmergeTrain;
    @FXML
    private ImageView                imgEmergeExam;
    @FXML
    private ImageView                imgSystemCfg;
    @FXML
    private ImageView                imgGotoMain;

    /** 事件代理器 */
    private final MainViewController parentView;

    /**
     * 初始化
     */
    public TopFrameControl(MainViewController parentView) {
        this.parentView = parentView;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TopFrameControl.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            LogUtils.error("初始化TopFrame组件异常!", e);
            throw new RuntimeException(e);
        }

        this.initViews();
    }

    @FXML
    private void onMouseOn(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
        } else if (node == this.imgSystemCfg) {
            // 系统功能
        } else if (node == this.imgGotoMain) {
            // 返回主界面
            this.imgGotoMain.setCursor(Cursor.HAND);
            this.imgGotoMain.setOpacity(UConst.OPACITY_ON);
        }
    }

    @FXML
    private void onMouseOut(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
        } else if (node == this.imgSystemCfg) {
            // 系统功能
        } else if (node == this.imgGotoMain) {
            // 返回主界面
            this.imgGotoMain.setCursor(Cursor.DEFAULT);
            this.imgGotoMain.setOpacity(UConst.OPACITY_OUT);
        }
    }

    @FXML
    private void onMouseClick(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
            LogUtils.info("系统课件");
            this.parentView.activeTab(TabDataEnum.COURSE_WARE);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            LogUtils.info("专项技能训练");
            this.parentView.activeTab(TabDataEnum.TOPIC_TRAIN);
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            LogUtils.info("专项急救案例训练");
            this.parentView.activeTab(TabDataEnum.EMERGE_TRAIN);
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            LogUtils.info("专项急救案例考核");
            this.parentView.activeTab(TabDataEnum.EMERGE_EXAM);
        } else if (node == this.imgSystemCfg) {
            // 系统功能
            LogUtils.info("系统功能设置");
            this.parentView.activeTab(TabDataEnum.SYSTEM_CFG);
        } else if (node == this.imgGotoMain) {
            // 返回主页面
            LogUtils.info("返回主页面");
            this.parentView.activeTab(TabDataEnum.MAIN_VIEW);
        }
    }

    /**
     * 初始化
     */
    private void initViews() {
        this.imgCourseWare.setUserData(TabDataEnum.COURSE_WARE.code());
        this.imgTopicTrain.setUserData(TabDataEnum.TOPIC_TRAIN.code());
        this.imgEmergeTrain.setUserData(TabDataEnum.EMERGE_TRAIN.code());
        this.imgEmergeExam.setUserData(TabDataEnum.EMERGE_EXAM.code());
        this.imgSystemCfg.setUserData(TabDataEnum.SYSTEM_CFG.code());
    }

    // ~~~~~~~~ getters and setters ~~~~~~~~~ //

    public HBox getTopFrame() {
        return topFrame;
    }

    public void setTopFrame(HBox topFrame) {
        this.topFrame = topFrame;
    }

    public ImageView getImgCourseWare() {
        return imgCourseWare;
    }

    public ImageView getImgTopicTrain() {
        return imgTopicTrain;
    }

    public ImageView getImgEmergeTrain() {
        return imgEmergeTrain;
    }

    public ImageView getImgEmergeExam() {
        return imgEmergeExam;
    }

    public ImageView getImgSystemCfg() {
        return imgSystemCfg;
    }

    public ImageView getImgGotoMain() {
        return imgGotoMain;
    }

    public MainViewController getParentView() {
        return parentView;
    }

}
