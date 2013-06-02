/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt.jface;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.atom.apps.eplat.SWTUtils;

/**
 * TreeViewer测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: TreeViewerTestMain.java, V1.0.1 2013-6-1 下午8:29:17 $
 */
public class TreeViewerTestMain {
    private static Tree tree;

    public static void main(String[] args) {
        final Display display = Display.getDefault();
        final Shell shell = new Shell();
        shell.setSize(500, 375);
        shell.setText("TreeViewer测试");

        SWTUtils.init();

        SWTUtils.center(shell);

        //
        final TreeViewer treeViewer = new TreeViewer(shell, SWT.BORDER | SWT.H_SCROLL);
        tree = treeViewer.getTree();
        tree.setBounds(83, 75, 264, 185);

        treeViewer.setLabelProvider(new TreeLabelProvider());
        treeViewer.setContentProvider(new TreeContentProvider());
        treeViewer.setInput(Factory.createTree());

        tree.addListener(SWT.MouseDown, new Listener() {
            public void handleEvent(Event event) {
                Point point = new Point(event.x, event.y);
                TreeItem item = tree.getItem(point);
                if (item != null) {
                    ITree node = (ITree) item.getData();
                    if (node.hasChildren()) {
                        if (treeViewer.getExpandedState(node)) {
                            treeViewer.collapseToLevel(node, 1);
                        } else {
                            treeViewer.expandToLevel(node, 1);
                        }
                    } else {
                        System.out.println("~~~~~~~~~~~~~~~~~~子节点: " + item);
                    }
                }
            }
        });

        /*
        treeViewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(DoubleClickEvent event) {
                ISelection selection = event.getSelection();
                //与此ISelection selection = treeViewer.getSelection(); 
                // 得到选中的项，注意方法是将得到的选项转换成 IStructuredSelection，在调用 getFirstElement 方法 
                Object object = ((IStructuredSelection) selection).getFirstElement();
                // 再将对象转为实际的树节点对象 
                ITree element = (ITree) object;
                // 处理展开/收缩子菜单 
                if (element.hasChildren()) {
                    //获取展开状态 
                    if (treeViewer.getExpandedState(element))
                        treeViewer.collapseToLevel(element, 1);
                    else
                        treeViewer.expandToLevel(element, 1);
                }
            }
        });
        */
        
        shell.open();
        shell.setLayout(new FillLayout());
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        SWTUtils.dispose();
    }

    private static class Factory {
        @SuppressWarnings("unchecked")
        public static List createTree() {
            //生成国家
            Country cn = new Country("中国");
            Country us = new Country("美国");
            Country jp = new Country("日本");
            //生成城市
            City beijing = new City("北京");
            City shanghai = new City("上海");
            City newyork = new City("纽约");
            City la = new City("洛杉矶");
            City tokyo = new City("东京");
            City osaka = new City("大阪");
            //北京人
            List list = new ArrayList();
            list.add(new People("张三"));
            list.add(new People("李四"));
            list.add(new People("王五"));
            beijing.setChildren(list);
            //上海人
            list = new ArrayList();
            list.add(new People("翠花"));
            list.add(new People("小红"));
            list.add(new People("小崔"));
            shanghai.setChildren(list);
            //纽约人
            list = new ArrayList();
            list.add(new People("tom"));
            list.add(new People("rose"));
            list.add(new People("john"));
            newyork.setChildren(list);
            //洛杉矶人
            list = new ArrayList();
            list.add(new People("Sofia"));
            list.add(new People("sarah"));
            list.add(new People("Jennifer"));
            la.setChildren(list);
            //东京人
            list = new ArrayList();
            list.add(new People("渡边"));
            list.add(new People("鬼冢"));
            list.add(new People("山本"));
            tokyo.setChildren(list);
            //大阪人
            list = new ArrayList();
            list.add(new People("奈奈子"));
            list.add(new People("菜菜子"));
            list.add(new People("新垣结衣"));
            osaka.setChildren(list);

            //关联城市与国家
            //中国
            ArrayList citys = new ArrayList();
            citys.add(beijing);
            citys.add(shanghai);
            cn.setChildren(citys);
            //美国
            citys = new ArrayList();
            citys.add(newyork);
            citys.add(la);
            us.setChildren(citys);
            //日本
            citys = new ArrayList();
            citys.add(tokyo);
            citys.add(osaka);
            jp.setChildren(citys);

            //国家列表
            List countrys = new ArrayList();
            countrys.add(cn);
            countrys.add(us);
            countrys.add(jp);
            return countrys;
        }
    }

    private static class TreeLabelProvider extends LabelProvider implements ILabelProvider {
        public String getText(Object element) {
            ITree node = (ITree) element;
            return node.getName();
        }

        public Image getImage(Object element) {
            return null; // SWTUtils.findImage("tab-ecgt-train.gif");
        }
    }

    private static class TreeContentProvider implements IStructuredContentProvider, ITreeContentProvider {

        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof List) {
                List input = (List) inputElement;
                return input.toArray();
            }
            return new Object[0];
        }

        public Object[] getChildren(Object parentElement) {
            ITree node = (ITree) parentElement;
            List list = node.getChildren();
            if (list == null) {
                return new Object[0];
            }
            return list.toArray();
        }

        public boolean hasChildren(Object element) {
            ITree node = (ITree) element;
            List list = node.getChildren();
            return !(list == null || list.isEmpty());
        }

        //以下三个函数根据需要填充
        public Object getParent(Object element) {
            return null;
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }

        public void dispose() {
        }
    }
}
