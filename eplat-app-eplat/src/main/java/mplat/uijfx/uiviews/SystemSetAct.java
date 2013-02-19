/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.mgt.GsetMgt;
import mplat.mgt.MgtFactory;
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
    private BorderPane       rootView;

    /* 按压深度范围 */

    @FXML
    private TextField        minPressDepth;
    @FXML
    private TextField        maxPressDepth;

    /* 吹入潮气量范围 */

    @FXML
    private TextField        minAirVolume;
    @FXML
    private TextField        maxAirVolume;

    /* 给予CPR 5个循环操作模式 */

    private ToggleGroup      cprOperateGrp     = new ToggleGroup();
    @FXML
    private RadioButton      cprOperateStandard;
    @FXML
    private RadioButton      cprOperateActual;

    /* 心肺复苏节拍提示音 */

    private ToggleGroup      operateRhythmGrp  = new ToggleGroup();
    @FXML
    private RadioButton      operateRhythmOn;
    @FXML
    private RadioButton      operateRhythmOff;

    /* 心肺复苏操作提示音 */

    private ToggleGroup      operateAudioGrp   = new ToggleGroup();
    @FXML
    private RadioButton      operateAudioOn;
    @FXML
    private RadioButton      operateAudioOff;

    /* CPR操作规则设置 */

    private ToggleGroup      saverModeGrp      = new ToggleGroup();
    @FXML
    private RadioButton      saverModeAmateur;
    @FXML
    private RadioButton      saverModePrefessional;

    private ToggleGroup      pressCountPrefGrp = new ToggleGroup();
    @FXML
    private RadioButton      pressCountPref3;
    @FXML
    private RadioButton      pressCountPref5;

    /* 虚拟监控器设置 */

    @FXML
    private ComboBox<String> monitorName;
    @FXML
    private CheckBox         joinHeartGraph;
    @FXML
    private CheckBox         joinBloodGraph;

    /* 虚拟体征日志设置 */

    @FXML
    private TextField        autoSaveSeconds;

    /** 系统参数管理器 */
    private GsetMgt          setMgt;

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

        // 组件
        this.cprOperateStandard.setToggleGroup(this.cprOperateGrp);
        this.cprOperateActual.setToggleGroup(this.cprOperateGrp);

        this.operateRhythmOn.setToggleGroup(this.operateRhythmGrp);
        this.operateRhythmOff.setToggleGroup(this.operateRhythmGrp);

        this.operateAudioOn.setToggleGroup(this.operateAudioGrp);
        this.operateAudioOff.setToggleGroup(this.operateAudioGrp);

        this.saverModeAmateur.setToggleGroup(this.saverModeGrp);
        this.saverModePrefessional.setToggleGroup(this.saverModeGrp);

        this.pressCountPref3.setToggleGroup(this.pressCountPrefGrp);
        this.pressCountPref5.setToggleGroup(this.pressCountPrefGrp);

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
