package test.test.swt.pdf;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.atom.core.lang.utils.CfgUtils;

public class PDFViewer extends ApplicationWindow {
    private final String pdfFile;
    
    private OleControlSite site;
    private OleAutomation  auto;

    public PDFViewer(String file) {
        super(null);
        
        this.pdfFile = file;
        
        this.addMenuBar();
    }

    protected Control createContents(Composite parent) {
        Shell shell = this.getShell();
        shell.setText("PDF Viewer");
        shell.setSize(500, 450);

        OleFrame frame = new OleFrame(shell, SWT.NONE);

        try {
            File file = new File(this.pdfFile);
            if(!file.exists()) {
                System.out.println("PDF file not exist : " + this.pdfFile);
                return null;
            }
            
            site = new OleControlSite(frame, SWT.NONE, new File(this.pdfFile));
            // auto = new OleAutomation(site);

            shell.layout();
            shell.open();

            // loadFile("");
        } catch (SWTException ex) {
            System.out.println("Failed to create <<Acrobat>> : " + ex.getMessage());
            ex.printStackTrace();

            return null;
        }

        return frame;
    }

    public static void main(String[] args) {
        String file = FilenameUtils.normalize(CfgUtils.findConfigPath() + "/manual.pdf");
        PDFViewer viewer = new PDFViewer(file);

        viewer.setBlockOnOpen(true);
        viewer.open();

        Display.getCurrent().dispose();
    }

    protected MenuManager createMenuManager() {
        MenuManager bar = new MenuManager("");

        MenuManager fileMenu = new MenuManager("&File");
        MenuManager helpMenu = new MenuManager("&Help");

        bar.add(fileMenu);
        bar.add(helpMenu);

        fileMenu.add(new OpenAction(this));
        fileMenu.add(new ExitAction(this));

        helpMenu.add(new AboutAction(this));

        return bar;
    }

    public void showPdfControl() {
        site.doVerb(OLE.OLEIVERB_SHOW);
    }

    public void loadFile(String file) {
        showPdfControl();

        int[] rgdispid = auto.getIDsOfNames(new String[] { "LoadFile" });
        int dispIdMember = rgdispid[0];

        Variant[] rgvarg = new Variant[1];
        rgvarg[0] = new Variant(file);

        Variant pVarResult = auto.invoke(dispIdMember, rgvarg);
    }
}
