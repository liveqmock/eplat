/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;

/**
 * 急救案例训练
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeTrainExt.java, V1.0.1 2013-4-4 下午5:50:06 $
 */
public class EmergeTrainExt extends AbstractWebEvent {
    /** 页面 */
    private static final String HTML_URL = "03-EmergeTrain.html";

    /**
     * 默认构造器
     */
    public EmergeTrainExt() {
        // 初始化页面
        super.initWebViewExt(SWTUtils.TD_EMERGE_TRAIN, SWT.CLOSE, "急救案例训练", SWTUtils.findHtml(HTML_URL));
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        // new CourseSlideExt(StringUtils.leftPad(arg, 2, "0"));

        return "1";
    }

}
