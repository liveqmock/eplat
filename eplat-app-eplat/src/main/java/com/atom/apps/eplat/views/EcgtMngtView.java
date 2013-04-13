package com.atom.apps.eplat.views;

import java.util.Collections;
import java.util.List;

import mplat.mgt.EcgtMgt;
import mplat.mgt.MgtFactory;
import mplat.mgt.dto.EcgtInfoDTO;

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

public class EcgtMngtView extends SashForm {
    private static final int ID_WIDTH   = 30;
    private static final int QRS_WIDTH  = 40;
    private static final int RATE_WIDTH = 50;

    private static final int DETA_WIDTH = 10;

    private static final int TAB_SRC    = 1;
    private static final int TAB_DST    = 2;

    public static final int  IDX_ID     = 0;
    public static final int  IDX_QRS    = 1;
    public static final int  IDX_RHYTHM = 2;
    public static final int  IDX_RATE   = 3;

    private final boolean    mngt;
    private final EcgtMgt    emgt       = MgtFactory.get().findEcgtMgt();

    private Composite        cmpSrc;
    private Composite        cmpDst;

    private ToolBar          tbarSrc;
    private ToolItem         titmCreateSrc;
    private ToolItem         titmUpdateSrc;
    private ToolItem         titmRemoveSrc;
    private ToolItem         titmSelectSrc;

    private ToolBar          tbarDst;

    private TableViewer      tbvSrc;
    private Table            tblSrc;

    private TableViewer      tbvDst;
    private Table            tblDst;

    private TableColumn      clmnSrcRhythm;
    private TableColumn      clmnSrcRate;

    private TableColumn      clmnDstRhythm;
    private TableColumn      clmnDstRate;

    /**
     * Create the composite.
     */
    public EcgtMngtView(Composite parent, int style, final boolean mngt) {
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
                EcgtInfoDTO ecgt = new EcgtInfoDTO();
                new EcgtUpdateDlg(getShell(), SWT.NONE, ecgt).open();

                if (StringUtils.isNotBlank(ecgt.getEcgtQrs())) {
                    emgt.create(ecgt);

                    crateTableItem(tblSrc, ecgt);
                    LogUtils.get().info("增加试题成功-{}", ecgt);
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
                EcgtInfoDTO ecgt = emgt.find(Long.valueOf(id));

                new EcgtUpdateDlg(getShell(), SWT.NONE, ecgt).open();
                emgt.update(ecgt);

                SWTUtils.removeValue(tblSrc, IDX_ID, id);
                crateTableItem(tblSrc, ecgt);

                LogUtils.get().info("更新试题成功-{}", ecgt);
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

                boolean rtn = SWTUtils.confirm(getShell(), "确定删除心律识别训练试题吗？");
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

                long id = Long.valueOf(item.getText(IDX_ID));
                EcgtInfoDTO ecgt = emgt.find(id);
                if (ecgt != null) {
                    crateTableItem(tblDst, ecgt);
                }

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
        clmnSrcId.setWidth(30);
        clmnSrcId.setText("ID");

        final TableColumn clmnSrcQrs = new TableColumn(tblSrc, SWT.CENTER);
        clmnSrcQrs.setWidth(40);
        clmnSrcQrs.setText("QRS");

        clmnSrcRhythm = new TableColumn(tblSrc, SWT.NONE);
        clmnSrcRhythm.setText("Basic Rhythm");
        clmnSrcRhythm.setWidth(100);

        clmnSrcRate = new TableColumn(tblSrc, SWT.CENTER);
        clmnSrcRate.setWidth(50);
        clmnSrcRate.setText("Rate");

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

                long id = Long.valueOf(item.getText(IDX_ID));
                EcgtInfoDTO ecgt = emgt.find(id);
                if (ecgt != null) {
                    crateTableItem(tblSrc, ecgt);
                }

                LogUtils.get().info("准备去除数据-{}", item);
                SWTUtils.removeValue(tblDst, IDX_ID, item.getText(IDX_ID));
                LogUtils.get().info("去除数据成功-{}", item);
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

                // TODO:
                // new ExamEvaluateDlg(getShell(), SWT.NONE, ids).open();
            }
        });

        tbvDst = new TableViewer(this.cmpDst, SWT.BORDER | SWT.FULL_SELECTION);
        tblDst = tbvDst.getTable();
        tblDst.setLinesVisible(true);
        tblDst.setHeaderVisible(true);
        tblDst.setLayoutData(BorderLayout.CENTER);

        final TableColumn clmnDstId = new TableColumn(tblDst, SWT.RIGHT);
        clmnDstId.setResizable(false);
        clmnDstId.setWidth(30);
        clmnDstId.setText("ID");

        final TableColumn clmnDstQrs = new TableColumn(tblDst, SWT.CENTER);
        clmnDstQrs.setWidth(40);
        clmnDstQrs.setText("QRS");

        clmnDstRhythm = new TableColumn(tblDst, SWT.NONE);
        clmnDstRhythm.setWidth(100);
        clmnDstRhythm.setText("Basic Rhythm");

        clmnDstRate = new TableColumn(tblDst, SWT.CENTER);
        clmnDstRate.setWidth(50);
        clmnDstRate.setText("Rate");

        this.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                if (mngt) {
                    int width = getBounds().width - ID_WIDTH - QRS_WIDTH - RATE_WIDTH - DETA_WIDTH;
                    clmnSrcRhythm.setWidth(width);
                    clmnDstId.setWidth(0);
                    clmnDstQrs.setWidth(0);
                    clmnDstRhythm.setWidth(0);
                    clmnDstRate.setWidth(0);
                } else {
                    int width = (getBounds().width - (ID_WIDTH + QRS_WIDTH + RATE_WIDTH) * 2) / 2 - DETA_WIDTH;
                    clmnSrcRhythm.setWidth(width);
                    clmnDstRhythm.setWidth(width);
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
        this.initTableValues();
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
     * 初始化数据
     */
    private void initTableValues() {
        List<EcgtInfoDTO> ecgts = this.emgt.findAll();
        Collections.sort(ecgts);

        for (EcgtInfoDTO ecgt : ecgts) {
            this.crateTableItem(tblSrc, ecgt);
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

        List<String> dstIds = SWTUtils.findValues(tabDst, IDX_ID);
        SWTUtils.removeValues(table, IDX_ID, dstIds);
    }

    /**
     * 增加数据行
     */
    private void crateTableItem(Table table, EcgtInfoDTO ecgt) {
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(IDX_ID, String.valueOf(ecgt.getId()));
        item.setText(IDX_QRS, ecgt.getEcgtQrs());
        item.setText(IDX_RHYTHM, ecgt.getEcgtRhythm());
        item.setText(IDX_RATE, ecgt.getEcgtRate());
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
