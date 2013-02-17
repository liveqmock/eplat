/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.app.msgs;

import java.nio.ByteBuffer;

/**
 * 上/下行消息基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractMSG.java, V1.0.1 2013-2-14 上午10:38:42 $
 */
public abstract class AbstractMSG implements Cloneable {
    /** 消息最小长度 */
    public static final int MSG_MIN_LENGTH = 5;
    /** 消息最大长度 */
    public static final int MSG_MAX_LENGTH = 128;

    /** 消息类型 */
    private MsgTypeEnum     msgTypeEnum;

    /** 消息头 */
    private byte[]          msgHead;

    /** 消息尾 */
    private byte[]          msgTail;

    /** 消息内容 */
    private ByteBuffer      content;

    /**
     * 默认构造器
     */
    public AbstractMSG() {
        this.msgTypeEnum = MsgTypeEnum.INPUT;
        this.msgHead = new byte[] {};
        this.msgTail = new byte[] {};

        this.content = ByteBuffer.allocate(MSG_MAX_LENGTH);
        this.content.clear();
    }

    /**
     * 在消息尾部增加消息内容
     */
    public final AbstractMSG put(byte msg) {
        this.content.put(msg);
        return this;
    }

    /**
     * 在消息尾部增加消息内容
     */
    public final AbstractMSG put(byte[] msgs) {
        this.content.put(msgs);
        return this;
    }

    /**
     * 在指定位置增加消息内容
     */
    public final AbstractMSG put(int index, byte msg) {
        this.content.put(index, msg);
        return this;
    }

    /**
     * 获取当前消息长度
     */
    public final int findMsgLength() {
        return this.findMsgLength(0);
    }

    /**
     * 获取当前消息长度
     */
    public final int findMsgLength(int deta) {
        return this.msgHead.length + this.msgTail.length + this.content.position() + deta;
    }

    /**
     * 组装成消息字节串
     */
    public final byte[] toMsgPackage() {
        ByteBuffer buffer = ByteBuffer.allocate(this.findMsgLength());
        buffer.put(this.msgHead).put(this.content).put(this.msgTail);

        this.content.clear();

        return buffer.array();
    }

}
