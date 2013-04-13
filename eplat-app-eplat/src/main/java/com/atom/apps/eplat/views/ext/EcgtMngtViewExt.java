/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.EcgtMngtView;

/**
 * 心律识别训练
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtMngtViewExt.java, V1.0.1 2013-4-13 上午11:32:12 $
 */
public class EcgtMngtViewExt extends AbstractExtEvent {
    /** 是否为管理 */
    private final boolean      mngt;

    /** 管理视图 */
    private final EcgtMngtView composite;

    /**
     * 默认构造器
     */
    public EcgtMngtViewExt(boolean mngt) {
        this.mngt = mngt;

        // 初始化页面
        String tabData = SWTUtils.TD_TOPIC_EXAM;
        this.removeTabItem(tabData);

        this.composite = new EcgtMngtView(this.findTabFolder(), SWT.NONE, this.mngt);
        super.initTabItem(tabData, SWT.CLOSE, "心律识别训练", composite);
    }

}
