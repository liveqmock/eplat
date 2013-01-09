/**
 * obullxl@gmail.com
 */
package com.mplat.das.daointerface;

import com.mplat.das.dataobject.ExamInfoDO;

import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * A dao interface provides methods to access database table <tt>mplat_exam_info</tt>.
 */
public interface ExamInfoDAO extends BaseDAO {
	/**
	 *  Insert one <tt>ExamInfoDO</tt> object to DB table <tt>mplat_exam_info</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into mplat_exam_info(id,title,rgt_no) values (?, ?, ?)</tt>
	 *
	 *	@param examInfo
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public long insert(ExamInfoDO examInfo) throws DataAccessException;

	/**
	 *  Query DB table <tt>mplat_exam_info</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_exam_info where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return ExamInfoDO
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public ExamInfoDO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>mplat_exam_info</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_exam_info</tt>
	 *
	 *	@return List<ExamInfoDO>
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public List<ExamInfoDO> findAll() throws DataAccessException;

	/**
	 *  Update DB table <tt>mplat_exam_info</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update mplat_exam_info set title=?, rgt_no=? where (id = ?)</tt>
	 *
	 *	@param examInfo
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int update(ExamInfoDO examInfo) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>mplat_exam_info</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from mplat_exam_info where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int delete(long id) throws DataAccessException;

}
