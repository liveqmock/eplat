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
import mplat.mgt.MgtFactory;
import mplat.mgt.PumpMgt;
import mplat.uijfx.uiviews.beans.PumpWO;
import mplat.utils.UConst;

import org.apache.commons.lang.math.RandomUtils;

import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 注射泵/输液泵使用训练（选择试题）
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain6800Act.java, V1.0.1 2013-3-25 下午9:04:38 $
 */
public class TopicTrain6800Act extends BaseXmlAct {
    /** 试题管理器 */
    private PumpMgt                     pumpMgt;

    /** 类型：注射泵/输液泵 */
    private final int                   catg;

    @FXML
    private BorderPane                  rootView;

    @FXML
    private TableView<PumpWO>           srcTableView;
    @FXML
    private TableColumn<PumpWO, String> srcTcolPumpTitle;

    @FXML
    private VBox                        vboxButtons;

    @FXML
    private TableView<PumpWO>           dstTableView;
    @FXML
    private TableColumn<PumpWO, String> dstTcolPumpTitle;

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
    public TopicTrain6800Act(int catg, Stage stage) {
        super(stage);

        this.catg = catg;
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseViewAct#initViewShown()
     */
    public final void initViewShown() {
        super.initViewShown();

        if (this.catg == PumpMgt.EJECTOR) {
            this.findTitleProperty().set("注射泵使用训练[选择试题]");
        } else {
            this.findTitleProperty().set("输液泵使用训练[选择试题]");
        }

        // 试题管理器
        if (this.catg == PumpMgt.EJECTOR) {
            this.pumpMgt = MgtFactory.get().findEjectorMgt();
        } else {
            this.pumpMgt = MgtFactory.get().findTransferMgt();
        }

        // 试题列表
        this.srcTableView.setItems(FXCollections.observableList(PumpWO.from(this.pumpMgt.findAll())));

        this.refreshTableView(this.srcTableView);
        this.refreshTableView(this.dstTableView);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public BaseXmlAct initXmlViews() {
        // 舞台属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(900.0, 450.0));
        this.findGroupViewProperty().set(this.rootView);

        // 组件属性
        double tableViewWidth = (this.findSizeProperty().get().getWidth() - this.vboxButtons.getWidth()) / 2;

        this.srcTableView.setPrefWidth(tableViewWidth);
        this.srcTcolPumpTitle.setPrefWidth(this.srcTableView.getPrefWidth() - 180);

        this.dstTableView.setPrefWidth(tableViewWidth);
        this.dstTcolPumpTitle.setPrefWidth(this.dstTableView.getPrefWidth() - 180);

        // 事件
        this.srcTableView.getItems().addListener(new ListChangeListener<PumpWO>() {
            public void onChanged(ListChangeListener.Change<? extends PumpWO> change) {
                boolean empty = srcTableView.getItems().isEmpty();
                btnRandomSelect.setDisable(empty);
                btnSelectAll.setDisable(empty);

                if (empty) {
                    btnSelectOne.setDisable(empty);
                }
            }
        });
        this.dstTableView.getItems().addListener(new ListChangeListener<PumpWO>() {
            public void onChanged(ListChangeListener.Change<? extends PumpWO> change) {
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

        this.srcTableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<PumpWO>() {
            public void onChanged(ListChangeListener.Change<? extends PumpWO> change) {
                List<PumpWO> pumps = srcTableView.getSelectionModel().getSelectedItems();
                btnSelectOne.setDisable(pumps.isEmpty());
            }
        });
        this.dstTableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<PumpWO>() {
            public void onChanged(ListChangeListener.Change<? extends PumpWO> change) {
                List<PumpWO> pumps = dstTableView.getSelectionModel().getSelectedItems();
                btnDeselectOne.setDisable(pumps.isEmpty());
            }
        });

        return this;
    }

    /**
     * 更新目标选择表格
     */
    public final void refreshTableView(TableView<PumpWO> tableView) {
        tableView.getSelectionModel().clearSelection();

        List<PumpWO> pumps = new ArrayList<PumpWO>(tableView.getItems());
        Collections.sort(pumps);

        tableView.getItems().clear();
        tableView.getItems().addAll(FXCollections.observableList(pumps).toArray(new PumpWO[] {}));
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
            ObservableList<PumpWO> srcObjs = this.srcTableView.getItems();
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
        List<PumpWO> srcObjs = new ArrayList<PumpWO>(this.dstTableView.getItems());
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
        List<PumpWO> srcObjs = new ArrayList<PumpWO>(this.srcTableView.getItems());
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
        PumpWO srcObj = this.srcTableView.getSelectionModel().getSelectedItem();
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
        PumpWO srcObj = this.dstTableView.getSelectionModel().getSelectedItem();
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

        List<PumpWO> srcObjs = new ArrayList<PumpWO>(this.dstTableView.getItems());
        // URL fxml = this.getClass().getResource("TopicTrain0602Act.fxml");
        // new TopicTrain0102Act(this.findStage(), fxml, srcObjs).show();
    }

    /**
     * 事件-取消，关闭窗口
     */
    @FXML
    private final void onCancelAction(ActionEvent evt) {
        this.closeNewStage();
    }
}
