/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTThread.java, V1.0.1 2013-4-6 下午8:54:24 $
 */
public class SWTThread {
    private int   size    = 10;  // 10 threads
    private long  runTime = 5000; // 5 seconds
    private List  list;
    private Shell shell;

    public void startThread() {
        for (int index = 0; index < size; index++) {
            final int listIndex = index;
            final Runnable refresh = new Runnable() {
                public void run() {
                    final Display disp = shell.getDisplay();

                    final Runnable runOne = new Runnable() {
                        public void run() {
                            final long start = System.currentTimeMillis();
                            while ((System.currentTimeMillis() - start) < runTime) {
                                disp.syncExec(new Runnable() {
                                    public void run() {
                                        if (list.isDisposed()) {
                                            return;
                                        }

                                        String string = "No." + listIndex;
                                        string += " " + (System.currentTimeMillis() - start);
                                        list.setItem(listIndex, string);
                                    }
                                });
                                
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    };

                    Thread thread = new Thread(runOne);
                    thread.start();
                }
            };

            BusyIndicator.showWhile(shell.getDisplay(), refresh);//这一句很关键
        }
    }

    public Shell open(final Display display) {
        shell = new Shell(display, SWT.DIALOG_TRIM | SWT.MIN);
        shell.setText("SWT Thread Test");
        shell.setLayout(new GridLayout(1, true));
        list = new List(shell, SWT.BORDER);
        list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        for (int index = 0; index < size; index++)
            list.add("String " + (index + 1));
        Button startBtn = new Button(shell, SWT.PUSH);
        startBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        startBtn.setText("Start");
        SelectionAdapter selection = new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                startThread();
            }
        };
        startBtn.addSelectionListener(selection);
        shell.setSize(400, 300);
        shell.open();
        return shell;
    }

    public static void main(String[] args) {
        Display display = new Display();
        SWTThread application = new SWTThread();
        Shell shell = application.open(display);

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
