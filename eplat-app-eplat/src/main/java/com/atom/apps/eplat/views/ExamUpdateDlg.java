package com.atom.apps.eplat.views;

import mplat.mgt.dto.ExamInfoDTO;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

import com.atom.apps.eplat.SWTUtils;

public class ExamUpdateDlg extends Dialog {

    private final ExamInfoDTO exam;
    private Shell             shell;

    private Text              txtTitle;
    private Text              txtItemA;
    private Text              txtItemB;
    private Text              txtItemC;
    private Text              txtItemD;
    private Combo             cboRgtNo;

    /**
     * Create the dialog.
     */
    public ExamUpdateDlg(Shell parent, int style, ExamInfoDTO exam) {
        super(parent, style);

        this.exam = exam;
    }

    /**
     * Open the dialog.
     * @return the result
     */
    public ExamInfoDTO open() {
        createContents();

        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        return exam;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setSize(600, 440);
        shell.setImages(SWTUtils.findImgIcons());
        shell.setText("增加/修改ACLS理论知识试题");
        shell.setLayout(new BorderLayout(0, 0));

        Composite cptCenter = new Composite(shell, SWT.NONE);
        cptCenter.setLayoutData(BorderLayout.CENTER);
        cptCenter.setLayout(new FormLayout());

        Group grpTitle = new Group(cptCenter, SWT.NONE);
        grpTitle.setText("标题");
        FormData fd_grpTitle = new FormData();
        fd_grpTitle.top = new FormAttachment(0, 10);
        fd_grpTitle.left = new FormAttachment(0, 10);
        fd_grpTitle.bottom = new FormAttachment(0, 79);
        fd_grpTitle.right = new FormAttachment(0, 584);
        grpTitle.setLayoutData(fd_grpTitle);

        txtTitle = new Text(grpTitle, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        txtTitle.setBounds(10, 22, 554, 37);

        Group grpItemA = new Group(cptCenter, SWT.NONE);
        grpItemA.setText("选项A");
        FormData fd_grpItemA = new FormData();
        fd_grpItemA.bottom = new FormAttachment(grpTitle, 60, SWT.BOTTOM);
        fd_grpItemA.top = new FormAttachment(grpTitle, 6);
        fd_grpItemA.right = new FormAttachment(grpTitle, 0, SWT.RIGHT);
        fd_grpItemA.left = new FormAttachment(0, 10);
        grpItemA.setLayoutData(fd_grpItemA);

        txtItemA = new Text(grpItemA, SWT.BORDER);
        txtItemA.setBounds(10, 20, 554, 23);

        Group grpItemB = new Group(cptCenter, SWT.NONE);
        grpItemB.setText("选项B");
        FormData fd_grpItemB = new FormData();
        fd_grpItemB.bottom = new FormAttachment(grpItemA, 60, SWT.BOTTOM);
        fd_grpItemB.top = new FormAttachment(grpItemA, 6);
        fd_grpItemB.right = new FormAttachment(grpTitle, 0, SWT.RIGHT);
        fd_grpItemB.left = new FormAttachment(0, 10);
        grpItemB.setLayoutData(fd_grpItemB);

        txtItemB = new Text(grpItemB, SWT.BORDER);
        txtItemB.setBounds(10, 20, 554, 23);

        Group grpItemC = new Group(cptCenter, SWT.NONE);
        grpItemC.setText("选项C");
        FormData fd_grpItemC = new FormData();
        fd_grpItemC.bottom = new FormAttachment(grpItemB, 60, SWT.BOTTOM);
        fd_grpItemC.top = new FormAttachment(grpItemB, 6);
        fd_grpItemC.right = new FormAttachment(grpTitle, 0, SWT.RIGHT);
        fd_grpItemC.left = new FormAttachment(0, 10);
        grpItemC.setLayoutData(fd_grpItemC);

        txtItemC = new Text(grpItemC, SWT.BORDER);
        txtItemC.setBounds(10, 20, 554, 23);

        Group grpItemD = new Group(cptCenter, SWT.NONE);
        grpItemD.setText("选项D");
        FormData fd_grpItemD = new FormData();
        fd_grpItemD.bottom = new FormAttachment(grpItemC, 60, SWT.BOTTOM);
        fd_grpItemD.top = new FormAttachment(grpItemC, 6);
        fd_grpItemD.right = new FormAttachment(grpTitle, 0, SWT.RIGHT);
        fd_grpItemD.left = new FormAttachment(0, 10);
        grpItemD.setLayoutData(fd_grpItemD);

        txtItemD = new Text(grpItemD, SWT.BORDER);
        txtItemD.setBounds(10, 21, 554, 23);

        Group grpRgtNo = new Group(cptCenter, SWT.NONE);
        grpRgtNo.setText("正确答案");
        FormData fd_grpRgtNo = new FormData();
        fd_grpRgtNo.bottom = new FormAttachment(grpItemD, 60, SWT.BOTTOM);
        fd_grpRgtNo.top = new FormAttachment(grpItemD, 6);
        fd_grpRgtNo.left = new FormAttachment(grpTitle, 0, SWT.LEFT);
        fd_grpRgtNo.right = new FormAttachment(100, -9);
        grpRgtNo.setLayoutData(fd_grpRgtNo);

        cboRgtNo = new Combo(grpRgtNo, SWT.READ_ONLY);
        cboRgtNo.setItems(new String[] { "A", "B", "C", "D" });
        cboRgtNo.setBounds(10, 21, 278, 25);

        Composite cptBottom = new Composite(shell, SWT.NONE);
        cptBottom.setLayoutData(BorderLayout.SOUTH);
        cptBottom.setLayout(null);

        Button btnSure = new Button(cptBottom, SWT.NONE);
        btnSure.setBounds(418, 0, 80, 27);
        btnSure.setText("确定");
        btnSure.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                onUpdateExam();
                shell.dispose();
            }
        });

        Button btnCancel = new Button(cptBottom, SWT.NONE);
        btnCancel.setBounds(504, 0, 80, 27);
        btnCancel.setText("取消");
        btnCancel.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });

        // 初始化组件
        this.initComponents();
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        if (this.exam.getId() > 0L) {
            this.txtTitle.setText(this.exam.getTitle());
            this.txtItemA.setText(this.exam.getItemA());
            this.txtItemB.setText(this.exam.getItemB());
            this.txtItemC.setText(this.exam.getItemC());
            this.txtItemD.setText(this.exam.getItemD());
            this.cboRgtNo.setText(this.exam.getRgtNo());
        }
    }

    /**
     * 新增/更新试题
     */
    private void onUpdateExam() {
        String title = this.txtTitle.getText();
        if (StringUtils.isBlank(title)) {
            SWTUtils.alert(this.shell, "试题标题不能为空！");
            return;
        }

        String itemA = this.txtItemA.getText();
        if (StringUtils.isBlank(itemA)) {
            SWTUtils.alert(this.shell, "试题选项A不能为空！");
            return;
        }

        String itemB = this.txtItemB.getText();
        if (StringUtils.isBlank(itemB)) {
            SWTUtils.alert(this.shell, "试题选项B不能为空！");
            return;
        }

        String itemC = this.txtItemC.getText();
        if (StringUtils.isBlank(itemC)) {
            SWTUtils.alert(this.shell, "试题选项C不能为空！");
            return;
        }

        String itemD = this.txtItemD.getText();
        if (StringUtils.isBlank(itemD)) {
            SWTUtils.alert(this.shell, "试题选项D不能为空！");
            return;
        }

        String rgtNo = this.cboRgtNo.getText();
        if (StringUtils.isBlank(rgtNo)) {
            SWTUtils.alert(this.shell, "试题正确选项不能为空！");
            return;
        }

        // 新增/修改
        this.exam.setTitle(title);
        this.exam.setItemA(itemA);
        this.exam.setItemB(itemB);
        this.exam.setItemC(itemC);
        this.exam.setItemD(itemD);
        this.exam.setRgtNo(rgtNo);
    }

}
