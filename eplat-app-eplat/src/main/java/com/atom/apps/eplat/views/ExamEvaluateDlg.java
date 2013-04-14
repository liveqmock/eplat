package com.atom.apps.eplat.views;

import java.util.Date;
import java.util.List;

import mplat.mgt.ExamMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.ExamInfoDTO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.dtos.ExamEvaluateDTO;
import com.atom.core.lang.utils.LogUtils;

public class ExamEvaluateDlg extends Dialog {

    private int                   indexNo  = 0;

    private final List<String>    ids;
    private final ExamEvaluateDTO evaluate = new ExamEvaluateDTO();

    private final ExamMgt         emgt     = MgtFactory.get().findExamMgt();

    private Shell                 shell;
    private Display               display;

    private Label                 lblTotile;
    private Label                 lblTimer;
    private Label                 lblTitle;
    private Label                 lblItemA;
    private Label                 lblItemB;
    private Label                 lblItemC;
    private Label                 lblItemD;
    private Combo                 cmbRgtNo;
    private Combo                 cmbAdvNo;

    private Button                btnPrev;
    private Button                btnNext;
    private Button                btnAdvice;
    private Button                btnComplete;

    /**
     * Create the dialog.
     */
    public ExamEvaluateDlg(Shell parent, int style, List<String> ids) {
        super(parent, style);

        this.ids = ids;
        this.evaluate.setTotalCount(this.ids.size());
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

        return this.evaluate;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setSize(600, 440);
        shell.setImages(SWTUtils.findImgIcons());
        shell.setText("ACLS理论知识试题考核");
        SWTUtils.center(this.getParent(), shell);
        shell.setLayout(new FormLayout());

        Group group = new Group(shell, SWT.NONE);
        FormData fd_group = new FormData();
        fd_group.top = new FormAttachment(0);
        fd_group.left = new FormAttachment(0, 10);
        fd_group.bottom = new FormAttachment(0, 57);
        fd_group.right = new FormAttachment(0, 584);
        group.setLayoutData(fd_group);

        lblTotile = new Label(group, SWT.NONE);
        lblTotile.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
        lblTotile.setBounds(10, 21, 188, 26);
        lblTotile.setText("第1题，共13题");

        lblTimer = new Label(group, SWT.NONE);
        lblTimer.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
        lblTimer.setAlignment(SWT.RIGHT);
        lblTimer.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
        lblTimer.setBounds(394, 21, 170, 26);
        lblTimer.setText("00:12:35");

        lblTitle = new Label(shell, SWT.WRAP);
        lblTitle.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
        FormData fd_lblTitle = new FormData();
        fd_lblTitle.bottom = new FormAttachment(group, 50, SWT.BOTTOM);
        fd_lblTitle.top = new FormAttachment(group, 6);
        fd_lblTitle.right = new FormAttachment(group, 0, SWT.RIGHT);
        fd_lblTitle.left = new FormAttachment(0, 10);
        lblTitle.setLayoutData(fd_lblTitle);
        lblTitle.setText("1.这是试题标题");

        lblItemA = new Label(shell, SWT.WRAP);
        FormData fd_lblItemA = new FormData();
        fd_lblItemA.bottom = new FormAttachment(lblTitle, 56, SWT.BOTTOM);
        fd_lblItemA.top = new FormAttachment(lblTitle, 18);
        fd_lblItemA.right = new FormAttachment(group, 0, SWT.RIGHT);
        fd_lblItemA.left = new FormAttachment(0, 10);
        lblItemA.setLayoutData(fd_lblItemA);
        lblItemA.setText("A.选项A的内容");

        lblItemB = new Label(shell, SWT.WRAP);
        FormData fd_lblItemB = new FormData();
        fd_lblItemB.bottom = new FormAttachment(lblItemA, 44, SWT.BOTTOM);
        fd_lblItemB.top = new FormAttachment(lblItemA, 6);
        fd_lblItemB.right = new FormAttachment(group, 0, SWT.RIGHT);
        fd_lblItemB.left = new FormAttachment(0, 10);
        lblItemB.setLayoutData(fd_lblItemB);
        lblItemB.setText("B.选项B的内容");

        lblItemC = new Label(shell, SWT.WRAP);
        FormData fd_lblItemC = new FormData();
        fd_lblItemC.bottom = new FormAttachment(lblItemB, 44, SWT.BOTTOM);
        fd_lblItemC.top = new FormAttachment(lblItemB, 6);
        fd_lblItemC.right = new FormAttachment(group, 0, SWT.RIGHT);
        fd_lblItemC.left = new FormAttachment(0, 10);
        lblItemC.setLayoutData(fd_lblItemC);
        lblItemC.setText("C.选项C的内容");

        lblItemD = new Label(shell, SWT.WRAP);
        FormData fd_lblItemD = new FormData();
        fd_lblItemD.bottom = new FormAttachment(lblItemC, 44, SWT.BOTTOM);
        fd_lblItemD.top = new FormAttachment(lblItemC, 6);
        fd_lblItemD.right = new FormAttachment(group, 0, SWT.RIGHT);
        fd_lblItemD.left = new FormAttachment(0, 10);
        lblItemD.setLayoutData(fd_lblItemD);
        lblItemD.setText("D.选项D的内容");

        Group group_1 = new Group(shell, SWT.NONE);
        group_1.setText("答案");
        FormData fd_group_1 = new FormData();
        fd_group_1.right = new FormAttachment(group, -340, SWT.RIGHT);
        fd_group_1.bottom = new FormAttachment(lblItemD, 66, SWT.BOTTOM);
        fd_group_1.top = new FormAttachment(lblItemD, 2);
        fd_group_1.left = new FormAttachment(0, 10);
        group_1.setLayoutData(fd_group_1);

        cmbRgtNo = new Combo(group_1, SWT.READ_ONLY);
        cmbRgtNo.setItems(new String[] {"", "A", "B", "C", "D"});
        cmbRgtNo.setBounds(10, 24, 182, 25);

        btnPrev = new Button(shell, SWT.NONE);
        btnPrev.setText("上一题");
        btnPrev.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                indexNo -= 1;
                if (indexNo >= 0) {
                    initComponents();
                }
            }
        });
        FormData fd_btnPrev = new FormData();
        fd_btnPrev.bottom = new FormAttachment(100, -10);
        fd_btnPrev.left = new FormAttachment(group, 0, SWT.LEFT);
        btnPrev.setLayoutData(fd_btnPrev);

        btnNext = new Button(shell, SWT.NONE);
        btnNext.setText("下一题");
        btnNext.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                indexNo += 1;
                if (indexNo <= (ids.size() - 1)) {
                    initComponents();
                }
            }
        });
        FormData fd_btnNext = new FormData();
        fd_btnNext.top = new FormAttachment(btnPrev, 0, SWT.TOP);
        fd_btnNext.right = new FormAttachment(100, -10);
        btnNext.setLayoutData(fd_btnNext);

        btnAdvice = new Button(shell, SWT.NONE);
        btnAdvice.setText("参考答案");
        btnAdvice.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String id = ids.get(indexNo);
                ExamInfoDTO exam = emgt.find(Long.valueOf(id));
                if (exam != null) {
                    cmbAdvNo.setText(exam.getRgtNo());
                }
            }
        });
        FormData fd_btnAdvice = new FormData();
        fd_btnAdvice.top = new FormAttachment(btnPrev, 0, SWT.TOP);
        fd_btnAdvice.left = new FormAttachment(btnPrev, 129);
        btnAdvice.setLayoutData(fd_btnAdvice);

        btnComplete = new Button(shell, SWT.NONE);
        btnComplete.setText("答题完成");
        btnComplete.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
        FormData fd_btnComplete = new FormData();
        fd_btnComplete.top = new FormAttachment(btnPrev, 0, SWT.TOP);
        fd_btnComplete.left = new FormAttachment(btnAdvice, 94);
        btnComplete.setLayoutData(fd_btnComplete);

        Group group_2 = new Group(shell, SWT.NONE);
        group_2.setText("参考答案");
        FormData fd_group_2 = new FormData();
        fd_group_2.right = new FormAttachment(group, 0, SWT.RIGHT);
        fd_group_2.top = new FormAttachment(group_1, -64);
        fd_group_2.bottom = new FormAttachment(group_1, 0, SWT.BOTTOM);
        fd_group_2.left = new FormAttachment(btnComplete, 0, SWT.LEFT);
        group_2.setLayoutData(fd_group_2);

        cmbAdvNo = new Combo(group_2, SWT.READ_ONLY);
        cmbAdvNo.setItems(new String[] {"", "A", "B", "C", "D"});
        cmbAdvNo.setBounds(10, 23, 198, 31);

        // 初始化
        this.initComponents();
    }

    /**
     * 启动计时器
     */
    private void startTimer() {
        SWTUtils.execute(new Runnable() {
            public void run() {
                while (!shell.isDisposed()) {
                    evaluate.setFinish(new Date());
                    long millis = evaluate.findMillis();
                    int hours = (int) (millis / DateUtils.MILLIS_PER_HOUR);
                    int minutes = (int) ((millis % DateUtils.MILLIS_PER_HOUR) / DateUtils.MILLIS_PER_MINUTE);
                    int seconds = (int) ((millis % DateUtils.MILLIS_PER_MINUTE) / DateUtils.MILLIS_PER_SECOND);

                    final StringBuilder txt = new StringBuilder();
                    txt.append(StringUtils.leftPad(Integer.toString(hours), 2, "0"));
                    txt.append(":").append(StringUtils.leftPad(Integer.toString(minutes), 2, "0"));
                    txt.append(":").append(StringUtils.leftPad(Integer.toString(seconds), 2, "0"));

                    display.syncExec(new Runnable() {
                        public void run() {
                            lblTimer.setText(txt.toString());
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

        String id = this.ids.get(this.indexNo);
        ExamInfoDTO exam = this.emgt.find(Long.valueOf(id));
        if (exam != null) {
            this.lblTotile.setText("第" + (this.indexNo + 1) + "题，共" + this.ids.size() + "题");
            this.lblTitle.setText((this.indexNo + 1) + "." + exam.getTitle());
            this.lblItemA.setText("A." + exam.getItemA());
            this.lblItemB.setText("B." + exam.getItemB());
            this.lblItemC.setText("C." + exam.getItemC());
            this.lblItemD.setText("D." + exam.getItemD());
            
            this.cmbRgtNo.clearSelection();
            this.cmbAdvNo.clearSelection();
            
            this.cmbRgtNo.setText("");
            this.cmbAdvNo.setText("");
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
            this.btnPrev.setEnabled(false);
            this.btnNext.setEnabled(false);
        } else {
            if (this.indexNo <= 0) {
                this.indexNo = 0;

                this.btnPrev.setEnabled(false);
            } else {
                this.btnPrev.setEnabled(true);
            }

            if (this.indexNo >= (count - 1)) {
                this.indexNo = (count - 1);

                this.btnNext.setEnabled(false);
            } else {
                this.btnNext.setEnabled(true);
            }
        }
    }

}
