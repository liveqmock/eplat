/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import javafx.scene.web.WebView;

/**
 * 急救案例考核事件管理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeExamViewEvent.java, V1.0.1 2013-2-13 下午1:16:28 $
 */
public final class EmergeExamViewEvent extends AbstractWebViewEvent {

    /**
     * 默认构造器
     */
    public EmergeExamViewEvent(WebView view) {
        super(view);
    }

    /** 
     * @see mplat.uijfx.uiviews.acts.AbstractWebViewEvent#onFireEvent(java.lang.String)
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
    }

}
