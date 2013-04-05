/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;

import com.atom.apps.eplat.SWTUtils;

/**
 * 主功能页面
 * 
 * @author obullxl@gmail.com
 * @version $Id: HomePageExt.java, V1.0.1 2013-4-4 下午3:26:27 $
 */
public final class HomePageExt extends AbstractWebEvent {
    /** 页面 */
    private static final String HTML_URL = "00-HomePage.html";

    /**
     * 默认构造器
     */
    public HomePageExt() {
        // 初始化页面
        CTabItem tabItem = super.initWebViewExt(SWTUtils.TD_HOME_MAIN, SWT.NONE, "欢迎使用", SWTUtils.findHtml(HTML_URL));
        tabItem.setImage(SWTUtils.findImage(this.findDisplay(), "tab-house.png"));
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public final Object onFireEvent(String arg) {
        return "1";
    }

}
