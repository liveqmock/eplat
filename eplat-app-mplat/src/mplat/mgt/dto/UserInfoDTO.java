/**
 * obullxl@gmail.com
 */
package mplat.mgt.dto;

import com.atom.core.lang.ToString;
import com.atom.core.lang.ids.ID;
import com.atom.core.lang.user.UserInfo;

/**
 * @author obullxl@gmail.com
 */
public class UserInfoDTO extends ToString implements ID<Long> {

    private long id;
    private String userName;
    private String userPasswd;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String userName, String userPasswd) {
        this.userName = userName;
        this.userPasswd = userPasswd;
    }

    public UserInfo toUser() {
        UserInfo user = new UserInfo();
        user.setUserId(this.id);
        user.setUserName(this.userName);
        user.setUserPasswd(this.userPasswd);

        return user;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }
}
