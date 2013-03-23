/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.msgs;

/**
 * 数据监听器
 * 
 * @author obullxl@gmail.com
 * @version $Id: DataListener.java, V1.0.1 2013-3-23 下午2:29:02 $
 */
public interface DataListener {

    /**
     * 数据可用
     */
    public void onData(int[] data);
    
}
