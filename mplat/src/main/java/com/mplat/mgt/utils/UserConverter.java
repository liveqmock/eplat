/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.utils;

import com.mplat.das.dataobject.UserInfoDO;
import com.mplat.mgt.dto.UserInfoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 * @author obullxl@gmail.com
 */
public class UserConverter {

    public static UserInfoDTO convert(UserInfoDO srcObj) {
        if (srcObj == null) {
            return null;
        }

        UserInfoDTO dstObj = new UserInfoDTO();
        BeanUtils.copyProperties(srcObj, dstObj);

        return dstObj;
    }

    public static List<UserInfoDTO> convert(List<UserInfoDO> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<UserInfoDTO> dstObjs = new ArrayList<UserInfoDTO>();
        for (UserInfoDO srcObj : srcObjs) {
            UserInfoDTO dstObj = convert(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    public static UserInfoDO convert(UserInfoDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        UserInfoDO dstObj = new UserInfoDO();
        BeanUtils.copyProperties(srcObj, dstObj);

        return dstObj;
    }
}
