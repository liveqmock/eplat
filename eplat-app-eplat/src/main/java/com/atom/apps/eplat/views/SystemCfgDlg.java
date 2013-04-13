package com.atom.apps.eplat.views;

import mplat.mgt.dto.CfgInfoDTO;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;

import com.atom.apps.eplat.SWTUtils;

public class SystemCfgDlg extends Dialog {

    private final CfgInfoDTO cfg;
    private Shell            shell;

    private Spinner          spnMinPressDepth;
    private Spinner          spnMaxPressDepth;
    private Spinner          spnMinAirVolume;
    private Spinner          spnMaxAirVolume;

    private Button           rbtnCprModeStand;
    private Button           rbtnCprModeActual;

    private Button           rbtnRhythmClose;
    private Button           rbtnRhythmOpen;

    private Button           rbtnOperateClose;
    private Button           rbtnOperateOpen;

    private Button           rbtnSaverProf;
    private Button           rbtnSaverAmat;
    private Spinner          spnPressCntAmat;
    private Button           rbtnPressCntThree;
    private Button           rbtnPressCntFive;

    private Combo            cmbMonitorName;
    private Button           cbtnJoinHeartGraph;
    private Button           cbtnJoinBloodGraph;

    private Spinner          spnAutoSaveSeconds;

    /**
     * Create the dialog.
     */
    public SystemCfgDlg(Shell parent, int style, CfgInfoDTO cfg) {
        super(parent, style);

        this.cfg = cfg;
        SWTUtils.removeSaveFlag(this.cfg);
    }

