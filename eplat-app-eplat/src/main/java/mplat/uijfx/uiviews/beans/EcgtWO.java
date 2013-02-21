/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.beans;

import java.util.ArrayList;
import java.util.List;

import mplat.mgt.dto.EcgtInfoDTO;

import com.atom.core.lang.ids.LongID;

/**
 * 心律试题信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtWO.java, V1.0.1 2013-2-21 下午9:10:49 $
 */
public final class EcgtWO extends LongID {
    private static final long serialVersionUID = 5023026150686492956L;

    private String            ecgtQrs;
    private String            ecgtRhythm;
    private String            ecgtSyst;
    private String            ecgtRate;

    private String            tipQrs;
    private String            tipRate;
    private String            tipRegular;
    private String            tipWave;
    private String            tipInterval;

    /**
     * 从EcgtWO对象转化为EcgtInfoDTO对象
     */
    public static EcgtInfoDTO to(EcgtWO srcObj) {
        if (srcObj == null) {
            return null;
        }

        EcgtInfoDTO dstObj = new EcgtInfoDTO();
        dstObj.setId(srcObj.getId());

        dstObj.setEcgtQrs(srcObj.getEcgtQrs());
        dstObj.setEcgtRhythm(srcObj.getEcgtRhythm());
        dstObj.setEcgtSyst(srcObj.getEcgtSyst());
        dstObj.setEcgtRate(srcObj.getEcgtRate());

        dstObj.setTipQrs(srcObj.getTipQrs());
        dstObj.setTipRate(srcObj.getTipRate());
        dstObj.setTipRegular(srcObj.getTipRegular());
        dstObj.setTipWave(srcObj.getTipWave());
        dstObj.setTipInterval(srcObj.getTipInterval());

        return dstObj;
    }

    /**
     * 从EcgtInfoDTO对象转化为EcgtWO对象
     */
    public static EcgtWO from(EcgtInfoDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        EcgtWO dstObj = new EcgtWO();
        dstObj.setId(srcObj.getId());

        dstObj.setEcgtQrs(srcObj.getEcgtQrs());
        dstObj.setEcgtRhythm(srcObj.getEcgtRhythm());
        dstObj.setEcgtSyst(srcObj.getEcgtSyst());
        dstObj.setEcgtRate(srcObj.getEcgtRate());

        dstObj.setTipQrs(srcObj.getTipQrs());
        dstObj.setTipRate(srcObj.getTipRate());
        dstObj.setTipRegular(srcObj.getTipRegular());
        dstObj.setTipWave(srcObj.getTipWave());
        dstObj.setTipInterval(srcObj.getTipInterval());

        return dstObj;
    }

    public static List<EcgtWO> from(List<EcgtInfoDTO> srcObjs) {
        List<EcgtWO> dstObjs = new ArrayList<EcgtWO>();

        if (srcObjs == null || srcObjs.isEmpty()) {
            return dstObjs;
        }

        for (EcgtInfoDTO srcObj : srcObjs) {
            EcgtWO dstObj = from(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getEcgtQrs() {
        return ecgtQrs;
    }

    public void setEcgtQrs(String ecgtQrs) {
        this.ecgtQrs = ecgtQrs;
    }

    public String getEcgtRhythm() {
        return ecgtRhythm;
    }

    public void setEcgtRhythm(String ecgtRhythm) {
        this.ecgtRhythm = ecgtRhythm;
    }

    public String getEcgtSyst() {
        return ecgtSyst;
    }

    public void setEcgtSyst(String ecgtSyst) {
        this.ecgtSyst = ecgtSyst;
    }

    public String getEcgtRate() {
        return ecgtRate;
    }

    public void setEcgtRate(String ecgtRate) {
        this.ecgtRate = ecgtRate;
    }

    public String getTipQrs() {
        return tipQrs;
    }

    public void setTipQrs(String tipQrs) {
        this.tipQrs = tipQrs;
    }

    public String getTipRate() {
        return tipRate;
    }

    public void setTipRate(String tipRate) {
        this.tipRate = tipRate;
    }

    public String getTipRegular() {
        return tipRegular;
    }

    public void setTipRegular(String tipRegular) {
        this.tipRegular = tipRegular;
    }

    public String getTipWave() {
        return tipWave;
    }

    public void setTipWave(String tipWave) {
        this.tipWave = tipWave;
    }

    public String getTipInterval() {
        return tipInterval;
    }

    public void setTipInterval(String tipInterval) {
        this.tipInterval = tipInterval;
    }

}
