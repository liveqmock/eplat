/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.mgt.GsetMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.GsetInfoDTO;
import mplat.utils.UConst;

import org.apache.commons.lang.math.NumberUtils;

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

    /* 操作按钮 */
    @FXML
    private Button           btnSure;
    @FXML
    private Button           btnCancel;
    @FXML
    private Button           btnApply;
    @FXML
    private Button           btnHelp;

    /* 按压深度范围 */

    @FXML
    private TextField        txtMinPressDepth;
    @FXML
    private TextField        txtMaxPressDepth;

    /* 吹入潮气量范围 */

    @FXML
    private TextField        txtMinAirVolume;
    @FXML
    private TextField        txtMaxAirVolume;

    /* 给予CPR 5个循环操作模式 */

    private ToggleGroup      rbtCprOperateGrp     = new ToggleGroup();
    @FXML
    private RadioButton      rbtCprOperateStandard;
    @FXML
    private RadioButton      rbtCprOperateActual;

    /* 心肺复苏节拍提示音 */

    private ToggleGroup      rbtOperateRhythmGrp  = new ToggleGroup();
    @FXML
    private RadioButton      rbtOperateRhythmOn;
    @FXML
    private RadioButton      rbtOperateRhythmOff;

    /* 心肺复苏操作提示音 */

    private ToggleGroup      rbtOperateAudioGrp   = new ToggleGroup();
    @FXML
    private RadioButton      rbtOperateAudioOn;
    @FXML
    private RadioButton      rbtOperateAudioOff;

    /* CPR操作规则设置 */

    private ToggleGroup      rbtSaverModeGrp      = new ToggleGroup();
    @FXML
    private RadioButton      rbtSaverModeAmateur;
    @FXML
    private RadioButton      rbtSaverModePrefessional;

    @FXML
    private TextField        txtPressCountAmateur;

    private ToggleGroup      rbtPressCountPrefGrp = new ToggleGroup();
    @FXML
    private RadioButton      rbtPressCountPref3;
    @FXML
    private RadioButton      rbtPressCountPref5;

    /* 虚拟监控器设置 */

    @FXML
    private ComboBox<String> cboxMonitorName;
    @FXML
    private CheckBox         chkJoinHeartGraph;
    @FXML
    private CheckBox         chkJoinBloodGraph;

    /* 虚拟体征日志设置 */

    @FXML
    private TextField        txtAutoSaveSeconds;

    /** 系统参数管理器 */
    private final GsetMgt    setMgt;

    /**
     * 默认构造函数
     */
    public SystemSetAct(Stage stage) {
        super(stage);
        this.setMgt = MgtFactory.get().findGsetMgt();
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
        this.rbtCprOperateStandard.setToggleGroup(this.rbtCprOperateGrp);
        this.rbtCprOperateActual.setToggleGroup(this.rbtCprOperateGrp);

        this.rbtOperateRhythmOn.setToggleGroup(this.rbtOperateRhythmGrp);
        this.rbtOperateRhythmOff.setToggleGroup(this.rbtOperateRhythmGrp);

        this.rbtOperateAudioOn.setToggleGroup(this.rbtOperateAudioGrp);
        this.rbtOperateAudioOff.setToggleGroup(this.rbtOperateAudioGrp);

        this.rbtSaverModeAmateur.setToggleGroup(this.rbtSaverModeGrp);
        this.rbtSaverModePrefessional.setToggleGroup(this.rbtSaverModeGrp);

        this.rbtPressCountPref3.setToggleGroup(this.rbtPressCountPrefGrp);
        this.rbtPressCountPref5.setToggleGroup(this.rbtPressCountPrefGrp);

        // 约束
        List<TextField> txts = Arrays.asList(this.txtMinPressDepth, this.txtMaxPressDepth, this.txtMinAirVolume, this.txtMaxAirVolume, this.txtPressCountAmateur, this.txtAutoSaveSeconds);
        for (TextField txt : txts) {
            txt.textProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                    onInputValueChanged();
                }
            });
        }

        List<RadioButton[]> rbtPairs = new ArrayList<RadioButton[]>();
        rbtPairs.add(new RadioButton[] { this.rbtCprOperateStandard, this.rbtCprOperateActual });
        rbtPairs.add(new RadioButton[] { this.rbtOperateRhythmOn, this.rbtOperateRhythmOff });
        rbtPairs.add(new RadioButton[] { this.rbtOperateAudioOn, this.rbtOperateAudioOff });
        rbtPairs.add(new RadioButton[] { this.rbtSaverModeAmateur, this.rbtSaverModePrefessional });
        rbtPairs.add(new RadioButton[] { this.rbtPressCountPref3, this.rbtPressCountPref5 });
        for (final RadioButton[] rbtPair : rbtPairs) {
            rbtPair[0].selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> rbt, Boolean oldValue, Boolean newValue) {
                    rbtPair[1].setSelected(!newValue);
                }
            });

            rbtPair[1].selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> rbt, Boolean oldValue, Boolean newValue) {
                    rbtPair[0].setSelected(!newValue);
                }
            });
        }

        return this;
    }

    /**
     * 初始化各个组件值
     */
    public final void initViewShown() {
        GsetInfoDTO set = this.setMgt.getGSet();

        this.txtMinPressDepth.setText(Integer.toString(set.getMinPressDepth()));
        this.txtMaxPressDepth.setText(Integer.toString(set.getMaxPressDepth()));

        this.txtMinAirVolume.setText(Integer.toString(set.getMinAirVolume()));
        this.txtMaxAirVolume.setText(Integer.toString(set.getMaxAirVolume()));

        if (set.getCprOperateMode() == 1) {
            this.rbtCprOperateStandard.setSelected(true);
        } else {
            this.rbtCprOperateActual.setSelected(true);
        }

        if (set.isOperateRhythm()) {
            this.rbtOperateRhythmOn.setSelected(true);
        } else {
            this.rbtOperateRhythmOff.setSelected(true);
        }

        if (set.isOperateAudio()) {
            this.rbtOperateAudioOn.setSelected(true);
        } else {
            this.rbtOperateAudioOff.setSelected(true);
        }

        if (set.getSaverMode() == 1) {
            this.rbtSaverModeAmateur.setSelected(true);
        } else {
            this.rbtSaverModePrefessional.setSelected(true);
        }
        this.txtPressCountAmateur.setText(Integer.toString(set.getPressCountAmateur()));
        if (set.getPressCountCycle() == 3) {
            this.rbtPressCountPref3.setSelected(true);
        } else {
            this.rbtPressCountPref5.setSelected(true);
        }

        this.chkJoinHeartGraph.setSelected(set.isJoinHeartGraph());
        this.chkJoinBloodGraph.setSelected(set.isJoinBloodGraph());

        this.txtAutoSaveSeconds.setText(Integer.toString(set.getAutoSaveSeconds()));
    }

    /**
     * 检查用户输入是否有效
     */
    private final void onInputValueChanged() {
        // 按压深度
        int min = NumberUtils.toInt(this.txtMinPressDepth.getText(), 0);
        int max = NumberUtils.toInt(this.txtMaxPressDepth.getText(), 0);
        if (min <= 0 || max <= 0 || min > max) {
            this.btnSure.setDisable(true);
            return;
        }

        // 吹入气量
        min = NumberUtils.toInt(this.txtMinAirVolume.getText(), 0);
        max = NumberUtils.toInt(this.txtMaxAirVolume.getText(), 0);
        if (min <= 0 || max <= 0 || min > max) {
            this.btnSure.setDisable(true);
            return;
        }

        // 虚拟体征
        int value = NumberUtils.toInt(this.txtAutoSaveSeconds.getText(), 0);
        if (value <= 0) {
            this.btnSure.setDisable(true);
            return;
        }

        // 输入合法
        this.btnSure.setDisable(false);
    }

    /**
     * 填充系统参数对象
     */
    private final GsetInfoDTO fillSystemSetInfo(GsetInfoDTO set) {
        if (set == null) {
            set = new GsetInfoDTO();
        }

        set.setMinPressDepth(Integer.parseInt(this.txtMinPressDepth.getText()));
        set.setMaxPressDepth(Integer.parseInt(this.txtMaxPressDepth.getText()));

        set.setMinAirVolume(Integer.parseInt(this.txtMinAirVolume.getText()));
        set.setMaxAirVolume(Integer.parseInt(this.txtMaxAirVolume.getText()));

        if (this.rbtCprOperateStandard.isSelected()) {
            // 标准
            set.setCprOperateMode(1);
        } else {
            // 实战
            set.setCprOperateMode(2);
        }

        // 操作节拍
        set.setOperateRhythm(this.rbtOperateRhythmOn.isSelected());
        // 操作提示
        set.setOperateAudio(this.rbtOperateAudioOn.isSelected());

        // 施救者
        if (this.rbtSaverModeAmateur.isSelected()) {
            set.setSaverMode(1);
        } else {
            set.setSaverMode(2);
        }
        set.setPressCountAmateur(NumberUtils.toInt(this.txtPressCountAmateur.getText(), 0));
        if (this.rbtPressCountPref3.isSelected()) {
            set.setPressCountCycle(3);
        } else {
            set.setPressCountCycle(5);
        }

        // 监视器
        set.setMonitorName(this.cboxMonitorName.getValue());

        // 心电图导联
        set.setJoinHeartGraph(this.chkJoinHeartGraph.isSelected());
        // 血氧探头
        set.setJoinBloodGraph(this.chkJoinBloodGraph.isSelected());

        // 自动保存
        set.setAutoSaveSeconds(Integer.parseInt(this.txtAutoSaveSeconds.getText()));

        return set;
    }

    /**
     * ‘确定’按钮
     */
    @FXML
    public final void onSureAction(ActionEvent evt) {
        // 填充
        GsetInfoDTO set = this.setMgt.getGSet();
        this.fillSystemSetInfo(set);

        // 保存
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
        this.fillSystemSetInfo(this.setMgt.getGSet());
    }

    /**
     * ‘帮助’按钮
     */
    @FXML
    public final void onHelpAction(ActionEvent evt) {
        UConst.doHelpAction();
    }

}
