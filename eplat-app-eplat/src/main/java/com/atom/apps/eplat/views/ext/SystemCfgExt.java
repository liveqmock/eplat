/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;

/**
 * 系统参数设置
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemCfgExt.java, V1.0.1 2013-4-4 下午5:50:44 $
 */
public class SystemCfgExt extends AbstractWebEvent {
    /** 页面 */
    private static final String HTML_URL = "05-SystemCfg.html";

    /**
     * 默认构造器
     */
    public SystemCfgExt() {
        // 初始化页面
        super.initWebViewExt(SWTUtils.TD_SYSTEM_CFG, SWT.CLOSE, "系统参数设置", SWTUtils.findHtml(HTML_URL));
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        // new CourseSlideExt(StringUtils.leftPad(arg, 2, "0"));

        return "1";
    }

}
