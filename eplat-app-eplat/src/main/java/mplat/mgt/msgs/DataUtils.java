/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.msgs;

import com.atom.core.lang.utils.LogUtils;

/**
 * 数据工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: DataUtils.java, V1.0.1 2013-5-25 下午5:19:10 $
 */
public class DataUtils {
    /** 消息头 */
    public static final int[] HEAD     = new int[] { 0xFF, 0x00, 0xFF, 0x00, 0xFF };
    /** 消息尾 */
    public static final int[] TAIL     = new int[] { 0xFC, 0xFC, 0xFC };
    /** 消息数据最小长度 */
    public static final int   MIN_BODY = 5;
    /** 整个消息最小长度 */
    public static final int   MIN_SIZE = HEAD.length + MIN_BODY + TAIL.length;

    /**
     * 解析消息（包括头和尾）
     */
    public static final DataItem parse(int[] src) {
        // 去掉消息头和尾
        int[] body = new int[src.length - HEAD.length - TAIL.length];
        System.arraycopy(src, HEAD.length, body, 0, body.length);

        // 消息数据内容
        int[] data = new int[body.length - 2];
        System.arraycopy(body, 2, data, 0, data.length);

        return new DataItem(body[0], body[1], data);
    }

    /**
     * 组装消息（包括头和尾）
     */
    public static final int[] compat(DataItem data) {
        if (data.getMsgSize() != data.getMsgBody().length) {
            LogUtils.error("组装消息长度错误-" + data, new RuntimeException("组装消息长度错误"));
            return null;
        }

        int[] msg = new int[data.getMsgSize() + 2 + HEAD.length + TAIL.length];

        // 复制头
        System.arraycopy(HEAD, 0, msg, 0, HEAD.length);

        // 类型+长度
        msg[HEAD.length] = data.getMsgCatg();
        msg[HEAD.length + 1] = data.getMsgSize();

        // 数据内容
        System.arraycopy(data.getMsgBody(), 0, msg, (HEAD.length + 2), data.getMsgSize());

        // 复制尾
        System.arraycopy(TAIL, 0, msg, (HEAD.length + 2 + data.getMsgSize()), TAIL.length);

        return msg;
    }

}