    /**
     * Open the dialog.
     */
    public CfgInfoDTO open() {
        createContents();

        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        return cfg;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setSize(460, 370);
        shell.setImages(SWTUtils.findImgIcons());
        shell.setText("系统参数设置");
        shell.setLayout(new BorderLayout(0, 0));

        CTabFolder tabFolder = new CTabFolder(shell, SWT.NONE);
        tabFolder.setLayoutData(BorderLayout.CENTER);
        tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

        CTabItem tabItem01 = new CTabItem(tabFolder, SWT.NONE);
        tabItem01.setText("心肺复苏参数");

        Composite composite_1 = new Composite(tabFolder, SWT.NONE);
        tabItem01.setControl(composite_1);
        composite_1.setLayout(null);

        Group group = new Group(composite_1, SWT.NONE);
        group.setText("按压深度范围：");
        group.setBounds(10, 10, 205, 84);

        Label label = new Label(group, SWT.NONE);
        label.setBounds(10, 24, 85, 17);
        label.setText("最小按压深度：");

        spnMinPressDepth = new Spinner(group, SWT.BORDER);
        spnMinPressDepth.setMaximum(999999);
        spnMinPressDepth.setBounds(101, 21, 70, 23);

        Label lblMm = new Label(group, SWT.NONE);
        lblMm.setBounds(170, 24, 30, 17);
        lblMm.setText("mm");

        Label label_1 = new Label(group, SWT.NONE);
        label_1.setBounds(10, 53, 85, 17);
        label_1.setText("最大按压深度：");

        spnMaxPressDepth = new Spinner(group, SWT.BORDER);
        spnMaxPressDepth.setMaximum(999999);
        spnMaxPressDepth.setBounds(101, 50, 70, 23);

        Label lblMm_1 = new Label(group, SWT.NONE);
        lblMm_1.setBounds(170, 53, 30, 17);
        lblMm_1.setText("mm");

        Group group_1 = new Group(composite_1, SWT.NONE);
        group_1.setText("潮气量范围：");
        group_1.setBounds(233, 10, 205, 84);

        Label label_2 = new Label(group_1, SWT.NONE);
        label_2.setBounds(10, 24, 85, 17);
        label_2.setText("最小潮气量：");

        Label label_3 = new Label(group_1, SWT.NONE);
        label_3.setBounds(10, 53, 85, 17);
        label_3.setText("最大潮气量：");

        spnMinAirVolume = new Spinner(group_1, SWT.BORDER);
        spnMinAirVolume.setMaximum(999999);
        spnMinAirVolume.setBounds(101, 21, 70, 23);

        spnMaxAirVolume = new Spinner(group_1, SWT.BORDER);
        spnMaxAirVolume.setMaximum(999999);
        spnMaxAirVolume.setBounds(101, 50, 70, 23);

        Label lblMl = new Label(group_1, SWT.NONE);
        lblMl.setBounds(170, 24, 30, 17);
        lblMl.setText("ml");

        Label lblMl_1 = new Label(group_1, SWT.NONE);
        lblMl_1.setBounds(170, 53, 30, 17);
        lblMl_1.setText("ml");

        Group grpcpr = new Group(composite_1, SWT.NONE);
        grpcpr.setText("给予CPR 5个循环操作模式：");
        grpcpr.setBounds(10, 100, 428, 55);

        rbtnCprModeStand = new Button(grpcpr, SWT.RADIO);
        rbtnCprModeStand.setBounds(10, 25, 97, 17);
        rbtnCprModeStand.setText("标准操作模式");

        rbtnCprModeActual = new Button(grpcpr, SWT.RADIO);
        rbtnCprModeActual.setBounds(228, 25, 97, 17);
        rbtnCprModeActual.setText("实战操作模式");

        Group group_2 = new Group(composite_1, SWT.NONE);
        group_2.setText("心肺复苏操作节拍音提示：");
        group_2.setBounds(10, 161, 428, 55);

        rbtnRhythmClose = new Button(group_2, SWT.RADIO);
        rbtnRhythmClose.setBounds(10, 25, 97, 17);
        rbtnRhythmClose.setText("无节拍音");

        rbtnRhythmOpen = new Button(group_2, SWT.RADIO);
        rbtnRhythmOpen.setBounds(228, 25, 97, 17);
        rbtnRhythmOpen.setText("有节拍音");

        Group group_3 = new Group(composite_1, SWT.NONE);
        group_3.setText("心肺复苏操作音提示：");
        group_3.setBounds(10, 222, 428, 55);

        rbtnOperateClose = new Button(group_3, SWT.RADIO);
        rbtnOperateClose.setBounds(10, 25, 97, 17);
        rbtnOperateClose.setText("无提示音");

        rbtnOperateOpen = new Button(group_3, SWT.RADIO);
        rbtnOperateOpen.setBounds(228, 25, 97, 17);
        rbtnOperateOpen.setText("有提示音");

        CTabItem tabItem02 = new CTabItem(tabFolder, SWT.NONE);
        tabItem02.setText("CPR操作规则设置");

        Composite composite_2 = new Composite(tabFolder, SWT.NONE);
        tabItem02.setControl(composite_2);
        composite_2.setLayout(new BorderLayout(0, 0));

        Composite composite_3 = new Composite(composite_2, SWT.NONE);
        composite_3.setLayoutData(BorderLayout.CENTER);

        Group group_4 = new Group(composite_3, SWT.NONE);
        group_4.setText("施救者类型");
        group_4.setBounds(10, 10, 430, 55);

        rbtnSaverProf = new Button(group_4, SWT.RADIO);
        rbtnSaverProf.setBounds(10, 25, 97, 17);
        rbtnSaverProf.setText("专业施救者");

        rbtnSaverAmat = new Button(group_4, SWT.RADIO);
        rbtnSaverAmat.setBounds(278, 25, 97, 17);
        rbtnSaverAmat.setText("非专业施救者");

        final Group grpSaverAmat = new Group(composite_3, SWT.NONE);
        grpSaverAmat.setText("非专业施救者");
        grpSaverAmat.setBounds(10, 70, 430, 55);

        Label label_4 = new Label(grpSaverAmat, SWT.NONE);
        label_4.setBounds(10, 26, 90, 17);
        label_4.setText("胸外按压次数：");

        spnPressCntAmat = new Spinner(grpSaverAmat, SWT.BORDER);
        spnPressCntAmat.setMaximum(999999);
        spnPressCntAmat.setBounds(106, 21, 70, 23);

        Label label_5 = new Label(grpSaverAmat, SWT.NONE);
        label_5.setBounds(177, 26, 61, 17);
        label_5.setText("次");

        final Group grpSaverProf = new Group(composite_3, SWT.NONE);
        grpSaverProf.setText("专业施救者");
        grpSaverProf.setBounds(10, 131, 430, 55);

        rbtnPressCntThree = new Button(grpSaverProf, SWT.RADIO);
        rbtnPressCntThree.setBounds(10, 25, 120, 17);
        rbtnPressCntThree.setText("三个循环CPR操作");

        rbtnPressCntFive = new Button(grpSaverProf, SWT.RADIO);
        rbtnPressCntFive.setBounds(278, 25, 120, 17);
        rbtnPressCntFive.setText("五个循环CPR操作");

        CTabItem tabItem03 = new CTabItem(tabFolder, SWT.NONE);
        tabItem03.setText("虚拟监控器参数");

        Composite composite_4 = new Composite(tabFolder, SWT.NONE);
        tabItem03.setControl(composite_4);
        composite_4.setLayout(new BorderLayout(0, 0));

        Composite composite_5 = new Composite(composite_4, SWT.NONE);
        composite_5.setLayoutData(BorderLayout.CENTER);

        Group group_7 = new Group(composite_5, SWT.NONE);
        group_7.setText("虚拟监控器名称");
        group_7.setBounds(10, 10, 430, 57);

        Label label_6 = new Label(group_7, SWT.NONE);
        label_6.setBounds(10, 25, 90, 17);
        label_6.setText("计算机名称：");

        cmbMonitorName = new Combo(group_7, SWT.READ_ONLY);
        cmbMonitorName.setBounds(106, 22, 150, 25);

        Group group_8 = new Group(composite_5, SWT.NONE);
        group_8.setText("心电图导联");
        group_8.setBounds(10, 80, 430, 55);

        cbtnJoinHeartGraph = new Button(group_8, SWT.CHECK);
        cbtnJoinHeartGraph.setBounds(10, 25, 400, 17);
        cbtnJoinHeartGraph.setText("导联接头正确连接模拟人后，显示心电图采样波形");

        Group group_9 = new Group(composite_5, SWT.NONE);
        group_9.setText("血氧探头");
        group_9.setBounds(10, 141, 430, 55);

        cbtnJoinBloodGraph = new Button(group_9, SWT.CHECK);
        cbtnJoinBloodGraph.setBounds(10, 25, 400, 17);
        cbtnJoinBloodGraph.setText("使用血氧探头连接模拟人后，显示血氧采样波形");

        CTabItem tabItem04 = new CTabItem(tabFolder, SWT.NONE);
        tabItem04.setText("虚拟体征日志");

        Composite composite_6 = new Composite(tabFolder, SWT.NONE);
        tabItem04.setControl(composite_6);
        composite_6.setLayout(new BorderLayout(0, 0));

        Composite composite_7 = new Composite(composite_6, SWT.NONE);
        composite_7.setLayoutData(BorderLayout.CENTER);

        Group group_10 = new Group(composite_7, SWT.NONE);
        group_10.setText("虚拟体征自动保存");
        group_10.setBounds(10, 10, 430, 60);

        Label label_7 = new Label(group_10, SWT.NONE);
        label_7.setBounds(10, 28, 70, 17);
        label_7.setText("间隔时间：");

        spnAutoSaveSeconds = new Spinner(group_10, SWT.BORDER);
        spnAutoSaveSeconds.setMaximum(999999);
        spnAutoSaveSeconds.setBounds(86, 25, 70, 23);

        Label label_8 = new Label(group_10, SWT.NONE);
        label_8.setBounds(160, 28, 61, 17);
        label_8.setText("秒");

        Composite composite = new Composite(shell, SWT.NONE);
        composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
        composite.setLayoutData(BorderLayout.SOUTH);

        Button btnSure = new Button(composite, SWT.NONE);
        btnSure.setBounds(181, 0, 80, 27);
        btnSure.setText("确定");
        btnSure.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                onUpdateSystemCfg();
                SWTUtils.setSaveFlag(cfg);

                shell.dispose();
            }
        });

        Button btnCancel = new Button(composite, SWT.NONE);
        btnCancel.setBounds(278, 0, 80, 27);
        btnCancel.setText("取消");
        btnCancel.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                SWTUtils.removeSaveFlag(cfg);

                shell.dispose();
            }
        });

        Button btnHelp = new Button(composite, SWT.NONE);
        btnHelp.setBounds(374, 0, 80, 27);
        btnHelp.setText("帮助");
        btnHelp.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                SWTUtils.openHelpManual();
            }
        });

        // 其他初始化
        rbtnSaverAmat.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                cprSaverMode();
            }
        });

        rbtnSaverProf.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                /*
                boolean prof = rbtnSaverProf.getSelection();
                spnPressCntAmat.setEnabled(!prof);
                rbtnPressCntThree.setEnabled(prof);
                rbtnPressCntFive.setEnabled(prof);
                */
                cprSaverMode();
            }
        });

        // 图片
        Image tabImg = SWTUtils.findImage("tab-sys-cfg.gif");
        tabItem01.setImage(tabImg);
        tabItem02.setImage(tabImg);
        tabItem03.setImage(tabImg);
        tabItem04.setImage(tabImg);

        // 初始化组件
        this.initComponents();
        this.cprSaverMode();
    }

    /**
     * 施救者-CPR操作规则
     */
    private void cprSaverMode() {
        boolean amat = this.rbtnSaverAmat.getSelection();
        this.spnPressCntAmat.setEnabled(amat);
        this.rbtnPressCntThree.setEnabled(!amat);
        this.rbtnPressCntFive.setEnabled(!amat);
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        this.spnMinPressDepth.setSelection(this.cfg.getMinPressDepth());
        this.spnMaxPressDepth.setSelection(this.cfg.getMaxPressDepth());
        this.spnMinAirVolume.setSelection(this.cfg.getMinAirVolume());
        this.spnMaxAirVolume.setSelection(this.cfg.getMaxAirVolume());

        boolean cprStand = (this.cfg.getCprOperateMode() == CfgInfoDTO.CPR_MODE_STAND);
        this.rbtnCprModeStand.setSelection(cprStand);
        this.rbtnCprModeActual.setSelection(!cprStand);

        this.rbtnRhythmOpen.setSelection(this.cfg.isOperateRhythm());
        this.rbtnRhythmOpen.setSelection(!this.cfg.isOperateRhythm());

        this.rbtnOperateOpen.setSelection(this.cfg.isOperateAudio());
        this.rbtnOperateClose.setSelection(!this.cfg.isOperateAudio());

        boolean saverProf = (this.cfg.getSaverMode() == CfgInfoDTO.SAVER_MODE_PROF);
        this.rbtnSaverProf.setSelection(saverProf);
        this.rbtnSaverAmat.setSelection(!saverProf);

        this.spnPressCntAmat.setSelection(this.cfg.getPressCountAmat());
        boolean pressCntProf = (this.cfg.getPressCountProf() == CfgInfoDTO.PROF_CPR_THREE);
        this.rbtnPressCntThree.setSelection(pressCntProf);
        this.rbtnPressCntFive.setSelection(!pressCntProf);

        if (StringUtils.isNotBlank(this.cfg.getMonitorName())) {
            this.cmbMonitorName.setText(this.cfg.getMonitorName());
        }

        this.cbtnJoinHeartGraph.setSelection(this.cfg.isJoinHeartGraph());
        this.cbtnJoinBloodGraph.setSelection(this.cfg.isJoinBloodGraph());

        this.spnAutoSaveSeconds.setSelection(this.cfg.getAutoSaveSeconds());
    }

    /**
     * 填充信息
     */
    private void onUpdateSystemCfg() {
        this.cfg.setMinPressDepth(spnMinPressDepth.getSelection());
        this.cfg.setMaxPressDepth(spnMaxPressDepth.getSelection());
        this.cfg.setMinAirVolume(spnMinAirVolume.getSelection());
        this.cfg.setMaxAirVolume(spnMaxAirVolume.getSelection());

        if (this.rbtnCprModeStand.getSelection()) {
            this.cfg.setCprOperateMode(CfgInfoDTO.CPR_MODE_STAND);
        } else {
            this.cfg.setCprOperateMode(CfgInfoDTO.CPR_MODE_ACTUAL);
        }

        this.cfg.setOperateRhythm(this.rbtnRhythmOpen.getSelection());
        this.cfg.setOperateAudio(this.rbtnOperateOpen.getSelection());

        if (this.rbtnSaverProf.getSelection()) {
            this.cfg.setSaverMode(CfgInfoDTO.SAVER_MODE_PROF);

            if (this.rbtnPressCntThree.getSelection()) {
                this.cfg.setPressCountProf(CfgInfoDTO.PROF_CPR_THREE);
            } else {
                this.cfg.setPressCountProf(CfgInfoDTO.PROF_CPR_FIVE);
            }
        } else {
            this.cfg.setSaverMode(CfgInfoDTO.SAVER_Mode_AMAT);
            this.cfg.setPressCountAmat(spnPressCntAmat.getSelection());
        }

        this.cfg.setMonitorName(this.cmbMonitorName.getText());
        this.cfg.setJoinHeartGraph(this.cbtnJoinHeartGraph.getSelection());
        this.cfg.setJoinBloodGraph(this.cbtnJoinBloodGraph.getSelection());

        this.cfg.setAutoSaveSeconds(this.spnAutoSaveSeconds.getSelection());
    }

}
