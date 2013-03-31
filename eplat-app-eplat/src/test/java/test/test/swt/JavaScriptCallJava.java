/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Browser example snippet: call Java from JavaScript.
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @author obullxl@gmail.com
 * @version $Id: JavaScriptCallJava.java, V1.0.1 2013-3-31 下午8:41:50 $
 */
public class JavaScriptCallJava {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        shell.setBounds(10, 10, 300, 200);

        final Browser browser;
        try {
            browser = new Browser(shell, SWT.NONE);
        } catch (SWTError e) {
            System.out.println("Could not instantiate Browser: " + e.getMessage());
            display.dispose();
            return;
        }
        // browser.setText(createHTML());
        String html = new File(JavaScriptCallJava.class.getResource("/JavaScriptCallJava.html").getFile()).getAbsolutePath();
        browser.setUrl(html);

        final BrowserFunction function = new CustomFunction(browser, "theJavaFunction");

        browser.addProgressListener(new ProgressAdapter() {
            public void completed(ProgressEvent event) {
                browser.addLocationListener(new LocationAdapter() {
                    public void changed(LocationEvent event) {
                        browser.removeLocationListener(this);
                        System.out.println("left java function-aware page, so disposed CustomFunction");
                        function.dispose();
                    }
                });
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    static class CustomFunction extends BrowserFunction {
        CustomFunction(Browser browser, String name) {
            super(browser, name);
        }

        public Object function(Object[] arguments) {
            System.out.println("theJavaFunction() called from javascript with args:");
            for (int i = 0; i < arguments.length; i++) {
                Object arg = arguments[i];
                if (arg == null) {
                    System.out.println("\t-->null");
                } else {
                    System.out.println("\t-->" + arg.getClass().getName() + ": " + arg.toString());
                }
            }
            Object returnValue = new Object[] { new Short((short) 3), new Boolean(true), null, new Object[] { "a string", new Boolean(false) }, "hi", new Float(2.0f / 3.0f), };
            //int z = 3 / 0; // uncomment to cause a java error instead
            return returnValue;
        }
    }

}
