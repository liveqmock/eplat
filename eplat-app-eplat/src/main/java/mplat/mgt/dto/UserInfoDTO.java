/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ids.LongID;

/**
 * 系统用户信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserInfoDTO.java, V1.0.1 2013-2-10 下午8:51:13 $
 */
public class UserInfoDTO  extends LongID {
    private static final long serialVersionUID = 6316843770631615617L;

    private String            userName;
    private String            userPasswd;
    
    private MapExt            ext;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String userName, String userPasswd) {
        this.userName = userName;
        this.userPasswd = userPasswd;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

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

    public MapExt getExt() {
        if (this.ext == null) {
            this.ext = new MapExt();
        }

        return ext;
    }

    public void setExt(MapExt ext) {
        this.ext = ext;
    }

}
