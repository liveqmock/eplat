/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mplat.uijfx.uiviews.beans.ExamWO;
import mplat.utils.UserHolder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.lang.utils.TimerUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * ACLS基础理论知识训练（试题训练）
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain0102Act.java, V1.0.1 2013-3-25 下午9:01:42 $
 */
public class TopicTrain0102Act extends BaseXmlAct {
    /** 试题列表 */
    private final List<ExamWO>           exams    = new ArrayList<ExamWO>();
    /** 当前试题 */
    private final ObjectProperty<ExamWO> current  = new SimpleObjectProperty<ExamWO>();
    /** 正确题目 */
    private Set<Long>                    rgtExams = new HashSet<Long>();

    @FXML
    private BorderPane                   rootView;

    @FXML
    private Label                        lblTimer;
    /*
    @FXML
    private Label                        lblExamNo;
    */
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
    public TopicTrain0102Act(Stage stage, List<ExamWO> exams) {
        super(stage);

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

        // this.lblExamNo.setText(Integer.toString(index + 1));
        this.lblExamTitle.setText(Integer.toString(index + 1) + "、" + exam.getTitle());
        this.lblItemA.setText("A、" + exam.getItemA());
        this.lblItemB.setText("B、" + exam.getItemB());
        this.lblItemC.setText("C、" + exam.getItemC());
        this.lblItemD.setText("D、" + exam.getItemD());
        this.cboxSelectNo.getSelectionModel().select(StringUtils.EMPTY);
    }

    /**
     * 设置正确结果
     */
    private final void checkExam() {
        ExamWO exam = this.current.get();
        if (exam != null) {
            if (StringUtils.equalsIgnoreCase(this.cboxSelectNo.getValue(), exam.getRgtNo())) {
                this.rgtExams.add(exam.getId());
            }
        }
    }

    /**
     * 事件-上一试题
     */
    @FXML
    private final void onLastExam(ActionEvent evt) {
        // 设置正确题目
        this.checkExam();

        // 上一题
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
        // 设置正确题目
        this.checkExam();

        // 下一题
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
        // 设置正确题目
        this.checkExam();

        // 答题完成
        Map<Object, Object> context = new HashMap<Object, Object>();
        context.put("user", UserHolder.get());

        int totalCount = this.exams.size();
        int rightCount = this.rgtExams.size();
        context.put("totalCount", totalCount);
        context.put("rightCount", rightCount);
        context.put("wrongCount", totalCount - rightCount);
        
        // 弹出确认窗口
        new TopicTrain0103Act(this.findNewStage(), context).show();
    }

}
