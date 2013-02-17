/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import com.atom.core.lang.ToString;

/**
 * 系统参数设置信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: GsetInfoDTO.java, V1.0.1 2013-2-10 下午8:50:42 $
 */
public class GsetInfoDTO extends ToString {
    private static final long serialVersionUID = 3233112917389329951L;

    /** 心肺复苏 */

    // 最小/最大按压深度
    private int               minPressDepth;
    private int               maxPressDepth;

    // 最小/最大吹气量
    private int               minAirVolume;
    private int               maxAirVolume;

    // CPR操作模式
    private int               cprOperateMode;

    // 是否有操作节拍提示音
    private boolean           operateTipAudio;

    // 是否有操作提示音
    private boolean           operateAudio;

    /** CPR操作规则设置 */

    // 施救者类型（专业/非专业）
    private boolean           saverMode;

    // 胸外按压次数（非专业）
    private int               pressCount;

    // CPR操作循环数（专业）
    private int               cprRecycleCount;

    /** 虚拟监控器参数 */

    // 虚拟监控器名称
    private String            monitorName;

    // 心电图导联
    private boolean           joinHeartGraph;

    // 血氧探头
    private boolean           joinBloodGraph;

    /** 虚拟体征日志 */

    // 自动保存日志间隔
    private int               autoSaveSeconds;

    // ~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ //

    public int getMinPressDepth() {
        return minPressDepth;
    }

    public void setMinPressDepth(int minPressDepth) {
        this.minPressDepth = minPressDepth;
    }

    public int getMaxPressDepth() {
        return maxPressDepth;
    }

    public void setMaxPressDepth(int maxPressDepth) {
        this.maxPressDepth = maxPressDepth;
    }

    public int getMinAirVolume() {
        return minAirVolume;
    }

    public void setMinAirVolume(int minAirVolume) {
        this.minAirVolume = minAirVolume;
    }

    public int getMaxAirVolume() {
        return maxAirVolume;
    }

    public void setMaxAirVolume(int maxAirVolume) {
        this.maxAirVolume = maxAirVolume;
    }

    public int getCprOperateMode() {
        return cprOperateMode;
    }

    public void setCprOperateMode(int cprOperateMode) {
        this.cprOperateMode = cprOperateMode;
    }

    public boolean isOperateTipAudio() {
        return operateTipAudio;
    }

    public void setOperateTipAudio(boolean operateTipAudio) {
        this.operateTipAudio = operateTipAudio;
    }

    public boolean isOperateAudio() {
        return operateAudio;
    }

    public void setOperateAudio(boolean operateAudio) {
        this.operateAudio = operateAudio;
    }

    public boolean isSaverMode() {
        return saverMode;
    }

    public void setSaverMode(boolean saverMode) {
        this.saverMode = saverMode;
    }

    public int getPressCount() {
        return pressCount;
    }

    public void setPressCount(int pressCount) {
        this.pressCount = pressCount;
    }

    public int getCprRecycleCount() {
        return cprRecycleCount;
    }

    public void setCprRecycleCount(int cprRecycleCount) {
        this.cprRecycleCount = cprRecycleCount;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public boolean isJoinHeartGraph() {
        return joinHeartGraph;
    }

    public void setJoinHeartGraph(boolean joinHeartGraph) {
        this.joinHeartGraph = joinHeartGraph;
    }

    public boolean isJoinBloodGraph() {
        return joinBloodGraph;
    }

    public void setJoinBloodGraph(boolean joinBloodGraph) {
        this.joinBloodGraph = joinBloodGraph;
    }

    public int getAutoSaveSeconds() {
        return autoSaveSeconds;
    }

    public void setAutoSaveSeconds(int autoSaveSeconds) {
        this.autoSaveSeconds = autoSaveSeconds;
    }

}
