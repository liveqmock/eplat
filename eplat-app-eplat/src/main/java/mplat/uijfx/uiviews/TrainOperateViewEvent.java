/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import com.atom.core.lang.utils.LogUtils;

import javafx.scene.web.WebView;

/**
 * 训练操作事件
 * 
 * @author obullxl@gmail.com
 * @version $Id: TrainOperateViewEvent.java, V1.0.1 2013-3-30 上午10:20:14 $
 */
public final class TrainOperateViewEvent extends AbstractWebViewEvent {

    /**
     * 默认构造器
     */
    public TrainOperateViewEvent(WebView view) {
        super(view);
    }

    /** 
     * @see mplat.uijfx.uiviews.AbstractWebViewEvent#onFireEvent(java.lang.String)
     */
    protected final void onFireEvent(String arg) {
        LogUtils.debug("急救案例训练-操作：" + arg);
    }

}
