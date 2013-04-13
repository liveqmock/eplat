/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.ExamMngtView;

/**
 * ACLS基础知识训练
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicEvent01Ext.java, V1.0.1 2013-4-9 下午9:55:28 $
 */
public class ExamMngtViewExt extends AbstractExtEvent {

    /** 是否为管理 */
    private final boolean      mngt;

    /** 管理视图 */
    private final ExamMngtView composite;

    /**
     * 默认构造器
     */
    public ExamMngtViewExt(boolean mngt) {
        this.mngt = mngt;

        // 初始化页面
        String tabData = SWTUtils.TD_TOPIC_EXAM;
        this.removeTabItem(tabData);

        this.composite = new ExamMngtView(this.findTabFolder(), SWT.NONE, this.mngt);
        super.initTabItem(tabData, SWT.CLOSE, "ACLS基础知识训练", composite);
    }

}
