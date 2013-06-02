/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt.jface;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: City.java, V1.0.1 2013-6-1 下午8:34:06 $
 */
public class City implements ITree {
    private Long   id;
    private String name;
    private List   children = new ArrayList();

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public boolean hasChildren() {
        return (this.children != null && this.children.size() > 0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
