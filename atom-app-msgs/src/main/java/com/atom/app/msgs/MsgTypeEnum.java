/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.app.msgs;

import com.atom.core.lang.enums.EnumBase;

/**
 * 消息类型枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: MsgTypeEnum.java, V1.0.1 2013-2-14 上午10:40:54 $
 */
public enum MsgTypeEnum implements EnumBase {
    //
    INPUT(1, "上行"),
    //
    OUTPUT(2, "下行"),
    //
    ;

    /** ID */
    private final int    id;
    /** 描述 */
    private final String desp;

    /**
     * 构造器
     */
    private MsgTypeEnum(int id, String desp) {
        this.id = id;
        this.desp = desp;
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
