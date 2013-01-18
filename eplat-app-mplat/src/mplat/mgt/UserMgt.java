/**
 * obullxl@gmail.com
 */
package mplat.mgt;

import com.atom.core.xstream.store.StoreFactory;
import mplat.mgt.dto.UserInfoDTO;
import mplat.store.UserStore;

/**
 * @author obullxl@gmail.com
 */
public class UserMgt {

    private static String A_NAME = "anonym";
    private static String A_PASSWD = "000000";
    private static String U_NAME = "admin";
    private static String U_PASSWD = "888888";

    private final UserStore userStore;

    public UserMgt() {
        this.userStore = StoreFactory.get().findStore(UserStore.class);

        UserInfoDTO admin = this.tryLogin(U_NAME, U_PASSWD);
        if (admin == null) {
            admin = new UserInfoDTO(U_NAME, U_PASSWD);
            boolean rtn = this.userStore.create(admin);
            if (!rtn) {
                throw new RuntimeException("UserMgt初始化管理员异常!");
            }
        }
    }

    public static UserInfoDTO toAnonyUser() {
        return new UserInfoDTO(A_NAME, A_PASSWD);
    }

    public UserInfoDTO tryLogin(String userName, String userPasswd) {
        return this.userStore.tryLogin(userName, userPasswd);
    }
}
