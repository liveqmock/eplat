/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * TopFrame事件代理
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopFrameEventProxy.java, V1.0.1 2013-1-29 下午6:42:03 $
 */
public class TopFrameEventProxy {
    /** 透明度 */
    public static final double       OPACITY_ON  = 1.0;
    public static final double       OPACITY_OUT = 0.5;
    
    /**
     * 默认实现
     */
    public static final TopFrameEventProxy get() {
        return new TopFrameEventProxy();
    }

    /* ~~ 系统课件 ~~ */

    public void onCourseWareInit(ImageView image) {
        image.setOpacity(OPACITY_ON);
    }

    public void onCourseWareMouseOn(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_ON);
        image.setCursor(Cursor.HAND);
    }

    public void onCourseWareMouseOut(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_ON);
    }

    public void onCourseWareMouseClick(MouseEvent evt, ImageView image) {
    }

    /* ~~ 专项技能训练 ~~ */

    public void onTopicTrainInit(ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onTopicTrainMouseOn(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_ON);
        image.setCursor(Cursor.HAND);
    }

    public void onTopicTrainMouseOut(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onTopicTrainMouseClick(MouseEvent evt, ImageView image) {
    }

    /* ~~ 专业急救训练 ~~ */

    public void onEmergeTrainInit(ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onEmergeTrainMouseOn(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_ON);
        image.setCursor(Cursor.HAND);
    }

    public void onEmergeTrainMouseOut(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onEmergeTrainMouseClick(MouseEvent evt, ImageView image) {
    }

    /* ~~ 专业急救考核 ~~ */

    public void onEmergeExamInit(ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onEmergeExamMouseOn(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_ON);
        image.setCursor(Cursor.HAND);
    }

    public void onEmergeExamMouseOut(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onEmergeExamMouseClick(MouseEvent evt, ImageView image) {
    }

    /* ~~ 系统功能设置 ~~ */

    public void onSystemCfgInit(ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onSystemCfgMouseOn(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_ON);
        image.setCursor(Cursor.HAND);
    }

    public void onSystemCfgMouseOut(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onSystemCfgMouseClick(MouseEvent evt, ImageView image) {
    }
    
    // ~~ 回到系统主页面 ~~ //
    
    public void onGotoMainInit(ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onGotoMainMouseOn(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_ON);
        image.setCursor(Cursor.HAND);
    }

    public void onGotoMainMouseOut(MouseEvent evt, ImageView image) {
        image.setOpacity(OPACITY_OUT);
        image.setCursor(Cursor.DEFAULT);
    }

    public void onGotoMainMouseClick(MouseEvent evt, ImageView image) {
    }

}
