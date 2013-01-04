/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 主机信息
 * 
 * @author shizihu
 * @version $Id: HostUtils.java, v 0.1 2012-9-6 下午05:03:29 shizihu Exp $
 */
public final class HostUtils {
    /** 主机信息 */
    private static final HostInfo HOST_INFO = new HostInfo();

    /**
     * 取得Host的信息。
     *
     * @return <code>HostInfo</code>对象
     */
    public static final HostInfo getHost() {
        return HOST_INFO;
    }

    /**
     * 代表当前主机的信息。
     */
    public static final class HostInfo {
        private final String HOST_NAME;
        private final String HOST_ADDRESS;

        /**
         * 防止从外界创建此对象。
         */
        private HostInfo() {
            String hostName;
            String hostAddress;

            try {
                InetAddress localhost = InetAddress.getLocalHost();

                hostName = localhost.getHostName();
                hostAddress = localhost.getHostAddress();
            } catch (UnknownHostException e) {
                hostName = "localhost";
                hostAddress = "127.0.0.1";
            }

            HOST_NAME = hostName;
            HOST_ADDRESS = hostAddress;
        }

        /**
         * 取得当前主机的名称。
         * 
         * <p>
         * 例如：<code>"webserver1"</code>
         * </p>
         *
         * @return 主机名
         */
        public final String getName() {
            return HOST_NAME;
        }

        /**
         * 取得当前主机的地址。
         * 
         * <p>
         * 例如：<code>"192.168.0.1"</code>
         * </p>
         *
         * @return 主机地址
         */
        public final String getAddress() {
            return HOST_ADDRESS;
        }

        /**
         * 将当前主机的信息转换成字符串。
         *
         * @return 主机信息的字符串表示
         */
        public final String toString() {
            StringBuffer buffer = new StringBuffer();

            append(buffer, "Host Name:    ", getName());
            append(buffer, "Host Address: ", getAddress());

            return buffer.toString();
        }
    }

    private static void append(StringBuffer buffer, String caption, String value) {
        buffer.append(caption).append(
            StringUtils.defaultString(StringEscapeUtils.escapeJava(value), "[n/a]")).append("\n");
    }

}
