package com.atom.apps.eplat.views;

import java.util.Date;
import java.util.List;

import mplat.mgt.EcgtMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.EcgtInfoDTO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.DateUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.dtos.ExamEvaluateDTO;
import com.atom.core.lang.utils.LogUtils;

public class EcgtTrainDlg extends Dialog {
    private static int            ECG_WIDTH  = 563;
    private static int            ECG_HEIGHT = 139;

    private int                   indexNo    = 0;

    private final List<String>    ids;
    private final ExamEvaluateDTO train      = new ExamEvaluateDTO();
    private final EcgtMgt         emgt       = MgtFactory.get().findEcgtMgt();

    private Shell                 shell;
    private Display               display;

    private Label                 lblEcgtPhoto;

    private Label                 lblRightCount;
    private Label                 lblTotalCount;
    private Label                 lblTimer;
    private Button                btnNext;

    private Combo                 cmbRhythm;
    private Combo                 cmbExtraSyst;

    private Text                  txtHint;
    private Text                  txtQrs;
    private Text                  txtRhythm;
    private Text                  txtRate;
    private Text                  txtExtraSyst;

    /**
     * Create the dialog.
     */
    public EcgtTrainDlg(Shell parent, List<String> ids) {
        super(parent, SWT.NONE);

        this.ids = ids;
        this.train.setTotalCount(this.ids.size());
        
        LogUtils.get().info("Ecgt试题考核-{}", this.ids);
    }

    /**
     * Open the dialog.
     * @return the result
     */
    public ExamEvaluateDTO open() {
        createContents();

        shell.open();
        shell.layout();
        display = getParent().getDisplay();

        // 启动计时器
        this.startTimer();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        return this.train;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setSize(600, 440);
        shell.setImages(SWTUtils.findImgIcons());
        shell.setText("ECG心律识别训练");
        SWTUtils.center(this.getParent(), shell);

        Group group = new Group(shell, SWT.NONE);
        group.setText("尺寸");
        group.setBounds(10, 10, 120, 72);

        Button rbtnSizeOne = new Button(group, SWT.RADIO);
        rbtnSizeOne.setBounds(10, 22, 97, 17);
        rbtnSizeOne.setText("Size 1X");

        Button rbtnSizeTwo = new Button(group, SWT.RADIO);
        rbtnSizeTwo.setBounds(10, 45, 97, 17);
        rbtnSizeTwo.setText("Size 2X");

        Group group_1 = new Group(shell, SWT.NONE);
        group_1.setText("速度");
        group_1.setBounds(236, 10, 120, 72);

        Button rbtnSpeedNormal = new Button(group_1, SWT.RADIO);
        rbtnSpeedNormal.setBounds(10, 22, 97, 17);
        rbtnSpeedNormal.setText("正常");

        Button rbtnSpeedFast = new Button(group_1, SWT.RADIO);
        rbtnSpeedFast.setBounds(10, 45, 97, 17);
        rbtnSpeedFast.setText("加速");

        Group group_2 = new Group(shell, SWT.NONE);
        group_2.setText("显示");
        group_2.setBounds(464, 10, 120, 72);

        Button rbtnShowLine = new Button(group_2, SWT.RADIO);
        rbtnShowLine.setBounds(10, 22, 97, 17);
        rbtnShowLine.setText("拖动显示");

        Button rbtnShowBlock = new Button(group_2, SWT.RADIO);
        rbtnShowBlock.setBounds(10, 45, 97, 17);
        rbtnShowBlock.setText("快刷新显示");

        Group group_3 = new Group(shell, SWT.NONE);
        group_3.setBounds(10, 78, 574, 156);

        lblEcgtPhoto = new Label(group_3, SWT.NONE);
        lblEcgtPhoto.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
        lblEcgtPhoto.setBounds(5, 12, ECG_WIDTH, ECG_HEIGHT);

        CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER);
        tabFolder.setBounds(10, 240, 574, 162);
        tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

        CTabItem titmTrain = new CTabItem(tabFolder, SWT.NONE);
        titmTrain.setImage(SWTUtils.findImage("tab-ecgt-train.gif"));
        titmTrain.setText("心律选择");

        Composite composite = new Composite(tabFolder, SWT.NONE);
        titmTrain.setControl(composite);

        Group group_4 = new Group(composite, SWT.NONE);
        group_4.setText("结果");
        group_4.setBounds(10, 10, 120, 115);

        Label label = new Label(group_4, SWT.NONE);
        label.setBounds(10, 22, 40, 17);
        label.setText("正确：");

        lblRightCount = new Label(group_4, SWT.CENTER);
        lblRightCount.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
        lblRightCount.setBounds(52, 21, 20, 17);
        lblRightCount.setText("2");

        Label label_2 = new Label(group_4, SWT.NONE);
        label_2.setBounds(11, 54, 40, 17);
        label_2.setText("总共：");

