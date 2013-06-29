/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.EmgeTrainView;
import com.atom.core.lang.utils.LogUtils;

/**
 * 急救考核
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeTrainView.java, V1.0.1 2013-6-16 下午1:53:17 $
 */
public class EmergeTrainEvtExt extends AbstractExtEvent {
    /** 急救编号 */
    private final String          argNo;

    /** 管理视图 */
    private final EmgeTrainView composite;

    /**
     * CTOR
     */
    public EmergeTrainEvtExt(String argNo) {
        this.argNo = argNo;
        LogUtils.get().info("[急救训练]-{}.", this.argNo);

        // 初始化页面
        String tabData = SWTUtils.TD_COMM_PORT;
        this.removeTabItem(tabData);

        this.composite = new EmgeTrainView(this.findTabFolder());
        super.initTabItem(tabData, SWT.CLOSE, "急救考核训练", composite);
    }

}
