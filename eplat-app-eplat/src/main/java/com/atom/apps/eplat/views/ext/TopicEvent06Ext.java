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
 * 注射泵使用训练页面
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicEvent06Ext.java, V1.0.1 2013-4-14 下午4:49:39 $
 */
public class TopicEvent06Ext extends AbstractWebEvent {
    /** 页面 */
    private static final String HTML_URL = "swf/02-TT06-注射泵使用训练.html";

    /**
     * 默认构造器
     */
    public TopicEvent06Ext() {
        // 初始化页面
        String tabData = SWTUtils.TD_COMM_PORT;
        this.removeTabItem(tabData);
        super.initWebViewExt(tabData, SWT.CLOSE, "注射泵使用训练", SWTUtils.findHtml(HTML_URL));

        super.findWebBrowser().addProgressListener(new ProgressAdapter() {
            public void completed(ProgressEvent event) {
                // evalScript("alert", "你好啊，老牛先生~~");
                // execScript("CPRStart();");
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
