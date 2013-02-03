/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.UIBtnMsg;
import com.atom.core.uijfx.UIConfig;
import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.UITipMsg;
import com.atom.core.uijfx.event.EventAdapter;
import com.atom.core.uijfx.utils.StageUtils;

import mplat.uijfx.uiviews.MainViewController;
import mplat.uijfx.uiviews.SystemAboutController;
import mplat.uijfx.utils.Alert;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

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
        this.primaryStage = this.parentView.getPrimaryStage();

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

        EventAdapter adapter = new EventAdapter() {
            public void onSure(ActionEvent evt) {
                Platform.exit();
            }
        };

        Alert.alert(UIConfig.get().setSize(UISize.to(400, 300)).setTipMsg(UITipMsg.to("退出系统", "你确定要退出系统吗？")).setBtnMsg(UIBtnMsg.get().setSure("确定").setCancel("取消")).setAdapter(adapter));
    }

    @FXML
    private void onMenuAbout(ActionEvent evt) {
        try {
            StageUtils.findController(SystemAboutController.class).initViews(this.primaryStage);
        } catch (Exception e) {
            LogUtils.error("打开关于窗口异常！", e);
        }
    }

}
