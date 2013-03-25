/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mplat.mgt.ExamMgt;
import mplat.mgt.MgtFactory;
import mplat.uijfx.uiviews.beans.ExamWO;
import mplat.utils.UConst;

import org.apache.commons.lang.math.RandomUtils;

import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * ACLS基础理论知识训练（选择试题）
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain0101Act.java, V1.0.1 2013-3-25 下午9:00:31 $
 */
public class TopicTrain0101Act extends BaseXmlAct {

    /** 试题管理器 */
    private ExamMgt                     examMgt;

    @FXML
    private BorderPane                  rootView;

    @FXML
    private TableView<ExamWO>           srcTableView;
    @FXML
    private TableColumn<ExamWO, String> srcTcolExamTitle;

    @FXML
    private VBox                        vboxButtons;

    @FXML
    private TableView<ExamWO>           dstTableView;
    @FXML
    private TableColumn<ExamWO, String> dstTcolExamTitle;

    @FXML
    private Button                      btnRandomSelect;
    @FXML
    private Button                      btnCancelSelect;
    @FXML
    private Button                      btnSelectAll;
    @FXML
    private Button                      btnSelectOne;
    @FXML
    private Button                      btnDeselectOne;
    @FXML
    private Button                      btnDeselectAll;

    @FXML
    private Button                      btnNextStep;

    /**
     * 默认构造器
     */
    public TopicTrain0101Act(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public BaseXmlAct initXmlViews() {
        // 试题管理器
        this.examMgt = MgtFactory.get().findExamMgt();

        // 舞台属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(650.0, 450.0));
        this.findTitleProperty().set("ACLS基础理论知识训练[选择试题]");
        this.findGroupViewProperty().set(this.rootView);

        // 组件属性
        double tableViewWidth = (this.findSizeProperty().get().getWidth() - this.vboxButtons.getWidth()) / 2;

        this.srcTableView.setPrefWidth(tableViewWidth);
        this.srcTcolExamTitle.setPrefWidth(this.srcTableView.getPrefWidth() - 80);

        this.dstTableView.setPrefWidth(tableViewWidth);
        this.dstTcolExamTitle.setPrefWidth(this.dstTableView.getPrefWidth() - 80);

        // 事件
        this.srcTableView.getItems().addListener(new ListChangeListener<ExamWO>() {
            public void onChanged(ListChangeListener.Change<? extends ExamWO> change) {
                boolean empty = srcTableView.getItems().isEmpty();
                btnRandomSelect.setDisable(empty);
                btnSelectAll.setDisable(empty);

                if (empty) {
                    btnSelectOne.setDisable(empty);
                }
            }
        });
        this.dstTableView.getItems().addListener(new ListChangeListener<ExamWO>() {
            public void onChanged(ListChangeListener.Change<? extends ExamWO> change) {
                boolean empty = dstTableView.getItems().isEmpty();
                btnCancelSelect.setDisable(empty);
                if (empty) {
                    btnDeselectOne.setDisable(empty);
                }
                btnDeselectAll.setDisable(empty);

                btnNextStep.setDisable(empty);
                btnRandomSelect.setDisable(!empty);
            }
        });

        this.srcTableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<ExamWO>() {
            public void onChanged(ListChangeListener.Change<? extends ExamWO> change) {
                List<ExamWO> exams = srcTableView.getSelectionModel().getSelectedItems();
                btnSelectOne.setDisable(exams.isEmpty());
            }
        });
        this.dstTableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<ExamWO>() {
            public void onChanged(ListChangeListener.Change<? extends ExamWO> change) {
                List<ExamWO> exams = dstTableView.getSelectionModel().getSelectedItems();
                btnDeselectOne.setDisable(exams.isEmpty());
            }
        });

        // 试题列表
        this.srcTableView.setItems(FXCollections.observableList(ExamWO.from(this.examMgt.findAll())));

        this.refreshTableView(this.srcTableView);
        this.refreshTableView(this.dstTableView);

        return this;
    }

    /**
     * 更新目标选择表格
     */
    public final void refreshTableView(TableView<ExamWO> tableView) {
        tableView.getSelectionModel().clearSelection();

        List<ExamWO> exams = new ArrayList<ExamWO>(tableView.getItems());
        Collections.sort(exams);

        tableView.getItems().clear();
        tableView.getItems().addAll(FXCollections.observableList(exams).toArray(new ExamWO[] {}));
    }

    /**
     * 产生随机数
     */
    private final int nextRandomInt(int size) {
        return RandomUtils.nextInt(size);
    }

    /**
     * 事件-随机选择
     */
    @FXML
    private final void onRandomSelect(ActionEvent evt) {
        for (int i = 0; i < UConst.MAX_EXAM_ITEMS; i++) {
            ObservableList<ExamWO> srcObjs = this.srcTableView.getItems();
            if (!srcObjs.isEmpty()) {
                this.dstTableView.getItems().add(srcObjs.remove(this.nextRandomInt(srcObjs.size())));
            }
        }

        this.refreshTableView(this.dstTableView);
    }

    /**
     * 事件-清除选择
     */
    @FXML
    private final void onCancelSelect(ActionEvent evt) {
        List<ExamWO> srcObjs = new ArrayList<ExamWO>(this.dstTableView.getItems());
        if (!srcObjs.isEmpty()) {
            this.srcTableView.getItems().addAll(srcObjs);
            this.dstTableView.getItems().clear();

            this.refreshTableView(this.srcTableView);
        }
    }

    /**
     * 事件-选择全部
     */
    @FXML
    private final void onSelectAll(ActionEvent evt) {
        List<ExamWO> srcObjs = new ArrayList<ExamWO>(this.srcTableView.getItems());
        if (!srcObjs.isEmpty()) {
            this.dstTableView.getItems().addAll(srcObjs);
            this.srcTableView.getItems().clear();

            this.refreshTableView(this.dstTableView);
        }
    }

    /**
     * 事件-选择单个
     */
    @FXML
    private final void onSelectOne(ActionEvent evt) {
        ExamWO srcObj = this.srcTableView.getSelectionModel().getSelectedItem();
        if (srcObj == null || srcObj.getId() <= 0L) {
            return;
        }

        this.dstTableView.getItems().add(srcObj);
        this.srcTableView.getItems().remove(srcObj);

        this.refreshTableView(dstTableView);
    }

    /**
     * 事件-取消选择单个
     */
    @FXML
    private final void onDeselectOne(ActionEvent evt) {
        ExamWO srcObj = this.dstTableView.getSelectionModel().getSelectedItem();
        if (srcObj == null || srcObj.getId() <= 0L) {
            return;
        }

        this.srcTableView.getItems().add(srcObj);
        this.dstTableView.getItems().remove(srcObj);

        this.refreshTableView(this.srcTableView);
    }

    /**
     * 事件-下一步
     */
    @FXML
    private final void onNextStep(ActionEvent evt) {
        this.closeNewStage();

        List<ExamWO> srcObjs = new ArrayList<ExamWO>(this.dstTableView.getItems());
        new TopicTrain0102Act(this.findStage(), srcObjs).show();
    }

    /**
     * 事件-取消，关闭窗口
     */
    @FXML
    private final void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }

}
