/**
 * obullxl@gmail.com
 */
package mplat.mgt;

import mplat.mgt.dto.UserInfoDTO;
import mplat.store.StoreFactory;
import mplat.store.impl.UserStore;

/**
 * @author obullxl@gmail.com
 */
public class UserMgt {

    private static String A_NAME = "anonym";
    private static String A_PASSWD = "000000";
    private static String U_NAME = "admin";
    private static String U_PASSWD = "888888";

    public UserMgt() {
        UserInfoDTO admin = this.tryLogin(U_NAME, U_PASSWD);
        if (admin == null) {
            admin = new UserInfoDTO(U_NAME, U_PASSWD);
            boolean rtn = StoreFactory.get().getUserStore().create(admin);
            if (!rtn) {
                throw new RuntimeException("UserMgt初始化管理员异常!");
            }
        }
    }

    public static UserInfoDTO toAnonyUser() {
        return new UserInfoDTO(A_NAME, A_PASSWD);
    }

    public UserInfoDTO tryLogin(String userName, String userPasswd) {
        UserStore store = StoreFactory.get().getUserStore();
        return store.tryLogin(userName, userPasswd);
    }
}
