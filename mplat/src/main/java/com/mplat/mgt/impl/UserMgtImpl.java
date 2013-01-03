/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.impl;

import com.mplat.das.daointerface.UserInfoDAO;
import com.mplat.das.dataobject.UserInfoDO;
import com.mplat.mgt.UserMgt;
import com.mplat.mgt.dto.UserInfoDTO;
import com.mplat.mgt.utils.UserConverter;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author obullxl@gmail.com
 */
@Component("userMgt")
public class UserMgtImpl implements UserMgt {

    private UserInfoDAO userInfoDAO;

    public long create(UserInfoDTO user) {
        UserInfoDO srcObj = UserConverter.convert(user);
        return this.userInfoDAO.insert(srcObj);
    }

    public List<UserInfoDTO> findAll() {
        List<UserInfoDO> srcObjs = this.userInfoDAO.findAll();
        return UserConverter.convert(srcObjs);
    }

    public UserInfoDTO findByName(String usrName) {
        UserInfoDO srcObj = this.userInfoDAO.findByName(usrName);
        return UserConverter.convert(srcObj);
    }

    public boolean update(UserInfoDTO user) {
        UserInfoDO srcObj = UserConverter.convert(user);
        int count = this.userInfoDAO.update(srcObj);
        return (count > 0);
    }

    public boolean remove(String usrName) {
        int count = this.userInfoDAO.delete(usrName);
        return (count > 0);
    }

    // ~~~~~~~ 依赖注入 ~~~~~~~~~ //
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }
}
