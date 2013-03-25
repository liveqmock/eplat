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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mplat.mgt.EcgtMgt;
import mplat.mgt.MgtFactory;
import mplat.uijfx.uiviews.beans.EcgtWO;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * ECG心律识别训练（选择试题）
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain0201Act.java, V1.0.1 2013-3-25 下午9:03:35 $
 */
public class TopicTrain0201Act extends BaseXmlAct {
    /** 试题管理器 */
    private EcgtMgt                     ecgtMgt;

    @FXML
    private BorderPane                  rootView;

    @FXML
    private TableView<EcgtWO>           srcTableView;
    @FXML
    private TableColumn<EcgtWO, String> srcTcolEcgtRhythm;

    @FXML
    private VBox                        vboxButtons;

    @FXML
    private TableView<EcgtWO>           dstTableView;
    @FXML
    private TableColumn<EcgtWO, String> dstTcolEcgtRhythm;

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
    public TopicTrain0201Act(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public BaseXmlAct initXmlViews() {
        // 试题管理器
        this.ecgtMgt = MgtFactory.get().findEcgtMgt();

        // 舞台属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(650.0, 450.0));
        this.findTitleProperty().set("ECG心律识别训练[选择试题]");
        this.findGroupViewProperty().set(this.rootView);

        // 组件属性
        double tableViewWidth = (this.findSizeProperty().get().getWidth() - this.vboxButtons.getWidth()) / 2;

        this.srcTableView.setPrefWidth(tableViewWidth);
        this.srcTcolEcgtRhythm.setPrefWidth(this.srcTableView.getPrefWidth() - 140);

        this.dstTableView.setPrefWidth(tableViewWidth);
        this.dstTcolEcgtRhythm.setPrefWidth(this.dstTableView.getPrefWidth() - 140);

        // 事件
        this.srcTableView.getItems().addListener(new ListChangeListener<EcgtWO>() {
            public void onChanged(ListChangeListener.Change<? extends EcgtWO> change) {
                boolean empty = srcTableView.getItems().isEmpty();
                btnSelectAll.setDisable(empty);

                if (empty) {
                    btnSelectOne.setDisable(empty);
                }
            }
        });
        this.dstTableView.getItems().addListener(new ListChangeListener<EcgtWO>() {
            public void onChanged(ListChangeListener.Change<? extends EcgtWO> change) {
                boolean empty = dstTableView.getItems().isEmpty();
                if (empty) {
                    btnDeselectOne.setDisable(empty);
                }

                btnDeselectAll.setDisable(empty);
                btnNextStep.setDisable(empty);
            }
        });

        this.srcTableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<EcgtWO>() {
            public void onChanged(ListChangeListener.Change<? extends EcgtWO> change) {
                List<EcgtWO> exams = srcTableView.getSelectionModel().getSelectedItems();
                btnSelectOne.setDisable(exams.isEmpty());
            }
        });
        this.dstTableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<EcgtWO>() {
            public void onChanged(ListChangeListener.Change<? extends EcgtWO> change) {
                List<EcgtWO> exams = dstTableView.getSelectionModel().getSelectedItems();
                btnDeselectOne.setDisable(exams.isEmpty());
            }
        });

        // 试题列表
        this.srcTableView.setItems(FXCollections.observableList(EcgtWO.from(this.ecgtMgt.findAll())));

        this.refreshTableView(this.srcTableView);
        this.refreshTableView(this.dstTableView);

        return this;
    }

    /**
     * 更新目标选择表格
     */
    public final void refreshTableView(TableView<EcgtWO> tableView) {
        tableView.getSelectionModel().clearSelection();

        List<EcgtWO> exams = new ArrayList<EcgtWO>(tableView.getItems());
        Collections.sort(exams);

        tableView.getItems().clear();
        tableView.getItems().addAll(FXCollections.observableList(exams).toArray(new EcgtWO[] {}));
    }

    /**
     * 事件-选择全部
     */
    @FXML
    private final void onSelectAll(ActionEvent evt) {
        List<EcgtWO> srcObjs = new ArrayList<EcgtWO>(this.srcTableView.getItems());
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
        EcgtWO srcObj = this.srcTableView.getSelectionModel().getSelectedItem();
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
        EcgtWO srcObj = this.dstTableView.getSelectionModel().getSelectedItem();
        if (srcObj == null || srcObj.getId() <= 0L) {
            return;
        }

        this.srcTableView.getItems().add(srcObj);
        this.dstTableView.getItems().remove(srcObj);

        this.refreshTableView(this.srcTableView);
    }

    /**
     * 事件-取消所有
     */
    @FXML
    private final void onCancelSelect(ActionEvent evt) {
        List<EcgtWO> srcObjs = new ArrayList<EcgtWO>(this.dstTableView.getItems());
        if (!srcObjs.isEmpty()) {
            this.srcTableView.getItems().addAll(srcObjs);
            this.dstTableView.getItems().clear();

            this.refreshTableView(this.srcTableView);
        }
    }

    /**
     * 事件-下一步
     */
    @FXML
    private final void onNextStep(ActionEvent evt) {
        this.closeNewStage();

        List<EcgtWO> srcObjs = new ArrayList<EcgtWO>(this.dstTableView.getItems());
        // URL fxml = this.getClass().getResource("TopicTrain0102Act.fxml");
        // new TopicTrain0102Act(this.findStage(), fxml, srcObjs).show();
        LogUtils.info("选择ECG试题:" + srcObjs);
    }

    /**
     * 事件-取消，关闭窗口
     */
    @FXML
    private final void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }
}
