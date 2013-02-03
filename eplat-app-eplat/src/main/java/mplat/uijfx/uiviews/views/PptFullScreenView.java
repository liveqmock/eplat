/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mplat.mgt.dto.PPTMapDTO;

import org.apache.commons.io.FilenameUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.utils.StageHolder;

/**
 * 全屏组件
 * 
 * @author obullxl@gmail.com
 * @version $Id: FullScreenView.java, V1.0.1 2013-2-2 下午9:11:23 $
 */
public class PptFullScreenView {

    /** 第几张PPT */
    private int                  pptNo;

    /** PPT浏览器 */
    private final BasePptView<?> pptView;

    /** PPT名称 */
    private final String         pptName;

    /** PPT路径 */
    private final String         pptPath;

    /** PPT映射信息 */
    private final PPTMapDTO      pptMap;

    /** PPT播放器 */
    private BorderPane           pptPane;

    private ImageView            imgPpt;

    private HBox                 hbox;
    private Button               btnFirst;
    private Button               btnPrevious;
    private Button               btnNext;
    private Button               btnLast;
    private Button               btnFullScreen;

    /** 全屏 */
    private Stage                stage;

    /**
     * CTOR
     */
    public PptFullScreenView(BasePptView<?> pptView, String pptName, int pptNo) {
        this.pptView = pptView;
        this.pptNo = pptNo;
        this.pptName = pptName;
        this.pptPath = FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/ppt/" + this.pptName);
        this.pptMap = new PPTMapDTO(this.pptPath);

        this.initViews();
        this.showPpt();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        this.pptPane = new BorderPane();
        this.imgPpt = new ImageView();

        this.btnFirst = new Button("First");
        this.btnFirst.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent evt) {
                onFirstAction(evt);
            }
        });

        this.btnPrevious = new Button("Previous");
        this.btnPrevious.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent evt) {
                onPreviousAction(evt);
            }
        });

        this.btnNext = new Button("Next");
        this.btnNext.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent evt) {
                onNextAction(evt);
            }
        });

        this.btnLast = new Button("Last");
        this.btnLast.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent evt) {
                onLastAction(evt);
            }
        });

        this.btnFullScreen = new Button("Full Screen");
        this.btnFullScreen.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent evt) {
                onFullScreenAction(evt);
            }
        });

        // Center
        this.pptPane.setCenter(this.imgPpt);

        // Bottom
        this.hbox = new HBox();
        this.hbox.setAlignment(Pos.CENTER_RIGHT);
        this.hbox.setPrefHeight(30.0);
        this.hbox.setSpacing(10.0);
        this.hbox.setPadding(new Insets(30.0));
        this.hbox.getChildren().addAll(this.btnFirst, this.btnPrevious, this.btnNext, this.btnLast, this.btnFullScreen);
        BorderPane.setAlignment(this.hbox, Pos.CENTER_RIGHT);

        this.pptPane.setBottom(this.hbox);

        // Stage
        this.stage = new Stage();
        this.stage.initOwner(StageHolder.get());
        this.stage.initModality(Modality.APPLICATION_MODAL);

        this.stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!stage.isFullScreen()) {
                    onFullScreenOut();
                }
            }
        });

        Scene scene = new Scene(this.pptPane);
        scene.getStylesheets().add("@BasePptView.css");
        this.hbox.setStyle("ppt-buttons");

        this.stage.setScene(scene);
        this.stage.setX(-100000);
        this.stage.setY(-100000);
        this.stage.setFullScreen(true);
        this.stage.show();
    }

    /**
     * 展示PPT
     */
    private void showPpt() {
        List<String> files = this.pptMap.getFiles();
        if (files.isEmpty()) {
            return;
        }

        if (this.pptNo < 0) {
            this.pptNo = 0;
        }

        int maxNo = (files.size() - 1);
        if (this.pptNo > maxNo) {
            this.pptNo = maxNo;
        }

        String file = this.findPptFile(files.get(this.pptNo));
        double height = this.stage.getHeight() - this.pptPane.getBottom().prefHeight(-1);
        this.imgPpt.setImage(new Image(file, 0, height, true, false));
        LogUtils.info("[全屏]-展示第[" + this.pptNo + "]张PPT[" + file + "].");

        // 同步修改父类页号
        this.pptView.chargePptNo(this.pptNo);
    }

    /**
     * 有效或无效一些按钮
     */
    private void processButtons() {
        int count = this.pptMap.getFiles().size();

        if (count == 0) {
            this.btnFullScreen.setDisable(true);
        }

        if (count <= 1) {
            this.btnFirst.setDisable(true);
            this.btnPrevious.setDisable(true);
            this.btnNext.setDisable(true);
            this.btnLast.setDisable(true);

            return;
        }

        if (this.pptNo <= 0) {
            this.btnFirst.setDisable(true);
            this.btnPrevious.setDisable(true);
        } else {
            this.btnFirst.setDisable(false);
            this.btnPrevious.setDisable(false);
        }

        if (this.pptNo >= (count - 1)) {
            this.btnNext.setDisable(true);
            this.btnLast.setDisable(true);
        } else {
            this.btnNext.setDisable(false);
            this.btnLast.setDisable(false);
        }
    }

    /**
     * 取得PPT文件路径
     */
    private String findPptFile(String imgName) {
        return "file:///" + FilenameUtils.normalize(this.pptPath + "/" + imgName);
    }

    // ~~~~~~~~~~~ 事件处理 ~~~~~~~~~~~~~ //

    private void onFirstAction(ActionEvent evt) {
        this.pptNo = 0;
        this.processButtons();

        this.showPpt();
    }

    private void onPreviousAction(ActionEvent evt) {
        this.pptNo = Math.max(this.pptNo - 1, 0);
        this.processButtons();

        this.showPpt();
    }

    private void onNextAction(ActionEvent evt) {
        this.pptNo += 1;
        this.processButtons();

        this.showPpt();
    }

    private void onLastAction(ActionEvent evt) {
        this.pptNo = Math.max(this.pptMap.getFiles().size() - 1, 0);
        this.processButtons();

        this.showPpt();
    }

    private void onFullScreenAction(ActionEvent evt) {
        this.onFullScreenOut();
    }

    private void onFullScreenOut() {
        Platform.runLater(new Runnable() {
            public void run() {
                if (stage != null) {
                    stage.close();
                }
            }
        });
    }

}
