package com.atom.apps.eplat.views;

import java.util.Collections;
import java.util.List;

import mplat.mgt.MgtFactory;
import mplat.mgt.UserMgt;
import mplat.mgt.dto.UserInfoDTO;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
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

public class UserMngtView extends Composite {
    private static final int ID_WIDTH   = 40;
    private static final int DETA_WIDTH = 20;

    public static final int  IDX_ID     = 0;
    public static final int  IDX_NAME   = 1;

    private final UserMgt    umgt       = MgtFactory.get().findUserMgt();

    private Table            tblUser;

    /**
     * Create the composite.
     * @param parent
     * @param style
     */
    public UserMngtView(Composite parent, int style) {
        super(parent, style);
        setLayout(new BorderLayout(0, 0));

        TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
        tblUser = tableViewer.getTable();
        tblUser.setLinesVisible(true);
        tblUser.setHeaderVisible(true);
        tblUser.setLayoutData(BorderLayout.CENTER);

        TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn clmnId = tableViewerColumn.getColumn();
        clmnId.setResizable(false);
        clmnId.setMoveable(true);
        clmnId.setWidth(ID_WIDTH);
        clmnId.setText("ID");

        TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
        final TableColumn clmnName = tableViewerColumn_1.getColumn();
        clmnName.setMoveable(true);
        clmnName.setWidth(100);
        clmnName.setText("用户名");

        ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
        toolBar.setLayoutData(BorderLayout.NORTH);

        ToolItem titmRefresh = new ToolItem(toolBar, SWT.NONE);
        titmRefresh.setImage(SWTUtils.findImage("icon-refresh.png"));
        titmRefresh.setText("刷新");
        titmRefresh.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                // 刷新用户信息
                refreshUsers();
            }
        });

        ToolItem titmCreate = new ToolItem(toolBar, SWT.NONE);
        titmCreate.setImage(SWTUtils.findImage("icon-add.png"));
        titmCreate.setText("增加");
        titmCreate.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                UserInfoDTO user = new UserInfoDTO();
                new UserUpdateView(getShell(), user).open();
                if (user.getId() > 0L) {
                    // 刷新用户信息
                    refreshUsers();
                }
            }
        });

        ToolItem titmModify = new ToolItem(toolBar, SWT.NONE);
        titmModify.setImage(SWTUtils.findImage("icon-edit.png"));
        titmModify.setText("修改");
        titmModify.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem item = findSelection(tblUser);
                if (item == null) {
                    return;
                }

                String userName = item.getText(IDX_NAME);
                UserInfoDTO user = umgt.find(userName);
                new UserUpdateView(getShell(), user).open();

                // 刷新用户信息
                refreshUsers();
            }
        });

        ToolItem titmRemove = new ToolItem(toolBar, SWT.NONE);
        titmRemove.setImage(SWTUtils.findImage("icon-delete.png"));
        titmRemove.setText("删除");
        titmRemove.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem item = findSelection(tblUser);
                if (item == null) {
                    return;
                }

                String userName = item.getText(IDX_NAME);
                if (UserMgt.isSystemAdmin(userName)) {
                    SWTUtils.alert(getShell(), "用户[" + userName + "]不能删除！");
                    return;
                }

                boolean rtn = SWTUtils.confirm(getShell(), "确定要删除用户[" + userName + "]吗？");
                if (rtn) {
                    UserInfoDTO user = umgt.find(userName);
                    umgt.remove(user);

                    SWTUtils.removeValue(tblUser, IDX_ID, String.valueOf(user.getId()));
                }
            }
        });

        this.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                clmnName.setWidth(getBounds().width - ID_WIDTH - DETA_WIDTH);
            }
        });

        // 刷新用户信息
        this.refreshUsers();
    }

    /**
     * 刷新用户信息
     */
    private void refreshUsers() {
        // 删除
        SWTUtils.removeValues(this.tblUser);

        // 增加
        List<UserInfoDTO> users = this.umgt.findAll();
        Collections.sort(users);

        for (UserInfoDTO user : users) {
            TableItem item = new TableItem(this.tblUser, SWT.NONE);
            item.setText(IDX_ID, String.valueOf(user.getId()));
            item.setText(IDX_NAME, user.getUserName());
        }
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
