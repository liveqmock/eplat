/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mplat.mgt.enums.TabDataEnum;
import mplat.uijfx.controls.TopFrameControl;
import mplat.uijfx.images.IMGS;
import mplat.uijfx.uiviews.acts.MainViewAct;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ObjectUtils;

import com.atom.core.lang.utils.CfgUtils;

/**
 * 主视图MainView工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: MViewUtils.java, V1.0.1 2013-2-13 上午11:31:48 $
 */
public final class MViewUtils {
    /** 主视图 */
    private static MainViewAct _view;

    /**
     * 获取MainView主视图控制器
     */
    public static MainViewAct get() {
        if (_view == null) {
            throw new IllegalArgumentException("获取主视图失败，主视图为NULL.");
        }

        return _view;
    }

    /**
     * 设置MainView主视图控制器
     */
    public static void set(MainViewAct view) {
        if (view == null) {
            throw new RuntimeException("主视图为NULL，设置失败！");
        }

        if (_view == null) {
            _view = view;
        } else {
            if (_view != view) {
                // 重复设置不同的主视图
                throw new RuntimeException("主视图已经存在为[" + _view.getClass().getName() + "]，新主视图为[" + view.getClass().getName() + "]，非同一个，主视图设置失败！");
            }
        }
    }

    /**
     * Tab图片：欢迎页面
     */
    public static Image findTabWelcome() {
        return new Image(IMGS.class.getResourceAsStream("tab-welcome.jpg"), 20, 20, false, false);
    }

    /**
     * Tab图片：系统课件
     */
    public static Image findTabCourseWare() {
        return new Image(IMGS.class.getResourceAsStream("btn-course-ware.png"), 20, 20, false, false);
    }

    /**
     * Tab图片：专项训练
     */
    public static Image findTabTopicTrain() {
        return new Image(IMGS.class.getResourceAsStream("btn-topic-train.jpg"), 20, 20, false, false);
    }

    /**
     * Tab图片：急救训练
     */
    public static Image findTabEmergeTrain() {
        return new Image(IMGS.class.getResourceAsStream("btn-emerge-train.jpg"), 20, 20, false, false);
    }

    /**
     * Tab图片：急救考核
     */
    public static Image findTabEmergeExam() {
        return new Image(IMGS.class.getResourceAsStream("btn-emerge-exam.jpg"), 20, 20, false, false);
    }

    /**
     * Tab图片：系统设置
     */
    public static Image findTabSystemCfg() {
        return new Image(IMGS.class.getResourceAsStream("btn-system-cfg.jpg"), 20, 20, false, false);
    }

    /**
     * 初始化Top组件
     */
    public static void initTopFrame(final TopFrameControl ctrl, final ImageView spec) {
        List<ImageView> imgs = new ArrayList<ImageView>();
        imgs.add(ctrl.findImgCourseWare());
        imgs.add(ctrl.findImgTopicTrain());
        imgs.add(ctrl.findImgEmergeTrain());
        imgs.add(ctrl.findImgEmergeExam());
        imgs.add(ctrl.findImgSystemCfg());

        spec.setOpacity(UConst.OPACITY_ON);
        
        for (final ImageView img : imgs) {
            // 鼠标ON
            img.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent evt) {
                    img.setCursor(Cursor.HAND);
                    if (img != spec) {
                        img.setOpacity(UConst.OPACITY_ON);
                    }
                }
            });

            // 鼠标OUT
            img.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent evt) {
                    img.setCursor(Cursor.DEFAULT);
                    if (img != spec) {
                        img.setOpacity(UConst.OPACITY_OUT);
                    }
                }
            });

            // 鼠标Click
            img.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent evt) {
                    String udata = ObjectUtils.toString(img.getUserData());
                    ctrl.findRootAct().activeTab(TabDataEnum.findByCode(udata));
                }
            });
        }
    }

    /**
     * 设置透明度
     */
    public static void chargeOpacity(Node node, double value) {
        node.setOpacity(value);
    }

    /**
     * HTML文件URL
     */
    public static String findHtmlUrl(String name) {
        // return "http://www.baidu.com";
        return "file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/" + name + ".html");
    }

}
