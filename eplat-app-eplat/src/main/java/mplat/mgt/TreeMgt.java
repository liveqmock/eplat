/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mplat.mgt.dto.TreeDTO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.lang.xml.XMLNode;
import com.atom.core.lang.xml.XMLUtils;

/**
 * 树节点管理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TreeMgt.java, V1.0.1 2013-6-1 下午9:41:35 $
 */
public class TreeMgt {
    /** 树类型 */
    public static final int                   CATG_ABC   = 1;
    public static final int                   CATG_MISC  = 2;
    public static final int                   CATG_MEDIC = 3;

    private final Map<Integer, List<TreeDTO>> trees      = new ConcurrentHashMap<Integer, List<TreeDTO>>();

    /**
     * CTOR
     */
    public TreeMgt() {
        this.initTrees(CATG_ABC);
        this.initTrees(CATG_MISC);
        this.initTrees(CATG_MEDIC);
    }

    /**
     * 获取文件名
     */
    private String findFileName(int catg) {
        if (catg == CATG_ABC) {
            return "EmergeTreeAbc.xml";
        } else if (catg == CATG_MISC) {
            return "EmergeTreeMisc.xml";
        } else if (catg == CATG_MEDIC) {
            return "EmergeTreeMedic.xml";
        }

        return null;
    }

    /**
     * 初始化
     */
    private final void initTrees(int catg) {
        List<TreeDTO> tree = this.trees.get(catg);
        if (tree == null) {
            tree = new ArrayList<TreeDTO>();
            this.trees.put(catg, tree);
        }

        InputStream input = null;
        String name = this.findFileName(catg);
        try {
            String path = CfgUtils.findConfigPath();
            String file = FilenameUtils.normalize(path + "/cfgs/" + name);
            input = new FileInputStream(file);

            XMLNode root = XMLUtils.toXMLNode(input);
            for (XMLNode node : root.getChildren()) {
                TreeDTO item = new TreeDTO();
                item.setParent(null);
                
                tree.add(item);

                // 处理节点
                this.parseTreeNode(item, node);
            }
        } catch (Exception e) {
            LogUtils.error("初始化急救树[" + name + "]信息异常！", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 解析数节点
     */
    private void parseTreeNode(TreeDTO item, XMLNode node) {
        if (node.getChildren().isEmpty()) {
            // 子节点
            item.setText(node.getText());
            item.setCode(node.getExtMap().get("code"));
        } else {
            // 父节点
            item.setText(node.getExtMap().get("tag"));

            for (XMLNode elem : node.getChildren()) {
                TreeDTO child = new TreeDTO();
                child.setParent(item);
                
                item.getChildren().add(child);

                // 处理节点
                this.parseTreeNode(child, elem);
            }
        }
    }

    /**
     * 获取急救ABC树节点
     */
    public List<TreeDTO> findEmergeAbcNodes() {
        return new ArrayList<TreeDTO>(this.trees.get(CATG_ABC));
    }

    /**
     * 获取急救杂项树节点
     */
    public List<TreeDTO> findEmergeMiscNodes() {
        return new ArrayList<TreeDTO>(this.trees.get(CATG_MISC));
    }

    /**
     * 获取急救施药树节点
     */
    public List<TreeDTO> findEmergeMedicNodes() {
        return new ArrayList<TreeDTO>(this.trees.get(CATG_MEDIC));
    }

}
