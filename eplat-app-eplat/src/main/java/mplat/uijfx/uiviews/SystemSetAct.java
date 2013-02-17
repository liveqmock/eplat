/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.mgt.MgtFactory;
import mplat.mgt.GsetMgt;
import mplat.utils.UConst;

import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 系统配置参数设置
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemSetAct.java, V1.0.1 2013-2-8 下午9:10:52 $
 */
public final class SystemSetAct extends BaseXmlAct {

    @FXML
    private BorderPane rootView;

    /** 系统参数管理器 */
    private GsetMgt    setMgt;

    /**
     * 默认构造函数
     */
    public SystemSetAct(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final BaseXmlAct initXmlViews() {
        this.setNewStage(new Stage());

        // 属性
        this.findGroupViewProperty().set(this.rootView);
        this.findSizeProperty().set(new Dimension2D(500.0, 400.0));
        this.findTitleProperty().set("系统参数设置");

        // 设置初始化值
        this.setMgt = MgtFactory.get().findGsetMgt();
        // SystemSetDTO setting = this.setMgt.getSetting();
        // TODO: 设置初始化值

        return this;
    }

    /**
     * ‘确定’按钮
     */
    @FXML
    public final void onSureAction(ActionEvent evt) {
        this.setMgt.persist();

        PopupUtils.success(this.findNewStage(), "系统参数保存成功！");
    }

    /**
     * ‘取消’按钮
     */
    @FXML
    public final void onCancelAction(ActionEvent evt) {
        // 重置参数内容
        this.setMgt.reset();

        this.closeNewStage();
    }

    /**
     * ‘应用’按钮
     */
    @FXML
    public final void onApplyAction(ActionEvent evt) {
        System.out.println("应用");
        // TODO:填充参数值
        // SystemSetDTO setting = this.setMgt.getSetting();
    }

    /**
     * ‘帮助’按钮
     */
    @FXML
    public final void onHelpAction(ActionEvent evt) {
        UConst.doHelpAction();
    }

}
