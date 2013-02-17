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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.mgt.ExamMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.ExamInfoDTO;

import org.apache.commons.lang.StringUtils;

import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 增加试题控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ExamCreateAct.java, V1.0.1 2013-2-15 下午6:35:08 $
 */
public final class ExamCreateAct extends BaseXmlAct {

    /** 试题管理器 */
    private final ExamMgt    examMgt;

    @FXML
    private BorderPane       rootView;
    @FXML
    private TextArea         txtExamTitle;
    @FXML
    private ComboBox<String> cboxExamRgtNo;
    @FXML
    private TextArea         txtExamItemA;
    @FXML
    private TextArea         txtExamItemB;
    @FXML
    private TextArea         txtExamItemC;
    @FXML
    private TextArea         txtExamItemD;

    @FXML
    private Button           btnSure;
    @FXML
    private Button           btnCancel;

    /**
     * 默认构造器
     */
    public ExamCreateAct(Stage stage, Object rootAct) {
        super(stage);

        this.setRootAct(rootAct);
        this.examMgt = MgtFactory.get().findExamMgt();
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public BaseXmlAct initXmlViews() {
        // 属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(500.0, 350.0));
        this.findTitleProperty().set("增加新试题");
        this.findGroupViewProperty().set(this.rootView);
        this.findResizableProperty().set(true);

        this.cboxExamRgtNo.setItems(FXCollections.observableList(ExamMgt.findExamChoices()));

        // 事件
        List<TextArea> texts = Arrays.asList(this.txtExamTitle, this.txtExamItemA, this.txtExamItemB, this.txtExamItemC, this.txtExamItemD);
        for (TextArea text : texts) {
            text.textProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                    btnSure.setDisable(!isInputValid());
                }
            });
        }

        this.cboxExamRgtNo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
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
        ExamInfoDTO exam = this.fillInputExamInfoDTO();

        if (StringUtils.isBlank(exam.getTitle()) || StringUtils.isBlank(exam.getRgtNo())) {
            return false;
        }

        if (StringUtils.isBlank(exam.getItemA()) || StringUtils.isBlank(exam.getItemB())) {
            return false;
        }
        if (StringUtils.isBlank(exam.getItemC()) || StringUtils.isBlank(exam.getItemD())) {
            return false;
        }

        // 合法
        return true;
    }

    /**
     * 根据输入填充试题对象
     */
    private final ExamInfoDTO fillInputExamInfoDTO() {
        ExamInfoDTO exam = new ExamInfoDTO();
        exam.setTitle(this.txtExamTitle.getText());
        exam.setRgtNo(this.cboxExamRgtNo.getSelectionModel().getSelectedItem());
        exam.setItemA(this.txtExamItemA.getText());
        exam.setItemB(this.txtExamItemB.getText());
        exam.setItemC(this.txtExamItemC.getText());
        exam.setItemD(this.txtExamItemD.getText());

        return exam;
    }

    /**
     * 清空输入框
     */
    private final void clearInput() {
        String text = StringUtils.EMPTY;
        this.txtExamTitle.setText(text);
        this.cboxExamRgtNo.getSelectionModel().select(0);
        this.txtExamItemA.setText(text);
        this.txtExamItemB.setText(text);
        this.txtExamItemC.setText(text);
        this.txtExamItemD.setText(text);
    }

    @FXML
    private final void onSureAction(ActionEvent evt) {
        // 保存试题
        ExamInfoDTO exam = this.fillInputExamInfoDTO();
        this.examMgt.create(exam);

        // 更新表格
        ExamMgtAct act = this.findRootAct();
        act.refreshExams();

        // 清空
        this.clearInput();

        // 提示
        PopupUtils.success(this.findNewStage(), "增加新试题成功！");
    }

    @FXML
    private final void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }

}
