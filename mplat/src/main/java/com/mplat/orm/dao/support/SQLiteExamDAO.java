/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.orm.dao.support;

import com.mplat.jdbc.JdbcMgt;
import com.mplat.orm.dao.ExamDAO;
import com.mplat.orm.dto.ExamInfoDTO;
import com.mplat.orm.dto.ExamItemDTO;
import com.mplat.util.DBFields;
import com.mplat.util.JdbcUtils;
import com.mplat.util.LogUtils;
import com.mplat.util.SQLUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class SQLiteExamDAO extends ExamDAO {

    /**
     * JDBC管理器
     */
    private JdbcMgt jdbcMgt;

    /**
     * 查询所有的课程
     */
    public List<ExamInfoDTO> selectAll() {
        List<ExamInfoDTO> exams = new ArrayList<ExamInfoDTO>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = SQLUtils.selectExamInfoAllSQL();
            conn = this.jdbcMgt.fetchConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 课程信息
                ExamInfoDTO exam = new ExamInfoDTO();
                exam.setId(rs.getLong(DBFields.EXAM_INFO_ID));
                exam.setTitle(rs.getString(DBFields.EXAM_INFO_TITLE));
                exam.setRgtNo(rs.getString(DBFields.EXAM_INFO_RGT_NO));

                // 课程选项
                String tsql = SQLUtils.selectExamItemByExamSQL();
                PreparedStatement tstmt = null;
                ResultSet trs = null;
                try {
                    tstmt = conn.prepareStatement(tsql);
                    tstmt.setLong(1, exam.getId());
                    trs = tstmt.executeQuery();
                    while (trs.next()) {
                        ExamItemDTO item = new ExamItemDTO();
                        item.setId(rs.getLong(DBFields.EXAM_ITEM_ID));
                        item.setNo(rs.getString(DBFields.EXAM_ITEM_NO));
                        item.setExamId(rs.getLong(DBFields.EXAM_ITEM_EXAM_ID));
                        item.setText(rs.getString(DBFields.EXAM_ITEM_TEXT));

                        exam.getItems().add(item);
                    }
                } finally {
                    JdbcUtils.close(trs);
                    JdbcUtils.close(tstmt);
                }

                exams.add(exam);
            }
        } catch (Exception e) {
            LogUtils.error("查询所有用户异常.", e);
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(pstmt);
            this.jdbcMgt.freeConnection(conn);
        }

        return exams;
    }

    /**
     * 查询单个课程
     */
    public abstract ExamInfoDTO select(long examId);

    /**
     * 更新课程
     */
    public abstract boolean update(ExamInfoDTO exam);

    /**
     * 删除课程
     */
    public abstract boolean delete(ExamInfoDTO exam);

    public JdbcMgt getJdbcMgt() {
        return jdbcMgt;
    }

    public void setJdbcMgt(JdbcMgt jdbcMgt) {
        this.jdbcMgt = jdbcMgt;
    }
}
