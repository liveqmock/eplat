/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt;

import mplat.mgt.dto.GsetInfoDTO;

import org.apache.commons.io.FilenameUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.xstream.store.ObjectStore;

/**
 * 系统参数设置管理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: GsetMgt.java, V1.0.1 2013-2-10 下午9:21:17 $
 */
public class GsetMgt {
    /** 参数分类 */
    // 心肺复苏
    public static final String CATG_RECOVER  = "recover";
    // CPR操作规则设置
    public static final String CATG_CPR_RULE = "cpr-rule";
    // 虚拟监控器设置
    public static final String CATG_MONITOR  = "monitor";
    // 虚拟体征日志
    public static final String CATG_BODY_LOG = "body-log";

    /** 存储器 */
    private final ObjectStore  store         = new ObjectStore();

    /** 参数对象 */
    private GsetInfoDTO        gset          = new GsetInfoDTO();

    /**
     * 默认构造函数，初始化
     */
    public GsetMgt() {
        ObjectStore.findXStream().alias("GSet", GsetInfoDTO.class);

        String path = CfgUtils.findConfigPath();
        this.store.setFilePath(FilenameUtils.normalize(path + "/store/GSetMgt.data"));
        this.store.init();

        GsetInfoDTO temp = (GsetInfoDTO) this.store.findObject();
        if (temp != null) {
            this.gset = temp;
        } else {
            LogUtils.warn("系统参数[" + GsetInfoDTO.class.getName() + "]初始化失败，使用默认值代替！");
        }
    }

    /**
     * 获取参数复本
     */
    public GsetInfoDTO getGSet() {
        return this.gset;
    }

    /**
     * 重置参数内容
     */
    public void reset() {
        this.gset = (GsetInfoDTO) this.store.findObject();
    }

    /**
     * 刷新参数设置
     */
    public void persist() {
        this.store.update(this.gset);
        this.store.persist();
    }

}
