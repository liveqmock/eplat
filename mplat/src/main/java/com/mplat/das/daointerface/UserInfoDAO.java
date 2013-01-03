/**
 * obullxl@gmail.com
 */
package com.mplat.das.daointerface;

import com.mplat.das.dataobject.UserInfoDO;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>mplat_user_info</tt>.
 */
public interface UserInfoDAO extends BaseDAO {
	/**
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
    public UserInfoDO findAll() throws DataAccessException;

	/**
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
    public int updatePasswd(String usrName, String usrPasswd) throws DataAccessException;

}
