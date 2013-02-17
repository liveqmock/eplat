/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.mgt.dto.PPTMapDTO;
import mplat.utils.PPTUtils;
import mplat.utils.PPTUtils.PPTViewCallback;

import org.apache.commons.io.FilenameUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 以图片形式的PPT控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: PptImageAct.java, V1.0.1 2013-2-12 下午10:35:30 $
 */
public class PptImageAct extends BaseXmlAct implements PPTViewCallback {
    /** PPT高度 */
    private static final double         HEIGHT    = 630.0;

    /** 第几张PPT */
    private int                         pptNo;

    /** PPT名称 */
    private final String                pptName;

    /** 是否全屏展示 */
    private final boolean               fullScreen;

    /** PPT路径 */
    private final String                pptPath;

    /** PPT映射信息 */
    private final PPTMapDTO             pptMap;

    /** PPT视图回调 */
    private final List<PPTViewCallback> callbacks = new ArrayList<PPTViewCallback>();

    /** PPT播放器 */
    @FXML
    protected BorderPane                pptPane;

    @FXML
    protected ImageView                 imgPpt;

    @FXML
    protected Button                    btnFirst;
    @FXML
    protected Button                    btnPrevious;
    @FXML
    protected Button                    btnNext;
    @FXML
    protected Button                    btnLast;
    @FXML
    protected Button                    btnFullScreen;

    /**
     * 默认构造器
     */
    public PptImageAct(Stage stage, String pptName) {
        this(stage, pptName, false);
    }

    public PptImageAct(Stage stage, String pptName, boolean fullScreen) {
        this(stage, pptName, fullScreen, 0);
    }

    public PptImageAct(Stage stage, String pptName, boolean fullScreen, int pptNo) {
        super(stage);

        this.pptNo = pptNo;
        this.pptName = pptName;
        this.fullScreen = fullScreen;
        this.pptPath = FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/ppt/" + this.pptName);
        this.pptMap = new PPTMapDTO(this.pptPath);

        // 全屏设置
        if (this.fullScreen) {
            this.setNewStage(new Stage());
        } else {
            // 展示当前PPT
            this.showCurrentPpt();
        }
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseViewAct#initViewShown()
     */
    public final void initViewShown() {
        // 展示当前PPT
        this.showCurrentPpt();
    }

    /**
     * 获取所有PPT视图回调函数
     */
    public final List<PPTViewCallback> findCallbacks() {
        return this.callbacks;
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final BaseXmlAct initXmlViews() {
        // 按钮图标
        PPTUtils.initFirstButton(this.btnFirst);
        PPTUtils.initPreviousButton(this.btnPrevious);
        PPTUtils.initNextButton(this.btnNext);
        PPTUtils.initLastButton(this.btnLast);
        PPTUtils.initFullScreenButton(this.btnFullScreen);
        
        // 属性设置
        this.findGroupViewProperty().set(this.pptPane);
        this.findFullScreenProperty().set(true);
        this.findCenterNewStageProperty().set(false);
        this.findSizeProperty().set(new Dimension2D(1000, 705));
        this.findTitleProperty().set("系统课件");

        return this;
    }

    /** 
     * @see mplat.utils.PPTUtils.PPTViewCallback#showPpt(int)
     */
    public final void showPpt(int pptNo) {
        this.pptNo = pptNo;

        this.processButtons();
        this.showCurrentPpt();
    }

    /**
     * 展示PPT
     */
    public final void showCurrentPpt() {
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
        double height = HEIGHT;
        if (this.fullScreen && this.findNewStage().getScene() != null) {
            height = this.findNewStage().getScene().getHeight() - this.pptPane.getBottom().prefHeight(-1);
        }
        this.imgPpt.setFitHeight(height);
        this.imgPpt.setImage(new Image(file, 0, height, true, false));

        LogUtils.info("展示第[" + this.pptNo + "]张PPT[" + file + "].");

        // 回调展示PPT
        for (PPTViewCallback callback : this.findCallbacks()) {
            callback.showPpt(this.pptNo);
        }
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

    @FXML
    protected void onFirstAction(ActionEvent evt) {
        this.pptNo = 0;
        this.processButtons();

        this.showCurrentPpt();
    }

    @FXML
    protected void onPreviousAction(ActionEvent evt) {
        this.pptNo = Math.max(this.pptNo - 1, 0);
        this.processButtons();

        this.showCurrentPpt();
    }

    @FXML
    protected void onNextAction(ActionEvent evt) {
        this.pptNo += 1;
        this.processButtons();

        this.showCurrentPpt();
    }

    @FXML
    protected void onLastAction(ActionEvent evt) {
        this.pptNo = Math.max(this.pptMap.getFiles().size() - 1, 0);
        this.processButtons();

        this.showCurrentPpt();
    }

    @FXML
    protected void onFullScreenAction(ActionEvent evt) {
        if (this.fullScreen) {
            // 关闭全屏
            this.closeNewStage();
        } else {
            // 显示全屏
            final PptImageAct ppt = new PptImageAct(this.findStage(), this.pptName, true, this.pptNo);
            ppt.findCallbacks().add(this);
            ppt.show();

            ppt.findNewStage().fullScreenProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                    if (!ppt.findNewStage().isFullScreen()) {
                        ppt.closeNewStage();
                    }
                }
            });
        }
    }

}
