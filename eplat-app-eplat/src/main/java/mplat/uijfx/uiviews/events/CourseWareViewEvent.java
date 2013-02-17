/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.events;

import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import mplat.mgt.enums.TabCWPptEnum;
import mplat.uijfx.uiviews.PptImageAct;

import org.apache.commons.lang.StringUtils;

import com.atom.core.uijfx.utils.TabPaneUtils;

/**
 * 系统课件页面事件处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: CourseWareViewEvent.java, V1.0.1 2013-2-13 下午1:12:03 $
 */
public final class CourseWareViewEvent extends AbstractWebViewEvent {

    /**
     * 默认构造器
     */
    public CourseWareViewEvent(WebView view) {
        super(view);
    }

    /** 
     * @see mplat.uijfx.uiviews.events.AbstractWebViewEvent#onFireEvent(java.lang.String)
     */
    protected final void onFireEvent(String arg) {
        String pptNo = StringUtils.leftPad(arg, 2, "0");
        PptImageAct view = new PptImageAct(this.findStage(), "CW" + pptNo);

        Tab tab = new Tab("PPT:" + TabCWPptEnum.findByID(Integer.parseInt(arg)).desp());
        tab.setClosable(true);
        tab.setContent(view.findGroupViewProperty().get());

        TabPaneUtils.addAndActiveTab(this.findTabPane(), tab);

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
