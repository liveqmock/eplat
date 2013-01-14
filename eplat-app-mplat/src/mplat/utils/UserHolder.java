/**
 * obullxl@gmail.com
 */
package mplat.utils;

import mplat.mgt.UserMgt;
import mplat.mgt.dto.UserInfoDTO;

/**
 * @author obullxl@gmail.com
 */
public class UserHolder {

    private static final ThreadLocal<UserInfoDTO> _holder = new ThreadLocal<>();

    public static void set(UserInfoDTO user) {
        _holder.set(user);
    }

    public static void remove() {
        _holder.remove();
    }

    public static UserInfoDTO get() {
        UserInfoDTO user = _holder.get();
        if(user == null) {
            _holder.set(UserMgt.toAnonyUser());
        }

        return _holder.get();
    }
}
