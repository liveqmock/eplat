/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt.jface;

import java.util.List;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: Itree.java, V1.0.1 2013-6-1 下午8:31:40 $
 */
public interface ITree {
    public String getName();

    public void setName(String name);

    public void setChildren(List Children);

    public List getChildren();
    
    public boolean hasChildren();
    
}
