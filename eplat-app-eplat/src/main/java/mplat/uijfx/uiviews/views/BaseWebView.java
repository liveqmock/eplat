/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import mplat.uijfx.uiviews.MainViewController;

/**
 * Web组件基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseWebView.java, V1.0.1 2013-2-1 下午10:56:51 $
 */
public abstract class BaseWebView extends BaseView {

    /** Web组件 */
    private final WebView webView;

    /**
     * CTOR
     */
    public BaseWebView(MainViewController rootView, String url) {
        super(rootView);
        
        this.webView = new WebView();
        this.webView.getEngine().load(url);

        final WebEngine webEngine = this.webView.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @SuppressWarnings("rawtypes")
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    onWebSuccessLoad(webEngine);
                }
            }
        });
    }
    
    /** 
     * @see mplat.uijfx.uiviews.views.BaseView#findView()
     */
    @SuppressWarnings("unchecked")
    public WebView findView() {
        return this.webView;
    }

    /**
     * 初始化事件
     */
    protected abstract void onWebSuccessLoad(final WebEngine webEngine);

}
