/**
 * obullxl@gmail.com
 */
package com.mplat.das.ibatis;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.mplat.das.daointerface.UserInfoDAO;// auto generated importsimport com.mplat.das.dataobject.UserInfoDO;import org.springframework.dao.DataAccessException;import java.util.Map;import java.util.HashMap;/** * An ibatis based implementation of dao interface <tt>com.mplat.das.daointerface.UserInfoDAO</tt>. * *
 * @author obullxl@gmail.com
 */public class IbatisUserInfoDAO extends SqlMapClientDaoSupport implements UserInfoDAO {	/**
	 *  Query DB table <tt>mplat_user_info</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_user_info</tt>
	 *
	 *	@return UserInfoDO
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public UserInfoDO findAll() throws DataAccessException {

        return (UserInfoDO) getSqlMapClientTemplate().queryForObject("MS-USER-INFO-FIND-ALL", null);

    }		/**
	 *  Update DB table <tt>mplat_user_info</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update mplat_user_info set usr_passwd='usrPasswd' where (usr_name = 'usrName')</tt>
	 *
	 *	@param usrName
	 *	@param usrPasswd
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int updatePasswd(String usrName, String usrPasswd) throws DataAccessException {
        Map param = new HashMap();

        param.put("usrName", usrName);
        param.put("usrPasswd", usrPasswd);

        return getSqlMapClientTemplate().update("MS-USER-INFO-UPDATE-PASSWD", param);
    }	}