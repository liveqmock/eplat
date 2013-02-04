/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import org.apache.commons.lang.StringUtils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Web组件基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseWebView.java, V1.0.1 2013-2-1 下午10:56:51 $
 */
public abstract class BaseWebView<T> extends BaseView<T, WebView> {

    /** Web组件 */
    private final WebView   webView;
    private final WebEngine webEngine;

    /**
     * CTOR
     */
    public BaseWebView(T rootView, String url) {
        super(rootView);

        this.webView = new WebView();
        this.webEngine = this.webView.getEngine();
        this.webEngine.load(url);

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @SuppressWarnings("rawtypes")
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    onWebSuccessLoad();
                }
            }
        });
    }

    /** 
     * @see mplat.uijfx.uiviews.views.BaseView#findView()
     */
    public WebView findView() {
        return this.webView;
    }

    /**
     * 取得WebEngine对象
     */
    public WebEngine findWebEngine() {
        return this.webEngine;
    }

    /**
     * 停止Vedio视频
     */
    public void stopVedio(String vedioId) {
        String script = "document.getElementById('$ID$').currentTime=0; document.getElementById('$ID$').pause();";
        script = StringUtils.replace(script, "$ID$", vedioId);
        
        this.webEngine.executeScript(script);
    }

    /**
     * 停止Audio音频
     */
    public void stopAudio(String audioId) {

    }

    /**
     * 初始化事件
     */
    protected abstract void onWebSuccessLoad();

}
