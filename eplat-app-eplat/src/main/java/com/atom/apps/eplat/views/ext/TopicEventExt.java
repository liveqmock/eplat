/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.LogUtils;

/**
 * 单个专项训练事件
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicEventExt.java, V1.0.1 2013-4-5 下午2:26:30 $
 */
public final class TopicEventExt extends AbstractExtEvent {
    /** 事件代码 */
    private static final Map<String, String> _evts = new ConcurrentHashMap<String, String>();
    {
        _evts.put("01", "ACLS基础知识训练");
        _evts.put("02", "心律识别训练");
        _evts.put("03", "心肺复苏急救训练");
        _evts.put("04", "除颤仪使用训练");
        _evts.put("05", "插管训练");
        _evts.put("06", "注射泵使用训练");
        _evts.put("07", "AED使用训练");
        _evts.put("08", "输液泵使用训练");
    }

    /** 事件序号 */
    private final String                     evtNo;

    /**
     * 默认构造器
     */
    public TopicEventExt(final String no) {
        this.evtNo = no;
    }

    /**
     * 获取事件标题
     */
    public static String findText(String evtNo) {
        return _evts.get(evtNo);
    }

    /**
     * 执行事件
     */
    public final void onTopicEvent() {
        LogUtils.get().info("[专项训练]-{}.{}", this.evtNo, _evts.get(this.evtNo));

        // 07.AED使用训练
        if (StringUtils.equalsIgnoreCase(this.evtNo, "07")) {
            // Composite composite = new TopicTrain07View(this.findTabFolder(), SWT.NONE);
            // super.initTabItem(SWTUtils.TD_COMM_PORT, SWT.CLOSE, this.evts.get(this.evtNo), composite);

            new TopicEvent07Ext();
        }
    }
}
