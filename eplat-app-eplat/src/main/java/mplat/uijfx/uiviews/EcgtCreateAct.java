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
 * 新建ECG心律识别
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtCreateAct.java, V1.0.1 2013-2-22 下午9:25:27 $
 */
public final class EcgtCreateAct extends BaseXmlAct {
    /** 试题管理器 */
    private final EcgtMgt    ecgtMgt;

    @FXML
    private BorderPane       rootView;

    @FXML
    private Button           btnSure;
    @FXML
    private Button           btnCancel;

    @FXML
    private ComboBox<String> cboxEcgtQrs;
    @FXML
    private ComboBox<String> cboxEcgtRhythm;
    @FXML
    private ComboBox<String> cboxEcgtSyst;
    @FXML
    private TextField        txtEcgtRate;

    @FXML
    private TextArea         txtTipRate;
    @FXML
    private TextArea         txtTipRegular;
    @FXML
    private TextArea         txtTipWave;
    @FXML
    private TextArea         txtTipInterval;
    @FXML
    private TextArea         txtTipQrs;

    /**
     * 默认构造器
     */
    public EcgtCreateAct(Stage stage, Object rootAct) {
        super(stage);

        this.setRootAct(rootAct);
        this.ecgtMgt = MgtFactory.get().findEcgtMgt();
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final BaseXmlAct initXmlViews() {
        // 属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(500.0, 350.0));
        this.findTitleProperty().set("增加ECG心律识别试题");
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

        // 清空
        this.clearInput();

        return this;
    }

    /**
     * 检查输入是否合法
     */
    private final boolean isInputValid() {
        EcgtInfoDTO ecgt = this.fillInputEcgtInfoDTO();

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
    private final EcgtInfoDTO fillInputEcgtInfoDTO() {
        EcgtInfoDTO ecgt = new EcgtInfoDTO();
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
     * 清空输入框
     */
    private final void clearInput() {
        String text = StringUtils.EMPTY;
        this.cboxEcgtQrs.getSelectionModel().select(0);
        this.cboxEcgtRhythm.getSelectionModel().select(0);
        this.cboxEcgtSyst.getSelectionModel().select(0);

        this.txtEcgtRate.setText(text);

        this.txtTipRate.setText(text);
        this.txtTipRegular.setText(text);
        this.txtTipWave.setText(text);
        this.txtTipInterval.setText(text);
        this.txtTipQrs.setText(text);
    }

    /**
     * 事件-确定按钮
     */
    @FXML
    private final void onSureAction(ActionEvent evt) {
        // 保存试题
        EcgtInfoDTO ecgt = this.fillInputEcgtInfoDTO();
        this.ecgtMgt.create(ecgt);

        // 更新表格
        EcgtMgtAct act = this.findRootAct();
        act.refreshEcgts();

        // 清空
        this.clearInput();

        // 提示
        PopupUtils.success(this.findNewStage(), "增加ECG心律识别试题成功！");
    }

    /**
     * 事件-取消按钮
     */
    @FXML
    private final void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }

}
