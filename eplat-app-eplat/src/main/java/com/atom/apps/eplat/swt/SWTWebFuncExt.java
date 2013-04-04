/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.swt;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.ToString;
import com.atom.core.lang.utils.LogUtils;

/**
 * 浏览器功能
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTWebFuncExt.java, V1.0.1 2013-4-4 下午2:57:08 $
 */
public class SWTWebFuncExt extends BrowserFunction {
    /** Java回调事件 */
    private final SWTWebEvent event;

    /**
     * 默认构造器
     */
    public SWTWebFuncExt(Browser browser, SWTWebEvent evt) {
        super(browser, SWTUtils.FUNC_FIRE_EVENT);
        this.event = evt;
    }

    /** 
     * @see org.eclipse.swt.browser.BrowserFunction#function(java.lang.Object[])
     */
    public Object function(Object[] args) {
        LogUtils.get().info("[JS回调]-收到回调事件-{}", ToString.toString(args));
        return this.event.fireEvent(args);
    }

}
