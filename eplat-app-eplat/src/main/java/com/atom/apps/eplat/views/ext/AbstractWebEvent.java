/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Display;

import com.atom.apps.eplat.SWTMainView;
import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.swt.SWTWebEvent;
import com.atom.apps.eplat.swt.SWTWebFuncExt;
import com.atom.core.lang.utils.LogUtils;

/**
 * 通用方法
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractWebEvent.java, V1.0.1 2013-4-4 下午4:30:02 $
 */
public abstract class AbstractWebEvent implements SWTWebEvent {
    /** 主页面 */
    private final SWTMainView                               mainView = SWTUtils.findMainView();

    /** 功能页面映射 */
    private final Map<String, Class<? extends SWTWebEvent>> events   = new ConcurrentHashMap<>();
    {
        this.events.put("A00", HomePageExt.class);
        this.events.put("A01", CourseWareExt.class);
        this.events.put("A02", TopicTrainExt.class);
        this.events.put("A03", EmergeTrainExt.class);
        this.events.put("A04", EmergeExamExt.class);
        this.events.put("A05", SystemCfgExt.class);
    }

    /**
     * 获取主页面
     */
    public final SWTMainView findMainView() {
        return this.mainView;
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
     * 初始化Web页面
     */
    protected CTabItem initWebViewExt(String tabData, int tabStyle, String text, String html) {
        CTabFolder tabFolder = this.findTabFolder();
        CTabItem tabItem = SWTUtils.findTabItem(tabFolder, tabData);
        if (tabItem == null) {
            tabItem = new CTabItem(tabFolder, tabStyle);
            tabItem.setText(text);
            tabItem.setData(SWTUtils.TAB_DATA_KEY, tabData);
            tabItem.setImage(SWTUtils.findImage(this.findDisplay(), "tab-default.png"));

            final Browser browser = new Browser(tabFolder, SWT.NONE);
            tabItem.setControl(browser);
            browser.setUrl(html);

            // 页面-鼠标事件
            browser.addMouseMoveListener(new MouseMoveListener() {
                public void mouseMove(MouseEvent e) {
                    findMainView().setStatusMessage("X:" + e.x + ", Y:" + e.y);
                }
            });

            // 标签-关闭事件
            tabItem.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent evt) {
                    browser.close();
                }
            });

            // 增加功能
            /* final BrowserFunction function = */new SWTWebFuncExt(browser, this);
            /*
            browser.addProgressListener(new ProgressAdapter() {
                public void completed(ProgressEvent event) {
                    browser.addLocationListener(new LocationAdapter() {
                        public void changed(LocationEvent event) {
                            browser.removeLocationListener(this);
                            function.dispose();
                        }
                    });
                }
            });
            */
        }

        // 选中标签
        tabFolder.setSelection(tabItem);

        return tabItem;
    }

    /** 
     * @see com.atom.apps.eplat.swt.SWTWebEvent#fireEvent(java.lang.Object[])
     */
    public final Object fireEvent(Object[] args) {
        if (args == null || args.length == 0) {
            LogUtils.get().warn("[Web事件]-参数为空.");
            return "-1";
        }

        String arg = ObjectUtils.toString(args[0]);
        if (StringUtils.startsWithIgnoreCase(arg, "A")) {
            // 功能导航
            LogUtils.get().info("[Web事件]-功能导航-ID[{}].", arg);
            try {
                this.events.get(arg).newInstance();
            } catch (Exception e) {
                LogUtils.get().error("[Web事件]-功能导航-寻找页面异常!", e);
                throw new RuntimeException("[Web事件]-功能导航-寻找页面异常!", e);
            }
        } else if (StringUtils.startsWithIgnoreCase(arg, "B")) {
            // 页面功能
            LogUtils.get().info("[Web事件]-页面功能-ID[{}].", arg);
            return this.onFireEvent(StringUtils.substringAfter(arg, "B"));
        } else if (StringUtils.equalsIgnoreCase(arg, "Z01")) {
            // 退出
            LogUtils.get().info("[Web事件]-退出系统-[{}].", arg);
            SWTUtils.exitSystem(this.findMainView().findShell());
        }

        return "1";
    }

    /**
     * 触发事件
     */
    public abstract Object onFireEvent(String arg);

}
