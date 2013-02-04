/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import mplat.uijfx.uiviews.MainViewController;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/**
 * 专业案例训练
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrainWebView.java, V1.0.1 2013-2-3 下午1:18:43 $
 */
public final class TopicTrainWebView extends BaseWebView<MainViewController> {

    /**
     * CTOR
     */
    public TopicTrainWebView(MainViewController rootView, String url) {
        super(rootView, url);
    }

    /** 
     * @see mplat.uijfx.uiviews.views.BaseWebView#onWebSuccessLoad(javafx.scene.web.WebEngine)
     */
    public void onWebSuccessLoad() {
        Document doc = this.findWebEngine().getDocument();
        for (int i = 1; i <= 8; i++) {
            Element ele = doc.getElementById("item_" + i);
            if (ele != null) {
                ((EventTarget) ele).addEventListener("click", this.buildEventListener(i), false);
            }
        }
    }

    /**
     * 事件监听器
     */
    private EventListener buildEventListener(final int no) {
        return new EventListener() {
            public void handleEvent(Event evt) {
                onHandleEvent(evt, no);
            }
        };
    }

    private void onHandleEvent(Event evt, int no) {
        System.out.println(no + ": 点击了~~~~~");
    }

}
