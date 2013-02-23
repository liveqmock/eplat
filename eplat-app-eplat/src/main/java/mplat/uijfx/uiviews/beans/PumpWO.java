/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.beans;

import java.util.ArrayList;
import java.util.List;

import mplat.mgt.dto.PumpInfoDTO;

import com.atom.core.lang.ids.LongID;

/**
 * 注射泵/输液泵训练信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: PumpWO.java, V1.0.1 2013-2-23 上午9:59:27 $
 */
public final class PumpWO extends LongID {
    private static final long serialVersionUID = 569299855564937572L;

    /** 属性-KEY*/
    private String            key;
    /** 名称 */
    private String            name;
    /** 医嘱 */
    private String            advice;
    /** 速度 */
    private String            rate;
    /** 剂量 */
    private String            dose;
    /** 体重 */
    private String            weight;

    /**
     * 构造器
     */
    public PumpWO() {
    }

    public PumpWO(long id, String key, String name, String advice, String rate, String dose, String weight) {
        this();

        this.setId(id);
        this.setKey(key);
        this.setName(name);
        this.setAdvice(advice);
        this.setRate(rate);
        this.setDose(dose);
        this.setWeight(weight);
    }
    
    /**
     * 从PumpInfoDTO对象转化为PumpWO对象
     */
    public static PumpWO from(PumpInfoDTO srcObj) {
        if(srcObj == null) {
            return null;
        }
        
        PumpWO dstObj = new PumpWO();
        
        dstObj.setId(srcObj.getId());
        dstObj.setKey(srcObj.getKey());
        dstObj.setName(srcObj.getName());
        dstObj.setAdvice(srcObj.getAdvice());
        dstObj.setRate(srcObj.getRate());
        dstObj.setDose(srcObj.getDose());
        dstObj.setWeight(srcObj.getWeight());
        
        return dstObj;
    }
    
    /**
     * 从PumpInfoDTO对象列比转化为PumpWO对象列表
     */
    public static List<PumpWO> from(List<PumpInfoDTO> srcObjs) {
        List<PumpWO> dstObjs = new ArrayList<PumpWO>();
        
        if(srcObjs == null || srcObjs.isEmpty()) {
            return dstObjs;
        }
        
        for(PumpInfoDTO srcObj : srcObjs) {
            PumpWO dstObj = from(srcObj);
            if(dstObj != null) {
                dstObjs.add(dstObj);
            }
        }
        
        return dstObjs;
    }

    // ~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~ //

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public void setTitle(String title) {
        // Ignore
    }

    public String getTitle() {
        return this.getName() + "+" + this.getAdvice();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
