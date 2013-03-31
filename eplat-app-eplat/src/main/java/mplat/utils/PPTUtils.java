/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.utils;

import mplat.uijfx.images.IMGS;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * PPT工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: PPTUtils.java, V1.0.1 2013-2-7 下午7:20:11 $
 */
public final class PPTUtils {

    /**
     * 初始化按钮
     */
    private static void initButton(Button button, Image image) {
        button.setText(null);
        button.setMaxWidth(30.0);
        button.setMaxHeight(30.0);
        button.setGraphicTextGap(0.0);
        button.setCursor(Cursor.HAND);
        button.setGraphic(new ImageView(image));
    }

    /**
     * 初始化第1张PPT按钮
     */
    public static void initFirstButton(Button button) {
        initButton(button, findFirst());
    }

    /**
     * 初始化上1张PPT按钮
     */
    public static void initPreviousButton(Button button) {
        initButton(button, findPrevious());
    }

    /**
     * 初始化下1张PPT按钮
     */
    public static void initNextButton(Button button) {
        initButton(button, findNext());
    }

    /**
     * 初始化最后1张PPT按钮
     */
    public static void initLastButton(Button button) {
        initButton(button, findLast());
    }

    /**
     * 初始化最大化PPT按钮
     */
    public static void initFullScreenButton(Button button) {
        initButton(button, findFullScreen());
    }

    /**
     * 第1张PPT箭头
     */
    public static Image findFirst() {
        return new Image(IMGS.class.getResourceAsStream("icon-arrow-first.png"));
    }

    /**
     * 前1张PPT箭头
     */
    public static Image findPrevious() {
        return new Image(IMGS.class.getResourceAsStream("icon-arrow-previous.png"));
    }

    /**
     * 下1张PPT箭头
     */
    public static Image findNext() {
        return new Image(IMGS.class.getResourceAsStream("icon-arrow-next.png"));
    }

    /**
     * 最后1张PPT箭头
     */
    public static Image findLast() {
        return new Image(IMGS.class.getResourceAsStream("icon-arrow-last.png"));
    }

    /**
     * 最大化PPT箭头
     */
    public static Image findFullScreen() {
        return new Image(IMGS.class.getResourceAsStream("icon-full-screen.png"));
    }

    /**
     * PPT视图回调函数
     * 
     * @author obullxl@gmail.com
     * @version $Id: PPTViewCallback.java, V1.0.1 2013-2-13 下午1:32:08 $
     */
    public static interface PPTViewCallback {
        
        /**
         * 展示第X张PPT
         */
        public void showPpt(int no);
        
    }

}
