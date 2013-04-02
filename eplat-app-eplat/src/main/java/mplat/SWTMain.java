package mplat;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;

public class SWTMain {

    protected Shell shell;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            SWTMain window = new SWTMain();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("SWT Application");
        shell.setLayout(new BorderLayout(0, 0));
        
        Menu menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);
        
        MenuItem menuFile = new MenuItem(menuBar, SWT.CASCADE);
        menuFile.setText("文件");
        
        Menu menu_1 = new Menu(menuFile);
        menuFile.setMenu(menu_1);
        
        MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
        menuItem_2.setText("退出");
        
        MenuItem menuItem_1 = new MenuItem(menuBar, SWT.CASCADE);
        menuItem_1.setText("系统管理");
        
        Menu menu_2 = new Menu(menuItem_1);
        menuItem_1.setMenu(menu_2);
        
        MenuItem menuItem_3 = new MenuItem(menuBar, SWT.CASCADE);
        menuItem_3.setText("关于");
        
        Menu menu_3 = new Menu(menuItem_3);
        menuItem_3.setMenu(menu_3);
        
        MenuItem menuItem_4 = new MenuItem(menu_3, SWT.NONE);
        menuItem_4.setText("关于系统");
    }
}
