/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import mplat.mgt.dto.PPTMapDTO;

import org.apache.commons.io.FilenameUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;

/**
 * PPT浏览器
 * 
 * @author obullxl@gmail.com
 * @version $Id: BasePptView.java, V1.0.1 2013-2-2 上午10:14:31 $
 */
public abstract class BasePptView<T> extends BaseView<T, Parent> {
    /** PPT高度 */
    private static final double HEIGHT     = 630.0;

    /** 第几张PPT */
    private int                 pptNo;

    /** PPT名称 */
    private final String        pptName;

    /** PPT路径 */
    private final String        pptPath;

    /** PPT映射信息 */
    private final PPTMapDTO     pptMap;

    /** PPT播放器 */
    @FXML
    protected BorderPane        pptPane;

    @FXML
    protected ImageView         imgPpt;

    @FXML
    protected Button            btnFirst;
    @FXML
    protected Button            btnPrevious;
    @FXML
    protected Button            btnNext;
    @FXML
    protected Button            btnLast;
    @FXML
    protected Button            btnFullScreen;

    /**
     * CTOR
     */
    public BasePptView(T rootView, String pptName) {
        this(rootView, pptName, 0);
    }

    /**
     * CTOR
     */
    public BasePptView(T rootView, String pptName, int pptNo) {
        super(rootView);

        this.pptNo = pptNo;
        this.pptName = pptName;
        this.pptPath = FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/ppt/" + this.pptName);
        this.pptMap = new PPTMapDTO(this.pptPath);

        // this.pptPane = this.initBorderPane();
        this.initPPTView();
        this.showPpt();
    }

    /**
     * 初始化BorderPane
     */
    private void initPPTView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BasePptView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            String msg = "初始化PPT组件异常!";
            LogUtils.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    /** 
     * @see mplat.uijfx.uiviews.views.BaseView#findView()
     */
    public BorderPane findView() {
        return this.pptPane;
    }

    /**
     * 改变当前PPT
     */
    public void chargePptNo(int pptNo) {
        this.pptNo = pptNo;
        
        this.processButtons();
        this.showPpt();
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
        this.imgPpt.setImage(new Image(file, 0, HEIGHT, true, false));
        LogUtils.info("展示第[" + this.pptNo + "]张PPT[" + file + "].");
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

        this.showPpt();
    }

    @FXML
    protected void onPreviousAction(ActionEvent evt) {
        this.pptNo = Math.max(this.pptNo - 1, 0);
        this.processButtons();

        this.showPpt();
    }

    @FXML
    protected void onNextAction(ActionEvent evt) {
        this.pptNo += 1;
        this.processButtons();

        this.showPpt();
    }

    @FXML
    protected void onLastAction(ActionEvent evt) {
        this.pptNo = Math.max(this.pptMap.getFiles().size() - 1, 0);
        this.processButtons();

        this.showPpt();
    }

    @FXML
    protected void onFullScreenAction(ActionEvent evt) {
        new PptFullScreenView(this, this.pptName, this.pptNo);
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public int getPptNo() {
        return pptNo;
    }

    public Button getBtnFirst() {
        return btnFirst;
    }

    public Button getBtnPrevious() {
        return btnPrevious;
    }

    public Button getBtnNext() {
        return btnNext;
    }

    public Button getBtnLast() {
        return btnLast;
    }

    public Button getBtnFullScreen() {
        return btnFullScreen;
    }

}
