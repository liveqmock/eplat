/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.events;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mplat.mgt.EcgtMgt;
import mplat.mgt.ExamMgt;
import mplat.mgt.MgtFactory;
import mplat.uijfx.uiviews.beans.EcgtWO;
import mplat.uijfx.uiviews.beans.ExamWO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.lang.utils.TimerUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * 专项技能训练事件处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrainViewEvent.java, V1.0.1 2013-2-13 下午1:17:58 $
 */
public final class TopicTrainViewEvent extends AbstractWebViewEvent {
    /** 随机最多选择个数 */
    public static final int MAX_EXAM_ITEMS = 8;

    /**
     * 默认构造器
     */
    public TopicTrainViewEvent(WebView view) {
        super(view);
    }

    /** 
     * @see mplat.uijfx.uiviews.events.AbstractWebViewEvent#onFireEvent(java.lang.String)
     */
    protected final void onFireEvent(String arg) {
        System.out.println(arg + ": 点击了专项训练~~~~~");

        Stage stage = this.findStage();
        if (StringUtils.equals("1", arg)) {
            // ACLS基础知识训练
            URL fxml = this.getClass().getResource("TopicTrain0101Act.fxml");
            new TopicTrain0101Act(stage, fxml).show();
        } else if (StringUtils.equals("2", arg)) {
            // ECG心律识别训练
            URL fxml = this.getClass().getResource("TopicTrain0201Act.fxml");
            new TopicTrain0201Act(stage, fxml).show();
        }
    }

    /**
     * ACLS基础理论知识训练（选择试题）
     * 
     * @version $Id: TopicTrain0101Act.java, V1.0.1 2013-2-15 下午10:11:09 $
     */
    public static class TopicTrain0101Act extends BaseXmlAct {
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
        public TopicTrain0101Act(Stage stage, URL fxml) {
            super(stage, fxml);
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
            for (int i = 0; i < MAX_EXAM_ITEMS; i++) {
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
            URL fxml = this.getClass().getResource("TopicTrain0102Act.fxml");
            new TopicTrain0102Act(this.findStage(), fxml, srcObjs).show();
        }

        /**
         * 事件-取消，关闭窗口
         */
        @FXML
        private final void onCancelAction(ActionEvent evt) {
            this.closeNewStage();
        }

    }

    /**
     * ACLS基础理论知识训练（试题训练）
     * 
     * @version $Id: TopicTrain0102Act.java, V1.0.1 2013-2-15 下午10:11:09 $
     */
    public static class TopicTrain0102Act extends BaseXmlAct {
        /** 试题列表 */
        private final List<ExamWO>           exams   = new ArrayList<ExamWO>();
        /** 当前试题 */
        private final ObjectProperty<ExamWO> current = new SimpleObjectProperty<ExamWO>();

        @FXML
        private BorderPane                   rootView;

        @FXML
        private Label                        lblTimer;
        @FXML
        private Label                        lblExamNo;
        @FXML
        private Label                        lblExamTitle;
        @FXML
        private Label                        lblItemA;
        @FXML
        private Label                        lblItemB;
        @FXML
        private Label                        lblItemC;
        @FXML
        private Label                        lblItemD;
        @FXML
        private ComboBox<String>             cboxSelectNo;
        @FXML
        private HBox                         hboxExamRgtNo;
        @FXML
        private ComboBox<String>             cboxExamRgtNo;

        @FXML
        private Button                       btnLastExam;
        @FXML
        private Button                       btnNextExam;
        @FXML
        private Button                       btnRgtExam;
        @FXML
        private Button                       btnExamFinish;

        /**
         * 默认构造器
         */
        public TopicTrain0102Act(Stage stage, URL fxml, List<ExamWO> exams) {
            super(stage, fxml);

            this.exams.addAll(exams);
        }

        /** 
         * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
         */
        public final BaseXmlAct initXmlViews() {
            // 舞台属性
            this.setNewStage(new Stage());
            this.findSizeProperty().set(new Dimension2D(700.0, 500.0));
            this.findTitleProperty().set("ACLS基础理论知识训练[试题训练]");
            this.findGroupViewProperty().set(this.rootView);

            return this;
        }

