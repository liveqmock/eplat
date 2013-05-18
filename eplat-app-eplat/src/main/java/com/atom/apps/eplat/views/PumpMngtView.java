package com.atom.apps.eplat.views;

import java.util.Collections;
import java.util.List;

import mplat.mgt.MgtFactory;
import mplat.mgt.PumpMgt;
import mplat.mgt.dto.PumpInfoDTO;

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
import com.atom.apps.eplat.views.ext.TopicEvent06Ext;
import com.atom.apps.eplat.views.ext.TopicEvent08Ext;
import com.atom.core.lang.utils.LogUtils;

public class PumpMngtView extends SashForm {
    private static final int ID_WIDTH   = 30;
    private static final int RATE_WIDTH = 120;

    private static final int DETA_WIDTH = 10;

    private static final int TAB_SRC    = 1;
    private static final int TAB_DST    = 2;

    public static final int  IDX_ID     = 0;
    public static final int  IDX_NAME   = 1;
    public static final int  IDX_RATE   = 2;

    private final int        catg;
    private final PumpMgt    pmgt;

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

    private TableColumn      clmnSrcName;
    private TableColumn      clmnSrcRate;

    private TableColumn      clmnDstName;
    private TableColumn      clmnDstRate;

    /**
     * Create the composite.
     */
    public PumpMngtView(Composite parent, final int catg) {
        super(parent, SWT.NONE);

        this.catg = catg;
        if (this.catg == PumpMgt.EJECTOR) {
            this.pmgt = MgtFactory.get().findEjectorMgt();
        } else {
            this.pmgt = MgtFactory.get().findTransferMgt();
        }

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
                // TODO:
            }
        });

        titmUpdateSrc = new ToolItem(tbarSrc, SWT.NONE);
        titmUpdateSrc.setImage(SWTUtils.findImage("icon-edit.png"));
        titmUpdateSrc.setText("编辑");
        titmUpdateSrc.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                // TODO:
            }
        });

        titmRemoveSrc = new ToolItem(tbarSrc, SWT.NONE);
        titmRemoveSrc.setImage(SWTUtils.findImage("icon-delete.png"));
        titmRemoveSrc.setText("删除");
        titmRemoveSrc.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                // TODO:
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
                PumpInfoDTO pump = pmgt.find(id);
                if (pump != null) {
                    crateTableItem(tblDst, pump);
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

        clmnSrcName = new TableColumn(tblSrc, SWT.NONE);
        clmnSrcName.setText("药品");
        clmnSrcName.setWidth(100);

        clmnSrcRate = new TableColumn(tblSrc, SWT.NONE);
        clmnSrcRate.setWidth(RATE_WIDTH);
        clmnSrcRate.setText("速度");

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
                PumpInfoDTO pump = pmgt.find(id);
                if (pump != null) {
                    crateTableItem(tblSrc, pump);
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
                if (catg == PumpMgt.EJECTOR) {
                    new TopicEvent06Ext(ids);
                } else {
                    new TopicEvent08Ext(ids);
                }
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

        clmnDstName = new TableColumn(tblDst, SWT.NONE);
        clmnDstName.setWidth(100);
        clmnDstName.setText("药品");

        clmnDstRate = new TableColumn(tblDst, SWT.NONE);
        clmnDstRate.setWidth(RATE_WIDTH);
        clmnDstRate.setText("速度");

        this.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                int width = (getBounds().width - (ID_WIDTH + RATE_WIDTH) * 2) / 2 - DETA_WIDTH;
                clmnSrcName.setWidth(width);
                clmnDstName.setWidth(width);
            }
        });

        // 分隔
        super.setWeights(new int[] { 1, 1 });

        // 初始化组件
        this.initComposites();

        // 初始化数据
        this.initTableValues();
    }

    /**
     * 初始化组件
     */
    private void initComposites() {
        this.titmCreateSrc.setEnabled(false);
        this.titmUpdateSrc.setEnabled(false);
        this.titmRemoveSrc.setEnabled(false);
    }

    /**
     * 初始化数据
     */
    private void initTableValues() {
        List<PumpInfoDTO> pumps = this.pmgt.findAll();
        Collections.sort(pumps);

        for (PumpInfoDTO pump : pumps) {
            this.crateTableItem(tblSrc, pump);
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
    private void crateTableItem(Table table, PumpInfoDTO pump) {
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(IDX_ID, String.valueOf(pump.getId()));
        item.setText(IDX_NAME, pump.getName() + " + " + pump.getAdvice());
        item.setText(IDX_RATE, pump.getRate());
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
