/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ids.LongID;

/**
 * 心律试题信息对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtInfoDTO.java, V1.0.1 2013-2-16 下午9:31:13 $
 */
public class EcgtInfoDTO extends LongID {
    private static final long serialVersionUID = 2190895621825643145L;

    private String            ecgtQrs;
    private String            ecgtRhythm;
    private String            ecgtSyst;
    private String            ecgtRate;

    private String            tipQrs;
    private String            tipRate;
    private String            tipRegular;
    private String            tipWave;
    private String            tipInterval;

    private MapExt            ext;

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

    public MapExt getExt() {
        if (this.ext == null) {
            this.ext = new MapExt();
        }

        return ext;
    }

    public void setExt(MapExt ext) {
        this.ext = ext;
    }

}
