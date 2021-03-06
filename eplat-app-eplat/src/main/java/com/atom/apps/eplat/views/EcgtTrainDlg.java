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
    private final List<String>    rhythms;
    private final List<String>    extraSysts;

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

        this.rhythms = EcgtMgt.findEcgtRhythm();
        this.extraSysts = EcgtMgt.findEcgtExtSyst();

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
        shell.setSize(600, 407);
        shell.setImages(SWTUtils.findImgIcons());
        shell.setText("ECG心律识别训练");
        SWTUtils.center(this.getParent(), shell);

        Group group_3 = new Group(shell, SWT.NONE);
        group_3.setBounds(10, 3, 574, 156);

        lblEcgtPhoto = new Label(group_3, SWT.NONE);
        lblEcgtPhoto.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
        lblEcgtPhoto.setBounds(5, 12, ECG_WIDTH, ECG_HEIGHT);

        CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER);
        tabFolder.setBounds(10, 172, 574, 200);
        tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

        CTabItem titmTrain = new CTabItem(tabFolder, SWT.NONE);
        titmTrain.setImage(SWTUtils.findImage("tab-ecgt-train.gif"));
        titmTrain.setText("心律选择");

        Composite composite = new Composite(tabFolder, SWT.NONE);
        titmTrain.setControl(composite);

        Group group_4 = new Group(composite, SWT.NONE);
        group_4.setText("结果");
        group_4.setBounds(10, 10, 120, 155);

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

        Button btnReset = new Button(group_4, SWT.NONE);
        btnReset.setBounds(10, 120, 100, 27);
        btnReset.setText("重置");
        btnReset.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                indexNo = 0;
                train.setRightCount(0);
                initComponents();
            }
        });

        Group group_5 = new Group(composite, SWT.NONE);
        group_5.setText("答题");
        group_5.setBounds(136, 10, 422, 155);

        cmbRhythm = new Combo(group_5, SWT.READ_ONLY);
        cmbRhythm.setBounds(10, 20, 130, 25);
        cmbRhythm.setItems(SWTUtils.toArray(this.rhythms));

        cmbExtraSyst = new Combo(group_5, SWT.READ_ONLY);
        cmbExtraSyst.setBounds(148, 20, 130, 25);
        cmbExtraSyst.setItems(SWTUtils.toArray(this.extraSysts));

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

        btnNext = new Button(group_5, SWT.NONE);
        btnNext.setBounds(220, 118, 80, 27);
        btnNext.setText("下一题");
        btnNext.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                EcgtInfoDTO ecgt = findCurrentEcgt();

                if (ecgt != null) {
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
            }
        });

        Button btnComplete = new Button(group_5, SWT.NONE);
        btnComplete.setBounds(332, 118, 80, 27);
        btnComplete.setText("结束");

        Button btnAdvice = new Button(group_5, SWT.NONE);
        btnAdvice.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                EcgtInfoDTO ecgt = findCurrentEcgt();
                if (ecgt != null) {
                    int idx = rhythms.indexOf(ecgt.getEcgtRhythm());
                    if (idx >= 0) {
                        cmbRhythm.select(idx);
                    }

                    idx = extraSysts.indexOf(ecgt.getEcgtSyst());
                    if (idx >= 0) {
                        cmbExtraSyst.select(idx);
                    }

                    txtQrs.setText(StringUtils.trimToEmpty(ecgt.getEcgtQrs()));
                    txtRhythm.setText(StringUtils.trimToEmpty(ecgt.getEcgtRhythm()));
                    txtRate.setText(StringUtils.trimToEmpty(ecgt.getEcgtRate()));
                    txtExtraSyst.setText(StringUtils.trimToEmpty(ecgt.getEcgtSyst()));
                }
            }
        });
        btnAdvice.setBounds(10, 118, 80, 27);
        btnAdvice.setText("参考答案");

        Button btnLast = new Button(group_5, SWT.NONE);
        btnLast.setBounds(120, 118, 80, 27);
        btnLast.setText("上一题");
        btnLast.setVisible(false);

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
        txtHint.setBounds(10, 10, 548, 155);

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

        if (this.indexNo <= 0) {
            this.indexNo = 0;
        }

        if (this.indexNo <= count) {
            this.btnNext.setEnabled(true);
        } else {
            this.btnNext.setEnabled(true);
        }
    }

    /**
     * 获取当前试题
     */
    private EcgtInfoDTO findCurrentEcgt() {
        if (this.indexNo > (this.ids.size() - 1)) {
            return null;
        }

        String id = this.ids.get(this.indexNo);
        return this.emgt.find(Long.valueOf(id));
    }
}
