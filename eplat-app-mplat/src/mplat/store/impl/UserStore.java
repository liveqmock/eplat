/**
 * obullxl@gmail.com
 */
package mplat.store.impl;

import com.thoughtworks.xstream.XStream;
import mplat.mgt.dto.UserInfoDTO;

/**
 * @author obullxl@gmail.com
 */
public class UserStore extends BaseStore<UserInfoDTO> {

    public void initExt() {
        XStream xstream = getXStream();
        xstream.alias("User", UserInfoDTO.class);
    }

    public String findDataName() {
        return "UserMgt.data";
    }
}
