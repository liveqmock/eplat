/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.utils.LogUtils;

/**
 * 心电图
 * 
 * @author obullxl@gmail.com
 * @version $Id: HeartBeatGraph.java, V1.0.1 2013-4-6 下午7:52:35 $
 */
public final class HeartBeatGraph {
    private static final int DEF_VALUE = 300;

    public static final int  WIDTH     = 500;
    public static final int  HEIGHT    = 400;

    public static final int  MAX_VALUE = 100;
    public static final int  MIN_VALUE = 30;

    public static boolean    drawing   = true;

    public static final int  MAX_X     = WIDTH;

    private static Text      txtValue;

    public static void main(String args[]) {
        final Display display = Display.getDefault();
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        shell.setSize(WIDTH, HEIGHT);
        shell.setText("Draw Line In Shell");
        shell.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        shell.setForeground(display.getSystemColor(SWT.COLOR_RED));

        txtValue = new Text(shell, SWT.BORDER);
        txtValue.setBounds(0, 0, 200, 25);

        final HeatBeat hbeat = new HeatBeat(display, shell);
        hbeat.drawGraph();

        shell.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                drawing = false;
            }
        });

        // 模拟心跳数据
        final Runnable timer = new Runnable() {
            public void run() {
                while (drawing) {
                    display.syncExec(new Runnable() {
                        public void run() {
                            hbeat.update(RandomUtils.nextInt(DEF_VALUE - 100));
                        }
                    });

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        LogUtils.get().error("", e);
                    }
                }
            }
        };

        new Thread(timer).start();

        // BusyIndicator.showWhile(display, timer);

        shell.open();
        SWTUtils.tryLoop(shell);
    }

    protected static class HeatBeat {
        private final int     xdeta = 1;
        private final int     sleep = 50;

        private Point         point = new Point(0, DEF_VALUE);

        private final Display display;
        private final Shell   shell;
        private final GC      gc;

        public HeatBeat(final Display display, final Shell shell) {
            this.display = display;
            this.shell = shell;
            this.gc = new GC(this.shell);
            this.gc.setLineWidth(1);

            this.clear();
        }

        public void update(int value) {
            int newX = point.x + xdeta;
            int newY = DEF_VALUE - value;

            gc.drawLine(point.x, point.y, newX, newY);

            point.x = newX;
            point.y = newY;

            if (point.x >= MAX_X) {
                clear();
                point.x = 0;
            }
        }

        public void clear() {
            gc.setForeground(this.display.getSystemColor(SWT.COLOR_WHITE));
            shell.drawBackground(gc, 0, 0, WIDTH, HEIGHT);
            gc.setForeground(this.display.getSystemColor(SWT.COLOR_RED));
        }

        private int findValue() {
            int defaultValue = 0;
            if (txtValue.isDisposed()) {
                return defaultValue;
            }

            return NumberUtils.toInt(txtValue.getText(), defaultValue);
        }

        public void drawGraph() {
            final Runnable refresh = new Runnable() {
                public void run() {
                    final Runnable timer = new Runnable() {
                        public void run() {
                            gc.drawPoint(point.x, point.y);

                            while (drawing) {
                                display.syncExec(new Runnable() {
                                    public void run() {
                                        update(findValue());
                                    }
                                });

                                try {
                                    Thread.sleep(sleep);
                                } catch (InterruptedException e) {
                                    LogUtils.get().error("", e);
                                }
                            }
                        }
                    };

                    new Thread(timer).start();
                }
            };

            BusyIndicator.showWhile(this.display, refresh);
        }
    }

}
