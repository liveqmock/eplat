/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import javafx.scene.web.WebView;
import mplat.utils.UserHolder;

import org.apache.commons.lang.StringUtils;

/**
 * 系统功能设置事件处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemCfgViewEvent.java, V1.0.1 2013-2-13 下午1:19:10 $
 */
public final class SystemCfgViewEvent extends AbstractWebViewEvent {

    /**
     * 默认构造器
     */
    public SystemCfgViewEvent(WebView view) {
        super(view);
    }

    /** 
     * @see mplat.uijfx.uiviews.acts.AbstractWebViewEvent#onFireEvent(java.lang.String)
     */
    protected final void onFireEvent(String arg) {
        if (StringUtils.equals(arg, "1")) {
            // 参数设置
            new SystemSetAct(this.findStage()).show();
        } else if (StringUtils.equals(arg, "2")) {
            // 修改密码
            new UserUpdateAct(this.findStage(), UserHolder.get()).show();
        }
    }

}
