/**
 * obullxl@gmail.com
 */
package com.mplat.das.ibatis;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.mplat.das.daointerface.ExamInfoDAO;// auto generated importsimport com.mplat.das.dataobject.ExamInfoDO;import org.springframework.dao.DataAccessException;import java.util.List;import com.mplat.das.dataobject.ExamInfoDO;import com.atom.core.ticket.MutexTicket;import com.atom.core.ticket.TicketException;/** * An ibatis based implementation of dao interface <tt>com.mplat.das.daointerface.ExamInfoDAO</tt>. *
 * @author obullxl@gmail.com
 */public class IbatisExamInfoDAO extends SqlMapClientDaoSupport implements ExamInfoDAO {	/** TicketID */	private MutexTicket mutexTicket;		public void setMutexTicket(MutexTicket mutexTicket) {		this.mutexTicket = mutexTicket;	}	/**
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
    public long insert(ExamInfoDO examInfo) throws DataAccessException {
    	if (examInfo == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        try {
            examInfo.setId(this.mutexTicket.nextValue());
        } catch (TicketException e) {
            throw new RuntimeException("Set TicketID exception.", e);
        }

        getSqlMapClientTemplate().insert("MS-EXAM-INFO-INSERT", examInfo);

        return examInfo.getId();
    }		/**
	 *  Query DB table <tt>mplat_exam_info</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from mplat_exam_info where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return List<ExamInfoDO>
	 *	@throws DataAccessException
	 */	 
    @SuppressWarnings("unchecked")
    public List<ExamInfoDO> find(long id) throws DataAccessException {
        Long param = new Long(id);

        return getSqlMapClientTemplate().queryForList("MS-EXAM-INFO-FIND", param);

    }		/**
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
    public List<ExamInfoDO> findAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-EXAM-INFO-FIND-ALL", null);

    }		/**
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
    public int update(ExamInfoDO examInfo) throws DataAccessException {
    	if (examInfo == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-EXAM-INFO-UPDATE", examInfo);
    }		/**
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
    public int delete(long id) throws DataAccessException {
        Long param = new Long(id);

        return getSqlMapClientTemplate().delete("MS-EXAM-INFO-DELETE", param);
    }}