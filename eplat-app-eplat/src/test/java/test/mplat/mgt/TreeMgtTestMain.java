/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.mgt;

import java.util.List;

import mplat.mgt.MgtFactory;
import mplat.mgt.dto.TreeDTO;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: TreeMgtTestMain.java, V1.0.1 2013-6-2 上午8:50:22 $
 */
public class TreeMgtTestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<TreeDTO> nodes = MgtFactory.get().findTreeMgt().findEmergeAbcNodes();
        for (TreeDTO item : nodes) {
            System.out.println(item);
        }
    }

}
