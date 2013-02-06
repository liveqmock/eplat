/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import mplat.uijfx.uiviews.MainViewController;
import mplat.uijfx.uiviews.SystemAboutController;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.utils.StageUtils;

/**
 * 菜单组件
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopMenuControl.java, V1.0.1 2013-2-3 下午7:07:22 $
 */
public class TopMenuControl extends MenuBar {

    /** 根组件 */
    private final MainViewController parentView;
    private Stage                    primaryStage;

    /**
     * 初始化
     */
    public TopMenuControl(MainViewController parentView) {
        this.parentView = parentView;
        this.primaryStage = this.parentView.findStage();

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

        this.initViews();
    }

    /**
     * 初始化
     */
    private void initViews() {
    }

    /**
     * 退出系统
     */
    @FXML
    private void onMenuExit(ActionEvent evt) {
        LogUtils.warn("退出系统~~~");
        PopupUtils.exitSystem(this.primaryStage);
    }

    @FXML
    private void onMenuAbout(ActionEvent evt) {
        StageUtils.findController(SystemAboutController.class).initViews(this.primaryStage);
    }

}
