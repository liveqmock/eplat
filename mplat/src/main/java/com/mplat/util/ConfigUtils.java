/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.io.IOUtils;

/**
 * @author shizihu
 */
public final class ConfigUtils {

    /**
     * 配置参数
     */
    private static final Map<String, String> cfgs = new ConcurrentHashMap<String, String>();

    /**
     * 初始化
     */
    public static void initialize(String path) {
        cfgs.clear();

        // 系统环境变更
        Properties syspaths = System.getProperties();
        for (Map.Entry<Object, Object> entry : syspaths.entrySet()) {
            cfgs.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

        // 应用配置
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            Properties props = new Properties();
            props.loadFromXML(input);

            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                cfgs.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("", e);
        } finally {
            IOUtils.closeQuietly(input);

            // 打印系统配置
            for (Map.Entry<String, String> entry : cfgs.entrySet()) {
                System.out.println(entry.getKey() + "\t=\t" + entry.getValue());
            }
        }
    }

    /**
     * 设置值
     */
    public static void setValue(String key, String value) {
        cfgs.put(key, value);
    }

    /**
     * 取得参数值
     */
    public static String findValue(String key) {
        return cfgs.get(key);
    }

    /**
     * 系统根目录
     */
    public static String findRootPath() {
        return findValue(Constants.ROOT_PATH_KEY);
    }

    /**
     * 系统配置目录
     */
    public static String findConfigPath() {
        return findValue(Constants.ROOT_PATH_KEY) + "/config";
    }

}
