/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.mgt;

import com.mplat.mgt.dto.ExamInfoDTO;
import java.util.List;

/**
 * @author obullxl@gmail.com
 */
public interface ExamMgt {
    
    public long createExamInfo(ExamInfoDTO exam);
    
    public boolean updateExamInfo(ExamInfoDTO exam);
    
    public boolean removeExamInfo(long id);
    
    public ExamInfoDTO findExamInfo(long id);
    
    public List<ExamInfoDTO> findExamInfos();
    
}
