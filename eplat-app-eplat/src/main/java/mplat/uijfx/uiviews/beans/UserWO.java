/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.beans;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mplat.mgt.UserMgt;
import mplat.mgt.dto.UserInfoDTO;
import mplat.uijfx.images.IMGS;

import com.atom.core.lang.ids.LongID;

/**
 * 用户信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserWO.java, V1.0.1 2013-2-15 下午1:03:58 $
 */
public final class UserWO extends LongID {
    private static final long serialVersionUID = -5359803569157411958L;

    private String            userName;
    private String            userPasswd;
    private ImageView         userIcon;

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

    /**
     * 默认情况下，只有非管理员才进行图片设置
     */
    public ImageView getUserIcon() {
        if (this.userIcon == null) {
            if (UserMgt.isSystemAdmin(this.userName)) {
                Image image = new Image(IMGS.class.getResourceAsStream("icon-admin.png"), 20, 20, false, false);
                this.userIcon = new ImageView(image);
            }
        }

        return this.userIcon;
    }

    public void setUserIcon(ImageView userIcon) {
        this.userIcon = userIcon;
    }

    /**
     * 从UserWO转化为UserInfoDTO对象
     */
    public static final UserInfoDTO to(UserWO srcObj) {
        if (srcObj == null) {
            return null;
        }

        UserInfoDTO dstObj = new UserInfoDTO();
        dstObj.setId(srcObj.getId());
        dstObj.setUserName(srcObj.getUserName());
        dstObj.setUserPasswd(srcObj.getUserPasswd());

        return dstObj;
    }

    /**
     * 从UserInfoDTO转化为UserWO对象
     */
    public static final UserWO from(UserInfoDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        UserWO dstObj = new UserWO();
        dstObj.setId(srcObj.getId());
        dstObj.setUserName(srcObj.getUserName());
        dstObj.setUserPasswd(srcObj.getUserPasswd());

        return dstObj;
    }

    public static final List<UserWO> from(List<UserInfoDTO> srcObjs) {
        List<UserWO> dstObjs = new ArrayList<UserWO>();

        if (srcObjs == null || srcObjs.isEmpty()) {
            return dstObjs;
        }

        for (UserInfoDTO srcObj : srcObjs) {
            UserWO dstObj = from(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

}
