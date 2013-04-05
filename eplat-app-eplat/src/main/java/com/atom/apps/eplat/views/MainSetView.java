/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views;

import mplat.mgt.MgtFactory;
import mplat.mgt.UserMgt;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
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
    private Action     actHomePage;

    private Action     act0101;
    private Action     act0102;
    private Action     act0103;
    private Action     act0104;
    private Action     act0105;
    private Action     act0106;
    private Action     act0107;
    private Action     act0108;
    private Action     act0109;
    private Action     act0110;

    private Action     act0201;
    private Action     act0202;
    private Action     act0203;
    private Action     act0204;
    private Action     act0205;
    private Action     act0206;
    private Action     act0207;
    private Action     act0208;

    private Action     actConfigSet;
    private Action     actUserUpdate;
    private Action     actUserMgt;
    private Action     actEcgMgt;
    private Action     actExamMgt;
    private Action     actHelp;
    private Action     actAbout;

    public static void main(String[] args) {
        try {
            SWTUtils.setLoginUser(MgtFactory.get().findUserMgt().find("admin"));

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
            actHomePage = new Action("欢迎使用", SWTUtils.findImgDesp("tab-house.png")) {
                public void run() {
                    SWTUtils.gotoHomePage();
                }
            };
        }
        {
            actExit = new Action("退出系统", SWTUtils.findImgDesp("icon-exit.png")) {
                public void run() {
                    SWTUtils.exitSystem(getShell());
                }
            };
        }

        {
            act0101 = new Action("1.呼吸系统急症") {
                public void run() {
                    SWTUtils.gotoPptSlide("01");
                }
            };
        }
        {
            act0102 = new Action("2.急性中风") {
                public void run() {
                    SWTUtils.gotoPptSlide("02");
                }
            };
        }
        {
            act0103 = new Action("3.被证实为室颤：用自动除颤器（AED）和CPR施救") {
                public void run() {
                    SWTUtils.gotoPptSlide("03");
                }
            };
        }
        {
            act0104 = new Action("4.心动过缓") {
                public void run() {
                    SWTUtils.gotoPptSlide("04");
                }
            };
        }
        {
            act0105 = new Action("5.室颤或无脉搏室速") {
                public void run() {
                    SWTUtils.gotoPptSlide("05");
                }
            };
        }
        {
            act0106 = new Action("6.无脉搏心电活动") {
                public void run() {
                    SWTUtils.gotoPptSlide("06");
                }
            };
        }
        {
            act0107 = new Action("7.急性冠状动脉综合征") {
                public void run() {
                    SWTUtils.gotoPptSlide("07");
                }
            };
        }
        {
            act0108 = new Action("8.不稳定性心动过速") {
                public void run() {
                    SWTUtils.gotoPptSlide("08");
                }
            };
        }
        {
            act0109 = new Action("9.心室停搏") {
                public void run() {
                    SWTUtils.gotoPptSlide("09");
                }
            };
        }
        {
            act0110 = new Action("10.稳定性心动过速") {
                public void run() {
                    SWTUtils.gotoPptSlide("10");
                }
            };
        }

        {
            act0201 = new Action("1.ACLS基础知识训练") {
                public void run() {
                    SWTUtils.gotoTopicEvent("01");
                }
            };
        }
        {
            act0202 = new Action("2.心律识别训练") {
                public void run() {
                    SWTUtils.gotoTopicEvent("02");
                }
            };
        }
        {
            act0203 = new Action("3.心肺复苏急救训练") {
                public void run() {
                    SWTUtils.gotoTopicEvent("03");
                }
            };
        }
        {
            act0204 = new Action("4.除颤仪使用训练") {
                public void run() {
                    SWTUtils.gotoTopicEvent("04");
                }
            };
        }
        {
            act0205 = new Action("5.插管训练") {
                public void run() {
                    SWTUtils.gotoTopicEvent("05");
                }
            };
        }
        {
            act0206 = new Action("6.注射泵使用训练") {
                public void run() {
                    SWTUtils.gotoTopicEvent("06");
                }
            };
        }
        {
            act0207 = new Action("7.AED使用训练") {
                public void run() {
                    SWTUtils.gotoTopicEvent("07");
                }
            };
        }
        {
            act0208 = new Action("8.输液泵使用训练") {
                public void run() {
                    SWTUtils.gotoTopicEvent("08");
                }
            };
        }

        {
            actConfigSet = new Action("系统参数设置") {

            };
        }
        {
            actUserUpdate = new Action("修改用户信息") {
                public void run() {
                    new UserUpdateView(getShell(), SWTUtils.findLoginUser()).open();
                }
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
            actHelp = new Action("帮助手册", SWTUtils.findImgDesp("icon-info.png")) {
                public void run() {
                    SWTUtils.openHelpManual();
                }
            };
        }
        {
            actAbout = new Action("关于系统") {
                public void run() {
                    SWTUtils.gotoSystemInfo();
                }
            };
        }
    }

    /** 
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("menu");

        MenuManager menuFile = new MenuManager("&文件", SWTUtils.findImgDesp("icon-disk.png"), null);
        menuManager.add(menuFile);
        menuFile.add(actHomePage);
        menuFile.add(new Separator());
        menuFile.add(actExit);

        MenuManager menuSysFunc = new MenuManager("&系统功能", SWTUtils.findImgDesp("icon-function.gif"), null);
        menuManager.add(menuSysFunc);
        MenuManager menuCauseWare = new MenuManager("&系统课件");
        menuSysFunc.add(menuCauseWare);
        menuCauseWare.add(act0101);
        menuCauseWare.add(act0102);
        menuCauseWare.add(act0103);
        menuCauseWare.add(act0104);
        menuCauseWare.add(act0105);
        menuCauseWare.add(act0106);
        menuCauseWare.add(act0107);
        menuCauseWare.add(act0108);
        menuCauseWare.add(act0109);
        menuCauseWare.add(act0110);
        menuSysFunc.add(new Separator());
        MenuManager menuTopicTrain = new MenuManager("&专项技能训练");
        menuSysFunc.add(menuTopicTrain);
        menuTopicTrain.add(act0201);
        menuTopicTrain.add(act0202);
        menuTopicTrain.add(act0203);
        menuTopicTrain.add(act0204);
        menuTopicTrain.add(act0205);
        menuTopicTrain.add(act0206);
        menuTopicTrain.add(act0207);
        menuTopicTrain.add(act0208);
        menuSysFunc.add(new Separator());
        MenuManager menuEmergeTrain = new MenuManager("&专业急救案例训练");
        menuSysFunc.add(menuEmergeTrain);
        menuSysFunc.add(new Separator());
        MenuManager menuEmergeExam = new MenuManager("&专业急救案例考核");
        menuSysFunc.add(menuEmergeExam);

        MenuManager menuSysConfig = new MenuManager("&系统设置", SWTUtils.findImgDesp("icon-config.png"), null);
        menuManager.add(menuSysConfig);
        menuSysConfig.add(actConfigSet);
        menuSysConfig.add(new Separator());
        menuSysConfig.add(actUserUpdate);

        if (UserMgt.isSystemAdmin(SWTUtils.findLoginUser())) {
            MenuManager menuSysMgt = new MenuManager("&系统管理", SWTUtils.findImgDesp("icon-user.png"), null);
            menuManager.add(menuSysMgt);
            menuSysMgt.add(actUserMgt);
            menuSysMgt.add(new Separator());
            menuSysMgt.add(actEcgMgt);
            menuSysMgt.add(new Separator());
            menuSysMgt.add(actExamMgt);
        }

        MenuManager menuHelp = new MenuManager("&帮助", SWTUtils.findImgDesp("icon-info.png"), null);
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
