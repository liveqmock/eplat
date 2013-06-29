package com.atom.apps.eplat.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import swing2swt.layout.BorderLayout;
import swing2swt.layout.FlowLayout;

import com.atom.apps.eplat.SWTUtils;

/**
 * 时间日志/属性日志
 * 
 * @author obullxl@gmail.com
 * @version $Id: CmptEmergeLog.java, V1.0.1 2013-6-24 下午2:31:34 $
 */
public class EmgeLogCmpt extends Composite implements SelectionListener {
    /** 表格宽度 */
    private static final int TB_WIDTH    = (EmgeMainView.SIZE.getOne() / 2 - 20);

    /** 事件日志表格 */
    public static final int  TB_EVT_TIME = 0;
    public static final int  TB_EVT_DATA = 1;

    /** 主视图 */
    private final Composite  mainView;

    private Table            tableEvtLog;
    private Button           btnNewEvtLog;
    private Table            table;

    /**
     * Create the composite.
     */
    public EmgeLogCmpt(Composite parent) {
        super(parent, SWT.NONE);

        this.mainView = parent;

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));

        TabFolder tabFolder = new TabFolder(this, SWT.NONE);
        tabFolder.setLayoutData(BorderLayout.CENTER);

        TabItem tbtmEventLog = new TabItem(tabFolder, SWT.NONE);
        tbtmEventLog.setText("事件日志");
        Composite evtComposite = new Composite(tabFolder, SWT.NONE);
        tbtmEventLog.setControl(evtComposite);
        evtComposite.setLayout(new BorderLayout(0, 0));

        Composite bottomComposite = new Composite(evtComposite, SWT.NONE);
        bottomComposite.setLayoutData(BorderLayout.SOUTH);
        bottomComposite.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        this.tableEvtLog = new Table(evtComposite, SWT.BORDER | SWT.FULL_SELECTION);
        this.tableEvtLog.setLayoutData(BorderLayout.CENTER);
        this.tableEvtLog.setHeaderVisible(true);
        this.tableEvtLog.setLinesVisible(true);

        TableColumn tblclmnEvtTime = new TableColumn(tableEvtLog, SWT.CENTER);
        tblclmnEvtTime.setWidth(60);
        tblclmnEvtTime.setText("Time");

        TableColumn tblclmnEvtLog = new TableColumn(tableEvtLog, SWT.NONE);
        tblclmnEvtLog.setWidth(TB_WIDTH - (60 + 10));
        tblclmnEvtLog.setText("内容");

        this.btnNewEvtLog = new Button(bottomComposite, SWT.NONE);
        this.btnNewEvtLog.addSelectionListener(this);
        this.btnNewEvtLog.setImage(SWTUtils.findImage("icon-add.png"));

        TabItem tbtmPropLog = new TabItem(tabFolder, SWT.NONE);
        tbtmPropLog.setText("属性日志");

        this.table = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
        tbtmPropLog.setControl(table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);

        TableColumn tblclmnTime = new TableColumn(table, SWT.CENTER);
        tblclmnTime.setWidth(70);
        tblclmnTime.setText("Time");

        TableColumn tblclmnHr = new TableColumn(table, SWT.NONE);
        tblclmnHr.setWidth(30);
        tblclmnHr.setText("HR");

        TableColumn tblclmnSbp = new TableColumn(table, SWT.NONE);
        tblclmnSbp.setWidth(40);
        tblclmnSbp.setText("SBP");

        TableColumn tblclmnDbp = new TableColumn(table, SWT.NONE);
        tblclmnDbp.setWidth(40);
        tblclmnDbp.setText("DBP");

        TableColumn tblclmnCvp = new TableColumn(table, SWT.NONE);
        tblclmnCvp.setWidth(40);
        tblclmnCvp.setText("CVP");

        TableColumn tblclmnCo = new TableColumn(table, SWT.NONE);
        tblclmnCo.setWidth(30);
        tblclmnCo.setText("CO");

        TableColumn tblclmnRr = new TableColumn(table, SWT.NONE);
        tblclmnRr.setWidth(30);
        tblclmnRr.setText("RR");

        TableColumn tblclmnEtco = new TableColumn(table, SWT.NONE);
        tblclmnEtco.setWidth(50);
        tblclmnEtco.setText("etCO2");

        TableColumn tblclmnTesoph = new TableColumn(table, SWT.NONE);
        tblclmnTesoph.setWidth(50);
        tblclmnTesoph.setText("TEsoph");
    }

    /** 
     * @see org.eclipse.swt.widgets.Composite#checkSubclass()
     */
    protected void checkSubclass() {
    }

    /** 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent evt) {
        Object source = evt.getSource();

        // 新增日志
        if (source == this.btnNewEvtLog) {
            List<String> logs = new ArrayList<String>();
            new EmgeLogDlg(this.getShell(), logs).open();
            String time = SWTUtils.findEvtTime();
            for (String log : logs) {
                this.onCreateEventLog(time, log);
            }
            logs.clear();
        }
    }

    /** 
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
    }

    /**
     * 增加事件属性日志
     */
    public void onCreateEventLog(String time, String log) {
        TableItem item = new TableItem(this.tableEvtLog, SWT.NONE);
        item.setText(TB_EVT_TIME, time);
        item.setText(TB_EVT_DATA, log);
        
        this.tableEvtLog.setSelection(item);
    }
}
