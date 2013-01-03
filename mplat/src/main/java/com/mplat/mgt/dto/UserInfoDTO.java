/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.dto;

/**
 * @author obullxl@gmail.com
 */
public class UserInfoDTO extends BaseDTO {

    /**
     * column:id
     */
    private long id;
    /**
     * column:usr_name
     */
    private String usrName;
    /**
     * column:usr_passwd
     */
    private String usrPasswd;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPasswd() {
        return usrPasswd;
    }

    public void setUsrPasswd(String usrPasswd) {
        this.usrPasswd = usrPasswd;
    }
}
