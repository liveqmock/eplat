package com.atom.apps.eplat.views;

import java.util.List;

import mplat.mgt.EcgtMgt;
import mplat.mgt.dto.EcgtInfoDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.utils.LogUtils;

import swing2swt.layout.BorderLayout;

public class EcgtUpdateDlg extends Dialog {

    private final EcgtInfoDTO ecgt;
    private Shell             shell;

    private Combo             cmbQrs;
    private Combo             cmbRhythm;
    private Combo             cmbSyst;

    private Text              txtRate;
    private Text              txtHintRate;
    private Text              txtHintRegular;
    private Text              txtHintWave;
    private Text              txtHintInterval;
    private Text              txtHintQrs;

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public EcgtUpdateDlg(Shell parent, int style, EcgtInfoDTO ecgt) {
        super(parent, style);

        this.ecgt = ecgt;
    }

    /**
     * Open the dialog.
     * @return the result
     */
    public EcgtInfoDTO open() {
        createContents();

        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        return this.ecgt;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setSize(600, 465);
        shell.setImages(SWTUtils.findImgIcons());
        shell.setText("增加/修改心律识别试题");
        shell.setLayout(new BorderLayout(0, 0));
        SWTUtils.center(this.getParent(), shell);

        Composite cptCenter = new Composite(shell, SWT.NONE);
        cptCenter.setLayoutData(BorderLayout.CENTER);

        Group grpQrs = new Group(cptCenter, SWT.NONE);
        grpQrs.setText("QRS");
        grpQrs.setBounds(10, 10, 270, 60);

        cmbQrs = new Combo(grpQrs, SWT.READ_ONLY);
        cmbQrs.setBounds(10, 21, 220, 25);

        Group grpBasicRhythm = new Group(cptCenter, SWT.NONE);
        grpBasicRhythm.setText("Basic Rhythm");
        grpBasicRhythm.setBounds(314, 10, 270, 60);

        cmbRhythm = new Combo(grpBasicRhythm, SWT.READ_ONLY);
        cmbRhythm.setBounds(10, 21, 220, 25);

        Group grpExtraSyst = new Group(cptCenter, SWT.NONE);
        grpExtraSyst.setText("Extra Syst");
        grpExtraSyst.setBounds(10, 76, 270, 60);

        cmbSyst = new Combo(grpExtraSyst, SWT.READ_ONLY);
        cmbSyst.setBounds(10, 21, 220, 25);

        Group grpRate = new Group(cptCenter, SWT.NONE);
        grpRate.setText("Rate");
        grpRate.setBounds(314, 76, 270, 60);

        txtRate = new Text(grpRate, SWT.BORDER);
        txtRate.setBounds(10, 21, 220, 23);

        Group grpHintRate = new Group(cptCenter, SWT.NONE);
        grpHintRate.setText("Hint Rate");
        grpHintRate.setBounds(10, 142, 270, 90);

        txtHintRate = new Text(grpHintRate, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        txtHintRate.setBounds(10, 21, 250, 55);

        Group grpHintRegularity = new Group(cptCenter, SWT.NONE);
        grpHintRegularity.setText("Hint Regularity");
        grpHintRegularity.setBounds(314, 142, 270, 90);

        txtHintRegular = new Text(grpHintRegularity, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        txtHintRegular.setBounds(10, 21, 250, 55);

        Group grpHintPwave = new Group(cptCenter, SWT.NONE);
        grpHintPwave.setText("Hint P-Wave");
        grpHintPwave.setBounds(10, 238, 270, 90);

        txtHintWave = new Text(grpHintPwave, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        txtHintWave.setBounds(10, 21, 250, 55);

        Group grpHintPrinterval = new Group(cptCenter, SWT.NONE);
        grpHintPrinterval.setText("Hint PR-Interval");
        grpHintPrinterval.setBounds(314, 238, 270, 90);

        txtHintInterval = new Text(grpHintPrinterval, SWT.BORDER);
        txtHintInterval.setBounds(10, 21, 250, 55);

        Group grpHintQrs = new Group(cptCenter, SWT.NONE);
        grpHintQrs.setText("Hint QRS");
        grpHintQrs.setBounds(10, 334, 574, 65);

        txtHintQrs = new Text(grpHintQrs, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        txtHintQrs.setBounds(10, 18, 554, 40);

        Composite cptBottom = new Composite(shell, SWT.NONE);
        cptBottom.setLayoutData(BorderLayout.SOUTH);

        Button btnSure = new Button(cptBottom, SWT.NONE);
        btnSure.setText("确定");
        btnSure.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                onUpdateEcgt();
                ecgt.setSaveFlag();
                shell.dispose();
            }
        });
        btnSure.setBounds(407, 10, 80, 27);

        Button btnCancel = new Button(cptBottom, SWT.NONE);
        btnCancel.setText("取消");
        btnCancel.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                ecgt.removeSaveFlag();
                shell.dispose();
            }
        });
        btnCancel.setBounds(504, 10, 80, 27);

        // 下拉框初始化
        List<String> qrs = EcgtMgt.findEcgtQRS();
        cmbQrs.setItems(SWTUtils.toArray(qrs));

        List<String> rhythms = EcgtMgt.findEcgtRhythm();
        cmbRhythm.setItems(SWTUtils.toArray(rhythms));

        List<String> systs = EcgtMgt.findEcgtExtSyst();
        cmbSyst.setItems(SWTUtils.toArray(systs));

        // 初始化组件
        if (this.ecgt.getId() > 0L) {
            cmbQrs.select(qrs.indexOf(this.ecgt.getEcgtQrs()));
            cmbRhythm.select(rhythms.indexOf(this.ecgt.getEcgtRhythm()));
            cmbSyst.select(systs.indexOf(this.ecgt.getEcgtSyst()));

            txtRate.setText(this.ecgt.getEcgtRate());

            txtHintRate.setText(this.ecgt.getTipRate());
            txtHintRegular.setText(this.ecgt.getTipRegular());
            txtHintWave.setText(this.ecgt.getTipWave());
            txtHintInterval.setText(this.ecgt.getTipInterval());
            txtHintQrs.setText(this.ecgt.getTipQrs());
        }
    }

    /**
     * 新增/更新试题
     */
    private void onUpdateEcgt() {
        ecgt.setEcgtQrs(cmbQrs.getText());
        ecgt.setEcgtRhythm(cmbRhythm.getText());
        ecgt.setEcgtSyst(cmbSyst.getText());

        int rate = 0;
        try {
            rate = Integer.parseInt(txtRate.getText());
        } catch (Exception e) {
            LogUtils.get().warn("心率值应该为数字-{}", txtRate.getText());
        }
        ecgt.setEcgtRate(Integer.toString(rate));

        ecgt.setTipQrs(txtHintQrs.getText());
        ecgt.setTipRate(txtHintRate.getText());
        ecgt.setTipRegular(txtHintRegular.getText());
        ecgt.setTipWave(txtHintWave.getText());
        ecgt.setTipInterval(txtHintInterval.getText());
    }

}
