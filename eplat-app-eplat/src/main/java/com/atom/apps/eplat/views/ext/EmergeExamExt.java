/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;

/**
 * 急救案例考核
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeExamExt.java, V1.0.1 2013-4-4 下午5:50:31 $
 */
public class EmergeExamExt extends AbstractWebEvent {
    /** 页面 */
    private static final String HTML_URL = "04-EmergeExam.html";

    /**
     * 默认构造器
     */
    public EmergeExamExt() {
        // 初始化页面
        super.initWebViewExt(SWTUtils.TD_EMERGE_EXAM, SWT.CLOSE, "急救案例考核", SWTUtils.findHtml(HTML_URL));
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        new EmergeWebExt(StringUtils.leftPad(arg, 2, "0"));

        return "1";
    }

}
