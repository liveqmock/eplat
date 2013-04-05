/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.atom.core.lang.utils.LogUtils;

/**
 * 单个专项训练事件
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicEventExt.java, V1.0.1 2013-4-5 下午2:26:30 $
 */
public class TopicEventExt {
    /** 事件代码 */
    private final Map<String, String> evts = new ConcurrentHashMap<String, String>();
    {
        this.evts.put("01", "ACLS基础知识训练");
        this.evts.put("02", "心律识别训练");
        this.evts.put("03", "心肺复苏急救训练");
        this.evts.put("04", "除颤仪使用训练");
        this.evts.put("05", "插管训练");
        this.evts.put("06", "注射泵使用训练");
        this.evts.put("07", "AED使用训练");
        this.evts.put("08", "输液泵使用训练");
    }

    /** 事件序号 */
    private final String              evtNo;

    /**
     * 默认构造器
     */
    public TopicEventExt(final String no) {
        this.evtNo = no;
    }

    /**
     * 执行事件
     */
    public final void onTopicEvent() {
        LogUtils.get().info("[专项训练]-{}.{}", this.evtNo, this.evts.get(this.evtNo));
    }

}
