/**
 * obullxl@gmail.com
 */
package mplat.store.impl;

import com.thoughtworks.xstream.XStream;
import mplat.mgt.dto.ExamInfoDTO;
import mplat.mgt.dto.ExamItemDTO;

/**
 * @author obullxl@gmail.com
 */
public class ExamStore extends BaseStore<ExamInfoDTO> {

    public void initExt() {
        XStream xstream = getXStream();
        xstream.alias("Exam", ExamInfoDTO.class);
        xstream.alias("Item", ExamItemDTO.class);
    }

    public String findDataName() {
        return "ExamMgt.data";
    }
}
