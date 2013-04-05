/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;

/**
 * 课件PPT
 * 
 * @author obullxl@gmail.com
 * @version $Id: CourseSlideExt.java, V1.0.1 2013-4-4 下午4:54:30 $
 */
public class CourseSlideExt extends AbstractWebEvent {
    /** PPT序号 */
    private final String              slideNo;

    /** PPT页面 */
    private final List<String>        ppts   = new ArrayList<String>();

    /** PPT序号 */
    private int                       pptNo  = -1;

    /** PPT */
    private final Map<String, String> slides = new ConcurrentHashMap<String, String>();
    {
        this.slides.put("01", "呼吸系统急症");
        this.slides.put("02", "急性中风");
        this.slides.put("03", "被证实为室颤：用自动除颤器（AED）和CPR施救");
        this.slides.put("04", "心动过缓");
        this.slides.put("05", "室颤或无脉搏室速");
        this.slides.put("06", "无脉搏心电活动");
        this.slides.put("07", "急性冠状动脉综合征");
        this.slides.put("08", "不稳定性心动过速");
        this.slides.put("09", "心室停搏");
        this.slides.put("10", "稳定性心动过速");
    }

    /** PPT路径 */
    private final String              path   = CfgUtils.findConfigPath() + "/views/ppt/";

    /**
     * 默认构造器
     */
    public CourseSlideExt(String slideNo) {
        this.slideNo = slideNo;

        // 初始化PPT
        this.initWebPpts();

        // 初始化页面
        String tabData = this.slides.get(this.slideNo);
        CTabItem tabItem = super.initWebViewExt(tabData, SWT.CLOSE, tabData, this.findPptMain());
        this.initPptEvents(tabItem);
    }

    /**
     * 初始化PPT
     */
    private void initWebPpts() {
        String name = this.slides.get(this.slideNo);
        String path = FilenameUtils.normalize(this.path + name + ".files");
        File root = new File(path);
        if (!root.exists() || !root.isDirectory()) {
            LogUtils.get().error("[系统课件]-目录不存在[{}].", path);
            throw new RuntimeException("[系统课件]-目录不存在[" + path + "].");
        }

        root.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (StringUtils.startsWithIgnoreCase(name, "slide") && (StringUtils.endsWithIgnoreCase(name, ".htm") || StringUtils.endsWithIgnoreCase(name, ".html"))) {
                    ppts.add(name);
                }

                return false;
            }
        });

        // 排序
        Collections.sort(this.ppts);
    }

    /**
     * 初始化事件
     */
    private void initPptEvents(CTabItem tabItem) {
        if (tabItem == null) {
            return;
        }

        final Browser browser = (Browser) tabItem.getControl();
        if (browser == null) {
            return;
        }

        // 鼠标左键事件
        browser.addMouseListener(new MouseAdapter() {
            public void mouseDown(MouseEvent evt) {
                // LogUtils.get().info("鼠标事件-{}", evt);
                if (evt.button == SWT.KeyDown) {
                    showPpt(browser, (pptNo + 1));
                }
            }
        });

        // 方向键盘事件
        browser.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                // LogUtils.get().info("键盘事件-{}", evt);
                // 前一张
                if (evt.keyCode == SWT.ARROW_UP || evt.keyCode == SWT.ARROW_LEFT) {
                    showPpt(browser, (pptNo - 1));
                }

                // 后一张
                else if (evt.keyCode == SWT.ARROW_DOWN || evt.keyCode == SWT.ARROW_RIGHT) {
                    showPpt(browser, (pptNo + 1));
                }
            }
        });
    }

    /**
     * 获取PPT主页
     */
    private String findPptMain() {
        String name = this.slides.get(this.slideNo);
        return "file:///" + FilenameUtils.normalize(this.path + name + ".htm");
    }

    /**
     * 获取PPT页面
     */
    private String findPptPage() {
        String name = this.slides.get(this.slideNo);
        return "file:///" + FilenameUtils.normalize(this.path + name + ".files/" + this.ppts.get(this.pptNo));
    }

    /**
     * 展示PPT页面
     */
    private void showPpt(final Browser browser, int no) {
        if (no < 0) {
            no = -1;
        }

        if (no >= this.ppts.size()) {
            no = (this.ppts.size() - 1);
        }

        if (this.pptNo != no) {
            this.pptNo = no;

            if (this.pptNo < 0) {
                browser.setUrl(this.findPptMain());
            } else {
                browser.setUrl(this.findPptPage());
            }
        } else {
            // 蜂鸣声
            this.findDisplay().beep();
        }
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        return "1";
    }

}
