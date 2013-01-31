/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.enums;

import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.enums.EnumBase;

/**
 * Tab数据枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: TabDataEnum.java, V1.0.1 2013-1-31 下午1:21:41 $
 */
public enum TabDataEnum implements EnumBase {
    //
    MAIN_VIEW(0x0001, "MainView", "欢迎使用"),
    //
    COURSE_WARE(0x0002, "CourseWare", "系统课件"),
    //
    TOPIC_TRAIN(0x0003, "TopicTrain", "专项技能训练"),
    //
    EMERGE_TRAIN(0x0004, "EmergeTrain", "专项急救案例训练"),
    //
    EMERGE_EXAM(0x0005, "EmergeExam", "专项急救案例考核"),
    //
    SYSTEM_CFG(0x0006, "SystemCfg", "系统功能设置"),
    //
    ;

    /** ID */
    private final int    id;
    /** 代码 */
    private final String code;
    /** 描述 */
    private final String desp;

    /**
     * CTOR
     */
    private TabDataEnum(int id, String code, String desp) {
        this.id = id;
        this.code = code;
        this.desp = desp;
    }

    /**
     * 根据代码取得枚举值
     */
    public static final TabDataEnum findByCode(String code) {
        for (TabDataEnum enumm : values()) {
            if (StringUtils.equalsIgnoreCase(enumm.code(), code)) {
                return enumm;
            }
        }

        return null;
    }

    /** 
     * @see com.atom.core.lang.enums.EnumBase#id()
     */
    public int id() {
        return this.id;
    }

    /** 
     * @see com.atom.core.lang.enums.EnumBase#code()
     */
    public String code() {
        return this.code;
    }

    /** 
     * @see com.atom.core.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

}
