/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Span and center columns with a GridLayout.
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTTest.java, V1.0.1 2013-3-31 下午9:15:46 $
 */
public class SWTTest {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(2, true));

        TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
        tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

        TabItem item = new TabItem(tabFolder, SWT.NONE);
        item.setText("Widget");
        Composite composite = new Composite(tabFolder, SWT.NONE);
        composite.setLayout(new GridLayout());
        Tree tree = new Tree(composite, SWT.BORDER);
        item.setControl(composite);
        tree.setHeaderVisible(true);
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        TreeColumn column1 = new TreeColumn(tree, SWT.NONE);
        column1.setText("Standard");
        TreeColumn column2 = new TreeColumn(tree, SWT.NONE);
        column2.setText("Widget");
        TreeItem branch = new TreeItem(tree, SWT.NONE);
        branch.setText(new String[] { "Efficient", "Portable" });
        TreeItem leaf = new TreeItem(branch, SWT.NONE);
        leaf.setText(new String[] { "Cross", "Platform" });
        branch.setExpanded(true);
        branch = new TreeItem(tree, SWT.NONE);
        branch.setText(new String[] { "Native", "Controls" });
        leaf = new TreeItem(branch, SWT.NONE);
        leaf.setText(new String[] { "Cross", "Platform" });
        branch = new TreeItem(tree, SWT.NONE);
        branch.setText(new String[] { "Cross", "Platform" });
        column1.pack();
        column2.pack();

        item = new TabItem(tabFolder, SWT.NONE);
        item.setText("Toolkit");

        Button button = new Button(shell, SWT.CHECK);
        button.setText("Totally");
        button.setSelection(true);
        button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));

        button = new Button(shell, SWT.PUSH);
        button.setText("Awesome");
        button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        
        display.dispose();
    }

}
