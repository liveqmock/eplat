/**
 * obullxl@gmail.com
 */
package com.mplat.das.ibatis;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.mplat.das.daointerface.ExamItemDAO;// auto generated importsimport com.mplat.das.dataobject.ExamItemDO;import org.springframework.dao.DataAccessException;import java.util.List;import com.mplat.das.dataobject.ExamItemDO;import com.atom.core.ticket.MutexTicket;import com.atom.core.ticket.TicketException;/** * An ibatis based implementation of dao interface <tt>com.mplat.das.daointerface.ExamItemDAO</tt>. *
 * @author obullxl@gmail.com
 */public class IbatisExamItemDAO extends SqlMapClientDaoSupport implements ExamItemDAO {	/** TicketID */	private MutexTicket mutexTicket;		public void setMutexTicket(MutexTicket mutexTicket) {		this.mutexTicket = mutexTicket;	}	/**
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
    public long insert(ExamItemDO examItem) throws DataAccessException {
    	if (examItem == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        try {
            examItem.setId(this.mutexTicket.nextValue());
        } catch (TicketException e) {
            throw new RuntimeException("Set TicketID exception.", e);
        }

        getSqlMapClientTemplate().insert("MS-EXAM-ITEM-INSERT", examItem);

        return examItem.getId();
    }		/**
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
    public ExamItemDO find(long id) throws DataAccessException {
        Long param = new Long(id);

        return (ExamItemDO) getSqlMapClientTemplate().queryForObject("MS-EXAM-ITEM-FIND", param);

    }		/**
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
    public List<ExamItemDO> findAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-EXAM-ITEM-FIND-ALL", null);

    }		/**
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
    public List<ExamItemDO> findByExam(long exmId) throws DataAccessException {
        Long param = new Long(exmId);

        return getSqlMapClientTemplate().queryForList("MS-EXAM-ITEM-FIND-BY-EXAM", param);

    }		/**
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
    public int update(ExamItemDO examItem) throws DataAccessException {
    	if (examItem == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-EXAM-ITEM-UPDATE", examItem);
    }		/**
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
    public int delete(long id) throws DataAccessException {
        Long param = new Long(id);

        return getSqlMapClientTemplate().delete("MS-EXAM-ITEM-DELETE", param);
    }	/**
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
    public int deleteByExam(long exmId) throws DataAccessException {
        Long param = new Long(exmId);

        return getSqlMapClientTemplate().delete("MS-EXAM-ITEM-DELETE-BY-EXAM", param);
    }}