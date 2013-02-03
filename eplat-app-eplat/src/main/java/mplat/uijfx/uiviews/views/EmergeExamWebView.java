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
 * 专业案例考核
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeExamWebView.java, V1.0.1 2013-2-3 下午1:32:30 $
 */
public final class EmergeExamWebView extends BaseWebView<MainViewController> {

    /**
     * CTOR
     */
    public EmergeExamWebView(MainViewController rootView, String url) {
        super(rootView, url);
    }

    /** 
     * @see mplat.uijfx.uiviews.views.BaseWebView#onWebSuccessLoad(javafx.scene.web.WebEngine)
     */
    public void onWebSuccessLoad(final WebEngine webEngine) {
        Document doc = webEngine.getDocument();

        // 1.心脏停搏
        for (int i = 1; i <= 5; i++) {
            Element ele = doc.getElementById("item_1" + i);
            if (ele != null) {
                ((EventTarget) ele).addEventListener("click", this.buildEventListener(1, i), false);
            }
        }

        // 2.心动过缓
        for (int i = 1; i <= 1; i++) {
            Element ele = doc.getElementById("item_2" + i);
            if (ele != null) {
                ((EventTarget) ele).addEventListener("click", this.buildEventListener(2, i), false);
            }
        }

        // 3.心动过速
        for (int i = 1; i <= 3; i++) {
            Element ele = doc.getElementById("item_3" + i);
            if (ele != null) {
                ((EventTarget) ele).addEventListener("click", this.buildEventListener(3, i), false);
            }
        }

        // 4.胸痛
        // 5.中毒及服药过量
        // 6.代谢病及环境伤害
        // 7.呼吸困难
        // 8.颅脑损伤
    }

    /**
     * 事件监听器
     */
    private EventListener buildEventListener(final int ctg, final int no) {
        return new EventListener() {
            public void handleEvent(Event evt) {
                onHandleEvent(evt, ctg, no);
            }
        };
    }

    private void onHandleEvent(Event evt, int ctg, int no) {
        System.out.println(ctg + "-" + no + ": 点击了~~~~~");
    }

}
