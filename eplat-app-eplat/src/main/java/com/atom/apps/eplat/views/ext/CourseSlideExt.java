/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.ext;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;

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

    /** PPT */
    private final Map<String, String> slides = new ConcurrentHashMap<String, String>();
    {
        this.slides.put("01", "呼吸系统急诊");
        this.slides.put("02", "急性中风");
        this.slides.put("03", "被证实为室颤：用自动除颤器（AED）和CPR施救");
        this.slides.put("04", "心动过缓");
        this.slides.put("05", "室颤/无脉搏室速");
        this.slides.put("06", "无脉搏心电活动");
        this.slides.put("07", "急性冠脉综合征");
        this.slides.put("08", "不稳定性心动过速");
        this.slides.put("09", "心室停搏");
        this.slides.put("10", "稳定性心动过速");
    }

    /**
     * 默认构造器
     */
    public CourseSlideExt(String slideNo) {
        this.slideNo = slideNo;

        // 初始化PPT
        this.initWebPpts();

        // 初始化页面
        String tabData = this.slides.get(this.slideNo);
        String html = "file:///D:/ACLS8000/Courseware/" + this.slides.get(this.slideNo) + ".htm";
        super.initWebViewExt(tabData, SWT.CLOSE, tabData, html);
    }

    /**
     * 初始化PPT
     */
    private void initWebPpts() {
        String name = this.slides.get(this.slideNo);
        String xml = FilenameUtils.normalize("D:/ACLS8000/Courseware/" + name + ".files/filelist.xml");
        InputStream input = null;
        try {
            input = new FileInputStream(xml);
            List<String> lines = IOUtils.readLines(input);

            for (String line : lines) {
                if (StringUtils.isBlank(line) || !StringUtils.containsIgnoreCase(line, "slide") || !StringUtils.containsIgnoreCase(line, ".htm")) {
                    continue;
                }

                String no = StringUtils.substringBetween(line, "slide", ".htm");
                this.ppts.add("slide" + no + ".htm");
            }
        } catch (Exception e) {
            LogUtils.get().error("[系统课件]-读取文件列表异常, XML[{}].", xml, e);
            throw new RuntimeException("[系统课件]-读取文件列表异常, XML[" + xml + "].", e);
        } finally {
            IOUtils.closeQuietly(input);
        }

        // 排序
        Collections.sort(this.ppts);
    }

    /** 
     * @see com.atom.apps.eplat.views.ext.AbstractWebEvent#onFireEvent(java.lang.String)
     */
    public Object onFireEvent(String arg) {
        return "1";
    }

}
