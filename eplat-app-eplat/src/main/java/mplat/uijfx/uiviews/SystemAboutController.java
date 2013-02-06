/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.utils.IconsHolder;

/**
 * 系统信息信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemAboutController.java, V1.0.1 2013-2-3 下午4:31:23 $
 */
public class SystemAboutController {
    private static final UISize SIZE = UISize.to(460, 160);

    /** 主场景 */
    private Stage               primaryStage;

    @FXML
    private AnchorPane          viewRoot;
    @FXML
    private ImageView           imgAbout;
    @FXML
    private Label               lblTitle;
    @FXML
    private Label               lblRelease;
    @FXML
    private Label               lblCategory;

    /** 弹出框场景 */
    private Stage               newStage;

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
    }

    /**
     * 页面初始化
     */
    public boolean initViews(Stage stage) {
        this.primaryStage = stage;

        this.newStage = new Stage();
        this.newStage.initOwner(this.primaryStage);
        this.newStage.initModality(Modality.APPLICATION_MODAL);
        
        this.newStage.getIcons().addAll(IconsHolder.getIconImages());
        this.newStage.setTitle("系统信息");
        this.newStage.setScene(new Scene(this.viewRoot, SIZE.getWidth(), SIZE.getHeight()));
        // this.stage.sizeToScene();
        this.newStage.setResizable(false);
        this.newStage.show();

        return true;
    }

}
