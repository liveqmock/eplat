/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.swt;

/**
 * JS调用Java事件
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTWebEvent.java, V1.0.1 2013-4-4 下午3:00:09 $
 */
public interface SWTWebEvent {

    /**
     * JS调用Java方法
     */
    public Object fireEvent(Object[] args);
    
}
