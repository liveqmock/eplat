package test.mplat.eplat.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.CmptEmergeBody;

public class CmptEmergeBodyDlg extends Dialog {

    protected Object result;
    protected Shell  shell;

    public static void main(String[] args) {
        new CmptEmergeBodyDlg(new Shell(), SWT.NONE).open();
    }

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public CmptEmergeBodyDlg(Shell parent, int style) {
        super(parent, style);
        setText("SWT Dialog");
        SWTUtils.center(parent);
    }

    /**
     * Open the dialog.
     * @return the result
     */
    public Object open() {
        createContents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return result;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), getStyle());
        shell.setSize(355, 306);
        shell.setText(getText());

        CmptEmergeBody composite = new CmptEmergeBody(shell); //new Composite(shell, SWT.NONE);
        composite.initComposites();
        
        composite.setBounds(0, 0, 355, 306);
    }

}
