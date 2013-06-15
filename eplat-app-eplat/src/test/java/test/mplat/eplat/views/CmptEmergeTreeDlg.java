package test.mplat.eplat.views;

import mplat.mgt.MgtFactory;
import mplat.mgt.TreeMgt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.CmptEmergeTree;

public class CmptEmergeTreeDlg extends Dialog {

    protected Object result;
    protected Shell  shell;

    public static void main(String[] args) {
        new CmptEmergeTreeDlg(new Shell(), SWT.NONE).open();
    }

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public CmptEmergeTreeDlg(Shell parent, int style) {
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
        shell.setSize(420, 250);
        shell.setText("测试");

        CmptEmergeTree composite = new CmptEmergeTree(shell); //new Composite(shell, SWT.NONE);
        
        TreeMgt mgt = MgtFactory.get().findTreeMgt();
        composite.updateTreeNodes(mgt.findEmergeAbcNodes(), mgt.findEmergeMiscNodes(), mgt.findEmergeMedicNodes());
        
        composite.setBounds(0, 0, 420, 250);
    }

}
