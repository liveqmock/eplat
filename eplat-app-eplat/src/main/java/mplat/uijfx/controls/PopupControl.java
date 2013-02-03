/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mplat.uijfx.images.IMGS;

import com.atom.core.lang.utils.LogUtils;

/**
 * 弹出窗口
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupControl.java, V1.0.1 2013-2-3 下午10:07:26 $
 */
public class PopupControl {

    /** 根视图 */
    private Stage      primaryStage;
    private Stage      newStage;

    @FXML
    private BorderPane rootView;
    @FXML
    private HBox       hboxTitle;
    @FXML
    private Label      lblTitle;
    @FXML
    private HBox       hboxContent;
    @FXML
    private Text       textContent;
    @FXML
    private HBox       hboxButtons;

    /**
     * 初始化
     */
    public PopupControl(Stage primaryStage) {
        this.primaryStage = primaryStage;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PopupControl.fxml"));
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            LogUtils.error("初始化Popup组件异常!", e);
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
     * 设置图片信息
     */
    public PopupControl setIcon(Image img) {
        this.lblTitle.setGraphic(new ImageView(img));
        return this;
    }

    /**
     * 展示视图
     */
    public void show() {
        this.newStage = new Stage();
        this.newStage.initOwner(this.primaryStage);
        this.newStage.initModality(Modality.APPLICATION_MODAL);

        this.newStage.getIcons().addAll(IMGS.findIconImages());
        this.newStage.setTitle("系统信息");
        this.newStage.setScene(new Scene(this.rootView));
        this.newStage.sizeToScene();
        this.newStage.setResizable(false);
        this.newStage.show();
    }
    
}
