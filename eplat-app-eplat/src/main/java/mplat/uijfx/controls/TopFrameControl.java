/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mplat.mgt.enums.TabDataEnum;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.uiviews.acts.MainViewAct;

import com.atom.core.lang.utils.LogUtils;

/**
 * 顶部导航栏
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopFrameControl.java, V1.0.1 2013-1-29 下午5:50:34 $
 */
public class TopFrameControl extends HBox {

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
    private ImageView         imgGotoMain;

    /** 根组件 */
    private final MainViewAct rootAct;

    /**
     * 初始化
     */
    public TopFrameControl(MainViewAct rootAct) {
        this.rootAct = rootAct;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TopFrameControl.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            String msg = "初始化TopFrame组件异常!";
            LogUtils.error(msg, e);
            throw new RuntimeException(msg, e);
        }

        this.initViews();
    }

    /**
     * 获取主视图控制器
     */
    public final MainViewAct findRootAct() {
        return this.rootAct;
    }

    /**
     * 获取主Stage舞台
     */
    public final Stage findStage() {
        return this.rootAct.findStage();
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
            this.imgGotoMain.setImage(this.findGotoMainOn());
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
            this.imgGotoMain.setImage(this.findGotoMainOut());
        }
    }

    @FXML
    private void onMouseClick(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
            this.rootAct.activeTab(TabDataEnum.COURSE_WARE);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            this.rootAct.activeTab(TabDataEnum.TOPIC_TRAIN);
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            this.rootAct.activeTab(TabDataEnum.EMERGE_TRAIN);
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            this.rootAct.activeTab(TabDataEnum.EMERGE_EXAM);
        } else if (node == this.imgSystemCfg) {
            // 系统功能
            this.rootAct.activeTab(TabDataEnum.SYSTEM_CFG);
        } else if (node == this.imgGotoMain) {
            // 返回主页面
            this.rootAct.activeTab(TabDataEnum.MAIN_VIEW);
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

    // ~~~~~~~~ 图片信息 ~~~~~~~~~~~~~~~ //

    private Image findGotoMainOn() {
        return new Image(IMGS.class.getResourceAsStream("btn-return-on.gif"), 52, 52, false, false);
    }

    private Image findGotoMainOut() {
        return new Image(IMGS.class.getResourceAsStream("btn-return-out.gif"), 52, 52, false, false);
    }

    // ~~~~~~~~ getters and setters ~~~~~~~~~ //

    public ImageView findImgCourseWare() {
        return imgCourseWare;
    }

    public ImageView findImgTopicTrain() {
        return imgTopicTrain;
    }

    public ImageView findImgEmergeTrain() {
        return imgEmergeTrain;
    }

    public ImageView findImgEmergeExam() {
        return imgEmergeExam;
    }

    public ImageView findImgSystemCfg() {
        return imgSystemCfg;
    }

    public ImageView findImgGotoMain() {
        return imgGotoMain;
    }

}
