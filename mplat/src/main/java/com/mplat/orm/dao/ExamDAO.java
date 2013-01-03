/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.orm.dao;

import com.mplat.jdbc.JdbcMgt;
import com.mplat.orm.dto.ExamInfoDTO;
import java.util.List;

/**
 *
 * @author Administrator
 */
public abstract class ExamDAO {
    /**
     * JDBC管理器
     */
    public abstract JdbcMgt getJdbcMgt();
    
    /**
     * 查询所有的课程
     */
    public abstract List<ExamInfoDTO> selectAll();
    
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
    
}
