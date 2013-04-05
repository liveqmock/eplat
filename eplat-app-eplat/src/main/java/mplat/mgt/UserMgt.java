/**
 * obullxl@gmail.com
 */
package mplat.mgt;

import java.util.List;

import mplat.mgt.dto.UserInfoDTO;
import mplat.store.UserStore;
import mplat.utils.UserHolder;

import org.apache.commons.lang.StringUtils;

import com.atom.core.xstream.store.StoreFactory;

/**
 * @author obullxl@gmail.com
 */
public final class UserMgt {

    private static String   U_NAME   = "admin";
    private static String   U_PASSWD = "888888";

    private final UserStore userStore;

    public UserMgt() {
        this.userStore = StoreFactory.get().findStore(UserStore.class);

        UserInfoDTO admin = this.userStore.find(U_NAME);
        if (admin == null) {
            admin = new UserInfoDTO(U_NAME, U_PASSWD);
            boolean rtn = this.userStore.create(admin);
            if (!rtn) {
                throw new RuntimeException("UserMgt初始化管理员异常!");
            }
        }
    }

    public static boolean isSystemAdmin() {
        UserInfoDTO user = UserHolder.get();
        if (user == null) {
            return false;
        }

        return isSystemAdmin(user.getUserName());
    }

    public static boolean isSystemAdmin(String userName) {
        return StringUtils.equals(U_NAME, userName);
    }

    public static boolean isSystemAdmin(UserInfoDTO user) {
        if (user == null) {
            return false;
        }

        return isSystemAdmin(user.getUserName());
    }

    public UserInfoDTO find(String userName) {
        return this.userStore.find(userName);
    }

    public UserInfoDTO login(String userName, String userPasswd) {
        UserInfoDTO user = this.userStore.find(userName);
        if (user != null && StringUtils.equals(userPasswd, user.getUserPasswd())) {
            return user;
        }

        return null;
    }

    public void create(UserInfoDTO user) {
        this.userStore.create(user);
    }

    public void update(UserInfoDTO user) {
        this.userStore.update(user);
    }

    public void remove(UserInfoDTO user) {
        long id = user.getId();
        if (id > 0L) {
            this.userStore.remove(user.getId());
        } else {
            UserInfoDTO utmp = this.userStore.find(user.getUserName());
            if (utmp != null) {
                this.userStore.remove(utmp.getId());
            }
        }
    }

    public List<UserInfoDTO> findAll() {
        return this.userStore.findAll();
    }

}
