/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import org.eclipse.swt.widgets.TreeItem;

/**
 * 树事件
 * 
 * @author obullxl@gmail.com
 * @version $Id: TreeEvtTuple.java, V1.0.1 2013-6-15 下午2:20:06 $
 */
public class TreeEvtTuple {

    /** 树节点 */
    private TreeItem treeItem;

    /** 树数据*/
    private TreeDTO  treeData;

    public TreeEvtTuple() {
    }

    public TreeEvtTuple(TreeItem item, TreeDTO data) {
        this.setTreeItem(item);
        this.setTreeData(data);
    }

    public TreeItem getTreeItem() {
        return treeItem;
    }

    public void setTreeItem(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

    public TreeDTO getTreeData() {
        return treeData;
    }

    public void setTreeData(TreeDTO treeData) {
        this.treeData = treeData;
    }

}
