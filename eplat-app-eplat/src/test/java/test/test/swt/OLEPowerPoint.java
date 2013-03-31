/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 *  Open an OLE PowerPoint slide.
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @author obullxl@gmail.com
 * @version $Id: OLEPowerPoint.java, V1.0.1 2013-3-31 下午8:53:05 $
 */
public class OLEPowerPoint {
    static OleClientSite clientSite;

    public static void main(String[] args) {
        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setText("PowerPoint Example");
        shell.setLayout(new FillLayout());
        try {
            OleFrame frame = new OleFrame(shell, SWT.NONE);
            clientSite = new OleClientSite(frame, SWT.NONE, "PowerPoint.Slide");
            addFileMenu(frame);
        } catch (SWTError e) {
            System.out.println("Unable to open activeX control");
            display.dispose();
            return;
        }
        shell.setSize(800, 600);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    static void addFileMenu(OleFrame frame) {
        final Shell shell = frame.getShell();
        Menu menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);
        MenuItem fileMenu = new MenuItem(menuBar, SWT.CASCADE);
        fileMenu.setText("&File");
        Menu menuFile = new Menu(fileMenu);
        fileMenu.setMenu(menuFile);
        MenuItem menuFileControl = new MenuItem(menuFile, SWT.CASCADE);
        menuFileControl.setText("Exit");
        menuFileControl.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
        frame.setFileMenus(new MenuItem[] { fileMenu });
    }

}
