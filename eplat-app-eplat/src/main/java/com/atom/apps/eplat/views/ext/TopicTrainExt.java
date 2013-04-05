/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;

/**
 * 专项技能训练
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrainExt.java, V1.0.1 2013-4-4 下午5:28:30 $
 */
public final class TopicTrainExt extends AbstractWebEvent {
    /** 页面 */
    private static final String       HTML_URL = "02-TopicTrain.html";

    /**
     * 默认构造器
     */
    public TopicTrainExt() {
        // 初始化页面
        super.initWebViewExt(SWTUtils.TD_TOPIC_TRAIN, SWT.CLOSE, "专项技能训练", SWTUtils.findHtml(HTML_URL));
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        new TopicEventExt(StringUtils.leftPad(arg, 2, "0")).onTopicEvent();

        return "1";
    }

}
