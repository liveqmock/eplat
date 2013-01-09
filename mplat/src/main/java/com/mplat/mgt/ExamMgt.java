/**
 * obullxl@gmail.com
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