        lblTotalCount = new Label(group_4, SWT.CENTER);
        lblTotalCount.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
        lblTotalCount.setBounds(52, 53, 20, 17);
        lblTotalCount.setText("10");

        Label label_4 = new Label(group_4, SWT.NONE);
        label_4.setBounds(10, 88, 40, 17);
        label_4.setText("时间：");

        lblTimer = new Label(group_4, SWT.NONE);
        lblTimer.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        lblTimer.setBounds(52, 88, 60, 17);
        lblTimer.setText("00:00:00");

        Group group_5 = new Group(composite, SWT.NONE);
        group_5.setText("答题");
        group_5.setBounds(136, 10, 422, 115);

        cmbRhythm = new Combo(group_5, SWT.READ_ONLY);
        cmbRhythm.setBounds(10, 20, 130, 25);
        cmbRhythm.setItems(SWTUtils.toArray(EcgtMgt.findEcgtRhythm()));

        cmbExtraSyst = new Combo(group_5, SWT.READ_ONLY);
        cmbExtraSyst.setBounds(148, 20, 130, 25);
        cmbExtraSyst.setItems(SWTUtils.toArray(EcgtMgt.findEcgtExtSyst()));

        Label lblQrs = new Label(group_5, SWT.NONE);
        lblQrs.setBounds(10, 53, 28, 17);
        lblQrs.setText("QRS");

        txtQrs = new Text(group_5, SWT.BORDER);
        txtQrs.setEditable(false);
        txtQrs.setBounds(39, 51, 30, 22);

        Label lblBasicRhythm = new Label(group_5, SWT.NONE);
        lblBasicRhythm.setBounds(80, 53, 80, 17);
        lblBasicRhythm.setText("Basic Rhythm");

        txtRhythm = new Text(group_5, SWT.BORDER);
        txtRhythm.setEditable(false);
        txtRhythm.setBounds(160, 50, 118, 23);

        Label lblRate = new Label(group_5, SWT.NONE);
        lblRate.setBounds(10, 88, 28, 17);
        lblRate.setText("Rate");

        txtRate = new Text(group_5, SWT.BORDER);
        txtRate.setEditable(false);
        txtRate.setBounds(39, 85, 30, 22);

        Label lblExtrasys = new Label(group_5, SWT.NONE);
        lblExtrasys.setAlignment(SWT.RIGHT);
        lblExtrasys.setBounds(80, 88, 80, 17);
        lblExtrasys.setText("Extrasyst.");

        txtExtraSyst = new Text(group_5, SWT.BORDER);
        txtExtraSyst.setEditable(false);
        txtExtraSyst.setBounds(160, 85, 118, 23);

