/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.utils;

import mplat.mgt.dto.UserInfoDTO;

/**
 * 用户持有器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserqHolder.java, V1.0.1 2013-2-3 下午3:25:27 $
 */
public final class UserHolder {
    private static final ThreadLocal<UserInfoDTO> _holder = new ThreadLocal<UserInfoDTO>();

    public static void set(UserInfoDTO user) {
        _holder.set(user);
    }

    public static void remove() {
        _holder.remove();
    }

    public static UserInfoDTO get() {
        return _holder.get();
    }
    
}
