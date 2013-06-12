/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.eplat;

import org.apache.commons.io.FilenameUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.xml.XMLNode;
import com.atom.core.lang.xml.XMLUtils;

/**
 * SystemScript
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemScriptTest.java, V1.0.1 2013-6-12 下午3:51:51 $
 */
public class SystemScriptTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String croot = CfgUtils.findConfigPath();
        String file = FilenameUtils.normalize(croot + "/spts/不稳定性心动过速.acls");
        XMLNode xroot = XMLUtils.toXMLNode(file);
        System.out.println(xroot);
    }

}
