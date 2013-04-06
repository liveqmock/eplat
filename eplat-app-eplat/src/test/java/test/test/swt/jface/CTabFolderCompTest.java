package test.test.swt.jface;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;

public class CTabFolderCompTest extends ApplicationWindow {

    /**
     * Create the application window.
     */
    public CTabFolderCompTest() {
        super(null);
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
    }

    /**
     * Create contents of the application window.
     * @param parent
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new BorderLayout(0, 0));
        
        CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER);
        tabFolder.setLayoutData(BorderLayout.CENTER);
        tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
        
        CTabItem tbtmComositeTest = new CTabItem(tabFolder, SWT.NONE);
        tbtmComositeTest.setText("Comosite Test");
        
        CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
        tabItem.setText("New Item");
        
        Composite composite = new Composite(tabFolder, SWT.NONE);
        tabItem.setControl(composite);
        
        Button btnNewButton = new Button(composite, SWT.NONE);
        btnNewButton.setBounds(10, 80, 80, 27);
        btnNewButton.setText("New Button");

        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
    }

    /**
     * Create the menu manager.
     * @return the menu manager
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("menu");
        return menuManager;
    }

    /**
     * Create the status line manager.
     * @return the status line manager
     */
    @Override
    protected StatusLineManager createStatusLineManager() {
        StatusLineManager statusLineManager = new StatusLineManager();
        return statusLineManager;
    }

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String args[]) {
        try {
            CTabFolderCompTest window = new CTabFolderCompTest();
            window.setBlockOnOpen(true);
            window.open();
            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure the shell.
     * @param newShell
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("New Application");
    }

    /**
     * Return the initial size of the window.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}
