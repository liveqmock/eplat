/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.events;

import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

import org.apache.commons.io.FilenameUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.uijfx.utils.TabPaneUtils;
import com.atom.core.uijfx.utils.WebViewUtils;

/**
 * 急救案例训练事件处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeTrainViewEvent.java, V1.0.1 2013-2-13 下午1:14:37 $
 */
public final class EmergeTrainViewEvent extends AbstractWebViewEvent {

    /**
     * 默认构造器
     */
    public EmergeTrainViewEvent(WebView view) {
        super(view);
    }

    /** 
     * @see mplat.uijfx.uiviews.events.AbstractWebViewEvent#onFireEvent(java.lang.String)
     */
    protected final void onFireEvent(String arg) {
        // 1.心脏停搏
        // 2.心动过缓
        // 3.心动过速
        // 4.胸痛
        // 5.中毒及服药过量
        // 6.代谢病及环境伤害
        // 7.呼吸困难
        // 8.颅脑损伤

        System.out.println(arg + ": 点击了~~~~~");

        BorderPane border = new BorderPane();

        // Center
        final WebView webView = WebViewUtils.initWebView(this.findHtmlUrl("PEA"));
        border.setCenter(webView);

        final Tab tab = new Tab("TEST");
        tab.setClosable(true);
        tab.setContent(border);

        tab.setOnClosed(new EventHandler<javafx.event.Event>() {
            public void handle(javafx.event.Event event) {
                WebViewUtils.stopVideo(webView, "videoPlayer");
            }
        });

        // 保存
        TabPaneUtils.addAndActiveTab(this.findTabPane(), tab);
    }

    /**
     * HTML文件URL
     */
    private String findHtmlUrl(String name) {
        return "file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/" + name + ".html");
    }

}
