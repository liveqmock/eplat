/**
 * obullxl@gmail.com
 */
package mplat.store;

import java.io.File;

import mplat.mgt.dto.ExamInfoDTO;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.xstream.store.BaseStore;
import com.thoughtworks.xstream.XStream;

/**
 * @author obullxl@gmail.com
 */
public class ExamStore extends BaseStore<ExamInfoDTO> {

    public ExamStore() {
        String path = CfgUtils.findConfigPath() + "/store";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        super.setFilePath(path + "/ExamMgt.data");
    }

    @Override
    public void initExt() {
        XStream xstream = findXStream();
        xstream.alias("Exam", ExamInfoDTO.class);
        xstream.alias("MapExt", MapExt.class);
    }

}
