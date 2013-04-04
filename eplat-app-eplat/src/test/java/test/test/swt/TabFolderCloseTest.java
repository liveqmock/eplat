/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.atom.apps.eplat.SWTUtils;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: TabFolderCloseTest.java, V1.0.1 2013-4-2 上午10:17:41 $
 */
public class TabFolderCloseTest {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        shell.setText("TabFolder");
        shell.setLayout(new FillLayout());

        final TabFolder folder = new TabFolder(shell, SWT.NONE);
        folder.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TabItem item = folder.getItem(folder.getSelectionIndex());
                System.out.println("Tab[" + item.getData("DATA") + "]-[" + item.getText() + "]选中~");
            }
        });

        final TabItem item1 = new TabItem(folder, SWT.NONE);
        item1.setData("DATA", "1");
        item1.setText("label");
        folder.setSelection(item1);
        
        // create the sc
        final ScrolledComposite sc = new ScrolledComposite(folder, SWT.V_SCROLL);
        sc.setExpandHorizontal(true);
        sc.setExpandVertical(true);
        sc.getVerticalBar().setIncrement(5);

        // create the composite on the sc
        final Composite endpointComp = new Composite(sc, SWT.NONE);
        GridData suGridData = new GridData(GridData.FILL_BOTH);
        endpointComp.setLayoutData(suGridData);
        endpointComp.setLayout(new GridLayout(1, true));
        // add button to the endpointComp
        addCompToEndpointComp(folder, endpointComp);

        sc.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                Point p = endpointComp.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                sc.setMinSize(p.x, p.y + 10);
                endpointComp.layout();
            }
        });
        sc.setContent(endpointComp);
        item1.setControl(sc);

        TabItem item2 = new TabItem(folder, SWT.NONE);
        item2.setData("DATA", "2");
        item2.setText("radio");
        Group group2 = new Group(folder, SWT.NONE);
        group2.setText("Radio Group");
        group2.setLayout(new GridLayout());
        Button btn2 = new Button(group2, SWT.PUSH);
        btn2.setText("Close \"label\" Item");
        item2.setControl(group2);

        btn2.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent arg0) {
                item1.dispose();
            }
        });

        shell.setVisible(true);
        SWTUtils.tryLoop(shell);
    }

    private static void addCompToEndpointComp(final TabFolder folder, Composite endpointComp) {
        Button button = new Button(endpointComp, SWT.PUSH);
        button.setText("New Tab-1");
        button.setLayoutData(new GridData());
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent arg0) {
                String data = Integer.toString(RandomUtils.nextInt(10));
                TabItem item = findByData(folder, "DATA", data);
                if (item != null) {
                    System.out.println("Tab[" + data + "]已经存在，直接显示~");
                    folder.setSelection(item);
                } else {
                    TabItem item1 = new TabItem(folder, SWT.NONE);
                    item1.setData("DATA", data);
                    item1.setText("新标签-" + data);
                    System.out.println("新标签-[" + data + "]~~");
                }
            }
        });
    }

    private static TabItem findByData(TabFolder folder, String key, String value) {
        TabItem[] items = folder.getItems();
        for (TabItem item : items) {
            if (ObjectUtils.equals(item.getData(key), value)) {
                return item;
            }
        }

        return null;
    }

    private static TabItem removeByData(TabFolder folder, String key, String value) {
        TabItem[] items = folder.getItems();
        for (TabItem item : items) {
            if (ObjectUtils.equals(item.getData(key), value)) {
                item.dispose();
                
                return null;
            }
        }

        return null;
    }
    
}
