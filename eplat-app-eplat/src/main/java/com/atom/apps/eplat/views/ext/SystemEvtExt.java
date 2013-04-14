/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mplat.mgt.dto.UserInfoDTO;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;

import com.atom.apps.eplat.views.SystemCfgDlg;
import com.atom.apps.eplat.views.UserUpdateView;
import com.atom.core.lang.utils.LogUtils;

/**
 * 系统设置
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemEvtExt.java, V1.0.1 2013-4-5 下午3:46:04 $
 */
public class SystemEvtExt extends AbstractExtEvent {
    /** 事件代码 */
    private final Map<String, String> evts = new ConcurrentHashMap<String, String>();
    {
        this.evts.put("01", "系统配置设置");
        this.evts.put("02", "登陆密码修改");
    }

    /** 事件序号 */
    private final String              evtNo;
    /** 用户信息 */
    private final UserInfoDTO         user;

    /**
     * 默认构造器
     */
    public SystemEvtExt(final String no, final UserInfoDTO user) {
        this.evtNo = no;
        this.user = user;
    }

    /**
     * 执行事件
     */
    public final void onSystemEvent() {
        LogUtils.get().info("[系统设置]-{}.{}", this.evtNo, this.evts.get(this.evtNo));

        // 01.参数设置
        if (StringUtils.equalsIgnoreCase("01", this.evtNo)) {
            new SystemCfgDlg(this.findShell(), SWT.NONE).open();
        }

        // 02.修改密码
        else if (StringUtils.equalsIgnoreCase("02", this.evtNo)) {
            new UserUpdateView(this.findShell(), this.user).open();
        }
    }
}
