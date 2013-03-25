/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

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

import com.atom.core.uijfx.popup.PopupBuilder;
import com.atom.core.uijfx.popup.PopupConst;
import com.atom.core.uijfx.popup.PopupEvent;
import com.atom.core.uijfx.utils.StageUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 修改试题控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ExamUpdateAct.java, V1.0.1 2013-2-15 下午7:55:40 $
 */
public final class ExamUpdateAct extends BaseXmlAct {
    /** 试题管理器 */
    private final ExamInfoDTO exam;
    private final ExamMgt     examMgt;

    @FXML
    private BorderPane        rootView;
    @FXML
    private TextArea          txtExamTitle;
    @FXML
    private ComboBox<String>  cboxExamRgtNo;
    @FXML
    private TextArea          txtExamItemA;
    @FXML
    private TextArea          txtExamItemB;
    @FXML
    private TextArea          txtExamItemC;
    @FXML
    private TextArea          txtExamItemD;

    @FXML
    private Button            btnSure;
    @FXML
    private Button            btnCancel;

    /**
     * 默认构造器
     */
    public ExamUpdateAct(Stage stage, Object rootAct, ExamInfoDTO exam) {
        super(stage);

        this.setRootAct(rootAct);
        this.exam = exam;
        this.examMgt = MgtFactory.get().findExamMgt();
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public BaseXmlAct initXmlViews() {
        // 属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(500.0, 350.0));
        this.findTitleProperty().set("修改试题信息");
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

        return this;
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseViewAct#initViewShown()
     */
    public final void initViewShown() {
        super.initViewShown();

        this.txtExamTitle.setText(this.exam.getTitle());
        this.cboxExamRgtNo.getSelectionModel().select(this.exam.getRgtNo());
        this.txtExamItemA.setText(this.exam.getItemA());
        this.txtExamItemB.setText(this.exam.getItemB());
        this.txtExamItemC.setText(this.exam.getItemC());
        this.txtExamItemD.setText(this.exam.getItemD());
    }

    /**
     * 检查输入是否合法
     */
    private final boolean isInputValid() {
        // 填充信息
        ExamInfoDTO exam = this.fillInputExamInfoDTO(null);

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
    private final ExamInfoDTO fillInputExamInfoDTO(ExamInfoDTO exam) {
        if (exam == null) {
            exam = new ExamInfoDTO();
        }

        exam.setTitle(this.txtExamTitle.getText());
        exam.setRgtNo(this.cboxExamRgtNo.getSelectionModel().getSelectedItem());
        exam.setItemA(this.txtExamItemA.getText());
        exam.setItemB(this.txtExamItemB.getText());
        exam.setItemC(this.txtExamItemC.getText());
        exam.setItemD(this.txtExamItemD.getText());

        return exam;
    }

    @FXML
    private final void onSureAction(ActionEvent evt) {
        // 保存试题
        this.fillInputExamInfoDTO(this.exam);
        this.examMgt.update(this.exam);

        // 弹出提示框
        PopupBuilder builder = PopupBuilder.create(this.findNewStage());
        builder.modal(true).imageValue(PopupConst.IMG_SUCCESS).buttons(PopupConst.BTN_SURE_VALUE);
        builder.title("操作成功").despMsg("[更新]-试题(" + this.exam.getTitle() + ")信息修改成功！");
        builder.callback(new PopupEvent() {
            public void callback(Stage stage, Stage newStage, int btnValue) {
                StageUtils.close(newStage);
                StageUtils.close(stage);

                // 更新表格
                ExamMgtAct act = findRootAct();
                act.refreshExams();
            }
        });

        builder.build().show();
    }

    @FXML
    private final void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }

}
