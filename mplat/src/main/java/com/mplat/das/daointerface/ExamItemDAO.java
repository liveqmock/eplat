/**
 * obullxl@gmail.com
 */
package com.mplat.das.daointerface;

import com.mplat.das.dataobject.ExamItemDO;

import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * A dao interface provides methods to access database table <tt>mplat_exam_item</tt>.
 */
public interface ExamItemDAO extends BaseDAO {
	/**
	 *  Insert one <tt>ExamItemDO</tt> object to DB table <tt>mplat_exam_item</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into mplat_exam_item(id,no,exm_id,text) values (?, ?, ?, ?)</tt>
	 *
	 *	@param examItem
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public long insert(ExamItemDO examItem) throws DataAccessException;

	/**
	 *  Query DB table <tt>mplat_exam_item</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_exam_item where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return ExamItemDO
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public ExamItemDO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>mplat_exam_item</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_exam_item</tt>
	 *
	 *	@return List<ExamItemDO>
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public List<ExamItemDO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>mplat_exam_item</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_exam_item where (exm_id = ?)</tt>
	 *
	 *	@param exmId
	 *	@return List<ExamItemDO>
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public List<ExamItemDO> findByExam(long exmId) throws DataAccessException;

	/**
	 *  Update DB table <tt>mplat_exam_item</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update mplat_exam_item set no=?, exm_id=?, text=? where (id = ?)</tt>
	 *
	 *	@param examItem
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int update(ExamItemDO examItem) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>mplat_exam_item</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from mplat_exam_item where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int delete(long id) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>mplat_exam_item</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from mplat_exam_item where (exm_id = ?)</tt>
	 *
	 *	@param exmId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public int deleteByExam(long exmId) throws DataAccessException;

}
