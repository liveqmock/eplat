/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.impl;

import com.mplat.das.dataobject.ExamInfoDO;
import com.mplat.das.dataobject.ExamItemDO;
import com.mplat.mgt.ExamMgt;
import com.mplat.mgt.dto.ExamInfoDTO;
import com.mplat.mgt.dto.ExamItemDTO;
import com.mplat.mgt.utils.ExamConverter;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

/**
 * @author obullxl@gmail.com
 */
@Component("examMgt")
public class ExamMgtImpl extends BaseMgtImpl implements ExamMgt {

    /*
     public long createExamInfo(final ExamInfoDTO exam) {
     long id = -1;
     try {
     id = this.transactionTemplate.execute(new TransactionCallback<Long>() {
     public Long doInTransaction(TransactionStatus status) {
     // 试题
     ExamInfoDO examObj = ExamConverter.convert(exam);
     long exmId = examInfoDAO.insert(examObj);
     exam.setId(exmId);

     // 选项
     for (ExamItemDTO item : exam.getItems()) {
     item.setExmId(exmId);
     ExamItemDO itemObj = ExamConverter.convert(item);
     long itmId = examItemDAO.insert(itemObj);
     item.setId(itmId);
     }

     // 试题ID
     return exmId;
     }
     });
     } catch (Exception e) {
     logger.error("保存试题异常, ExamInfo[" + exam + "].", e);
     }

     return id;
     }
     */
    public long createExamInfo(final ExamInfoDTO exam) {
        long id = 1L;
        try {
            // 试题
            ExamInfoDO examObj = ExamConverter.convert(exam);
            long exmId = examInfoDAO.insert(examObj);
            exam.setId(exmId);

            // 选项
            for (ExamItemDTO item : exam.getItems()) {
                item.setExmId(exmId);
                ExamItemDO itemObj = ExamConverter.convert(item);
                long itmId = examItemDAO.insert(itemObj);
                item.setId(itmId);
            }

            // 试题ID
            id = exmId;
        } catch (Exception e) {
            logger.error("保存试题异常, ExamInfo[" + exam + "].", e);
        }

        return id;
    }

    public boolean updateExamInfo(final ExamInfoDTO exam) {
        boolean rtn = false;
        try {
            rtn = this.transactionTemplate.execute(new TransactionCallback<Boolean>() {
                public Boolean doInTransaction(TransactionStatus status) {
                    // 试题
                    ExamInfoDO examObj = ExamConverter.convert(exam);
                    examInfoDAO.update(examObj);

                    // 删除选项
                    examItemDAO.deleteByExam(exam.getId());

                    // 增加选项
                    for (ExamItemDTO item : exam.getItems()) {
                        ExamItemDO itemObj = ExamConverter.convert(item);
                        examItemDAO.insert(itemObj);
                    }

                    return true;
                }
            });
        } catch (Exception e) {
            logger.error("更新试题异常, ExamInfo[" + exam + "].", e);
        }

        return rtn;
    }

    public boolean removeExamInfo(final long id) {
        boolean rtn = false;
        try {
            rtn = this.transactionTemplate.execute(new TransactionCallback<Boolean>() {
                public Boolean doInTransaction(TransactionStatus status) {
                    // 删除选项
                    examItemDAO.deleteByExam(id);

                    // 删除试题
                    examInfoDAO.delete(id);

                    return true;
                }
            });
        } catch (Exception e) {
            logger.error("删除试题异常, ID[" + id + "].", e);
        }

        return rtn;
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

    public List<ExamInfoDTO> findExamInfos() {
        List<ExamInfoDO> srcObjs = this.examInfoDAO.findAll();

        List<ExamInfoDTO> dstObjs = ExamConverter.convert(srcObjs);
        for (ExamInfoDTO dstObj : dstObjs) {
            List<ExamItemDO> items = this.examItemDAO.findByExam(dstObj.getId());
            dstObj.setItems(ExamConverter.convertItems(items));
        }

        return dstObjs;
    }
}
