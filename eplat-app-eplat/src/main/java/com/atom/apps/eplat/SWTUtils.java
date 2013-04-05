/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mplat.SWTMain;
import mplat.uijfx.images.IMGS;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.atom.core.lang.utils.CfgUtils;

/**
 * SWT工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTUtils.java, V1.0.1 2013-4-1 下午1:53:26 $
 */
public final class SWTUtils {
    /** 图片缓存 */
    private static Map<String, Image> _images         = new ConcurrentHashMap<String, Image>();

    /** 应用主窗口 */
    private static SWTMainView        _MainView;

    /** JS回调函数名 */
    public static final String        FUNC_FIRE_EVENT = "fireEvent";

    /** 标签值 */
    public static final String        TAB_DATA_KEY    = "EPLAT-TAB-DATA-KEY";

    public static final String        TD_HOME_MAIN    = "EPLAT-TAB-DATA-HomeMain";
    public static final String        TD_COURSE_WARE  = "EPLAT-TAB-DATA-CourseWare";
    public static final String        TD_TOPIC_TRAIN  = "EPLAT-TAB-DATA-TopicTrain";
    public static final String        TD_EMERGE_TRAIN = "EPLAT-TAB-DATA-EmergeTrain";
    public static final String        TD_EMERGE_EXAM  = "EPLAT-TAB-DATA-EmergeExam";
    public static final String        TD_SYSTEM_CFG   = "EPLAT-TAB-DATA-SystemCfg";

    /**
     * 执行销毁
     */
    public static void dispose() {
        // 1.图片
        for (Image image : _images.values()) {
            if (!image.isDisposed()) {
                image.dispose();
            }
        }
        _images.clear();
    }

    /**
     * 设置系统主窗口
     */
    public static void setMainView(SWTMainView _view) {
        _MainView = _view;
    }

    /**
     * 获取系统主窗口
     */
    public static SWTMainView findMainView() {
        return _MainView;
    }

    /**
     * 窗口循环，避免退出
     */
    public static void tryLoop(Shell shell) {
        Display display = Display.getDefault();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        display.dispose();
    }

    /**
     * 窗口居中
     */
    public static void center(final Control control) {
        int width = control.getMonitor().getClientArea().width;
        int height = control.getMonitor().getClientArea().height;
        int x = control.getSize().x;
        int y = control.getSize().y;

        if (x > width) {
            control.getSize().x = width;
        }
        if (y > height) {
            control.getSize().y = height;
        }

        control.setLocation((width - x) / 2, (height - y) / 2);
    }

    /**
     * 获取图片
     */
    public static Image findImage(String name) {
        return findImage(Display.getDefault(), name);
    }

    /**
     * 获取图片
     */
    public static Image findImage(final Device device, String name) {
        Image image = _images.get(name);
        if (image == null || image.isDisposed()) {
            _images.remove(name);

            InputStream input = IMGS.class.getResourceAsStream(name);
            try {
                image = new Image(device, input);
                _images.put(name, image);
            } finally {
                IOUtils.closeQuietly(input);
            }
        }

        return image;
    }

    /**
     * 获取图片
     */
    public static Image findImage(final Device device, String name, Rectangle size) {
        Image tmpImg = findImage(device, name);
        if (tmpImg == null) {
            return null;
        }

        if (size.width == size.height && size.width == 0) {
            return tmpImg;
        }

        Rectangle tmpSize = tmpImg.getBounds();
        int tmpWidth = tmpSize.width;
        int tmpHeight = tmpSize.height;

        if (size.width == 0 || size.height == 0) {
            // 重新计算尺寸
            if (size.height == 0) {
                size.height = (tmpHeight * size.width) / tmpWidth;
            } else if (size.width == 0) {
                size.width = (tmpWidth * size.height) / tmpHeight;
            }
        }

        // 缩放
        return new Image(device, tmpImg.getImageData().scaledTo(size.width, size.height));
    }

    /**
     * 获取应用小图标
     */
    public static Image[] findImgIcons() {
        Image[] images = new Image[1];
        images[0] = findImage("img-icon-01.png");

        return images;
    }

    /**
     * 获取HTML视图
     */
    public static String findHtml(String name) {
        return "file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/" + name);
    }

    /**
     * 新建尺寸
     */
    public static Rectangle toSize(int width, int height) {
        return new Rectangle(0, 0, width, height);
    }

    /**
     * 警告提示
     */
    public static void alert(final Shell shell, String message) {
        alert(shell, "警告提示", message, SWT.ICON_ERROR | SWT.OK);
    }

    /**
     * 警告提示
     */
    public static void alert(final Shell shell, String title, String message, int style) {
        MessageBox msgBox = new MessageBox(shell, style);
        msgBox.setText(title);
        msgBox.setMessage(message);

        msgBox.open();
    }

    /**
     * 确认提示
     */
    public static int confirm(final Shell shell, String message) {
        return confirm(shell, "确认提示", message, SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
    }

    /**
     * 确认提示
     */
    public static int confirm(final Shell shell, String title, String message, int style) {
        MessageBox msgBox = new MessageBox(shell, style);
        msgBox.setText(title);
        msgBox.setMessage(message);

        return msgBox.open();
    }

    /**
     * 退出系统
     */
    public static void exitSystem() {
        SWTMain.exitSystem();
    }

    /**
     * 退出系统
     */
    public static void exitSystem(final Shell shell) {
        int rtn = confirm(shell, "你确认退出系统吗？");
        if (rtn == SWT.OK) {
            SWTMain.exitSystem();
        }
    }

    /**
     * 获取标签
     */
    public static CTabItem findTabItem(CTabFolder tabFolder, String value) {
        CTabItem[] items = tabFolder.getItems();
        for (CTabItem item : items) {
            String data = String.valueOf(item.getData(TAB_DATA_KEY));
            if (StringUtils.equalsIgnoreCase(value, data)) {
                return item;
            }
        }

        return null;
    }

}
