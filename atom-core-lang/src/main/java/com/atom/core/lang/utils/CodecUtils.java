/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 提供与编码、解码相关的工具方法
 * 
 * @author obullxl@gmail.com
 * @version $Id: CodecUtils.java, 2012-9-4 下午11:01:20 Exp $
 */
public final class CodecUtils {

    /** UserID SEQ长度 */
    public static final int UID_SEQ_LENGTH = 10;

    /**
     * 生成用户UID
     * <p/>
     * 生成规则:
     * [6688固定前缀] + [10位流水号的第2位] + [10位流水号的第1位]+ [账户类别固定为1] + [10位流水号的后8位] + [校验位]
     */
    public static final String makeUserID(long id) {
        StringBuilder txt = new StringBuilder();

        // 生成10位顺序号
        String idseq = Long.toString(id);
        if (StringUtils.length(idseq) > UID_SEQ_LENGTH) {
            throw new RuntimeException("UserID SEQ超长, 最长只能为[" + UID_SEQ_LENGTH + "]位!");
        }

        String strSeq = StringUtils.leftPad(idseq, UID_SEQ_LENGTH, "0");

        // 2088
        txt.append("6688");

        // 10位流水号的第2位
        txt.append(strSeq.charAt(1));
        // 10位流水号的第1位
        txt.append(strSeq.charAt(0));

        // 账户类别
        txt.append("0");

        // 10位流水号的后8位
        txt.append(strSeq.substring(2));

        // 校验码
        int checksum = checksum(txt.toString());
        txt.append(Integer.toString(checksum));

        return txt.toString();
    }

    /**
     * 计算校验码。
     * <p/>
     * 校验码规则: 异或，并用10取模。
     */
    public static final int checksum(String string) {
        // 计算校验码
        int checksum = 0;

        // 计算校验和
        for (int i = 0; i < string.length(); i++) {
            checksum ^= (string.charAt(i) - '0');
        }

        return checksum % 10;
    }

    /**
     * 获取一个随机字符串。
     *
     * @param length
     *            随机字符串的长度
     *
     * @return 一个随机字符串
     */
    public static final String getRandomString(int length) {
        return getRandomString(length, true);
    }

    /**
     * 获取一个随机字符串。
     *
     * @param length
     *            随机字符串的长度
     * @param allowLetter
     *            是否允许使用字母
     *
     * @return 一个随机字符串
     */
    public static final String getRandomString(int length, boolean allowLetter) {
        if (length < 1) {
            return StringUtils.EMPTY;
        }

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(allowLetter ? getRandomChar(i != 0) : getRandomDigit(i != 0));
        }

        return sb.toString();
    }

    /**
     * 获取一个随机字符（允许数字0-9和小写字母）。
     *
     * @return 一个随机字符
     */
    public static final char getRandomChar(boolean allowZero) {
        int randomNumber = 0;

        do {
            randomNumber = (int) (Math.random() * 36);
        } while ((randomNumber == 0) && !allowZero);

        if (randomNumber < 10) {
            return (char) (randomNumber + '0');
        } else {
            return (char) (randomNumber - 10 + 'a');
        }
    }

    /**
     * 获取一个随机字符（只允许数字0-9）。
     *
     * @return 一个随机字符
     */
    public static final char getRandomDigit(boolean allowZero) {
        int randomNumber = 0;

        do {
            randomNumber = (int) (Math.random() * 10);
        } while ((randomNumber == 0) && !allowZero);

        return (char) (randomNumber + '0');
    }

}
