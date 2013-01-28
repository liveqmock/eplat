/**
 * obullxl@gmail.com
 */
package mplat.store;

import com.atom.core.xstream.store.BaseStore;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import mplat.mgt.dto.ExamInfoDTO;
import mplat.mgt.dto.ExamItemDTO;
import mplat.utils.CfgUtils;

/**
 * @author obullxl@gmail.com
 */
public class ExamStore extends BaseStore<ExamInfoDTO> {

    public ExamStore() {
        String path = CfgUtils.findConfigPath() + "/store";
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

        super.setFilePath(path + "/ExamMgt.data");
    }

    @Override
    public void initExt() {
        String path = CfgUtils.findConfigPath() + "/store";
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

        super.setFilePath(path + "/Exam.data");

        XStream xstream = findXStream();
        xstream.alias("Exam", ExamInfoDTO.class);
        xstream.alias("Item", ExamItemDTO.class);
    }

}
