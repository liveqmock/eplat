/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mplat.uijfx.images.IMGS;

import org.apache.commons.io.IOUtils;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * SWT工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTUtils.java, V1.0.1 2013-4-1 下午1:53:26 $
 */
public class SWTUtils {
    /** 图片缓存 */
    private static final Map<String, Image> _images = new ConcurrentHashMap<String, Image>();

    /**
     * 执行销毁
     */
    public static final void dispose() {
        // 1.图片
        for (Image image : _images.values()) {
            if (!image.isDisposed()) {
                image.dispose();
            }
        }
        _images.clear();
    }

    /**
     * 窗口循环，避免退出
     */
    public static final void tryLoop(Shell shell) {
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
    public static final void center(final Control control) {
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
    public static final Image findImage(Device device, String name) {
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

}
