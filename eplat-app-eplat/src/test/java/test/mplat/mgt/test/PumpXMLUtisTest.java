/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.mgt.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.lang.xml.XMLNode;
import com.atom.core.lang.xml.XMLUtils;

/**
 * PumpEjectorCfg.xml
 * 
 * @author obullxl@gmail.com
 * @version $Id: PumpXMLUtisTest.java, V1.0.1 2013-2-25 下午10:25:45 $
 */
public class PumpXMLUtisTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        InputStream input = null;
        try {
            String path = CfgUtils.findConfigPath();
            String file = FilenameUtils.normalize(path + "/cfgs/PumpEjectorCfg.xml");
            input = new FileInputStream(file);

            XMLNode root = XMLUtils.toXMLNode(input);
            System.out.println(root);
        } catch (Exception e) {
            LogUtils.error("初始化注射泵/输液泵信息异常！", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

}
