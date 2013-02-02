/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import mplat.uijfx.uiviews.MainViewController;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.atom.core.lang.utils.CfgUtils;

/**
 * 系统课件
 * 
 * @author obullxl@gmail.com
 * @version $Id: CourseWareWebView.java, V1.0.1 2013-2-1 下午10:42:17 $
 */
public final class CourseWareWebView extends BaseWebView {

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
        for (int i = 1; i <= 5; i++) {
            // Element el = doc.getElementById("item_" + i);
            // ((EventTarget) el).addEventListener("click", this.buildEventListener(i), false);
        }

        Element el = doc.getElementById("btn_11B");
        ((EventTarget) el).addEventListener("click", this.buildEventListener(), false);
    }

    /**
     * 事件监听器
     */
    private EventListener buildEventListener() {
        return new EventListener() {
            public void handleEvent(Event evt) {
                BorderPane border = new BorderPane();
                WebView webView = new WebView();
                webView.getEngine().load("file:///D:/ACLS8000/Resource/InterfaceIndex/%E6%80%A5%E6%80%A7%E5%BF%83%E5%8C%85%E5%A1%AB%E5%A1%9E%E5%BC%95%E8%B5%B7PEA.html");
                border.setCenter(webView);
                
                Tab tab = new Tab("PPT");
                tab.setClosable(true);
                tab.setContent(border);

                getRootView().activeTab(tab);
            }
        };
    }
    
    /**
     * 事件监听器
     */
    private EventListener buildEventListener(final int no) {
        return new EventListener() {
            public void handleEvent(Event evt) {
                /*
                BorderPane border = new BorderPane();
                WebView webView = new WebView();
                webView.getEngine().load("file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/v01/01/slide0011.htm"));
                border.setCenter(webView);
                
                Tab tab = new Tab("PPT");
                tab.setClosable(true);
                tab.setContent(border);

                rootView.activeTab(tab);
                */
                System.out.println(no + ": 哈哈~~~~");
            }
        };
    }

}
