/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.util;

import java.io.File;

/**
 * @author Kitty
 */
public class FileUtils {
    
    /**
     * 文件或目录是否存在
     */
    public static boolean exist(String path) {
        File file = new File(path);
        return (file != null && file.exists());
    }
}
