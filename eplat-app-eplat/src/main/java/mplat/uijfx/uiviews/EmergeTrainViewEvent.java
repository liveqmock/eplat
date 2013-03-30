/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

import mplat.utils.UConst;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.utils.TabPaneUtils;
import com.atom.core.uijfx.utils.WebViewUtils;

/**
 * 急救案例训练事件处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeTrainViewEvent.java, V1.0.1 2013-2-13 下午1:14:37 $
 */
public final class EmergeTrainViewEvent extends AbstractWebViewEvent {
    /** 急救训练ID与Web映射 */
    private final Map<String, String> webs = new ConcurrentHashMap<String, String>();

    /**
     * 默认构造器
     */
    public EmergeTrainViewEvent(WebView view) {
        super(view);

        // 初始化Web映射关系
        this.initEmergeTrainWebs();
    }

    /**
     * 初始化Web映射关系
     */
    private final void initEmergeTrainWebs() {
        // 1.心脏停搏
        this.webs.put("11", "急性心包填塞引起PEA");
        this.webs.put("12", "无脉搏心脏骤停-室颤");
        this.webs.put("13", "急性心肌梗死引起心动过缓");
        this.webs.put("14", "张力性气胸引起PEA");
        this.webs.put("15", "肺栓塞引起PEA");

        // 2.心动过缓
        this.webs.put("21", "急性心肌梗死引起心动过缓");

        // 3.心动过速
        this.webs.put("31", "急性冠脉综合征-不稳定型心绞痛");
        this.webs.put("32", "不稳定性心动过速");
        this.webs.put("33", "急性冠脉综合征-前间壁心肌梗死");

        // 4.胸痛
        this.webs.put("41", "肺栓塞引起PEA");
        this.webs.put("42", "急性冠脉综合征-不稳定型心绞痛");
        this.webs.put("43", "急性冠脉综合征-前间壁心肌梗死");

        // 5.中毒及服药过量
        this.webs.put("51", "急性有机磷农药中毒");
        this.webs.put("52", "药物过量中毒-异搏定");
        this.webs.put("53", "药物过量中毒-心律平");

        // 6.代谢病及环境伤害
        this.webs.put("61", "糖尿病酮症酸中毒");

        // 7.呼吸困难
        this.webs.put("71", "支气管哮喘急性重度发作");
        this.webs.put("72", "呼吸衰竭");
        this.webs.put("73", "气道异物梗阻");

        // 8.颅脑损伤
        this.webs.put("81", "硬膜外血肿");
        this.webs.put("82", "脑卒中");
    }

    /** 
     * @see mplat.uijfx.uiviews.acts.AbstractWebViewEvent#onFireEvent(java.lang.String)
     */
    protected final void onFireEvent(String arg) {
        // 页面ID
        String id = StringUtils.substring(arg, 1);
        LogUtils.debug("急救案例训练-详细：" + arg + "-ID[" + id + "]-Web[" + this.webs.get(id) + "]~");

        // 布局
        BorderPane border = new BorderPane();

        // Center
        String html = this.findHtmlUrl(this.webs.get(id));
        final WebView webView = WebViewUtils.initWebView(html);
        WebViewUtils.registWebStateSucceedEvent(webView, new TrainOperateViewEvent(webView));
        border.setCenter(webView);

        final Tab tab = new Tab(this.webs.get(id));
        tab.setUserData(UConst.PORT_TAB_ID);
        tab.setClosable(true);
        tab.setContent(border);

        tab.setOnClosed(new EventHandler<Event>() {
            public void handle(Event event) {
                WebViewUtils.stopAllAudios(webView);
                WebViewUtils.stopAllVideos(webView);
            }
        });

        // 保存
        TabPaneUtils.addUniqueTab(this.findTabPane(), tab);
    }

    /**
     * HTML文件URL
     */
    private String findHtmlUrl(String name) {
        return "file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/" + name + ".html");
    }

}
