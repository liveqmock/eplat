/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import javafx.scene.web.WebView;
import javafx.stage.Stage;
import mplat.mgt.PumpMgt;
import mplat.uijfx.uiviews.acts.TopicTrain0101Act;
import mplat.uijfx.uiviews.acts.TopicTrain0201Act;
import mplat.uijfx.uiviews.acts.TopicTrain6800Act;

import org.apache.commons.lang.StringUtils;

/**
 * 专项技能训练事件处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrainViewEvent.java, V1.0.1 2013-2-13 下午1:17:58 $
 */
public final class TopicTrainViewEvent extends AbstractWebViewEvent {

    /**
     * 默认构造器
     */
    public TopicTrainViewEvent(WebView view) {
        super(view);
    }

    /** 
     * @see mplat.uijfx.uiviews.acts.AbstractWebViewEvent#onFireEvent(java.lang.String)
     */
    protected final void onFireEvent(String arg) {
        System.out.println(arg + ": 点击了专项训练~~~~~");

        Stage stage = this.findStage();

        // ACLS基础知识训练
        if (StringUtils.equals("1", arg)) {
            new TopicTrain0101Act(stage).show();
        }

        // ECG心律识别训练
        else if (StringUtils.equals("2", arg)) {
            new TopicTrain0201Act(stage).show();
        }

        // 注射泵使用训练
        else if (StringUtils.equals("6", arg)) {
            new TopicTrain6800Act(PumpMgt.EJECTOR, stage).show();
        }

        // 注射泵使用训练
        else if (StringUtils.equals("8", arg)) {
            new TopicTrain6800Act(PumpMgt.TRANSFER, stage).show();
        }
    }

}
