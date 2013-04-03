package com.atom.apps.eplat.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import swing2swt.layout.BorderLayout;

public class UserMgtView extends ApplicationWindow {
    private Action actExit;

    /**
     * Create the application window.
     */
    public UserMgtView() {
        super(null);
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
    }

    /**
     * Create contents of the application window.
     * @param parent
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new BorderLayout(0, 0));

        TabFolder tabFolder = new TabFolder(container, SWT.NONE);
        tabFolder.setLayoutData(BorderLayout.CENTER);
        
        TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
        tabItem.setText("用户管理");

        parent.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
                setStatus("X:" + e.x + ", Y:" + e.y);
            }
        });
        
        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        actExit = new Action("退出") {
            public void run() {
                System.out.println("退出系统……");
            }
        };
    }

    /**
     * Create the menu manager.
     * @return the menu manager
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("");

        MenuManager fileMenu = new MenuManager("&文件");
        MenuManager helpMenu = new MenuManager("&Help");
        menuManager.add(fileMenu);
        fileMenu.add(actExit);
        menuManager.add(helpMenu);

        return menuManager;
    }

    /**
     * Create the status line manager.
     * @return the status line manager
     */
    @Override
    protected StatusLineManager createStatusLineManager() {
        StatusLineManager statusLineManager = new StatusLineManager();
        return statusLineManager;
    }

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String args[]) {
        try {
            UserMgtView window = new UserMgtView();
            window.setBlockOnOpen(true);
            window.open();
            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure the shell.
     * @param newShell
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("New Application");
    }

    /**
     * Return the initial size of the window.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}
