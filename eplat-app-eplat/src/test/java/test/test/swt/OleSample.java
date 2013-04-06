package test.test.swt;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.atom.core.lang.utils.CfgUtils;

public class OleSample {

    private int   width  = 1200;
    private int   height = 850;

    private Shell shell;

    public OleSample() {
        super();
    }

    public static void main(String[] args) {
        new OleSample().open();
    }

    private void open() {
        Display display = Display.getDefault();
        shell = new Shell(display, SWT.BORDER);
        shell.setSize(width, height);
        shell.setText("ActiveX范例");

        centerShell();

        Button button = new Button(shell, SWT.NONE);
        button.setBounds(150, 410, 100, 30);
        button.setText("Close");

        Composite frame = new OleFrame(shell, SWT.BORDER);
        frame.setSize(width, height);

        OleClientSite clientSite = new OleClientSite(frame, SWT.NONE, "ShockwaveFlash.ShockwaveFlash");
        clientSite.doVerb(OLE.OLEIVERB_SHOW);

        OleAutomation oa = new OleAutomation(clientSite);
        int[] methodIDs = oa.getIDsOfNames(new String[] { "LoadMovie" });
        // String file = new File(OleSample.class.getResource("/a.swf").getFile()).getAbsolutePath();
        String file = FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/swf/~SimulateLink2.swf");
        Variant[] str = { new Variant(0), new Variant(file + "?name=老牛啊&age=29") };
        oa.invoke(methodIDs[0], str);

        button.addListener(SWT.MouseDown, new Listener() {
            public void handleEvent(Event e) {
                shell.dispose();
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    private void centerShell() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Point p = shell.getSize();
        shell.setLocation((dim.width - p.x) / 2, (dim.height - p.y) / 2);
    }

}
