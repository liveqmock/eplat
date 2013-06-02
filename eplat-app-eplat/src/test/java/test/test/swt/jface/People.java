/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt.jface;

import java.util.List;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: People.java, V1.0.1 2013-6-1 下午8:33:36 $
 */
public class People implements ITree {
    private Long   id;
    private String name;

    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    public List getChildren() {
        return null;
    }

    public void setChildren(List children) {
    }

    public boolean hasChildren() {
        return false;
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
