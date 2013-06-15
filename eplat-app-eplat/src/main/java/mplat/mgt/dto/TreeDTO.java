/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import java.util.ArrayList;
import java.util.List;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ToString;

/**
 * 解救树节点
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmergeTreeNode.java, V1.0.1 2013-6-1 下午9:19:27 $
 */
public class TreeDTO {
    /** 扩展参数名 */
    private static final String EXT_COUNT = "__ext_count_";

    /** 展示文本 */
    private String              text;

    /** 子节点代码 */
    private String              code;

    /** 父节点 */
    private TreeDTO             parent;

    /** 子节点 */
    private final List<TreeDTO> children  = new ArrayList<TreeDTO>();

    /** 扩展参数 */
    private final MapExt        ext       = new MapExt();

    /**
     * CTOR
     */
    public TreeDTO() {
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToString.toString(this);
    }

    /* ~~~~~~~~~~~ 扩展参数 ~~~~~~~~~~ */

    public int findExtCount() {
        return this.ext.getInt(EXT_COUNT, 0);
    }

    public TreeDTO increaseExtCount() {
        int src = this.findExtCount();
        this.ext.put(EXT_COUNT, Integer.toString(src + 1));
        return this;
    }

    public String findTreeText() {
        int count = this.findExtCount();
        if (count > 0) {
            return "(" + count + ")" + this.text;
        } else {
            return this.text;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean hasChildren() {
        return !(this.children.isEmpty());
    }

    public List<TreeDTO> getChildren() {
        return children;
    }

    public TreeDTO getParent() {
        return parent;
    }

    public void setParent(TreeDTO parent) {
        this.parent = parent;
    }

    public MapExt getExt() {
        return ext;
    }

}
