/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.enums;

import com.atom.core.lang.enums.EnumBase;

/**
 * CourseWare PPT枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: TabCWPptEnum.java, V1.0.1 2013-2-2 下午8:36:39 $
 */
public enum TabCWPptEnum implements EnumBase {
    //
    CW01(0x0001, "呼吸系统急诊"),
    //
    CW02(0x0002, "急性中风"),
    //
    CW03(0x0003, "被证实为室颤：用自动除颤器（AED）和CPR施救"),
    //
    CW04(0x0004, "心动过缓"),
    //
    CW05(0x0005, "室颤/无脉搏室速"),
    //
    CW06(0x0006, "无脉搏心电活动"),
    //
    CW07(0x0007, "急性冠脉综合征"),
    //
    CW08(0x0008, "不稳定性心动过速"),
    //
    CW09(0x0009, "心室停搏"),
    //
    CW10(0x000A, "稳定性心动过速"),
    //
    ;

    /** ID */
    private final int    id;
    /** 描述 */
    private final String desp;

    /**
     * CTOR
     */
    private TabCWPptEnum(int id, String desp) {
        this.id = id;
        this.desp = desp;
    }

    /**
     * 根据代码取得枚举值
     */
    public static final TabCWPptEnum findByID(int id) {
        for (TabCWPptEnum enumm : values()) {
            if (enumm.id() == id) {
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
        return this.name();
    }

    /** 
     * @see com.atom.core.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

}
