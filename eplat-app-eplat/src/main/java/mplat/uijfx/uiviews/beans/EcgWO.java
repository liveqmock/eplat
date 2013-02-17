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
 * @version $Id: EcgWO.java, V1.0.1 2013-2-16 下午9:49:37 $
 */
public final class EcgWO extends LongID {
    private static final long serialVersionUID = 2587557328956805920L;

    /**
     * 从EcgWO对象转化为EcgInfoDTO对象
     */
    public static EcgtInfoDTO to(EcgWO srcObj) {
        if (srcObj == null) {
            return null;
        }

        EcgtInfoDTO dstObj = new EcgtInfoDTO();
        dstObj.setId(srcObj.getId());

        return dstObj;
    }

    /**
     * 从EcgInfoDTO对象转化为EcgWO对象
     */
    public static EcgWO from(EcgtInfoDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        EcgWO dstObj = new EcgWO();
        dstObj.setId(srcObj.getId());

        return dstObj;
    }

    /**
     * 从EcgInfoDTO对象转化为EcgWO对象
     */
    public static List<EcgWO> from(List<EcgtInfoDTO> srcObjs) {
        List<EcgWO> dstObjs = new ArrayList<EcgWO>();

        if (srcObjs == null || srcObjs.isEmpty()) {
            return dstObjs;
        }

        for (EcgtInfoDTO srcObj : srcObjs) {
            dstObjs.add(from(srcObj));
        }

        return dstObjs;
    }

}
