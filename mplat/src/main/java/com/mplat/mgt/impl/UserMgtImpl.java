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
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author obullxl@gmail.com
 */
@Component("userMgt")
public class UserMgtImpl implements UserMgt {

    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(UserMgt.class);
    
    private UserInfoDAO userInfoDAO;

    public long create(UserInfoDTO user) {
        try {
            UserInfoDO srcObj = UserConverter.convert(user);
            return this.userInfoDAO.insert(srcObj);
        } catch (Exception e) {
            logger.error("[用户]-增加用户异常, User[" + user + "].", e);
            return -1L;
        }
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
        try {
            UserInfoDO srcObj = UserConverter.convert(user);
            int count = this.userInfoDAO.update(srcObj);
            return (count > 0);
        } catch (Exception e) {
            logger.error("[用户]-更新用户异常, User[" + user + "].", e);
            return false;
        }
    }

    public boolean remove(String usrName) {
        try {
            int count = this.userInfoDAO.deleteByName(usrName);
            return (count > 0);
        } catch (Exception e) {
            logger.error("[用户]-删除用户异常, User[" + usrName + "].", e);
            return false;
        }
    }

    // ~~~~~~~ 依赖注入 ~~~~~~~~~ //
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }
}
