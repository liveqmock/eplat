/**
 * obullxl@gmail.com
 */
package com.mplat.das.daointerface;

import com.mplat.das.dataobject.UserInfoDO;

import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * A dao interface provides methods to access database table <tt>mplat_user_info</tt>.
 */
public interface UserInfoDAO extends BaseDAO {
	/**
	 *  Insert one <tt>UserInfoDO</tt> object to DB table <tt>mplat_user_info</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into mplat_user_info(id,usr_name,usr_passwd) values (?, ?, ?)</tt>
	 *
	 *	@param userInfo
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public long insert(UserInfoDO userInfo) throws DataAccessException;

	/**
	 *  Query DB table <tt>mplat_user_info</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_user_info where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return List<UserInfoDO>
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public List<UserInfoDO> find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>mplat_user_info</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_user_info</tt>
	 *
	 *	@return List<UserInfoDO>
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public List<UserInfoDO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>mplat_user_info</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_user_info where (usr_name = ?)</tt>
	 *
	 *	@param usrName
	 *	@return UserInfoDO
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public UserInfoDO findByName(String usrName) throws DataAccessException;

	/**
	 *  Update DB table <tt>mplat_user_info</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update mplat_user_info set usr_passwd=? where (id = ?)</tt>
	 *
	 *	@param userInfo
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int update(UserInfoDO userInfo) throws DataAccessException;

	/**
	 *  Update DB table <tt>mplat_user_info</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update mplat_user_info set usr_passwd=? where (usr_name = ?)</tt>
	 *
	 *	@param userInfo
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int updateByName(UserInfoDO userInfo) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>mplat_user_info</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from mplat_user_info where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int delete(long id) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>mplat_user_info</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from mplat_user_info where (usr_name = ?)</tt>
	 *
	 *	@param usrName
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int deleteByName(String usrName) throws DataAccessException;

}
