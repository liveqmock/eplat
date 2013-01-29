/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
    private final TopFrameEventProxy eventProxy;

    /**
     * 初始化
     */
    public TopFrameControl(final TopFrameEventProxy eventProxy) {
        this.eventProxy = eventProxy;

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
            this.eventProxy.onCourseWareMouseOn(evt, this.imgCourseWare);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            this.eventProxy.onTopicTrainMouseOn(evt, this.imgTopicTrain);
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            this.eventProxy.onEmergeTrainMouseOn(evt, this.imgEmergeTrain);
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            this.eventProxy.onEmergeExamMouseOn(evt, this.imgEmergeExam);
        } else if (node == this.imgSystemCfg) {
            // 系统功能
            this.eventProxy.onSystemCfgMouseOn(evt, this.imgSystemCfg);
        } else if (node == this.imgGotoMain) {
            // 系统功能
            this.eventProxy.onGotoMainMouseOn(evt, this.imgGotoMain);
        }
    }

    @FXML
    private void onMouseOut(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
            this.eventProxy.onCourseWareMouseOut(evt, this.imgCourseWare);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            this.eventProxy.onTopicTrainMouseOut(evt, this.imgTopicTrain);
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            this.eventProxy.onEmergeTrainMouseOut(evt, this.imgEmergeTrain);
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            this.eventProxy.onEmergeExamMouseOut(evt, this.imgEmergeExam);
        } else if (node == this.imgSystemCfg) {
            // 系统功能
            this.eventProxy.onSystemCfgMouseOut(evt, this.imgSystemCfg);
        } else if (node == this.imgGotoMain) {
            // 系统功能
            this.eventProxy.onGotoMainMouseOut(evt, this.imgGotoMain);
        }
    }

    @FXML
    private void onMouseClick(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
            LogUtils.info("系统课件");
            this.eventProxy.onCourseWareMouseClick(evt, this.imgCourseWare);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            LogUtils.info("专项技能训练");
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            LogUtils.info("专项急救案例训练");
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            LogUtils.info("专项急救案例考核");
        } else if (node == this.imgSystemCfg) {
            // 系统功能
            LogUtils.info("系统功能");
        } else if (node == this.imgGotoMain) {
            // 系统功能
            LogUtils.info("返回主页面");
        }
    }

    /**
     * 初始化
     */
    private void initViews() {
        this.eventProxy.onCourseWareInit(this.imgCourseWare);
        this.eventProxy.onTopicTrainInit(this.imgTopicTrain);
        this.eventProxy.onEmergeTrainInit(this.imgEmergeTrain);
        this.eventProxy.onEmergeExamInit(this.imgEmergeExam);
        this.eventProxy.onSystemCfgInit(this.imgSystemCfg);
        this.eventProxy.onGotoMainInit(this.imgGotoMain);
    }

}
