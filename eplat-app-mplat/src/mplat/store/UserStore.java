/**
 * obullxl@gmail.com
 */
package mplat.store;

import com.atom.core.xstream.store.BaseStore;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import mplat.mgt.dto.UserInfoDTO;
import mplat.utils.CfgUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author obullxl@gmail.com
 */
public class UserStore extends BaseStore<UserInfoDTO> {

    public UserStore() {
        String path = CfgUtils.findConfigPath() + "/store";
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

        super.setFilePath(path + "/UserMgt.data");
    }

    @Override
    public void initExt() {
        XStream xstream = findXStream();
        xstream.alias("User", UserInfoDTO.class);
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
