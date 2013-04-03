package com.atom.apps.eplat.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.atom.apps.SWTUtils;

import swing2swt.layout.BorderLayout;

public class HomeMainView extends ApplicationWindow {
    private static final String HTML_00_HOME_MAIN = "default.html";

    private Action              actExit;
    private Action              actConfigSet;
    private Action              actUserModify;
    private Action              actUserMgt;
    private Action              actEcgMgt;
    private Action              actExamMgt;
    private Action              actHelp;
    private Action              actAbout;

    public static void main(String[] args) {
        try {
            HomeMainView window = new HomeMainView();
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
    public HomeMainView() {
        super(null);
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
    }

    /** 
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new BorderLayout(0, 0));

        TabFolder tabFolder = new TabFolder(container, SWT.NONE);
        tabFolder.setLayoutData(BorderLayout.CENTER);

        TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
        tabItem.setText("欢迎使用");

        Browser homeMainBrowser = new Browser(tabFolder, SWT.NONE);
        tabItem.setControl(homeMainBrowser);
        homeMainBrowser.setUrl(SWTUtils.findHtml(HTML_00_HOME_MAIN));

        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        {
            actExit = new Action("退出") {
                public void run() {
                    System.out.println("退出系统……");
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

        MenuManager menuSysMgt = new MenuManager("&系统管理");
        menuManager.add(menuSysMgt);
        menuSysMgt.add(actUserMgt);
        menuSysMgt.add(new Separator());
        menuSysMgt.add(actEcgMgt);
        menuSysMgt.add(new Separator());
        menuSysMgt.add(actExamMgt);

        MenuManager menuHelp = new MenuManager("&帮助");
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
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("GD/ACLS 8000 高级生命支持急救技能训练软件2013版 - [欢迎使用]");
        
        newShell.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
                setStatus("X:" + e.x + ", Y:" + e.y);
            }
        });
        
        newShell.setSize(1000, 750);
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
}
