/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 系统信息信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemAboutAct.java, V1.0.1 2013-2-3 下午4:31:23 $
 */
public final class SystemAboutAct extends BaseXmlAct {

    @FXML
    private AnchorPane rootView;
    @FXML
    private ImageView  imgAbout;
    @FXML
    private Label      lblTitle;
    @FXML
    private Label      lblRelease;
    @FXML
    private Label      lblCategory;

    /**
     * 默认构造器
     */
    public SystemAboutAct(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseViewAct#initActViews()
     */
    public SystemAboutAct initXmlViews() {
        this.setNewStage(new Stage());

        // 属性
        this.findGroupViewProperty().set(this.rootView);
        this.findSizeProperty().set(new Dimension2D(460.0, 160.0));
        this.findTitleProperty().set("系统信息");

        return this;
    }

}
