/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

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
public abstract class BaseWebView {

    /** Web组件 */
    private final WebView webView;

    /**
     * CTOR
     */
    public BaseWebView(String url) {
        this.webView = new WebView();
        this.webView.getEngine().load(url);

        final WebEngine webEngine = this.webView.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    onSuccessBodyLoad(webEngine);
                }
            }
        });
    }

    /**
     * 获取当前组件
     */
    public WebView findWebView() {
        return this.webView;
    }

    /**
     * 初始化事件
     */
    protected abstract void onSuccessBodyLoad(final WebEngine webEngine);

}
