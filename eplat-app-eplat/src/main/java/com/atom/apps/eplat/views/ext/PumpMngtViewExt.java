/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import mplat.mgt.PumpMgt;

import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.PumpMngtView;

/**
 * 注射泵/输液泵视图
 * 
 * @author obullxl@gmail.com
 * @version $Id: PumpMngtViewExt.java, V1.0.1 2013-4-14 下午5:06:49 $
 */
public class PumpMngtViewExt extends AbstractExtEvent {
    /** 类型 */
    private final int          pumpCatg;

    /** 管理视图 */
    private final PumpMngtView composite;

    /**
     * 默认构造器
     */
    public PumpMngtViewExt(int pumpCatg) {
        this.pumpCatg = pumpCatg;

        // 初始化页面
        String tabData = SWTUtils.TD_TOPIC_EXAM;
        this.removeTabItem(tabData);

        this.composite = new PumpMngtView(this.findTabFolder(), this.pumpCatg);
        if (this.pumpCatg == PumpMgt.EJECTOR) {
            super.initTabItem(tabData, SWT.CLOSE, "注射泵使用训练", this.composite);
        } else {
            super.initTabItem(tabData, SWT.CLOSE, "输液泵使用训练", this.composite);
        }
    }

}
