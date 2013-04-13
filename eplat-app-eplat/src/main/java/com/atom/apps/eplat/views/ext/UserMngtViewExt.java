/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.UserMngtView;

/**
 * 用户管理
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserMngtViewExt.java, V1.0.1 2013-4-13 上午10:54:21 $
 */
public class UserMngtViewExt extends AbstractExtEvent {
    /** 管理视图 */
    private final UserMngtView composite;

    /**
     * 默认构造器
     */
    public UserMngtViewExt() {
        // 初始化页面
        String tabData = SWTUtils.TD_USER_MNGT;
        this.removeTabItem(tabData);

        this.composite = new UserMngtView(this.findTabFolder(), SWT.NONE);
        super.initTabItem(tabData, SWT.CLOSE, "用户信息管理", composite);
    }
}
