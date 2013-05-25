/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.msgs;

import com.atom.core.lang.utils.LogUtils;

/**
 * 无操作
 * 
 * @author obullxl@gmail.com
 * @version $Id: NopDataListener.java, V1.0.1 2013-5-25 下午6:19:56 $
 */
public class NopDataListener implements DataListener {
    /** 单例 */
    public static final NopDataListener INSTANCE = new NopDataListener();

    /** 
     * @see mplat.mgt.msgs.DataListener#onData(mplat.mgt.msgs.DataItem)
     */
    public void onData(DataItem data) {
        LogUtils.info("处理消息：" + data);
    }

}
