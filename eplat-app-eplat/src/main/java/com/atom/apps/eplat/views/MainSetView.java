/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

import com.atom.apps.eplat.SWTMainView;
import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.ext.HomePageExt;

/**
 * 主窗口
 * 
 * @author obullxl@gmail.com
 * @version $Id: MainSetView.java, V1.0.1 2013-4-4 下午3:38:05 $
 */
public final class MainSetView extends ApplicationWindow implements SWTMainView {

    private CTabFolder tabFolder;

    private Action     actExit;
    private Action     actConfigSet;
    private Action     actUserModify;
    private Action     actUserMgt;
    private Action     actEcgMgt;
    private Action     actExamMgt;
    private Action     actHelp;
    private Action     actAbout;

    public static void main(String[] args) {
        try {
            MainSetView window = new MainSetView();
            window.setBlockOnOpen(true);
            window.open();
            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the application window.
     */
    public MainSetView() {
        super(null);
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();

        // 设置系统主窗口
        SWTUtils.setMainView(this);
    }

    /** 
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new BorderLayout(0, 0));

        tabFolder = new CTabFolder(container, SWT.BORDER);
        tabFolder.setLayoutData(BorderLayout.CENTER);
        tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

        // 设置主功能页面
        new HomePageExt();

        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        {
            actExit = new Action("退出") {
                public void run() {
                    SWTUtils.exitSystem(getShell());
                }
            };
        }
        {
            actConfigSet = new Action("系统参数设置") {

            };
        }
        {
            actUserModify = new Action("修改用户信息") {

            };
        }
        {
            actUserMgt = new Action("用户信息管理") {

            };
        }
        {
            actEcgMgt = new Action("ECG心律识别试题管理") {

            };
        }
        {
            actExamMgt = new Action("ACLS理论知识试题管理") {

            };
        }
        {
            actHelp = new Action("帮助手册") {

            };
        }
        {
            actAbout = new Action("关于系统") {

            };
        }
    }

    /** 
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("menu");

        MenuManager menuFile = new MenuManager("&文件");
        MenuManager menuSysFunc = new MenuManager("&系统功能");
        menuManager.add(menuFile);
        menuFile.add(actExit);
        menuManager.add(menuSysFunc);

        MenuManager menuCauseWare = new MenuManager("&系统课件");
        menuSysFunc.add(menuCauseWare);

        MenuManager menuTopicTrain = new MenuManager("&专项技能训练");
        menuSysFunc.add(menuTopicTrain);
        menuSysFunc.add(new Separator());

        MenuManager menuEmergeTrain = new MenuManager("&专业急救案例训练");
        menuSysFunc.add(menuEmergeTrain);

        MenuManager menuEmergeExam = new MenuManager("&专业急救案例考核");
        menuSysFunc.add(menuEmergeExam);

        MenuManager menuSysConfig = new MenuManager("&系统设置");
        menuManager.add(menuSysConfig);
        menuSysConfig.add(actConfigSet);
        menuSysConfig.add(new Separator());
        menuSysConfig.add(actUserModify);

        Image menuSysImg = SWTUtils.findImage("icon-user.png");
        ImageDescriptor menuSysImgDesp = ImageDescriptor.createFromImage(menuSysImg);
        MenuManager menuSysMgt = new MenuManager("&系统管理", menuSysImgDesp, null);
        menuManager.add(menuSysMgt);
        menuSysMgt.add(actUserMgt);
        menuSysMgt.add(new Separator());
        menuSysMgt.add(actEcgMgt);
        menuSysMgt.add(new Separator());
        menuSysMgt.add(actExamMgt);

        Image menuHelpImg = SWTUtils.findImage("icon-info.png");
        ImageDescriptor menuHelpImgDesp = ImageDescriptor.createFromImage(menuHelpImg);
        MenuManager menuHelp = new MenuManager("&帮助", menuHelpImgDesp, null);
        menuManager.add(menuHelp);
        menuHelp.add(actHelp);
        menuHelp.add(new Separator());
        menuHelp.add(actAbout);

        return menuManager;
    }

    /** 
     * @see org.eclipse.jface.window.ApplicationWindow#createStatusLineManager()
     */
    protected StatusLineManager createStatusLineManager() {
        StatusLineManager statusLineManager = new StatusLineManager();
        return statusLineManager;
    }

    /** 
     * @see org.eclipse.jface.window.ApplicationWindow#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("GD/ACLS 8000 高级生命支持急救技能训练软件2013版 - [欢迎使用]");
        newShell.setSize(1000, 750);
        newShell.setImages(SWTUtils.findImgIcons());

        newShell.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
                setStatus("X:" + e.x + ", Y:" + e.y);
            }
        });

        newShell.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent evt) {
                SWTUtils.exitSystem();
            }
        });

        SWTUtils.center(newShell);
    }

    /** 
     * @see org.eclipse.jface.window.Window#getShellStyle()
     */
    protected int getShellStyle() {
        return SWT.CLOSE | SWT.MIN | SWT.SYSTEM_MODAL;
    }

    /** 
     * @see org.eclipse.jface.window.Window#getInitialSize()
     */
    protected Point getInitialSize() {
        return new Point(450, 970);
    }

    /** 
     * @see com.atom.apps.eplat.SWTMainView#findDisplay()
     */
    public Display findDisplay() {
        return this.getShell().getDisplay();
    }

    /** 
     * @see com.atom.apps.eplat.SWTMainView#findShell()
     */
    public Shell findShell() {
        return this.getShell();
    }

    /** 
     * @see com.atom.apps.eplat.SWTMainView#findCTabFolder()
     */
    public CTabFolder findCTabFolder() {
        return this.tabFolder;
    }

    /** 
     * @see com.atom.apps.eplat.SWTMainView#setStatusMessage(java.lang.String)
     */
    public void setStatusMessage(String msg) {
        super.setStatus(msg);
    }

}
