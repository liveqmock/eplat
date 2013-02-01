/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import javafx.scene.web.WebEngine;
import mplat.uijfx.uiviews.MainViewController;

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
public final class CourseWareWebView extends BaseWebView {

    /** 根组件 */
    private final MainViewController rootView;

    /**
     * CTOR
     */
    public CourseWareWebView(MainViewController rootView, String url) {
        super(url);

        this.rootView = rootView;
    }

    /** 
     * @see mplat.uijfx.uiviews.views.BaseWebView#onSuccessBodyLoad(javafx.scene.web.WebEngine)
     */
    public void onSuccessBodyLoad(final WebEngine webEngine) {
        Document doc = webEngine.getDocument();

        Element el = doc.getElementById("item_1");
        ((EventTarget) el).addEventListener("click", new EventListener() {
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
                System.out.println("哈哈~~~~");
            }
        }, false);
    }

}
