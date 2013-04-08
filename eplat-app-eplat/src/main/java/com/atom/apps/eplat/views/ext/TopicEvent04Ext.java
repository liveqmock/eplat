/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;

import com.atom.apps.eplat.SWTUtils;

/**
 * 除颤仪使用训练页面
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain04Ext.java, V1.0.1 2013-4-8 下午10:30:53 $
 */
public class TopicEvent04Ext extends AbstractWebEvent {
    /** 页面 */
    private static final String HTML_URL = "02-TT04-除颤仪使用训练.html";

    /**
     * 默认构造器
     */
    public TopicEvent04Ext() {
        // 初始化页面
        super.initWebViewExt(SWTUtils.TD_COMM_PORT, SWT.CLOSE, "除颤仪使用训练", SWTUtils.findHtml(HTML_URL));

        super.findWebBrowser().addProgressListener(new ProgressAdapter() {
            public void completed(ProgressEvent event) {
                // evalScript("alert", "你好啊，老牛先生~~");
                evalScript("opRecoder", "CPRend");
            }
        });
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        return "1";
    }
}
