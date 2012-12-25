/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat;

import com.mplat.controls.LoginFrame;
import com.mplat.util.ConfigUtils;
import com.mplat.util.Constants;
import com.mplat.util.JARUtils;
import java.awt.Frame;
import java.io.File;
import java.io.StringReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author shizihu
 */
public class MplatMain {

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        System.out.println("系统启动");

        initSysConfig();
        
        initLogConfig();

        // ApplicationContext context = new ClassPathXmlApplicationContext("");

        Frame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }

    /**
     * 系统配置初始化
     */
    private static void initSysConfig() {
        File file = new File(".");
        String root = file.getAbsolutePath();
        System.out.println("#系统根目录：" + root);
        
        File libpath = new File(file, "lib");
        System.out.println("#程序库根目录：" + libpath.getAbsolutePath());
        JARUtils.loadJarPath(libpath.getAbsolutePath());
        
        root = FilenameUtils.normalizeNoEndSeparator(root);
         System.out.println("#转换系统根目录：" + root);
         
         ConfigUtils.setValue(Constants.ROOT_PATH_KEY, root);
    }
    
    /**
     * 系统日志系统初始化
     */
    private static void initLogConfig() {
        try {
            String root = ConfigUtils.findRootPath();
            String logpath = FilenameUtils.normalize(root + "/logs");
            FileUtils.forceMkdir(new File(logpath));

            String config = ConfigUtils.findConfigPath();
            String log4jpath = FilenameUtils.normalize(config + "/log4j.xml");
            File log4jfile = new File(log4jpath);

            String log4jContent = "";

            if (log4jfile != null && log4jfile.exists()) {
                log4jContent = FileUtils.readFileToString(log4jfile, "UTF-8");
            }

            String tmpath = StringUtils.replace(logpath, "\\", "/");
            log4jContent = StringUtils.replace(log4jContent, "${loggingRoot}", tmpath);
            new DOMConfigurator().doConfigure(new StringReader(log4jContent), LogManager.getLoggerRepository());
        } catch (Exception e) {
            System.err.println("#初始化Log4j异常！");
            e.printStackTrace();
        }
    }
    
}
