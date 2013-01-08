/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.impl;

import com.mplat.das.daointerface.ExamInfoDAO;
import com.mplat.das.daointerface.ExamItemDAO;
import com.mplat.das.dataobject.ExamInfoDO;
import com.mplat.das.dataobject.ExamItemDO;
import com.mplat.mgt.ExamMgt;
import com.mplat.mgt.dto.ExamInfoDTO;
import com.mplat.mgt.dto.ExamItemDTO;
import com.mplat.mgt.utils.ExamConverter;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author obullxl@gmail.com
 */
@Component("examMgt")
public class ExamMgtImpl implements ExamMgt {

    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(ExamMgt.class);
    @Autowired
    private ExamInfoDAO examInfoDAO;
    @Autowired
    private ExamItemDAO examItemDAO;

    public long createExamInfo(ExamInfoDTO exam) {
        // 试题
        ExamInfoDO examObj = ExamConverter.convert(exam);
        long id = this.examInfoDAO.insert(examObj);
        exam.setId(id);

        // 选项
        for (ExamItemDTO item : exam.getItems()) {
            item.setExmId(id);
            ExamItemDO itemObj = ExamConverter.convert(item);
            long itmId = this.examItemDAO.insert(itemObj);
            item.setId(itmId);
        }

        return id;
    }

    public boolean updateExamInfo(ExamInfoDTO exam);

    public boolean removeExamInfo(long id) {
        try {
            // 删除选项
            this.examItemDAO.deleteByExam(id);

            // 删除试题
            this.examInfoDAO.delete(id);

            return true;
        } catch (Exception e) {
            logger.error("[用户]-删除试题异常, ID[" + id + "].", e);
            return false;
        }
    }

    public ExamInfoDTO findExamInfo(long id) {
        ExamInfoDO srcObj = this.examInfoDAO.find(id);
        ExamInfoDTO dstObj = ExamConverter.convert(srcObj);

        if (dstObj != null) {
            List<ExamItemDO> items = this.examItemDAO.findByExam(id);
            for (ExamItemDO item : items) {
                dstObj.getItems().add(ExamConverter.convert(item));
            }
        }

        return dstObj;
    }

    public List<ExamInfoDTO> findExamInfos();
}
