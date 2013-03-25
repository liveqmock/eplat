/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import javafx.concurrent.Worker.State;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import mplat.utils.MViewUtils;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.utils.WebViewUtils;
import com.atom.core.uijfx.utils.WebViewUtils.WebViewStateEvent;

/**
 * Web页面视图事件基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractWebViewEvent.java, V1.0.1 2013-2-14 下午8:13:05 $
 */
public abstract class AbstractWebViewEvent implements WebViewStateEvent {
    /** Java存放JS变量名 */
    public static final String VAR_APP = "app";

    /** Web网页视图 */
    private final WebView      view;

    /** 主视图控制器 */
    private final MainViewAct  rootAct;

    /**
     * 默认构造器
     */
    public AbstractWebViewEvent(WebView view) {
        this.view = view;
        this.rootAct = MViewUtils.get();
    }

    /**
     * 获取网页视图
     */
    public final WebView findView() {
        return this.view;
    }

    /**
     * 获取Stage舞台
     */
    public final Stage findStage() {
        return this.rootAct.findStage();
    }

    /**
     * 获取主控制器
     */
    public final MainViewAct findRootAct() {
        return this.rootAct;
    }

    /**
     * 获取Tab视图组件
     */
    public final TabPane findTabPane() {
        return this.rootAct.findTabPane();
    }

    /** 
     * @see com.atom.core.uijfx.utils.WebViewUtils.WebViewStateEvent#onWebViewStateChanged(javafx.concurrent.Worker.State, javafx.concurrent.Worker.State)
     */
    public final void onWebViewStateChanged(State oldState, State newState) {
        WebViewUtils.putHtmlWindowObject(this.view, VAR_APP, this);

        this.afterWebViewStateChanged(oldState, newState);
    }

    /**
     * Web页加载成功事件，供子类实现额外操作使用
     */
    public void afterWebViewStateChanged(State oldState, State newState) {
    }

    /**
     * 页面视图中触发事件
     */
    public final void fireEvent(String arg) {
        LogUtils.info("收到JS回调事件[" + this.getClass().getName() + "]，参数[" + arg + "].");

        // JS回调函数
        this.onFireEvent(arg);
    }

    /**
     * 页面视图中触发事件
     */
    protected abstract void onFireEvent(String arg);

}
