/**
 * obullxl@gmail.com
 */
package mplat.store.impl;

import com.thoughtworks.xstream.XStream;
import mplat.mgt.dto.UserInfoDTO;
import org.apache.commons.lang.StringUtils;

/**
 * @author obullxl@gmail.com
 */
public class UserStore extends BaseStore<UserInfoDTO> {

    @Override
    public void initExt() {
        XStream xstream = getXStream();
        xstream.alias("User", UserInfoDTO.class);
    }

    @Override
    public String findDataName() {
        return "UserMgt.data";
    }

    public UserInfoDTO tryLogin(String userName, String userPasswd) {
        for(UserInfoDTO user : this.findAll()) {
            if(StringUtils.equals(userName, user.getUserName()) && StringUtils.equals(userPasswd, user.getUserPasswd())) {
                return user;
            }
        }

        return null;
    }

}
