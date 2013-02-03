/**
 * obullxl@gmail.com
 */
package mplat.store;

import java.io.File;

import mplat.mgt.dto.UserInfoDTO;

import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.xstream.store.BaseStore;
import com.thoughtworks.xstream.XStream;

/**
 * @author obullxl@gmail.com
 */
public class UserStore extends BaseStore<UserInfoDTO> {

    public UserStore() {
        String path = CfgUtils.findConfigPath() + "/store";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        super.setFilePath(path + "/UserMgt.data");
    }

    @Override
    public void initExt() {
        XStream xstream = findXStream();
        xstream.alias("User", UserInfoDTO.class);
    }

    public UserInfoDTO find(String userName) {
        for (UserInfoDTO user : this.findAll()) {
            if (StringUtils.equals(userName, user.getUserName())) {
                return user;
            }
        }

        return null;
    }

}
