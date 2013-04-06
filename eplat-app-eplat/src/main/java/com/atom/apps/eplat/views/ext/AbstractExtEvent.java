/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.atom.apps.eplat.SWTMainView;
import com.atom.apps.eplat.SWTUtils;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractExtEvent.java, V1.0.1 2013-4-6 下午3:39:15 $
 */
public abstract class AbstractExtEvent {
    /** 主页面 */
    private final SWTMainView mainView = SWTUtils.findMainView();

    /**
     * 获取主页面
     */
    public final SWTMainView findMainView() {
        return this.mainView;
    }
    
    /**
     * 获取Shell窗口
     */
    public final Shell findShell() {
        return this.mainView.findShell();
    }

    /**
     * 获取SWT设备
     */
    public final Display findDisplay() {
        return this.mainView.findDisplay();
    }

    /**
     * 获取标签容器
     */
    public final CTabFolder findTabFolder() {
        return this.findMainView().findCTabFolder();
    }

    /**
     * 删除标签页面
     */
    public final void removeTabItem(String tabData) {
        SWTUtils.removeTabItem(this.findTabFolder(), tabData);
    }

    /**
     * 初始化TabItem标签页面
     */
    protected CTabItem initTabItem(String tabData, int tabStyle, String text, Composite composite) {
        CTabFolder tabFolder = this.findTabFolder();
        CTabItem tabItem = SWTUtils.findTabItem(tabFolder, tabData);
        if (tabItem == null) {
            tabItem = new CTabItem(tabFolder, tabStyle);
            tabItem.setText(text);
            tabItem.setData(SWTUtils.TAB_DATA_KEY, tabData);
            tabItem.setImage(SWTUtils.findImage(this.findDisplay(), "tab-default.png"));

            tabItem.setControl(composite);

            // 页面-鼠标事件
            composite.addMouseMoveListener(new MouseMoveListener() {
                public void mouseMove(MouseEvent e) {
                    findMainView().setStatusMessage("X:" + e.x + ", Y:" + e.y);
                }
            });
        }

        // 选中标签
        tabFolder.setSelection(tabItem);

        return tabItem;
    }

}
