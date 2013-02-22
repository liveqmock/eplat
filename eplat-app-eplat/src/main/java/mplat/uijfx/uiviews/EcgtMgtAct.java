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
import mplat.mgt.EcgtMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.EcgtInfoDTO;
import mplat.uijfx.uiviews.beans.EcgtWO;

import com.atom.core.uijfx.popup.PopupConst;
import com.atom.core.uijfx.popup.PopupEvent;
import com.atom.core.uijfx.popup.PopupUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * ECG心律识别试题
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtMgtAct.java, V1.0.1 2013-2-22 下午9:08:12 $
 */
public final class EcgtMgtAct extends BaseXmlAct {

    @FXML
    private BorderPane                  viewRoot;
    @FXML
    private Button                      btnCreateEcgt;
    @FXML
    private Button                      btnUpdateEcgt;
    @FXML
    private Button                      btnRemoveEcgt;
    @FXML
    private Button                      btnUnselectEcgt;
    @FXML
    private TableView<EcgtWO>           tableView;
    @FXML
    private TableColumn<EcgtWO, Long>   tcolEcgtID;
    @FXML
    private TableColumn<EcgtWO, String> tcolEcgtQrs;
    @FXML
    private TableColumn<EcgtWO, String> tcolEcgtRhythm;
    @FXML
    private TableColumn<EcgtWO, String> tcolEcgtRate;

    private EcgtMgt                     ecgtMgt;

    /**
     * 默认构造器
     */
    public EcgtMgtAct(Stage stage) {
        super(stage);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public final BaseXmlAct initXmlViews() {
        // 试题管理
        this.ecgtMgt = MgtFactory.get().findEcgtMgt();

        // 舞台属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(650.0, 450.0));
        this.findTitleProperty().set("ECG心律识别试题管理");
        this.findGroupViewProperty().set(this.viewRoot);
        this.findResizableProperty().set(true);

        // 组件属性
        this.tableView.setPrefWidth(this.findSizeProperty().get().getWidth());
        this.tcolEcgtRhythm.setPrefWidth(this.tableView.getPrefWidth() - 130);

        // 事件
        this.tableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<EcgtWO>() {
            public void onChanged(ListChangeListener.Change<? extends EcgtWO> change) {
                List<EcgtWO> ecgts = tableView.getSelectionModel().getSelectedItems();
                if (ecgts.isEmpty()) {
                    // 没有选择
                    btnRemoveEcgt.setDisable(true);
                    btnUpdateEcgt.setDisable(true);
                    btnUnselectEcgt.setDisable(true);
                } else {
                    // 选择试题
                    btnRemoveEcgt.setDisable(false);
                    btnUpdateEcgt.setDisable(false);
                    btnUnselectEcgt.setDisable(false);
                }
            }
        });

        // 试题列表
        this.refreshEcgts();

        return this;
    }

    /**
     * 更新表格
     */
    public final void refreshEcgts() {
        this.tableView.getSelectionModel().clearSelection();
        this.tableView.getItems().clear();
        
        List<EcgtWO> ecgts = EcgtWO.from(this.ecgtMgt.findAll());
        Collections.sort(ecgts);
        this.tableView.getItems().addAll(FXCollections.observableList(ecgts));
    }

    /**
     * 事件-新增试题
     */
    @FXML
    private final void onCreateEcgt(ActionEvent evt) {
        new EcgtCreateAct(this.findNewStage(), this).show();
    }

    /**
     * 事件-修改试题
     */
    @FXML
    private final void onUpdateEcgt(ActionEvent evt) {
        EcgtWO srcObj = this.tableView.getSelectionModel().getSelectedItem();
        if (srcObj == null) {
            PopupUtils.alert(this.findNewStage(), "[更新]-请选择一个ECG心律识别试题！");
            return;
        }

        EcgtInfoDTO dstObj = EcgtWO.to(srcObj);
        new EcgtUpdateAct(this.findNewStage(), this, dstObj).show();
    }

    /**
     * 事件-删除试题
     */
    @FXML
    private final void onRemoveEcgt(ActionEvent evt) {
        final EcgtWO srcObj = this.tableView.getSelectionModel().getSelectedItem();
        if (srcObj == null) {
            PopupUtils.alert(this.findNewStage(), "[删除]-请选择一个ECG心律识别试题！");
            return;
        }

        PopupUtils.confirm(this.findNewStage(), "删除ECG心律识别试题", "[删除]-确定删除ECG心律识别试题(" + srcObj.getEcgtRhythm() + ")信息吗？", new PopupEvent() {
            public void callback(Stage newStage, int btnValue) {
                if (btnValue == PopupConst.BTN_SURE_VALUE) {
                    ecgtMgt.remove(EcgtWO.to(srcObj));
                    tableView.getItems().remove(srcObj);
                }

                // 关闭弹出对话框
                closeStage(newStage);
            }
        });
    }

    /**
     * 事件-取消选择
     */
    @FXML
    private final void onUnselectEcgt(ActionEvent evt) {
        this.tableView.getSelectionModel().clearSelection();
    }

}
