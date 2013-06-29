package com.atom.apps.eplat.views;

import java.util.List;

import mplat.mgt.dto.TreeDTO;
import mplat.mgt.dto.TreeEvtTuple;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import swing2swt.layout.BorderLayout;

public class EmgeTreeCmpt extends Composite implements Listener {
    /** 主视图 */
    private final Composite mainView;

    /** 组件 */
    private TabFolder       tabFolder;

    private TabItem         tbtmAbc;
    private TabItem         tbtmMisc;
    private TabItem         tbtmMedic;

    private Tree            treeAbc;
    private Tree            treeMisc;
    private Tree            treeMedic;

    private TreeViewer      treeViewAbc;
    private TreeViewer      treeViewMisc;
    private TreeViewer      treeViewMedic;

    /** 数据 */
    private List<TreeDTO>   treeAbcNodes;
    private List<TreeDTO>   treeMiscNodes;
    private List<TreeDTO>   treeMedicNodes;

    /**
     * Create the composite.
     */
    public EmgeTreeCmpt(Composite parent) {
        super(parent, SWT.NONE);
        super.setLayout(new BorderLayout(0, 0));

        this.mainView = parent;

        this.tabFolder = new TabFolder(this, SWT.NONE);
        this.tbtmAbc = new TabItem(this.tabFolder, SWT.NONE);
        this.tbtmAbc.setText("ABC");

        this.treeViewAbc = new TreeViewer(this.tabFolder, SWT.BORDER);
        this.treeAbc = this.treeViewAbc.getTree();
        this.tbtmAbc.setControl(this.treeAbc);

        this.tbtmMisc = new TabItem(this.tabFolder, SWT.NONE);
        this.tbtmMisc.setText("杂项");

        this.treeViewMisc = new TreeViewer(this.tabFolder, SWT.BORDER);
        this.treeMisc = this.treeViewMisc.getTree();
        this.tbtmMisc.setControl(this.treeMisc);

        this.tbtmMedic = new TabItem(this.tabFolder, SWT.NONE);
        this.tbtmMedic.setText("施药");

        this.treeViewMedic = new TreeViewer(this.tabFolder, SWT.BORDER);
        this.treeMedic = this.treeViewMedic.getTree();
        this.tbtmMedic.setControl(this.treeMedic);
    }

    /**
     * 设置树数据
     */
    public void updateTreeNodes(List<TreeDTO> abcNodes, List<TreeDTO> miscNodes, List<TreeDTO> medicNodes) {
        this.treeAbcNodes = abcNodes;
        this.treeMiscNodes = miscNodes;
        this.treeMedicNodes = medicNodes;

        this.initEmergeTrees();
    }

    private void initEmergeTrees() {
        this.treeViewAbc.setLabelProvider(new TreeLabelProvider());
        this.treeViewAbc.setContentProvider(new TreeContentProvider());

        this.treeViewMisc.setLabelProvider(new TreeLabelProvider());
        this.treeViewMisc.setContentProvider(new TreeContentProvider());

        this.treeViewMedic.setLabelProvider(new TreeLabelProvider());
        this.treeViewMedic.setContentProvider(new TreeContentProvider());

        this.treeViewAbc.setInput(this.treeAbcNodes);
        this.treeViewMisc.setInput(this.treeMiscNodes);
        this.treeViewMedic.setInput(this.treeMedicNodes);

        this.treeAbc.addListener(SWT.MouseDown, this);
        this.treeMisc.addListener(SWT.MouseDown, this);
        this.treeMedic.addListener(SWT.MouseDown, this);
    }

    /** 
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event evt) {
        TabItem[] tbtms = this.tabFolder.getSelection();
        if (tbtms == null || tbtms.length < 1) {
            return;
        }

        TabItem tbtm = tbtms[0];
        if (tbtm == this.tbtmAbc) {
            this.onTreeAbcNode(evt);
        } else if (tbtm == this.tbtmMisc) {
            this.onTreeMiscNode(evt);
        } else if (tbtm == this.tbtmMedic) {
            this.onTreeMedicNode(evt);
        }
    }

    /**
     * 树ABC点击事件
     */
    private final void onTreeAbcNode(Event evt) {
        TreeEvtTuple tuple = this.findTreeTuple(this.treeAbc, evt);
        TreeDTO node = tuple.getTreeData();
        if (node == null) {
            return;
        }

        // 父节点
        if (node.hasChildren()) {
            if (this.treeViewAbc.getExpandedState(node)) {
                this.treeViewAbc.collapseToLevel(node, 1);
            } else {
                this.treeViewAbc.expandToLevel(node, 1);
            }

            return;
        }

        TreeItem item = tuple.getTreeItem();
        item.setText(node.increaseExtCount().findTreeText());

        // TODO:
        // this.mainView.onCreateEventLog(SWTUtils.findEvtTime(), node.getText());
    }

