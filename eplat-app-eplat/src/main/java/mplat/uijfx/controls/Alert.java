/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mplat.uijfx.images.IMGS;

import com.atom.core.lang.utils.LogUtils;

/**
 * Alert弹出框
 * 
 * @author obullxl@gmail.com
 * @version $Id: Alert.java, V1.0.1 2013-2-3 下午10:28:54 $
 */
public class Alert {

    /** 根视图 */
    private Stage     primaryStage;
    private Stage     newStage;

    @FXML
    private GridPane  rootView;
    @FXML
    private ImageView icon;
    @FXML
    private Label     lblMsg;
    @FXML
    private Label     lblContent;
    @FXML
    private HBox      hboxButtons;

    /**
     * 初始化
     */
    public Alert(Stage primaryStage) {
        this.primaryStage = primaryStage;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Alert.fxml"));
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            LogUtils.error("初始化Alert组件异常!", e);
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
     * 图片信息
     */
    public Alert setIconImage(List<Image> imgs) {
        this.newStage.getIcons().setAll(imgs);
        return this;
    }

    /**
     * 设置图片信息
     */
    public Alert setIcon(Image img) {
        this.icon.setImage(IMGS.findSuccessIcon(0.0));
        return this;
    }

    /**
     * 设置提示信息标题
     */
    public Alert setMsg(String msg) {
        this.lblMsg.setText(msg);
        return this;
    }

    /**
     * 设置提示信息内容
     */
    public Alert setContent(String content) {
        this.lblContent.setText(content);
        return this;
    }

    /**
     * 展示视图
     */
    public void show() {
        this.newStage = new Stage();
        this.newStage.initOwner(this.primaryStage);
        this.newStage.initModality(Modality.APPLICATION_MODAL);

        // this.newStage.getIcons().addAll(IMGS.findIconImages());
        this.newStage.setTitle("提示信息信息");
        this.newStage.setScene(new Scene(this.rootView));
        this.newStage.sizeToScene();
        this.newStage.setResizable(false);
        this.newStage.show();
    }

}
