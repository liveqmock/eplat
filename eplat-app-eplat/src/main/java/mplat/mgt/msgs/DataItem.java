/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.msgs;

/**
 * 消息内容
 * 
 * @author obullxl@gmail.com
 * @version $Id: DataItem.java, V1.0.1 2013-5-25 下午5:27:29 $
 */
public class DataItem {
    /** 消息类型 */
    private final int   msgCatg;

    /** 消息长度 */
    private final int   msgSize;

    /** 消息内容 */
    private final int[] msgBody;

    /**
     * 构造消息内容对象
     */
    public DataItem(int msgCatg, int msgSize, int[] msgBody) {
        this.msgCatg = msgCatg;
        this.msgSize = msgSize;
        this.msgBody = msgBody;
    }

    // ~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public int getMsgCatg() {
        return msgCatg;
    }

    public int getMsgSize() {
        return msgSize;
    }

    public int[] getMsgBody() {
        return msgBody;
    }

}
