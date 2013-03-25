/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.KVUtils;

/**
 * 心律识别Key/Value设置
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtKVMgt.java, V1.0.1 2013-3-22 下午9:34:05 $
 */
public final class EcgtKVMgt {
    /** 缓存 */
    private static final Map<String, String> values = new ConcurrentHashMap<String, String>();

    /**
     * 初始化
     */
    public EcgtKVMgt() {
        String path = CfgUtils.findConfigPath() + "/cfgs/EcgtKeyValueCfg.xml";
        values.putAll(KVUtils.fromXML(path));
    }

    /**
     * 获取内容
     */
    public final String findValue(String key) {
        return values.get(key);
    }

}
