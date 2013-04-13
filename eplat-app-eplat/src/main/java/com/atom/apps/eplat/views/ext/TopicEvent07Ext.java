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
 * AED使用训练页面
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain072View.java, V1.0.1 2013-4-6 下午4:42:10 $
 */
public class TopicEvent07Ext extends AbstractWebEvent {
    /** 页面 */
    private static final String HTML_URL = "02-TT07-AED使用训练.html";

    /**
     * 默认构造器
     */
    public TopicEvent07Ext() {
        // 初始化页面
        String tabData = SWTUtils.TD_COMM_PORT;
        this.removeTabItem(tabData);
        super.initWebViewExt(tabData, SWT.CLOSE, "AED使用训练", SWTUtils.findHtml(HTML_URL));

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
