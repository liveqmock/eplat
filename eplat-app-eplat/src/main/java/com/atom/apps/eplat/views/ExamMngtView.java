package com.atom.apps.eplat.views;

import java.util.Collections;
import java.util.List;

import mplat.mgt.ExamMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.ExamInfoDTO;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import swing2swt.layout.BorderLayout;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.utils.LogUtils;

public class ExamMngtView extends SashForm {
    private static final int ID_WIDTH   = 40;
    private static final int DETA_WIDTH = 20;

    private static final int TAB_SRC    = 1;
    private static final int TAB_DST    = 2;

    public static final int  IDX_ID     = 0;
    public static final int  IDX_TITLE  = 1;

    private final boolean    mngt;
    private final ExamMgt    emgt       = MgtFactory.get().findExamMgt();

    private Composite        cmpSrc;
    private Composite        cmpDst;

    private ToolBar          tbarSrc;
    private ToolItem         titmCreateSrc;
    private ToolItem         titmUpdateSrc;
    private ToolItem         titmRemoveSrc;
    private ToolItem         titmSelectSrc;

    private TableViewer      tbvSrc;
    private Table            tblSrc;

    private ToolBar          tbarDst;
    private TableViewer      tbvDst;
    private Table            tblDst;

    /**
     * Create the composite.
     */
    public ExamMngtView(Composite parent, int style, final boolean mngt) {
        super(parent, SWT.NONE);

        this.mngt = mngt;

        this.cmpSrc = new Composite(this, SWT.NO_BACKGROUND);
        this.cmpSrc.setLayout(new BorderLayout(0, 0));

        tbarSrc = new ToolBar(this.cmpSrc, SWT.FLAT | SWT.RIGHT);
        tbarSrc.setLayoutData(BorderLayout.NORTH);

        ToolItem titmRefreshSrc = new ToolItem(tbarSrc, SWT.NONE);
        titmRefreshSrc.setImage(SWTUtils.findImage("icon-refresh.png"));
        titmRefreshSrc.setText("刷新");
        titmRefreshSrc.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                refresh(tblSrc, TAB_SRC);
            }
        });

        titmCreateSrc = new ToolItem(tbarSrc, SWT.NONE);
        titmCreateSrc.setImage(SWTUtils.findImage("icon-add.png"));
        titmCreateSrc.setText("增加");
        titmCreateSrc.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                ExamInfoDTO exam = new ExamInfoDTO();
                new ExamUpdateDlg(getShell(), SWT.NONE, exam).open();

                if (StringUtils.isNotBlank(exam.getTitle())) {
                    emgt.create(exam);

                    TableItem titm = new TableItem(tblSrc, SWT.NONE);
                    titm.setText(IDX_ID, String.valueOf(exam.getId()));
                    titm.setText(IDX_TITLE, exam.getTitle());

                    LogUtils.get().info("增加试题成功-{}", exam);
                }
            }
        });

        titmUpdateSrc = new ToolItem(tbarSrc, SWT.NONE);
        titmUpdateSrc.setImage(SWTUtils.findImage("icon-edit.png"));
        titmUpdateSrc.setText("编辑");
        titmUpdateSrc.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem item = findSelection(tblSrc);
                if (item == null) {
                    return;
                }

                String id = item.getText(IDX_ID);
                ExamInfoDTO exam = emgt.find(Long.valueOf(id));

                new ExamUpdateDlg(getShell(), SWT.NONE, exam).open();
                emgt.update(exam);

                TableItem titem = SWTUtils.findTableItem(tblSrc, IDX_ID, String.valueOf(exam.getId()));
                if (titem != null) {
                    titem.setText(IDX_TITLE, exam.getTitle());
                    LogUtils.get().info("更新试题成功-{}", item);
                }
            }
        });

        titmRemoveSrc = new ToolItem(tbarSrc, SWT.NONE);
        titmRemoveSrc.setImage(SWTUtils.findImage("icon-delete.png"));
        titmRemoveSrc.setText("删除");
        titmRemoveSrc.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem item = findSelection(tblSrc);
                if (item == null) {
                    return;
                }

                LogUtils.get().info("准备删除数据-{}", item);

                boolean rtn = SWTUtils.confirm(getShell(), "确定删除ACLS理论知识试题吗？");
                if (rtn) {
                    emgt.remove(Long.valueOf(item.getText(IDX_ID)));
                    SWTUtils.removeValue(tblSrc, IDX_ID, item.getText(IDX_ID));

                    LogUtils.get().info("删除试题成功-{}", item);
                }
            }
        });

        this.titmSelectSrc = new ToolItem(tbarSrc, SWT.NONE);
        this.titmSelectSrc.setImage(SWTUtils.findImage("icon-select.png"));
        this.titmSelectSrc.setText("选择");
        this.titmSelectSrc.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem item = findSelection(tblSrc);
                if (item == null) {
                    return;
                }

                int max = SWTUtils.MAX_EXAM_COUNT;
                if (tblDst.getItemCount() >= max) {
                    SWTUtils.alert(getShell(), "你选择的个数已经为最大[" + max + "]，请先去除，然后选择！");
                    return;
                }

                TableItem nitem = new TableItem(tblDst, SWT.NONE);
                nitem.setText(IDX_ID, item.getText(IDX_ID));
                nitem.setText(IDX_TITLE, item.getText(IDX_TITLE));

                SWTUtils.removeValue(tblSrc, IDX_ID, item.getText(IDX_ID));
            }
        });

        tbvSrc = new TableViewer(cmpSrc, SWT.BORDER | SWT.FULL_SELECTION);
        tblSrc = tbvSrc.getTable();
        tblSrc.setLinesVisible(true);
        tblSrc.setHeaderVisible(true);
        tblSrc.setLayoutData(BorderLayout.CENTER);

        TableColumn clmnSrcId = new TableColumn(tblSrc, SWT.RIGHT);
        clmnSrcId.setResizable(false);
        clmnSrcId.setWidth(ID_WIDTH);
        clmnSrcId.setText("ID");

        final TableColumn clmnSrcTitle = new TableColumn(tblSrc, SWT.NONE);
        clmnSrcTitle.setWidth(200);
        clmnSrcTitle.setText("标题");

        this.cmpDst = new Composite(this, SWT.NO_BACKGROUND);
        this.cmpDst.setLayout(new BorderLayout(0, 0));

        tbarDst = new ToolBar(this.cmpDst, SWT.FLAT | SWT.RIGHT);
        tbarDst.setLayoutData(BorderLayout.NORTH);

        ToolItem titmRefreshDst = new ToolItem(tbarDst, SWT.NONE);
        titmRefreshDst.setImage(SWTUtils.findImage("icon-refresh.png"));
        titmRefreshDst.setText("刷新");
        titmRefreshDst.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                refresh(tblDst, TAB_DST);
            }
        });

        ToolItem titmRemoveDst = new ToolItem(tbarDst, SWT.NONE);
        titmRemoveDst.setImage(SWTUtils.findImage("icon-delete.png"));
        titmRemoveDst.setText("去除");
        titmRemoveDst.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem item = findSelection(tblDst);
                if (item == null) {
                    return;
                }

                SWTUtils.removeValue(tblDst, IDX_ID, item.getText(IDX_ID));
                refresh(tblSrc, TAB_SRC);
            }
        });

        ToolItem titmNextDst = new ToolItem(tbarDst, SWT.NONE);
        titmNextDst.setImage(SWTUtils.findImage("icon-go.png"));
        titmNextDst.setText("下一步");
        titmNextDst.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (tblDst.getItemCount() < 1) {
                    SWTUtils.alert(getShell(), "请至少选择一个试题！");
                    return;
                }

                List<String> ids = SWTUtils.findValues(tblDst, IDX_ID);
                LogUtils.get().info("选择试题-{}", ids);

                new ExamTrainDlg(getShell(), SWT.NONE, ids).open();
            }
        });

        tbvDst = new TableViewer(this.cmpDst, SWT.BORDER | SWT.FULL_SELECTION);
        tblDst = tbvDst.getTable();
        tblDst.setLinesVisible(true);
        tblDst.setHeaderVisible(true);
        tblDst.setLayoutData(BorderLayout.CENTER);

        final TableColumn clmnDstId = new TableColumn(tblDst, SWT.RIGHT);
        clmnDstId.setResizable(false);
        clmnDstId.setWidth(ID_WIDTH);
        clmnDstId.setText("ID");

        final TableColumn clmnDstTitle = new TableColumn(tblDst, SWT.NONE);
        clmnDstTitle.setWidth(200);
        clmnDstTitle.setText("标题");

        this.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                if (mngt) {
                    clmnSrcTitle.setWidth(getBounds().width - ID_WIDTH - DETA_WIDTH);
                    clmnDstId.setWidth(0);
                    clmnDstTitle.setWidth(0);
                } else {
                    int width = (getBounds().width - ID_WIDTH * 2) / 2 - DETA_WIDTH;
                    clmnSrcTitle.setWidth(width);
                    clmnDstTitle.setWidth(width);
                }
            }
        });

        // 分隔
        if (this.mngt) {
            super.setWeights(new int[] { 1, 0 });
        } else {
            super.setWeights(new int[] { 1, 1 });
        }

        // 初始化组件
        this.initComposites();

        // 初始化数据
        this.refresh(this.tblSrc, TAB_SRC);
    }

    /**
     * 初始化组件
     */
    private void initComposites() {
        if (this.mngt) {
            this.cmpDst.setVisible(false);

            this.titmSelectSrc.setEnabled(false);
        } else {
            this.titmCreateSrc.setEnabled(false);
            this.titmUpdateSrc.setEnabled(false);
            this.titmRemoveSrc.setEnabled(false);
        }
    }

    /**
     * 刷新表格
     */
    private void refresh(Table table, int tabType) {
        Table tabDst = this.tblDst;
        if (tabType == TAB_DST) {
            tabDst = this.tblSrc;
        }

        SWTUtils.removeValues(table);

        List<ExamInfoDTO> exams = this.emgt.findAll();
        Collections.sort(exams);
        for (ExamInfoDTO exam : exams) {
            this.crateTableItem(table, exam);
        }

        List<String> dstIds = SWTUtils.findValues(tabDst, IDX_ID);
        SWTUtils.removeValues(table, IDX_ID, dstIds);
    }
    
    /**
     * 增加数据行
     */
    private void crateTableItem(Table table, ExamInfoDTO exam) {
        TableItem titm = new TableItem(table, SWT.NONE);
        titm.setText(IDX_ID, String.valueOf(exam.getId()));
        titm.setText(IDX_TITLE, exam.getTitle());
    }

    /**
     * 获取选中行
     */
    private TableItem findSelection(Table table) {
        TableItem[] items = table.getSelection();
        if (items == null || items.length < 1) {
            SWTUtils.alert(getShell(), "请选择一行数据！");
            return null;
        }

        return items[0];
    }

}
