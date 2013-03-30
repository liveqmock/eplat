/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.utils;

import java.awt.Desktop;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.atom.core.lang.utils.CfgUtils;

/**
 * 常量
 * 
 * @author obullxl@gmail.com
 * @version $Id: UConst.java, V1.0.1 2013-1-31 下午1:34:06 $
 */
public class UConst {

    /** 1.0透明度 */
    public static final double OPACITY_ON     = 1.0;

    /** 0.5透明度 */
    public static final double OPACITY_OUT    = 0.5;

    /** 随机最多选择个数 */
    public static final int    MAX_EXAM_ITEMS = 8;

    /** Java存放JS变量名 */
    public static final String VAR_APP        = "app";

    /** 串口唯一TabID */
    public static final String PORT_TAB_ID    = "PORT_TAB_ID";

    /**
     * 单机个人版用户手册
     */
    public static final String findManualUrl() {
        return FilenameUtils.normalize(CfgUtils.findConfigPath() + "/manual.pdf");
    }

    /**
     * 执行帮助操作
     */
    public static final void doHelpAction() {
        String file = findManualUrl();
        try {
            Desktop.getDesktop().open(new File(file));
        } catch (Exception e) {
            throw new RuntimeException("打开帮助手册[" + file + "]异常", e);
        }
    }

}
