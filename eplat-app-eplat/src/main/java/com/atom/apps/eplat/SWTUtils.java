/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

import mplat.SWTMain;
import mplat.mgt.dto.UserInfoDTO;
import mplat.uijfx.images.IMGS;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.atom.apps.eplat.views.EmgeMainView;
import com.atom.apps.eplat.views.SystemInfoView;
import com.atom.apps.eplat.views.ext.CourseSlideExt;
import com.atom.apps.eplat.views.ext.HomePageExt;
import com.atom.apps.eplat.views.ext.TopicEventExt;
import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.DateUtils;
import com.atom.core.lang.utils.LogUtils;

/**
 * SWT工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTUtils.java, V1.0.1 2013-4-1 下午1:53:26 $
 */
public final class SWTUtils {
    /** Flash参数 */
    public final static int                     DISPID_LOADMOVIE   = 0x0000008e;
    public final static int                     DISPID_STATECHANGE = 0xfffffd9f;
    public final static int                     DISPID_ONPROGRESS  = 0x000007a6;
    public final static int                     DISPID_FSCOMMAND   = 0x00000096;

    /** 图片缓存 */
    private static Map<String, Image>           _images            = new ConcurrentHashMap<String, Image>();
    private static Map<String, ImageDescriptor> _imgDesps          = new ConcurrentHashMap<String, ImageDescriptor>();
    private static Image[]                      _iconImgs;
    private static ImageRegistry                _registry;

    /** 线程服务 */
    private static final ExecutorService        _executors         = Executors.newFixedThreadPool(2);

    /** 音频播放标记 */
    private static final Lock                   _audioLock         = new ReentrantLock();
    private static final ExecutorService        _audioExecutor     = Executors.newFixedThreadPool(1);

    /** 登录用户 */
    private static UserInfoDTO                  _UserInfo;

    /** 应用主窗口 */
    private static SWTMainView                  _MainView;

    /** 急救训练主窗口 */
    private static EmgeMainView                 _EmgeMainView;

    /** JS回调函数名 */
    public static final String                  FUNC_FIRE_EVENT    = "fireEvent";

    /** 标签值 */
    public static final String                  TAB_DATA_KEY       = "EPLAT-TAB-DATA-KEY";

    public static final String                  TD_COMM_PORT       = "EPLAT-TAB-DATA-CommPort";
    public static final String                  TD_HOME_MAIN       = "EPLAT-TAB-DATA-HomeMain";
    public static final String                  TD_COURSE_WARE     = "EPLAT-TAB-DATA-CourseWare";
    public static final String                  TD_TOPIC_TRAIN     = "EPLAT-TAB-DATA-TopicTrain";
    public static final String                  TD_TOPIC_EXAM      = "EPLAT-TAB-DATA-TopicExam";
    public static final String                  TD_EMERGE_TRAIN    = "EPLAT-TAB-DATA-EmergeTrain";
    public static final String                  TD_EMERGE_EXAM     = "EPLAT-TAB-DATA-EmergeExam";
    public static final String                  TD_EMERGE_WEB      = "EPLAT-TAB-DATA-EmergeWeb";
    public static final String                  TD_SYSTEM_CFG      = "EPLAT-TAB-DATA-SystemCfg";

    public static final String                  TD_USER_MNGT       = "EPLAT-TAB-DATA-UserMngt";

    /** PPT */
    private static final Map<String, String>    _slides            = new ConcurrentHashMap<String, String>();
    {
        _slides.put("01", "呼吸系统急症");
        _slides.put("02", "急性中风");
        _slides.put("03", "被证实为室颤：用自动除颤器（AED）和CPR施救");
        _slides.put("04", "心动过缓");
        _slides.put("05", "室颤/无脉搏室速");
        _slides.put("06", "无脉搏心电活动");
        _slides.put("07", "急性冠脉综合征");
        _slides.put("08", "不稳定性心动过速");
        _slides.put("09", "心室停搏");
        _slides.put("10", "稳定性心动过速");
    }

