package test.mplat.eplat.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.EmgeMainView;

public class EmergeMainViewDlg extends Dialog {

    public static void main(String[] args) {
        new EmergeMainViewDlg(new Shell()).open();
    }

    /**
     * Create the dialog.
     */
    public EmergeMainViewDlg(Shell parentShell) {
        super(parentShell);
    }

    /** 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new BorderLayout(0, 0));

        Composite composite = new EmgeMainView(container, "AnimeCase8");
        composite.setLayoutData(BorderLayout.CENTER);

        return container;
    }

    /** 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
    }

    /** 
     * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
     */
    protected Point getInitialSize() {
        return new Point(1000, 800);
    }

    /** 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        
        SWTUtils.center(shell);

        shell.setText("TEST-急救训练");
        shell.setImages(SWTUtils.findImgIcons());
    }

}
