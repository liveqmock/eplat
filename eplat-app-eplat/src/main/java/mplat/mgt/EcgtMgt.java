/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mplat.mgt.dto.EcgtInfoDTO;
import mplat.store.EcgtStore;

import org.apache.commons.lang.StringUtils;

import com.atom.core.xstream.store.StoreFactory;

/**
 * 心律试题管理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgMgt.java, V1.0.1 2013-2-16 下午9:37:10 $
 */
public final class EcgtMgt {
    /** 试题选项 */
    private static final List<String>        ECGT_QRS      = Arrays.asList("", "A", "B", "C", "D", "E", "F", "G");

    /** 基本心律 */
    private static final Map<String, String> ECGT_TYPES    = initEcgtTypes();

    /** 额外心律 */
    private static final List<String>        ECGT_EXT_SYST = initEcgtExtSysts();

    /** 心律试题存储器 */
    private final EcgtStore                  ecgtStore;

    /**
     * 初始化基本心律
     */
    private static final Map<String, String> initEcgtTypes() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("一度房室传导阻滞", "1degree_AVB_{QRS}.jpg");
        map.put("二度Ⅰ型房室传导阻滞", "2degree_AVB_I_{QRS}.jpg");
        map.put("二度Ⅱ型房室传导阻滞", "2degree_AVB_II_{QRS}.jpg");
        map.put("三度房室传导阻滞", "3degree_AVB.jpg");
        map.put("房颤", "Afib_{QRS}.jpg");
        map.put("房扑", "Aflutt_{QRS}.jpg");
        map.put("临终心律", "AgonalRhythm.jpg");
        map.put("心搏停止", "Asystole.jpg");
        map.put("房性心动过速", "Atach_{QRS}.jpg");
        map.put("死亡心律", "Die.jpg");
        map.put("心室律", "Idiov_{QRS}.jpg");
        map.put("交界性心律", "Junct_{QRS}.jpg");
        map.put("房颤起搏", "PacemakerAFib.jpg");
        map.put("起搏器心律", "PacemakerAtr.jpg");
        map.put("房室顺序起搏", "PacemakerAV_Seq.jpg");
        map.put("Pacemarker LOC", "PacemakerLoc.jpg");
        map.put("Pacemaker No Ps", "PacemakerNoPs.jpg");
        map.put("Pacemaker p=90 Q=100", "PacemakerP90Q100.jpg");
        map.put("Pacemaker p=90 Q=80", "PacemakerP90Q80.jpg");
        map.put("窦性", "Sinus_{QRS}.jpg");
        map.put("窦性双发型室性早搏", "Sinus_Coupled_PVC.jpg");
        map.put("窦性房性早搏", "Sinus_PAC.jpg");
        map.put("窦性交界性早搏", "Sinus_PJC.jpg");
        map.put("窦性单源性室性早搏", "Sinus_Unifocal_PVC.jpg");
        map.put("尖端扭转型室速", "Torsade.jpg");
        map.put("心室停顿(搏)", "Ventr_Standstill.jpg");
        map.put("粗室颤", "Vfibr_coars.jpg");
        map.put("细室颤", "Vfibr_fine.jpg");
        map.put("十分粗室颤", "Vfibr_verycoars.jpg");
        map.put("十分细室颤", "Vfibr_veryfine.jpg");
        map.put("室颤", "Vfibr.jpg");
        map.put("室性心动过速", "Vtach_{QRS}.jpg");
        map.put("室速220", "Vtach220.jpg");

        return map;
    }

    /**
     * 初始化额外心律
     */
    private static final List<String> initEcgtExtSysts() {
        List<String> list = new ArrayList<String>();
        list.add(StringUtils.EMPTY);
        list.add("单形性室性早搏");
        list.add("室性早搏RonT");
        list.add("室性早搏二联律");
        list.add("多形性室性早搏");
        list.add("房性早搏");
        list.add("交界性早搏");

        return list;
    }

    /**
     * 默认构造器
     */
    public EcgtMgt() {
        this.ecgtStore = StoreFactory.get().findStore(EcgtStore.class);
    }

    /**
     * 获取ECG-QRS选项
     */
    public static List<String> findEcgtQRS() {
        return ECGT_QRS;
    }

    /**
     * 获取ECG基本心律选项
     */
    public static List<String> findEcgtRhythm() {
        List<String> list = new ArrayList<String>(ECGT_TYPES.keySet());
        list.add(0, StringUtils.EMPTY);

        return list;
    }

    /**
     * 获取ECG额外心律选项
     */
    public static List<String> findEcgtExtSyst() {
        return ECGT_EXT_SYST;
    }

    /**
     * 获取ECG心电图
     */
    public static String findEcgGraph(String rhythm, String qrs) {
        String ecgt = ECGT_TYPES.get(rhythm);
        if (StringUtils.isBlank(ecgt)) {
            throw new RuntimeException("ECG心电图不存在, Rhythm[" + rhythm + "], QRS[" + qrs + "].");
        }

        qrs = StringUtils.trimToNull(qrs);

        return StringUtils.replace(ecgt, "{QRS}", qrs);
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
