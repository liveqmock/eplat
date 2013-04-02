/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

/**
 * 主页
 * 
 * @author obullxl@gmail.com
 * @version $Id: HomeMainView.java, V1.0.1 2013-4-2 上午8:52:47 $
 */
public final class HomeMainView {
    protected Shell shell;

    /**
     * Open the window.
     */
    public void open() {
        createContents();
        shell.open();
        shell.layout();

        Display display = Display.getDefault();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("SWT Application");
        shell.setLayout(new BorderLayout(0, 0));

        Browser browser = new Browser(shell, SWT.NONE);
        browser.setLayoutData(BorderLayout.CENTER);

        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.setLayoutData(BorderLayout.SOUTH);
        btnNewButton.setText("New Button");

        Menu menu = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menu);

        MenuItem menuItem = new MenuItem(menu, SWT.NONE);
        menuItem.setText("文件");

        MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
        mntmNewItem.setText("New Item");
    }
}
