/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.beans;

import java.util.ArrayList;
import java.util.List;

import mplat.mgt.dto.ExamInfoDTO;

import com.atom.core.lang.ids.LongID;

/**
 * 试题信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: ExamWO.java, V1.0.1 2013-2-15 下午5:42:50 $
 */
public final class ExamWO extends LongID {
    private static final long serialVersionUID = -568929709358118190L;

    private String            title;
    private String            rgtNo;
    private String            itemA;
    private String            itemB;
    private String            itemC;
    private String            itemD;

    /**
     * 从ExamWO对象转化为ExamInfoDTO对象
     */
    public static ExamInfoDTO to(ExamWO srcObj) {
        if (srcObj == null) {
            return null;
        }

        ExamInfoDTO dstObj = new ExamInfoDTO();
        dstObj.setId(srcObj.getId());
        dstObj.setTitle(srcObj.getTitle());
        dstObj.setRgtNo(srcObj.getRgtNo());
        dstObj.setItemA(srcObj.getItemA());
        dstObj.setItemB(srcObj.getItemB());
        dstObj.setItemC(srcObj.getItemC());
        dstObj.setItemD(srcObj.getItemD());

        return dstObj;
    }

    /**
     * 从ExamInfoDTO对象转化为ExamWO对象
     */
    public static ExamWO from(ExamInfoDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        ExamWO dstObj = new ExamWO();
        dstObj.setId(srcObj.getId());
        dstObj.setTitle(srcObj.getTitle());
        dstObj.setRgtNo(srcObj.getRgtNo());
        dstObj.setItemA(srcObj.getItemA());
        dstObj.setItemB(srcObj.getItemB());
        dstObj.setItemC(srcObj.getItemC());
        dstObj.setItemD(srcObj.getItemD());

        return dstObj;
    }

    public static List<ExamWO> from(List<ExamInfoDTO> srcObjs) {
        List<ExamWO> dstObjs = new ArrayList<ExamWO>();

        if (srcObjs == null || srcObjs.isEmpty()) {
            return dstObjs;
        }

        for (ExamInfoDTO srcObj : srcObjs) {
            ExamWO dstObj = from(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRgtNo() {
        return rgtNo;
    }

    public void setRgtNo(String rgtNo) {
        this.rgtNo = rgtNo;
    }

    public String getItemA() {
        return itemA;
    }

    public void setItemA(String itemA) {
        this.itemA = itemA;
    }

    public String getItemB() {
        return itemB;
    }

    public void setItemB(String itemB) {
        this.itemB = itemB;
    }

    public String getItemC() {
        return itemC;
    }

    public void setItemC(String itemC) {
        this.itemC = itemC;
    }

    public String getItemD() {
        return itemD;
    }

    public void setItemD(String itemD) {
        this.itemD = itemD;
    }

}
