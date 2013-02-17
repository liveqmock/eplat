/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.store;

import java.io.File;

import mplat.mgt.dto.EcgtInfoDTO;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.xstream.store.BaseStore;
import com.thoughtworks.xstream.XStream;

/**
 * 心律训练存储器
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtStore.java, V1.0.1 2013-2-16 下午9:38:19 $
 */
public final class EcgtStore extends BaseStore<EcgtInfoDTO> {

    /**
     * 默认构造器
     */
    public EcgtStore() {
        String path = CfgUtils.findConfigPath() + "/store";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        super.setFilePath(path + "/EcgtMgt.data");
    }
    
    /**
     * @see com.atom.core.xstream.store.BaseStore#initExt()
     */
    public final void initExt() {
        XStream xstream = findXStream();
        xstream.alias("Ecgt", EcgtInfoDTO.class);
        xstream.alias("MapExt", MapExt.class);
    }

}
