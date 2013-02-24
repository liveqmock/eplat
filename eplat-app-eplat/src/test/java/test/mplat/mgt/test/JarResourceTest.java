/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.mgt.test;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.atom.app.msgs.AbstractMSG;

/**
 * JAR包资源测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: JarResourceTest.java, V1.0.1 2013-2-24 上午11:29:52 $
 */
public class JarResourceTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        URL url = AbstractMSG.class.getResource("/com/atom/app/msgs/MsgConfig.xml");
        System.out.println(url);
        System.out.println("=================================");

        Package pkg = AbstractMSG.class.getPackage();
        System.out.println(pkg);

        String pack = pkg.getName();
        System.out.println(pack);
        pack = StringUtils.replaceChars(pack, ".", "/");
        pack = FilenameUtils.normalizeNoEndSeparator(pack);
        System.out.println(pack);
        pack = FilenameUtils.normalize("/" + pack + "/../fxmls/LoginAct.fxml", true);
        System.out.println(pack);
        System.out.println("=================================");

        InputStream input = null;
        try {
            input = AbstractMSG.class.getResourceAsStream("MsgConfig.xml");
            List<String> lines = IOUtils.readLines(input);
            for (String line : lines) {
                System.out.println(line);
            }
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

}
