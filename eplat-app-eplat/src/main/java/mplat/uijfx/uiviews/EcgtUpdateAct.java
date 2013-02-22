/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.mgt.EcgtMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.EcgtInfoDTO;

import org.apache.commons.lang.StringUtils;

import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 修改ECG心律识别试题
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtUpdateAct.java, V1.0.1 2013-2-22 下午10:48:42 $
 */
public final class EcgtUpdateAct extends BaseXmlAct {
    /** 试题管理器 */
    private final EcgtInfoDTO ecgt;
    private final EcgtMgt     ecgtMgt;

    @FXML
    private BorderPane        rootView;

    @FXML
    private Button            btnSure;
    @FXML
    private Button            btnCancel;

    @FXML
    private ComboBox<String>  cboxEcgtQrs;
    @FXML
    private ComboBox<String>  cboxEcgtRhythm;
    @FXML
    private ComboBox<String>  cboxEcgtSyst;
    @FXML
    private TextField         txtEcgtRate;

    @FXML
    private TextArea          txtTipRate;
    @FXML
    private TextArea          txtTipRegular;
    @FXML
    private TextArea          txtTipWave;
    @FXML
    private TextArea          txtTipInterval;
    @FXML
    private TextArea          txtTipQrs;

    /**
     * 默认构造器
     */
    public EcgtUpdateAct(Stage stage, Object rootAct, EcgtInfoDTO ecgt) {
        super(stage);

        this.setRootAct(rootAct);
        this.ecgt = ecgt;
        this.ecgtMgt = MgtFactory.get().findEcgtMgt();
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final BaseXmlAct initXmlViews() {
        // 属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(500.0, 350.0));
        this.findTitleProperty().set("修改ECG心律识别试题");
        this.findGroupViewProperty().set(this.rootView);
        this.findResizableProperty().set(true);

        this.cboxEcgtQrs.setItems(FXCollections.observableList(EcgtMgt.findEcgtQRS()));
        this.cboxEcgtRhythm.setItems(FXCollections.observableList(EcgtMgt.findEcgtRhythm()));
        this.cboxEcgtSyst.setItems(FXCollections.observableList(EcgtMgt.findEcgtExtSyst()));

        // 事件
        List<ComboBox<String>> cboxs = Arrays.asList(this.cboxEcgtQrs, this.cboxEcgtRhythm, this.cboxEcgtSyst);
        for (ComboBox<String> cbox : cboxs) {
            cbox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                    btnSure.setDisable(!isInputValid());
                }
            });
        }

        this.txtEcgtRate.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                btnSure.setDisable(!isInputValid());
            }
        });

        return this;
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseViewAct#initViewShown()
     */
    public final void initViewShown() {
        super.initViewShown();

        this.cboxEcgtQrs.getSelectionModel().select(this.ecgt.getEcgtQrs());
        this.cboxEcgtRhythm.getSelectionModel().select(this.ecgt.getEcgtRhythm());
        this.cboxEcgtSyst.getSelectionModel().select(this.ecgt.getEcgtSyst());

        this.txtEcgtRate.setText(this.ecgt.getEcgtRate());

        this.txtTipRate.setText(this.ecgt.getTipRate());
        this.txtTipRegular.setText(this.ecgt.getTipRegular());
        this.txtTipWave.setText(this.ecgt.getTipWave());
        this.txtTipInterval.setText(this.ecgt.getTipInterval());
        this.txtTipQrs.setText(this.ecgt.getTipQrs());
    }

    /**
     * 检查输入是否合法
     */
    private final boolean isInputValid() {
        EcgtInfoDTO ecgt = this.fillInputEcgtInfoDTO(null);

        if (StringUtils.isBlank(ecgt.getEcgtQrs()) || StringUtils.isBlank(ecgt.getEcgtRhythm())) {
            return false;
        }

        if (StringUtils.isBlank(ecgt.getEcgtSyst()) || StringUtils.isBlank(ecgt.getEcgtRate())) {
            return false;
        }

        // 合法
        return true;
    }

    /**
     * 根据输入填充试题对象
     */
    private final EcgtInfoDTO fillInputEcgtInfoDTO(EcgtInfoDTO ecgt) {
        if (ecgt == null) {
            ecgt = new EcgtInfoDTO();
        }

        ecgt.setEcgtQrs(this.cboxEcgtQrs.getSelectionModel().getSelectedItem());
        ecgt.setEcgtRhythm(this.cboxEcgtRhythm.getSelectionModel().getSelectedItem());
        ecgt.setEcgtSyst(this.cboxEcgtSyst.getSelectionModel().getSelectedItem());
        ecgt.setEcgtRate(this.txtEcgtRate.getText());

        ecgt.setTipQrs(this.txtTipQrs.getText());
        ecgt.setTipRate(this.txtTipRate.getText());
        ecgt.setTipRegular(this.txtTipRegular.getText());
        ecgt.setTipWave(this.txtTipWave.getText());
        ecgt.setTipInterval(this.txtTipInterval.getText());

        return ecgt;
    }

    /**
     * 事件-确定按钮
     */
    @FXML
    private final void onSureAction(ActionEvent evt) {
        // 保存试题
        this.fillInputEcgtInfoDTO(this.ecgt);
        this.ecgtMgt.update(this.ecgt);

        // 更新表格
        EcgtMgtAct act = this.findRootAct();
        act.refreshEcgts();

        // 提示
        PopupUtils.success(this.findNewStage(), "[更新]-ECG心律识别试题(" + ecgt.getEcgtRhythm() + ")信息修改成功！");
    }

    /**
     * 事件-取消按钮
     */
    @FXML
    private final void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }

}
