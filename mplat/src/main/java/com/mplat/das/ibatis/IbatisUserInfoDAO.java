/**
 * obullxl@gmail.com
 */
package com.mplat.das.ibatis;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.mplat.das.daointerface.UserInfoDAO;// auto generated importsimport com.mplat.das.dataobject.UserInfoDO;import org.springframework.dao.DataAccessException;import java.util.List;import com.mplat.das.dataobject.UserInfoDO;/** * An ibatis based implementation of dao interface <tt>com.mplat.das.daointerface.UserInfoDAO</tt>. * *
 * @author obullxl@gmail.com
 */public class IbatisUserInfoDAO extends SqlMapClientDaoSupport implements UserInfoDAO {	/**
	 *  Insert one <tt>UserInfoDO</tt> object to DB table <tt>mplat_user_info</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into mplat_user_info(id,usr_name,usr_passwd) values (NULL, ?, ?)</tt>
	 *
	 *	@param userInfo
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public long insert(UserInfoDO userInfo) throws DataAccessException {
    	if (userInfo == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-USER-INFO-INSERT", userInfo);

        return userInfo.getId();
    }		/**
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
    public List<UserInfoDO> findAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-USER-INFO-FIND-ALL", null);

    }		/**
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
    public UserInfoDO findByName(String usrName) throws DataAccessException {

        return (UserInfoDO) getSqlMapClientTemplate().queryForObject("MS-USER-INFO-FIND-BY-NAME", usrName);

    }		/**
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
    public int update(UserInfoDO userInfo) throws DataAccessException {
    	if (userInfo == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-USER-INFO-UPDATE", userInfo);
    }		/**
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
    public int delete(String usrName) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-USER-INFO-DELETE", usrName);
    }}