/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mplat.mgt.UserMgt;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.uiviews.ExamMgtAct;
import mplat.uijfx.uiviews.MainViewAct;
import mplat.uijfx.uiviews.SystemAboutAct;
import mplat.uijfx.uiviews.UserMgtAct;
import mplat.utils.UConst;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.popup.PopupUtils;

/**
 * 菜单组件
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopMenuControl.java, V1.0.1 2013-2-3 下午7:07:22 $
 */
public class TopMenuControl extends MenuBar {

    /** 根组件 */
    private final MainViewAct rootAct;
    private final Stage       stage;

    /** 系统管理 */
    @FXML
    private Menu              menuSystemMgt;

    /**
     * 初始化
     */
    public TopMenuControl(MainViewAct rootAct) {
        this.rootAct = rootAct;
        this.stage = this.rootAct.findStage();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TopMenuControl.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            LogUtils.error("初始化TopMenu组件异常!", e);
            throw new RuntimeException(e);
        }

        // 其它初始化
        this.initViews();
    }

    /**
     * 初始化
     */
    private void initViews() {
        // 管理员菜单
        if (!UserMgt.isSystemAdmin()) {
            this.menuSystemMgt.setVisible(false);
        }

        this.menuSystemMgt.setGraphic(new ImageView(this.findSystemMgt()));
    }

    /**
     * 退出系统
     */
    @FXML
    public final void onMenuExit(ActionEvent evt) {
        PopupUtils.exitSystem(this.stage);
    }

    /**
     * 系统管理-用户管理
     */
    @FXML
    public final void onMenuUserMgt(ActionEvent evt) {
        new UserMgtAct(this.stage).show();
    }

    /**
     * 系统管理-试题管理
     */
    @FXML
    public final void onMenuExamMgt(ActionEvent evt) {
        new ExamMgtAct(this.stage).show();
    }

    /**
     * 关于系统
     */
    @FXML
    public final void onMenuAbout(ActionEvent evt) {
        new SystemAboutAct(this.stage).show();
    }

    /**
     * 帮助手册
     */
    @FXML
    public final void onMenuManual(ActionEvent evt) {
        UConst.doHelpAction();
    }

    /**
     * 菜单图片：系统管理
     */
    private final Image findSystemMgt() {
        return new Image(IMGS.class.getResourceAsStream("icon-system-mgt.png"), 14, 14, false, false);
    }

}
