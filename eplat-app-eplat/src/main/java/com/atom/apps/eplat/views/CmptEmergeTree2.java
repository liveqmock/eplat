package com.atom.apps.eplat.views;

import java.util.List;

import mplat.mgt.dto.TreeDTO;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class CmptEmergeTree2 extends Composite {
    /** 标签高度 */
    private static final int LBL_HEIGHT = 20;

    /** 组件宽度 */
    private int              compWidth;
    private int              compHeight;

    /** 组件 */
    private Label            lblAbc;
    private Label            lblMisc;
    private Label            lblMedic;

    private Tree             treeAbc;
    private Tree             treeMisc;
    private Tree             treeMedic;

    private TreeViewer       treeViewerAbc;
    private TreeViewer       treeViewerMisc;
    private TreeViewer       treeViewerMedic;

    /** 数据 */
    private List<TreeDTO>    treeAbcNodes;
    private List<TreeDTO>    treeMiscNodes;
    private List<TreeDTO>    treeMedicNodes;

    /**
     * 设置树节点数据
     */
    public void updateTreeNodes(List<TreeDTO> abcNodes, List<TreeDTO> miscNodes, List<TreeDTO> medicNodes) {
        this.treeAbcNodes = abcNodes;
        this.treeMiscNodes = miscNodes;
        this.treeMedicNodes = medicNodes;
    }

    /**
     * 初始化视图
     */
    public void initComposites(int compWidth, int compHeight) {
        this.compWidth = compWidth;
        this.compHeight = compHeight;

        int width = this.compWidth / 3;
        int width2 = this.compWidth - (width * 2);
        int height = this.compHeight - LBL_HEIGHT;

        this.lblAbc.setBounds(0, 0, width, LBL_HEIGHT);
        this.lblMisc.setBounds(width, 0, width2, LBL_HEIGHT);
        this.lblMedic.setBounds((width + width2), 0, width, LBL_HEIGHT);

        this.treeAbc = this.treeViewerAbc.getTree();
        this.treeAbc.setBounds(0, LBL_HEIGHT, width, height);

        this.treeMisc = this.treeViewerMisc.getTree();
        this.treeMisc.setBounds(width, LBL_HEIGHT, width2, height);

        this.treeMedic = this.treeViewerMedic.getTree();
        this.treeMedic.setBounds((width + width2), LBL_HEIGHT, width, height);

        this.treeViewerAbc.setInput(this.treeAbcNodes);
        this.treeAbc.addListener(SWT.MouseDown, new Listener() {
            public void handleEvent(Event event) {
                Point point = new Point(event.x, event.y);
                TreeItem item = treeAbc.getItem(point);
                if (item != null) {
                    TreeDTO node = (TreeDTO) item.getData();
                    if (node != null) {
                        if (node.hasChildren()) {
                            if (treeViewerAbc.getExpandedState(node)) {
                                treeViewerAbc.collapseToLevel(node, 1);
                            } else {
                                treeViewerAbc.expandToLevel(node, 1);
                            }
                        } else {
                            System.out.println("~~~~~~~~~~~~~~~~~~子节点: " + item);
                        }
                    }
                }
            }
        });

        this.treeViewerMisc.setInput(this.treeMiscNodes);

        this.treeViewerMedic.setInput(this.treeMedicNodes);
    }

    /**
     * Create the composite.
     */
    public CmptEmergeTree2(Composite parent) {
        super(parent, SWT.NONE);

        this.lblAbc = new Label(this, SWT.NONE);
        this.lblAbc.setAlignment(SWT.CENTER);
        this.lblAbc.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
        this.lblAbc.setBounds(0, 0, 140, LBL_HEIGHT);
        this.lblAbc.setText("ABC");

        this.lblMisc = new Label(this, SWT.NONE);
        this.lblMisc.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
        this.lblMisc.setAlignment(SWT.CENTER);
        this.lblMisc.setBounds(140, 0, 140, LBL_HEIGHT);
        this.lblMisc.setText("杂项");

        this.lblMedic = new Label(this, SWT.NONE);
        this.lblMedic.setAlignment(SWT.CENTER);
        this.lblMedic.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
        this.lblMedic.setBounds(280, 0, 140, LBL_HEIGHT);
        this.lblMedic.setText("施药");

        this.treeViewerAbc = new TreeViewer(this, SWT.BORDER);
        this.treeAbc = this.treeViewerAbc.getTree();
        this.treeAbc.setBounds(0, LBL_HEIGHT, 140, 230);

        this.treeViewerMisc = new TreeViewer(this, SWT.BORDER);
        this.treeMisc = this.treeViewerMisc.getTree();
        this.treeMisc.setBounds(140, LBL_HEIGHT, 140, 230);

        this.treeViewerMedic = new TreeViewer(this, SWT.BORDER);
        this.treeMedic = this.treeViewerMedic.getTree();
        this.treeMedic.setBounds(280, LBL_HEIGHT, 140, 230);

        this.treeViewerAbc.setLabelProvider(new TreeLabelProvider());
        this.treeViewerAbc.setContentProvider(new TreeContentProvider());

        this.treeViewerMisc.setLabelProvider(new TreeLabelProvider());
        this.treeViewerMisc.setContentProvider(new TreeContentProvider());

        this.treeViewerMedic.setLabelProvider(new TreeLabelProvider());
        this.treeViewerMedic.setContentProvider(new TreeContentProvider());
    }

    /** 
     * @see org.eclipse.swt.widgets.Composite#checkSubclass()
     */
    protected void checkSubclass() {
        super.checkSubclass();
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
                return node.getText();
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
