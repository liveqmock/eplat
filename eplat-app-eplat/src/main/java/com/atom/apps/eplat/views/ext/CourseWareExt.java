/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;

/**
 * 系统课件
 * 
 * @author obullxl@gmail.com
 * @version $Id: CourseWareExt.java, V1.0.1 2013-4-4 下午3:07:59 $
 */
public final class CourseWareExt extends AbstractWebEvent {
    /** 页面 */
    private static final String HTML_URL = "01-CourseWare.html";

    /**
     * 默认构造器
     */
    public CourseWareExt() {
        // 初始化页面
        super.initWebViewExt(SWTUtils.TD_COURSE_WARE, SWT.CLOSE, "系统课件", SWTUtils.findHtml(HTML_URL));
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        new CourseSlideExt(StringUtils.leftPad(arg, 2, "0"));

        return "1";
    }

}