    /**
     * 树杂项点击事件
     */
    private final void onTreeMiscNode(Event evt) {
        TreeEvtTuple tuple = this.findTreeTuple(this.treeMisc, evt);
        TreeDTO node = tuple.getTreeData();
        if (node == null) {
            return;
        }

        // 父节点
        if (node.hasChildren()) {
            if (this.treeViewMisc.getExpandedState(node)) {
                this.treeViewMisc.collapseToLevel(node, 1);
            } else {
                this.treeViewMisc.expandToLevel(node, 1);
            }

            return;
        }

        TreeItem item = tuple.getTreeItem();
        item.setText(node.increaseExtCount().findTreeText());
        // TODO:
        //         this.mainView.onCreateEventLog(SWTUtils.findEvtTime(), node.getText());
    }

    /**
     * 树施药点击事件
     */
    private final void onTreeMedicNode(Event evt) {
        TreeEvtTuple tuple = this.findTreeTuple(this.treeMedic, evt);
        TreeDTO node = tuple.getTreeData();
        if (node == null) {
            return;
        }

        // 父节点
        if (node.hasChildren()) {
            if (this.treeViewMedic.getExpandedState(node)) {
                this.treeViewMedic.collapseToLevel(node, 1);
            } else {
                this.treeViewMedic.expandToLevel(node, 1);
            }

            return;
        }

        TreeItem item = tuple.getTreeItem();
        item.setText(node.increaseExtCount().findTreeText());
        // TODO:
        // this.mainView.onCreateEventLog(SWTUtils.findEvtTime(), node.getText());
    }

    /**
     * 获取点击事件数据
     */
    private final TreeEvtTuple findTreeTuple(Tree tree, Event evt) {
        TreeItem item = tree.getItem(new Point(evt.x, evt.y));
        TreeDTO data = null;
        if (item != null) {
            data = (TreeDTO) item.getData();
        }

        return new TreeEvtTuple(item, data);
    }

    /** 
     * @see org.eclipse.swt.widgets.Composite#checkSubclass()
     */
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

    /**
     * 树节点文本
     * 
     * @author obullxl@gmail.com
     * @version $Id: EmergeTreeView.java, V1.0.1 2013-6-1 下午9:24:46 $
     */
    private static class TreeLabelProvider extends LabelProvider implements ILabelProvider {
        /** 
         * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
         */
        public String getText(Object element) {
            TreeDTO node = (TreeDTO) element;
            if (node != null) {
                return node.findTreeText();
            }
            return "<NULL>";
        }

        /** 
         * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
         */
        public Image getImage(Object element) {
            return null; // SWTUtils.findImage("tab-ecgt-train.gif");
        }
    }

    /**
     * 数节点
     * 
     * @author obullxl@gmail.com
     * @version $Id: EmergeTreeView.java, V1.0.1 2013-6-1 下午9:26:15 $
     */
    private static class TreeContentProvider implements IStructuredContentProvider, ITreeContentProvider {
        /** 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        @SuppressWarnings("unchecked")
        public Object[] getElements(Object element) {
            if (element instanceof List) {
                List<TreeDTO> nodes = (List<TreeDTO>) element;
                if (nodes != null) {
                    return nodes.toArray();
                }
            }

            return new Object[0];
        }

        /** 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren(Object parentElement) {
            TreeDTO parent = (TreeDTO) parentElement;
            if (parent != null) {
                List<TreeDTO> list = parent.getChildren();
                if (list != null) {
                    return list.toArray();
                }
            }

            return new Object[0];
        }

        /** 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren(Object element) {
            TreeDTO parent = (TreeDTO) element;
            if (parent != null) {
                return parent.hasChildren();
            }

            return false;
        }

        /** 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        public Object getParent(Object element) {
            TreeDTO parent = (TreeDTO) element;
            if (parent != null) {
                return parent.getParent();
            }

            return null;
        }

        /** 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }

        /** 
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose() {
        }
    }

}
