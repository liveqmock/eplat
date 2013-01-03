/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat;

import com.mplat.context.MplatContextHolder;
import com.mplat.controls.LoginFrame;
import com.mplat.util.ConfigUtils;
import com.mplat.util.Constants;
import com.mplat.util.JARUtils;
import com.mplat.util.UIUtils;
import java.awt.Frame;
import java.io.File;
import java.io.StringReader;
import javax.swing.SwingUtilities;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shizihu
 */
public class MplatMain {

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("欢迎使用mplat系统，当前版本:" + Constants.VERSION);
        System.out.println("===============================================");

        // 系统根目录
        String rootPath = findRootPath();

        // 初始化系统配置
        initSysConfig(rootPath);

        // 初始化日志配置
        initLogConfig();

        // 初始化上下文
        initApplicationContext();

        // 启动GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIUtils.initSystemUI();

                Frame frame = new LoginFrame();
                UIUtils.center(frame);

                frame.setVisible(true);
            }
        });
    }

    /**
     * 寻找根目录
     */
    private static final String findRootPath() {
        File file = new File(".");
        String root = file.getAbsolutePath();
        System.out.println("#系统根目录：" + root);

        return root;
    }

    /**
     * 系统配置初始化
     */
    private static void initSysConfig(String rootPath) {
        File file = new File(rootPath);
        String root = file.getAbsolutePath();

        File libPath = new File(file, "lib");
        System.out.println("#程序库根目录：" + libPath.getAbsolutePath());
        JARUtils.loadJarPath(libPath.getAbsolutePath());

        root = FilenameUtils.normalizeNoEndSeparator(root);
        System.out.println("#转换系统根目录：" + root);

        String config = FilenameUtils.normalizeNoEndSeparator(root + "/config/system-config.xml");
        ConfigUtils.initialize(config);

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

    /**
     * 初始化上下文
     */
    private static void initApplicationContext() {
        String config = ConfigUtils.findConfigPath();
        String path = FilenameUtils.normalize(config + "/mplat-context.xml");

        new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/*.xml");

        MplatContextHolder.testMessage();
    }
    
    /**
     * 退出系统
     */
    public static void exitSystem() {
        System.exit(0);
    }
    
}