    /** 试题最大个数 */
    public static final int                     MAX_EXAM_COUNT     = 10;

    /**
     * 初始化
     */
    public static void init() {
        findImgIcons();

        if (_registry == null) {
            _registry = new ImageRegistry();
        }
    }

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

        // 2.图片描述
        _imgDesps.clear();

        // 3.图片图标
        if (_iconImgs != null && _iconImgs.length > 0) {
            for (Image iconImg : _iconImgs) {
                if (!iconImg.isDisposed()) {
                    iconImg.dispose();
                }
            }
        }

        // 4.线程服务
        _audioExecutor.shutdownNow();
        _executors.shutdownNow();
    }

    /**
     * 设置登录用户
     */
    public static void setLoginUser(UserInfoDTO _user) {
        _UserInfo = _user;
    }

    /**
     * 获取登录用户
     */
    public static UserInfoDTO findLoginUser() {
        return _UserInfo;
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
     * 设置急救训练主窗口
     */
    public static void setEmgeMainView(EmgeMainView _view) {
        _EmgeMainView = _view;
    }

    /**
     * 获取急救训练主窗口
     */
    public static EmgeMainView findEmgeMainView() {
        return _EmgeMainView;
    }

    /**
     * 窗口循环，避免退出
     */
    public static void tryLoop(Shell shell) {
        Display display = shell.getDisplay();

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
        center(control.getMonitor().getClientArea(), control);
    }

    /**
     * 窗口居中
     */
    public static void center(final Control parent, final Control control) {
        center(parent.getBounds(), control);
    }

    /**
     * 窗口居中
     */
    public static void center(final Rectangle parent, final Control control) {
        int px = parent.x + parent.width / 2;
        int py = parent.y + parent.height / 2;

        int cx = px - control.getBounds().width / 2;
        int cy = py - control.getBounds().height / 2;

        cx = cx > 0 ? cx : 0;
        cy = cy > 0 ? cy : 0;
        control.setLocation(cx, cy);
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
    public static Image findImage(String name, Rectangle size) {
        return findImage(Display.getDefault(), name, size);
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
        if (_iconImgs == null) {
            _iconImgs = new Image[1];
            _iconImgs[0] = findImage("img-icon-01.png");

            Window.setDefaultImages(_iconImgs);
        }

        return _iconImgs;
    }

    /**
     * 获取图片描述
     */
    public static ImageDescriptor findImgDesp(String name) {
        return findImgDesp(Display.getDefault(), name);
    }

    /**
     * 获取图片描述
     */
    public static ImageDescriptor findImgDesp(final Device device, String name) {
        ImageDescriptor imgDesp = _imgDesps.get(name);
        if (imgDesp == null) {
            _imgDesps.remove(name);

            Image image = findImage(device, name);
            if (image != null) {
                imgDesp = ImageDescriptor.createFromImage(image);
                _imgDesps.put(name, imgDesp);
            }
        }

        return imgDesp;
    }

    /**
     * 获取ECG图片
     */
    public static Image findEcgImage(String name) {
        String key = "ECT-" + name;
        Image image = _registry.get(key);
        if (image != null && !image.isDisposed()) {
            return image;
        }

        String file = FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/ecgts/" + name);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            image = new Image(Display.getCurrent(), input);
            _registry.put(key, image);
        } catch (Exception e) {
            LogUtils.get().error("获取ECG图片[{}]异常！", name, e);
        } finally {
            IOUtils.closeQuietly(input);
        }

        return image;
    }

    /**
     * 获取图片
     */
    public static Image findEcgImage(String name, Rectangle size) {
        Image tmpImg = findEcgImage(name);
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
        return new Image(Display.getCurrent(), tmpImg.getImageData().scaledTo(size.width, size.height));
    }

    /**
     * 获取HTML视图
     */
    public static String findHtml(String name) {
        return "file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/" + name);
    }

    /**
     * 列表转换为数组
     */
    public static String[] toArray(List<String> items) {
        if (items == null) {
            return null;
        }

        String[] rtns = new String[items.size()];
        for (int i = 0; i < rtns.length; i++) {
            rtns[i] = items.get(i);
        }

        return rtns;
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
        MessageDialog.openWarning(shell, "警告提示", message);
    }

    /**
     * 警告提示
     */
    public static void alert(final Shell shell, String title, String message) {
        MessageDialog.openWarning(shell, title, message);
    }

    /**
     * 确认提示
     */
    public static boolean confirm(final Shell shell, String message) {
        return MessageDialog.openConfirm(shell, "确认提示", message);
    }

    /**
     * 确认提示
     */
    public static boolean confirm(final Shell shell, String title, String message) {
        return MessageDialog.openConfirm(shell, title, message);
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
        boolean rtn = confirm(shell, "你确认退出系统吗？");
        if (rtn) {
            exitSystem();
        }
    }

    /**
     * 获取标签
     */
    public static CTabItem findTabItem(CTabFolder tabFolder, String tabData) {
        CTabItem[] items = tabFolder.getItems();
        for (CTabItem item : items) {
            String data = String.valueOf(item.getData(TAB_DATA_KEY));
            if (StringUtils.equalsIgnoreCase(tabData, data)) {
                return item;
            }
        }

        return null;
    }

    /**
     * 删除标签
     */
    public static void removeTabItem(CTabFolder tabFolder, String tabData) {
        CTabItem[] items = tabFolder.getItems();
        for (CTabItem item : items) {
            String data = String.valueOf(item.getData(TAB_DATA_KEY));
            if (StringUtils.equalsIgnoreCase(tabData, data)) {
                item.dispose();
            }
        }
    }

    /**
     * 设置组件是否有效
     */
    public static void setEnabled(Composite composite, boolean enabled) {
        if (composite == null || composite.isDisposed()) {
            return;
        }

        Control[] ctrls = composite.getChildren();
        for (Control ctrl : ctrls) {
            if (!ctrl.isDisposed()) {
                ctrl.setEnabled(enabled);
            }
        }

        composite.setEnabled(enabled);
    }

    /**
     * 展示功能主页
     */
    public static void gotoHomePage() {
        new HomePageExt();
    }

    /**
     * 展示PPT页面
     */
    public static void gotoPptSlide(final String no) {
        new CourseSlideExt(no);
    }

    /**
     * 显示专项训练
     */
    public static void gotoTopicEvent(final String no) {
        new TopicEventExt(no).onTopicEvent();
    }

    /**
     * 打开系统信息
     */
    public static void gotoSystemInfo() {
        new SystemInfoView(SWTUtils.findMainView().findShell()).open();
    }

    /**
     * 打开帮助操作手册
     */
    public static void openHelpManual() {
        String file = FilenameUtils.normalize(CfgUtils.findConfigPath() + "/manual.pdf");
        try {
            Desktop.getDesktop().open(new File(file));
        } catch (Exception e) {
            throw new RuntimeException("打开帮助手册[" + file + "]异常", e);
        }
    }

    /**
     * 根据值找到数据行
     */
    public static TableItem findTableItem(Table table, int index, String value) {
        if (table == null || index < 0) {
            return null;
        }

        for (TableItem item : table.getItems()) {
            if (StringUtils.equalsIgnoreCase(value, item.getText(index))) {
                return item;
            }
        }

        return null;
    }

    /**
     * 获取某列数据
     */
    public static List<String> findValues(Table table, int index) {
        List<String> values = new ArrayList<String>();

        if (table == null || index < 0) {
            return values;
        }

        for (TableItem item : table.getItems()) {
            values.add(item.getText(index));
        }

        return values;
    }

    /**
     * 删除表格数据行
     */
    public static int removeValue(Table table, int index, String value) {
        int count = 0;

        if (table == null) {
            return count;
        }

        TableItem[] items = table.getItems();
        if (items == null || items.length == 0) {
            return count;
        }

        int itemCount = items.length;

        boolean searching = true;
        while (searching) {
            searching = false;

            for (int i = 0; i < itemCount; i++) {
                TableItem item = items[i];
                if (item.isDisposed()) {
                    continue;
                }

                String text = item.getText(index);
                if (StringUtils.equals(text, value)) {
                    table.remove(i);
                    searching = true;

                    count += 1;
                    break;
                }
            }
        }

        return count;
    }

    /**
     * 删除表格数据
     */
    public static int removeValues(Table table) {
        if (table == null || table.isDisposed()) {
            return 0;
        }

        int count = table.getItemCount();
        for (int i = 0; i < count; i++) {
            table.remove(0);
        }

        return count;
    }

    /**
     * 删除表格数据行
     */
    public static int removeValues(Table table, int index, List<String> values) {
        int count = 0;

        if (table == null) {
            return count;
        }

        for (String value : values) {
            count += removeValue(table, index, value);
        }

        return count;
    }

    /**
     * 根据注射泵/输液泵ID列表获取其索引
     */
    public static String findPumpIndexs(List<String> ids) {
        StringBuilder txt = new StringBuilder(64);
        for (String id : ids) {
            txt.append(Integer.parseInt(id) - 1);
        }

        return txt.toString();
    }

    /**
     * 通过线程服务执行
     */
    public static void execute(Runnable runnable) {
        _executors.execute(runnable);
    }

    /**
     * 播放音频文件
     */
    public static void playAudio(final File file) {
        execute(new Runnable() {
            public void run() {
                if (!_audioLock.tryLock()) {
                    LogUtils.get().debug("Lock fail.");
                    return;
                }

                Future<Boolean> future = _audioExecutor.submit(new Callable<Boolean>() {
                    public Boolean call() throws Exception {
                        Clip clip = null;
                        AudioInputStream input = null;
                        try {
                            AudioLineListener listener = new AudioLineListener();
                            clip = AudioSystem.getClip();
                            clip.addLineListener(listener);

                            input = AudioSystem.getAudioInputStream(file);
                            clip.open(input);
                            clip.start();
                            listener.waitUntilDone();
                        } catch (Exception e) {
                            LogUtils.get().error("播放音频文件[{}]异常！", file, e);
                        } finally {
                            clip.close();
                            IOUtils.closeQuietly(input);
                        }

                        return true;
                    }
                });

                try {
                    future.get();
                } catch (Exception e) {
                    LogUtils.get().error("", e);
                }

                _audioLock.unlock();
            }
        });
    }

    /**
     * 音频状态监听器
     */
    private static class AudioLineListener implements LineListener {
        private boolean done = false;

        /** 
         * @see javax.sound.sampled.LineListener#update(javax.sound.sampled.LineEvent)
         */
        public synchronized void update(LineEvent event) {
            Type eventType = event.getType();

            if (eventType == Type.STOP || eventType == Type.CLOSE) {
                this.done = true;
                notifyAll();
            }
        }

        public synchronized void waitUntilDone() throws InterruptedException {
            while (!this.done) {
                wait();
            }
        }
    }

    /**
     * 获取事件时间
     */
    public static String findEvtTime() {
        return findEvtTime(new Date());
    }

    /**
     * 获取事件时间
     */
    public static String findEvtTime(Date date) {
        return DateUtils.toString(date, "HH:mm:ss");
    }

    /**
     * 获取急救场景动画文件
     */
    public static String findEmgeCaseFlash(String caseName) {
        if (!StringUtils.endsWith(caseName, ".swf")) {
            caseName += ".swf";
        }

        String path = CfgUtils.findConfigPath() + "/spts/cases/" + caseName;
        return FilenameUtils.normalize(path);
    }

    /**
     * 获取急救场景HTML文件
     */
    public static String findEmgeCaseHtml(String caseName) {
        if (!StringUtils.endsWith(caseName, ".html")) {
            caseName += ".html";
        }

        String path = CfgUtils.findConfigPath() + "/spts/cases/" + caseName;
        return "file:///" + FilenameUtils.normalize(path);
    }

}
