/**
 * obullxl@gmail.com
 */
package mplat.mgt.dto;

import com.atom.core.lang.ToString;

/**
 * @author obullxl@gmail.com
 */
public class UserInfoDTO extends ToString implements ID {

    private long id;
    private String userName;
    private String userPasswd;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String userName, String userPasswd) {
        this.userName = userName;
        this.userPasswd = userPasswd;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
