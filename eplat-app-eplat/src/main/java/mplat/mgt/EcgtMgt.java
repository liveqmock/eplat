/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt;

import java.util.ArrayList;
import java.util.Arrays;
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
    /** 试题选项 */
    private static final List<String> ECGT_QRS      = Arrays.asList("", "A", "B", "C", "D", "E", "F", "G");

    /** 基本心律 */
    private static final List<String> ECGT_RHYTHM   = initEcgtRhythms();

    /** 额外心律 */
    private static final List<String> ECGT_EXT_SYST = initEcgtExtSysts();

    /** 心律试题存储器 */
    private final EcgtStore           ecgtStore;

    /**
     * 初始化基本心律
     */
    private static final List<String> initEcgtRhythms() {
        List<String> list = new ArrayList<String>();
        list.add("窦性");
        list.add("窦性单源性室性早搏");
        list.add("窦性双发型室性早搏");
        list.add("窦性房性早搏");
        list.add("窦性交界性早搏");
        list.add("房性心动过速");
        list.add("房扑");
        list.add("房颤");
        list.add("交界性心律");
        list.add("心室律");
        list.add("室性心动过速");
        list.add("室速220");
        list.add("尖端扭转型室速");
        list.add("十分粗室颤");
        list.add("粗室颤");
        list.add("室颤");
        list.add("细室颤");
        list.add("十分细室颤");
        list.add("心搏停止");
        list.add("临终心律");
        list.add("心室停顿(搏)");
        list.add("死亡心律");
        list.add("一度房室传导阻滞");
        list.add("二度Ⅰ型房室传导阻滞");
        list.add("二度Ⅱ型房室传导阻滞");
        list.add("三度房室传导阻滞");
        list.add("起搏器心律");
        list.add("房室顺序起搏");
        list.add("房颤起搏");
        list.add("Pacemarker LOC");
        list.add("Pacemaker No Ps");
        list.add("Pacemaker p=90 Q=80");
        list.add("Pacemaker p=90 Q=100");

        return list;
    }

    /**
     * 初始化额外心律
     */
    private static final List<String> initEcgtExtSysts() {
        List<String> list = new ArrayList<String>();
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
        return ECGT_RHYTHM;
    }

    /**
     * 获取ECG额外心律选项
     */
    public static List<String> findEcgtExtSyst() {
        return ECGT_EXT_SYST;
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
