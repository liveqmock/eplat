package test.test.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class HeartBeatComposit extends Composite {
    public static final int  WIDTH     = 500;
    public static final int  HEIGHT    = 400;

    public static final int  MAX_X     = WIDTH;

    private static final int DEF_VALUE = 300;

    private boolean          drawing   = true;
    private final Point      point     = new Point(0, DEF_VALUE);

    private final int        xdeta     = 1;
    private final int        sleep     = 50;

    private final Display    display;
    private final Shell      shell;
    private final GC         gc;

    /**
     * Create the composite.
     */
    public HeartBeatComposit(Composite parent, int style) {
        super(parent, style);

        this.display = this.getDisplay();
        this.shell = this.getShell();

        this.gc = new GC(this);
        this.gc.setLineWidth(1);
    }

    public final void clear() {
        this.gc.setForeground(this.display.getSystemColor(SWT.COLOR_WHITE));
        this.shell.drawBackground(gc, 0, 0, WIDTH, HEIGHT);
        this.gc.setForeground(this.display.getSystemColor(SWT.COLOR_RED));
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

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

}
