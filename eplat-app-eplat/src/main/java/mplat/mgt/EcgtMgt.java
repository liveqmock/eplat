/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt;

import java.util.List;

import mplat.mgt.dto.EcgtInfoDTO;
import mplat.store.EcgtStore;

import com.atom.core.xstream.store.StoreFactory;

/**
 * 心律试题管理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgMgt.java, V1.0.1 2013-2-16 下午9:37:10 $
 */
public final class EcgtMgt {
    /** 心律试题存储器 */
    private final EcgtStore ecgtStore;

    /**
     * 默认构造器
     */
    public EcgtMgt() {
        this.ecgtStore = StoreFactory.get().findStore(EcgtStore.class);
    }

    /**
     * 获取信息
     */
    public EcgtInfoDTO find(long id) {
        return this.ecgtStore.find(id);
    }

    /**
     * 获取所有信息
     */
    public List<EcgtInfoDTO> findAll() {
        return this.ecgtStore.findAll();
    }

    /**
     * 新增信息
     */
    public void create(EcgtInfoDTO ecgInfo) {
        this.ecgtStore.create(ecgInfo);
    }

    /**
     * 更新信息
     */
    public void update(EcgtInfoDTO ecgInfo) {
        this.ecgtStore.update(ecgInfo);
    }

    /**
     * 删除信息
     */
    public void remove(long id) {
        this.ecgtStore.remove(id);
    }

    /**
     * 删除信息
     */
    public void remove(EcgtInfoDTO ecgInfo) {
        if (ecgInfo != null) {
            this.remove(ecgInfo.getId());
        }
    }

}
