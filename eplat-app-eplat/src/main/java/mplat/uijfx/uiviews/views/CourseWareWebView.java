/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import mplat.mgt.enums.TabCWPptEnum;
import mplat.uijfx.uiviews.MainViewController;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/**
 * 系统课件
 * 
 * @author obullxl@gmail.com
 * @version $Id: CourseWareWebView.java, V1.0.1 2013-2-1 下午10:42:17 $
 */
public final class CourseWareWebView extends BaseWebView<MainViewController> {

    /**
     * CTOR
     */
    public CourseWareWebView(MainViewController rootView, String url) {
        super(rootView, url);
    }

    /** 
     * @see mplat.uijfx.uiviews.views.BaseWebView#onWebSuccessLoad(javafx.scene.web.WebEngine)
     */
    public void onWebSuccessLoad(final WebEngine webEngine) {
        Document doc = webEngine.getDocument();
        for (int i = 1; i <= 10; i++) {
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
        String pptNo = StringUtils.leftPad(Integer.toString(no), 2, "0");
        BaseView<?, ?> view = new CourseWarePptView(this, "CW" + pptNo);

        Tab tab = new Tab("PPT:" + TabCWPptEnum.findByID(no).desp());
        tab.setClosable(true);
        tab.setContent((Node) view.findView());

        this.getRootView().activeTab(tab);
    }

}
