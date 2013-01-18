/**
 * obullxl@gmail.com
 */
package mplat.utils;

import com.atom.core.lang.utils.LogUtils;
import java.io.File;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author obullxl@gmail.com
 */
public class CfgUtils {

    private static String ROOT_PATH = "";
    private static String CONFIG_PATH = "";

    public static final String findRootPath() {
        if (StringUtils.isBlank(ROOT_PATH)) {
            File root = new File(".");
            ROOT_PATH = FilenameUtils.normalizeNoEndSeparator(root.getAbsolutePath());
            LogUtils.info("系统根目录：" + ROOT_PATH);
        }

        return ROOT_PATH;
    }

    public static final String findConfigPath() {
        if (StringUtils.isBlank(CONFIG_PATH)) {
            File root = new File(".");

            // ./config
            File config = new File(root, "cfgs");
            if (config.exists() && config.isDirectory()) {
                CONFIG_PATH = FilenameUtils.normalizeNoEndSeparator(config.getAbsolutePath());
                LogUtils.info("系统配置目录：" + CONFIG_PATH);
            } else {
                // ../config
                config = new File(root, "../cfgs");
                if (config.exists() && config.isDirectory()) {
                    CONFIG_PATH = FilenameUtils.normalizeNoEndSeparator(config.getAbsolutePath());
                    LogUtils.info("系统配置目录：" + CONFIG_PATH);
                } else {
                    throw new RuntimeException("系统配置目录没有找到，请检查系统配置是否正确！");
                }
            }
        }

        return CONFIG_PATH;
    }
}
