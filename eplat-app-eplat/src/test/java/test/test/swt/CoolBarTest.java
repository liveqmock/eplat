package test.test.swt;

import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.custom.CLabel;

public class CoolBarTest extends Composite {

    /**
     * Create the composite.
     * @param parent
     * @param style
     */
    public CoolBarTest(Composite parent, int style) {
        super(parent, style);
        setLayout(new BorderLayout(0, 0));
        
        CoolBar coolBar = new CoolBar(this, SWT.FLAT);
        coolBar.setLocked(true);
        coolBar.setLayoutData(BorderLayout.NORTH);
        
        CoolItem coolItem_1 = new CoolItem(coolBar, SWT.NONE);
        
        CoolItem coolItem = new CoolItem(coolBar, SWT.NONE);

    }

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

}
