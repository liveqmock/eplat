package test.test.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class PopupMenuTest extends Dialog {

    protected Object result;
    protected Shell  shell;

    public static void main(String[] args) {
        new PopupMenuTest(new Shell(), SWT.NONE).open();
    }

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public PopupMenuTest(Shell parent, int style) {
        super(parent, style);
        setText("SWT Dialog");
    }

    /**
     * Open the dialog.
     * @return the result
     */
    public Object open() {
        initComponents();
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
    private void initComponents() {
        shell = new Shell(getParent(), getStyle());
        shell.setSize(450, 300);
        shell.setText(getText());

        final Menu menu = new Menu(shell);
        shell.setMenu(menu);

        MenuItem mntmAbc = new MenuItem(menu, SWT.RADIO);
        mntmAbc.setSelection(true);
        mntmAbc.setText("abc");

        MenuItem mntmEfg = new MenuItem(menu, SWT.RADIO);
        mntmEfg.setText("efg");

        MenuItem mntmXyz = new MenuItem(menu, SWT.RADIO);
        mntmXyz.setText("xyz");
        
        MenuItem mntmNewRadiobutton = new MenuItem(menu, SWT.RADIO);
        mntmNewRadiobutton.setText("New RadioButton");

        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                Button btn = (Button) evt.getSource();
                if (btn != null) {
                    Point p = btn.getLocation();
                    int height = btn.getSize().y;

                    menu.setLocation(p.x, p.x + height);
                }
                
                menu.setVisible(true);
            }
        });
        btnNewButton.setBounds(27, 22, 80, 27);
        btnNewButton.setText("New Button");
    }
}