        /** 
         * @see com.atom.core.uijfx.views.BaseViewAct#initViewShown()
         */
        public final void initViewShown() {
            // 计时
            final long start = System.currentTimeMillis();
            final Thread timer = new Thread() {
                public void run() {
                    boolean loop = true;
                    while (loop) {
                        try {
                            long millis = System.currentTimeMillis() - start;

                            long minutes = millis / DateUtils.MILLIS_PER_MINUTE;
                            long seconds = (millis / DateUtils.MILLIS_PER_SECOND) % 60;
                            final String m = StringUtils.leftPad(Long.toString(minutes), 2, "0");
                            final String s = StringUtils.leftPad(Long.toString(seconds), 2, "0");

                            Platform.runLater(new Runnable() {
                                public void run() {
                                    lblTimer.setText(m + ":" + s);
                                }
                            });

                            Thread.sleep(DateUtils.MILLIS_PER_SECOND / 2);
                        } catch (InterruptedException e) {
                            // exit
                            loop = false;
                        } catch (Exception e) {
                            // ignore
                            LogUtils.error("基础理论知识训练异常！", e);
                        }
                    }
                }
            };
            // 启动计时
            timer.start();

            this.findNewStage().addEventFilter(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
                public void handle(WindowEvent arg0) {
                    timer.interrupt();
                }
            });

            // 设置视图组件属性
            this.current.set(this.exams.get(0));
            this.initExamViews();
        }

        /**
         * 设置视图组件属性
         */
        private final void initExamViews() {
            ExamWO exam = this.current.get();
            int index = this.exams.indexOf(exam);

            // 上1个试题
            if (index <= 0) {
                this.btnLastExam.setDisable(true);
            } else {
                this.btnLastExam.setDisable(false);
            }

            // 最后1个试题
            if (index >= (this.exams.size() - 1)) {
                this.btnNextExam.setDisable(true);
            } else {
                this.btnNextExam.setDisable(false);
            }

            this.lblExamNo.setText(Integer.toString(index + 1));
            this.lblExamTitle.setText(exam.getTitle());
            this.lblItemA.setText(exam.getItemA());
            this.lblItemB.setText(exam.getItemB());
            this.lblItemC.setText(exam.getItemC());
            this.lblItemD.setText(exam.getItemD());
            this.cboxSelectNo.getSelectionModel().select(StringUtils.EMPTY);
        }

        /**
         * 事件-上一试题
         */
        @FXML
        private final void onLastExam(ActionEvent evt) {
            int index = this.exams.indexOf(this.current.get()) - 1;
            if (index >= 0) {
                this.current.set(this.exams.get(index));
            }

            // 设置视图组件属性
            this.initExamViews();
        }

        /**
         * 事件-下一试题
         */
        @FXML
        private final void onNextExam(ActionEvent evt) {
            int index = this.exams.indexOf(this.current.get()) + 1;
            if (index < this.exams.size()) {
                this.current.set(this.exams.get(index));
            }

            // 设置视图组件属性
            this.initExamViews();
        }

        /**
         * 事件-参考答案
         */
        @FXML
        private final void onRgtExam(ActionEvent evt) {
            ExamWO exam = this.current.get();
            this.cboxExamRgtNo.getSelectionModel().select(exam.getRgtNo());
            this.hboxExamRgtNo.setVisible(true);

            TimerUtils.findTimer().schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            hboxExamRgtNo.setVisible(false);
                        }
                    });
                }
            }, DateUtils.MILLIS_PER_SECOND);
        }

        /**
         * 事件-完成训练
         */
        @FXML
        private final void onExamFinish(ActionEvent evt) {
            this.closeNewStage();
        }

    }

    /**
     * ECG心律识别训练（选择试题）
     * 
     * @version $Id: TopicTrain0201Act.java, V1.0.1 2013-2-15 下午10:11:09 $
     */
    public static class TopicTrain0201Act extends BaseXmlAct {
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
        public TopicTrain0201Act(Stage stage, URL fxml) {
            super(stage, fxml);
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
            this.dstTcolEcgtRhythm.setPrefWidth(this.srcTableView.getPrefWidth() - 140);

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

}
