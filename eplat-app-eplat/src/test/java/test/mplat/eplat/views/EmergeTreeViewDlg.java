package test.mplat.eplat.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.EmergeTreeView;

public class EmergeTreeViewDlg extends Dialog {

    protected Object result;
    protected Shell  shell;

    public static void main(String[] args) {
        new EmergeTreeViewDlg(new Shell(), SWT.NONE).open();
    }

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public EmergeTreeViewDlg(Shell parent, int style) {
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
        shell.setSize(424, 252);
        shell.setText(getText());

        Composite composite = new EmergeTreeView(shell, 424, 252); //new Composite(shell, SWT.NONE);
        composite.setBounds(0, 0, 424, 252);
    }
}
