/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt.jface;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.atom.apps.eplat.SWTUtils;

/**
 * JFace
 * 
 * @author obullxl@gmail.com
 * @version $Id: JFaceExample.java, V1.0.1 2013-4-2 下午4:59:02 $
 */
public class JFaceExample extends ApplicationWindow {

    /**
     * 应用入口
     */
    public static void main(String[] args) {
        JFaceExample fe = new JFaceExample();
        fe.setBlockOnOpen(true);
        fe.open();

        Display.getCurrent().dispose();
    }

    private ExitAction m_ExitAction;

    public JFaceExample() {
        super(null); //父类接受一个参数为Shell类的对象作为父窗口句柄，  
        //如果这个窗口为顶级窗口的话,则传null  

        m_ExitAction = new ExitAction(this);

        // 这三个为父类方法，作用已经通过方法名表示得很清楚
        // 当它们时被程序调用时，它们依次调用createMenuManager()，createStatusLineManager()，以及createToolBarManager()。
        // 如果你希望能有一个菜单栏，状态条，或者一个工具栏，那就需要重载这些方法，确保它们返回正确的类型。
        this.addMenuBar();
        this.addStatusLine();
        this.addToolBar(SWT.FLAT | SWT.WRAP);
    }

    protected Control createContents(Composite parent) {
        getShell().setText("JFace Example");
        setStatus("JFace Example v1.0");

        this.getShell().addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
                setStatus("X:" + e.x + ", Y:" + e.y);
            }
        });

        return parent;
    }

    protected void initializeBounds() {
        getShell().setSize(500, 400);

        SWTUtils.center(this.getShell());
    }

    protected MenuManager createMenuManager() {
        MenuManager menuBar = new MenuManager("");
        MenuManager fileMenu = new MenuManager("&File");
        MenuManager helpMenu = new MenuManager("&Help");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // 由于helpMenu没有子项，所以它不会显示出来  
        fileMenu.add(m_ExitAction);

        return menuBar;
    }

    // StatusLineManager类提供了设置状态条文字的方法，可以用来控制显示在状态栏的一个进度条，也可以用来显示错误文字和图片
    protected StatusLineManager createStatusLineManager() {
        StatusLineManager statusLineManager = new StatusLineManager();
        statusLineManager.setMessage("Hello, world!");
        return statusLineManager;
    }

    private static class ExitAction extends Action {
        private final ApplicationWindow m_Window;

        public ExitAction(ApplicationWindow w) {
            m_Window = w; //得到窗口对象  
            setText("Exit@Ctrl+W");
            setToolTipText("Exit the application");
        }

        // 这个方法用于定义被激活时的行为  
        public void run() {
            m_Window.close(); //当被激活时关闭窗口  
        }
    }

}