        Button btnReset = new Button(group_5, SWT.NONE);
        btnReset.setBounds(332, 18, 80, 27);
        btnReset.setText("重置");
        btnReset.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                indexNo = 0;
                train.setTotalCount(0);
                initComponents();
            }
        });

        btnNext = new Button(group_5, SWT.NONE);
        btnNext.setBounds(332, 48, 80, 27);
        btnNext.setText("下一题");
        btnNext.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                final EcgtInfoDTO ecgt = findCurrentEcgt();
                if (ecgt != null) {
                    txtQrs.setText(StringUtils.trimToEmpty(ecgt.getEcgtQrs()));
                    txtRhythm.setText(StringUtils.trimToEmpty(ecgt.getEcgtRhythm()));
                    txtRate.setText(StringUtils.trimToEmpty(ecgt.getEcgtRate()));
                    txtExtraSyst.setText(StringUtils.trimToEmpty(ecgt.getEcgtSyst()));
                }

                // 停止3秒
                if (indexNo <= (ids.size() - 1)) {
                    SWTUtils.execute(new Runnable() {
                        public void run() {
                            // 按钮不可用
                            display.syncExec(new Runnable() {
                                public void run() {
                                    btnNext.setEnabled(false);
                                }
                            });

                            // 暂停3秒
                            long start = System.currentTimeMillis();
                            while (System.currentTimeMillis() - start < 3 * DateUtils.MILLIS_PER_SECOND) {
                                // do nothing
                            }

                            // 计算是否正确
                            display.syncExec(new Runnable() {
                                public void run() {
                                    String rhythm = StringUtils.trimToEmpty(cmbRhythm.getText());
                                    String extraSyst = StringUtils.trimToEmpty(cmbExtraSyst.getText());
                                    if (StringUtils.equals(StringUtils.trimToEmpty(ecgt.getEcgtRhythm()), rhythm)) {
                                        if (StringUtils.equals(StringUtils.trimToEmpty(ecgt.getEcgtSyst()), extraSyst)) {
                                            // 正确+1
                                            train.setRightCount(train.getRightCount() + 1);
                                        }
                                    }

                                    // 下一题
                                    indexNo += 1;
                                    initComponents();
                                }
                            });
                        }
                    });
                }
            }
        });

        Button btnComplete = new Button(group_5, SWT.NONE);
        btnComplete.setBounds(332, 78, 80, 27);
        btnComplete.setText("结束");
        btnComplete.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });

        CTabItem titmHint = new CTabItem(tabFolder, SWT.NONE);
        titmHint.setImage(SWTUtils.findImage("tab-ecgt-train.gif"));
        titmHint.setText("心律识别线索");

        Composite composite_1 = new Composite(tabFolder, SWT.NONE);
        titmHint.setControl(composite_1);

        txtHint = new Text(composite_1, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        txtHint.setEditable(false);
        txtHint.setBounds(10, 10, 548, 115);

        // 初始化
        tabFolder.setSelection(titmTrain);

        this.initComponents();
    }

    /**
     * 启动计时器
     */
    private void startTimer() {
        SWTUtils.execute(new Runnable() {
            public void run() {
                while (!shell.isDisposed()) {
                    train.setFinish(new Date());
                    long millis = train.findMillis();
                    int hours = (int) (millis / DateUtils.MILLIS_PER_HOUR);
                    int minutes = (int) ((millis % DateUtils.MILLIS_PER_HOUR) / DateUtils.MILLIS_PER_MINUTE);
                    int seconds = (int) ((millis % DateUtils.MILLIS_PER_MINUTE) / DateUtils.MILLIS_PER_SECOND);

                    final StringBuilder txt = new StringBuilder();
                    txt.append(StringUtils.leftPad(Integer.toString(hours), 2, "0"));
                    txt.append(":").append(StringUtils.leftPad(Integer.toString(minutes), 2, "0"));
                    txt.append(":").append(StringUtils.leftPad(Integer.toString(seconds), 2, "0"));

                    display.syncExec(new Runnable() {
                        public void run() {
                            if (lblTimer != null && !lblTimer.isDisposed()) {
                                lblTimer.setText(txt.toString());
                            }
                            
                            if (lblRightCount != null && !lblRightCount.isDisposed()) {
                                lblRightCount.setText(String.valueOf(train.getRightCount()));
                            }
                        }
                    });

                    try {
                        Thread.sleep(DateUtils.MILLIS_PER_SECOND);
                    } catch (Exception e) {
                        LogUtils.get().error("计时器异常！", e);
                    }
                }
            }
        });
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        this.initButtons();

        EcgtInfoDTO ecgt = this.findCurrentEcgt();
        if (ecgt != null) {
            // 心电图
            String name = EcgtMgt.findEcgGraph(ecgt.getEcgtRhythm(), ecgt.getEcgtQrs());
            Rectangle size = new Rectangle(0, 0, ECG_WIDTH, ECG_HEIGHT);
            this.lblEcgtPhoto.setImage(SWTUtils.findEcgImage(name, size));

            this.lblRightCount.setText(Integer.toString(this.train.getRightCount()));
            this.lblTotalCount.setText(Integer.toString(this.train.getTotalCount()));

            this.cmbRhythm.select(0);
            this.cmbExtraSyst.select(0);

            this.txtQrs.setText(StringUtils.EMPTY);
            this.txtRhythm.setText(StringUtils.EMPTY);
            this.txtRate.setText(StringUtils.EMPTY);
            this.txtExtraSyst.setText(StringUtils.EMPTY);

            String ln = SystemUtils.LINE_SEPARATOR;
            StringBuilder txt = new StringBuilder();
            txt.append("QRS:").append(ln).append("\t").append(ecgt.getTipQrs());
            txt.append(ln).append("PR_Interval:").append(ln).append("\t").append(ecgt.getTipInterval());
            txt.append(ln).append("p_Wave:").append(ln).append("\t").append(ecgt.getTipWave());
            txt.append(ln).append("Regularity:").append(ln).append("\t").append(ecgt.getTipRegular());
            txt.append(ln).append("Rate:").append(ln).append("\t").append(ecgt.getTipRate());
            this.txtHint.setText(txt.toString());
        }
    }

    /**
     * 初始化按钮
     */
    private void initButtons() {
        int count = this.ids.size();

        if (count < 1) {
            this.shell.dispose();
        }

        if (count == 1) {
            this.btnNext.setEnabled(false);
        } else {
            if (this.indexNo <= 0) {
                this.indexNo = 0;
            }

            if (this.indexNo >= (count - 1)) {
                this.indexNo = (count - 1);

                this.btnNext.setEnabled(false);
            } else {
                this.btnNext.setEnabled(true);
            }
        }
    }

    /**
     * 获取当前试题
     */
    private EcgtInfoDTO findCurrentEcgt() {
        String id = this.ids.get(this.indexNo);
        return this.emgt.find(Long.valueOf(id));
    }
}
