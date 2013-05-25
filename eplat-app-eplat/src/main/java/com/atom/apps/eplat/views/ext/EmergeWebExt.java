/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.swt.SWT;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.utils.LogUtils;

/**
 * 急救训练/考核
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeWebExt.java, V1.0.1 2013-4-5 下午2:49:57 $
 */
public class EmergeWebExt extends AbstractWebEvent {
    /** 页面ID */
    private final String              webNo;

    /** 急救训练ID与Web映射 */
    private final Map<String, String> webs = new ConcurrentHashMap<String, String>();
    {
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
     * 默认构造器
     */
    public EmergeWebExt(final String no) {
        this.webNo = no;

        // 初始化页面
        String tabData = SWTUtils.TD_EMERGE_WEB;
        this.removeTabItem(tabData);

        String name = this.webs.get(this.webNo);
        super.initWebViewExt(tabData, SWT.CLOSE, name, SWTUtils.findHtml(name + ".html"));
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        LogUtils.get().info("进入急救案例训练操作-[{}].", arg);
        
        // TODO: 最复杂的页面
        
        return "1";
    }

}
