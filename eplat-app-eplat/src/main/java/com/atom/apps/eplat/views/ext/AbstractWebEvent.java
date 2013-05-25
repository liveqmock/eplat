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
public abstract class AbstractWebEvent extends AbstractExtEvent implements SWTWebEvent {
    /** 浏览器 */
    private Browser                                         browser;

    /** 功能页面映射 */
    private final Map<String, Class<? extends SWTWebEvent>> events = new ConcurrentHashMap<>();
    {
        this.events.put("A00", HomePageExt.class);
        this.events.put("A01", CourseWareExt.class);
        this.events.put("A02", TopicTrainExt.class);
        this.events.put("A03", EmergeTrainExt.class);
        this.events.put("A04", EmergeExamExt.class);
        this.events.put("A05", SystemCfgExt.class);
    }

    /**
     * 获取浏览器
     */
    public final Browser findWebBrowser() {
        return this.browser;
    }

    /**
     * 设置浏览器
     */
    public final void setWebBrowser(Browser browser) {
        this.browser = browser;
    }

    /**
     * 初始化Web页面
     */
    protected CTabItem initWebViewExt(String tabData, int tabStyle, String text, String html) {
        CTabFolder tabFolder = this.findTabFolder();
        CTabItem tabItem = SWTUtils.findTabItem(tabFolder, tabData);
        if (tabItem != null) {
            // 选中标签
            tabFolder.setSelection(tabItem);
        } else {
            this.browser = new Browser(tabFolder, SWT.NONE);
            this.browser.setUrl(html);

            tabItem = super.initTabItem(tabData, tabStyle, text, this.browser);

            // 标签-关闭事件
            tabItem.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent evt) {
                    browser.close();
                }
            });

            // 增加功能
            new SWTWebFuncExt(this.browser, this);
        }

        return tabItem;
    }

    /**
     * 执行JS脚本
     */
    public final boolean execScript(final String script) {
        if (this.browser == null) {
            throw new RuntimeException("浏览器为NULL, 无法执行JS脚本, Script[" + script + "].");
        }

        LogUtils.get().debug("执行JS脚本: {}", script);

        return this.browser.execute(script);
    }

    /**
     * 执行JS方法
     */
    public final Object evalScript(final String name) {
        return this.evalScript(name, null);
    }

    /**
     * 执行JS方法
     */
    public final Object evalScript(final String name, String arg) {
        if (this.browser == null) {
            throw new RuntimeException("浏览器为NULL, 无法执行JS脚本, Name[" + name + "], Args[" + arg + "].");
        }

        StringBuilder script = new StringBuilder();
        script.append(name).append("(");
        if (arg != null) {
            script.append("\"").append(arg).append("\"");
        }
        script.append(");");

        LogUtils.get().debug("执行JS方法: {}", script.toString());

        return this.browser.evaluate(script.toString());
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
        } else {
            // 其他事件
            LogUtils.get().info("[Web事件]-其他事件-[{}].", arg);
            this.onFireEvent(arg);
        }

        return "1";
    }

    /**
     * 触发事件
     */
    public abstract Object onFireEvent(String arg);

}
