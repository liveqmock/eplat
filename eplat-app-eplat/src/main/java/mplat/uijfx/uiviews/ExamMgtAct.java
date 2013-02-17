/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mplat.mgt.ExamMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.ExamInfoDTO;
import mplat.uijfx.uiviews.beans.ExamWO;

import com.atom.core.uijfx.popup.PopupConst;
import com.atom.core.uijfx.popup.PopupEvent;
import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 试题管理控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ExamMgtAct.java, V1.0.1 2013-2-15 下午5:30:02 $
 */
public final class ExamMgtAct extends BaseXmlAct {

    @FXML
    private BorderPane                  viewRoot;
    @FXML
    private Button                      btnCreateExam;
    @FXML
    private Button                      btnUpdateExam;
    @FXML
    private Button                      btnRemoveExam;
    @FXML
    private Button                      btnUnselectExam;
    @FXML
    private TableView<ExamWO>           tableView;
    @FXML
    private TableColumn<ExamWO, Long>   tcolExamID;
    @FXML
    private TableColumn<ExamWO, String> tcolExamRgtNo;
    @FXML
    private TableColumn<ExamWO, String> tcolExamTitle;

    private ExamMgt                     examMgt;

    /**
     * 默认构造器
     */
    public ExamMgtAct(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final BaseXmlAct initXmlViews() {
        // 试题管理
        this.examMgt = MgtFactory.get().findExamMgt();

        // 舞台属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(650.0, 450.0));
        this.findTitleProperty().set("培训试题管理");
        this.findGroupViewProperty().set(this.viewRoot);
        this.findResizableProperty().set(true);

        // 组件属性
        this.tableView.setPrefWidth(this.findSizeProperty().get().getWidth());
        this.tcolExamID.setPrefWidth(30);
        this.tcolExamRgtNo.setPrefWidth(30);
        this.tcolExamRgtNo.setSortable(false);
        this.tcolExamTitle.setPrefWidth(this.tableView.getPrefWidth() - 80);

        // 事件
        this.tableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<ExamWO>() {
            public void onChanged(ListChangeListener.Change<? extends ExamWO> change) {
                List<ExamWO> exams = tableView.getSelectionModel().getSelectedItems();
                if (exams.isEmpty()) {
                    // 没有选择
                    btnRemoveExam.setDisable(true);
                    btnUpdateExam.setDisable(true);
                    btnUnselectExam.setDisable(true);
                } else {
                    // 选择试题
                    btnRemoveExam.setDisable(false);
                    btnUpdateExam.setDisable(false);
                    btnUnselectExam.setDisable(false);
                }
            }
        });

        // 试题列表
        this.refreshExams();
        
        return this;
    }

    /**
     * 更新表格
     */
    public final void refreshExams() {
        this.tableView.getSelectionModel().clearSelection();
        this.tableView.getItems().clear();
        
        List<ExamWO> exams = ExamWO.from(this.examMgt.findAll());
        Collections.sort(exams);
        this.tableView.setItems(FXCollections.observableList(exams));
    }

    @FXML
    private final void onCreateExam(ActionEvent evt) {
        new ExamCreateAct(this.findNewStage(), this).show();
    }

    @FXML
    private final void onUpdateExam(ActionEvent evt) {
        ExamWO srcObj = this.tableView.getSelectionModel().getSelectedItem();
        if (srcObj == null) {
            PopupUtils.alert(this.findNewStage(), "[更新]-请选择一个试题！");
            return;
        }

        ExamInfoDTO dstObj = ExamWO.to(srcObj);
        new ExamUpdateAct(this.findNewStage(), this, dstObj).show();
    }

    @FXML
    private final void onRemoveExam(ActionEvent evt) {
        final ExamWO srcObj = this.tableView.getSelectionModel().getSelectedItem();
        if (srcObj == null) {
            PopupUtils.alert(this.findNewStage(), "[删除]-请选择一个试题！");
            return;
        }

        PopupUtils.confirm(this.findNewStage(), "删除试题", "[删除]-确定删除试题(" + srcObj.getTitle() + ")信息吗？", new PopupEvent() {
            public void callback(Stage newStage, int btnValue) {
                if (btnValue == PopupConst.BTN_SURE_VALUE) {
                    examMgt.remove(ExamWO.to(srcObj));
                    tableView.getItems().remove(srcObj);
                }

                // 关闭弹出对话框
                closeStage(newStage);
            }
        });
    }

    @FXML
    private final void onUnselectExam(ActionEvent evt) {
        this.tableView.getSelectionModel().clearSelection();
    }

}
