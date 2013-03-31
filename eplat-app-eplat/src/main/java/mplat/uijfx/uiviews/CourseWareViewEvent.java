/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import mplat.uijfx.uiviews.acts.PptImageAct;

import org.apache.commons.lang.StringUtils;

import com.atom.core.uijfx.utils.TabPaneUtils;

/**
 * 系统课件页面事件处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: CourseWareViewEvent.java, V1.0.1 2013-2-13 下午1:12:03 $
 */
public final class CourseWareViewEvent extends AbstractWebViewEvent {
    /** PPT前缀 */
    private static final String       PPT_PREFIX = "CourseWare";

    /** PPT */
    private final Map<String, String> ppts       = new ConcurrentHashMap<String, String>();

    /**
     * 默认构造器
     */
    public CourseWareViewEvent(WebView view) {
        super(view);

        // 初始化PPT映射
        this.initCourseWarePpts();
    }

    /**
     * 初始化PPT映射
     */
    private final void initCourseWarePpts() {
        this.ppts.put("01", "呼吸系统急诊");
        this.ppts.put("02", "急性中风");
        this.ppts.put("03", "被证实为室颤：用自动除颤器（AED）和CPR施救");
        this.ppts.put("04", "心动过缓");
        this.ppts.put("05", "室颤/无脉搏室速");
        this.ppts.put("06", "无脉搏心电活动");
        this.ppts.put("07", "急性冠脉综合征");
        this.ppts.put("08", "不稳定性心动过速");
        this.ppts.put("09", "心室停搏");
        this.ppts.put("10", "稳定性心动过速");
    }

    /** 
     * @see mplat.uijfx.uiviews.acts.AbstractWebViewEvent#onFireEvent(java.lang.String)
     */
    protected final void onFireEvent(String arg) {
        String pptNo = StringUtils.leftPad(arg, 2, "0");
        PptImageAct view = new PptImageAct(this.findStage(), "CW" + pptNo);

        Tab tab = new Tab(this.ppts.get(pptNo));
        tab.setClosable(true);
        tab.setUserData(PPT_PREFIX + pptNo);
        tab.setContent(view.findGroupViewProperty().get());

        TabPaneUtils.addUniqueTab(this.findTabPane(), tab);

        this.findStage().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent evt) {
                KeyCode kc = evt.getCode();
                if (kc == KeyCode.DOWN) {
                    System.out.println("123-按了向下的键哦，哈哈~~~~");
                }
            }
        });

        view.findGroupViewProperty().get().addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent evt) {
                KeyCode kc = evt.getCode();
                if (kc == KeyCode.DOWN) {
                    System.out.println("按了向下的键哦，哈哈~~~~");
                }
            }
        });
    }
}
