/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * SWT工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTUtils.java, V1.0.1 2013-4-1 下午1:53:26 $
 */
public class SWTUtils {

    public static final void tryLoop(Shell shell) {
        Display display = Display.getDefault();
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        
        display.dispose();
    }
    
    public static final void center(final Shell shell) {
        int width = shell.getMonitor().getClientArea().width;
        int height = shell.getMonitor().getClientArea().height;
        int x = shell.getSize().x;
        int y = shell.getSize().y;
        
        if (x > width) {
            shell.getSize().x = width;
        }
        if (y > height) {
            shell.getSize().y = height;
        }
        
        shell.setLocation((width - x) / 2, (height - y) / 2);
    }
    
    
}
