/**
 * obullxl@gmail.com
 */
package mplat.mgt;

import java.util.Arrays;
import java.util.List;

import mplat.mgt.dto.ExamInfoDTO;
import mplat.store.ExamStore;

import com.atom.core.xstream.store.StoreFactory;

/**
 * @author obullxl@gmail.com
 */
public final class ExamMgt {
    /** 试题选项 */
    private static final List<String> CHOICES = Arrays.asList("", "A", "B", "C", "D");
    
    /** 用户存储器 */
    private final ExamStore examStore;

    /**
     * 默认构造器
     */
    public ExamMgt() {
        this.examStore = StoreFactory.get().findStore(ExamStore.class);
    }

    /**
     * 获取试题选项
     */
    public static List<String> findExamChoices() {
        return CHOICES;
    }
    
    /**
     * 根据ID查找试题信息
     */
    public ExamInfoDTO find(long id) {
        return this.examStore.find(id);
    }

    /**
     * 创建新试题
     */
    public void create(ExamInfoDTO exam) {
        this.examStore.create(exam);
    }

    /**
     * 更新试题
     */
    public void update(ExamInfoDTO exam) {
        this.examStore.update(exam);
    }

    /**
     * 删除试题
     */
    public void remove(long id) {
        this.examStore.remove(id);
    }

    public void remove(ExamInfoDTO exam) {
        if (exam != null) {
            this.remove(exam.getId());
        }
    }

    /**
     * 查找所有试题
     */
    public List<ExamInfoDTO> findAll() {
        return this.examStore.findAll();
    }

}
