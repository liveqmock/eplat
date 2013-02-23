/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import com.atom.core.lang.ids.LongID;

/**
 * 注射泵/输液泵训练信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: PumpInfoDTO.java, V1.0.1 2013-2-23 上午9:59:27 $
 */
public final class PumpInfoDTO extends LongID {
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
    public PumpInfoDTO() {
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
