/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: ShellToolWindow.java, V1.0.1 2013-4-5 下午8:48:06 $
 */
public class ShellToolWindow {
    
    public static void main(String[] args) {
        final Display display = new Display();
        final Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.TOOL);
        shell.setLayout(new FillLayout());

        shell.open();
        // Set up the event loop.
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                // If no more entries in event queue
                display.sleep();
            }
        }
        display.dispose();
    }
}
