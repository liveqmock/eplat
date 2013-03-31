/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * WebKit in a Browser
 *
 * The requirements for using WebKit-based Browsers are described
 * at http://www.eclipse.org/swt/faq.php#howusewebkit
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.7
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebKitTest.java, V1.0.1 2013-3-31 下午8:29:34 $
 */
public class WebKitTest {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        shell.setText("WebKit");
        final Browser browser;
        try {
            browser = new Browser(shell, SWT.WEBKIT);
        } catch (SWTError e) {
            System.out.println("Could not instantiate Browser: " + e.getMessage());
            display.dispose();
            return;
        }
        shell.open();
        browser.setUrl("http://webkit.org");
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

}
