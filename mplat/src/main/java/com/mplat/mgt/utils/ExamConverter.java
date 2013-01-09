/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.utils;

import com.mplat.das.dataobject.ExamInfoDO;
import com.mplat.das.dataobject.ExamItemDO;
import com.mplat.mgt.dto.ExamInfoDTO;
import com.mplat.mgt.dto.ExamItemDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 * @author obullxl@gmail.com
 */
public class ExamConverter {

    public static ExamInfoDTO convert(ExamInfoDO srcObj) {
        if (srcObj == null) {
            return null;
        }

        ExamInfoDTO dstObj = new ExamInfoDTO();
        BeanUtils.copyProperties(srcObj, dstObj);

        return dstObj;
    }
    
    public static List<ExamInfoDTO> convert(List<ExamInfoDO> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<ExamInfoDTO> dstObjs = new ArrayList<ExamInfoDTO>();
        for(ExamInfoDO srcObj : srcObjs) {
            dstObjs.add(convert(srcObj));
        }

        return dstObjs;
    }

    public static ExamInfoDO convert(ExamInfoDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        ExamInfoDO dstObj = new ExamInfoDO();
        BeanUtils.copyProperties(srcObj, dstObj);

        return dstObj;
    }

    public static ExamItemDTO convert(ExamItemDO srcObj) {
        if (srcObj == null) {
            return null;
        }

        ExamItemDTO dstObj = new ExamItemDTO();
        BeanUtils.copyProperties(srcObj, dstObj);

        return dstObj;
    }
    
    public static List<ExamItemDTO> convertItems(List<ExamItemDO> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<ExamItemDTO> dstObjs = new ArrayList<ExamItemDTO>();
        for(ExamItemDO srcObj : srcObjs) {
            dstObjs.add(convert(srcObj));
        }

        return dstObjs;
    }

    public static ExamItemDO convert(ExamItemDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        ExamItemDO dstObj = new ExamItemDO();
        BeanUtils.copyProperties(srcObj, dstObj);

        return dstObj;
    }
    
}
