/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.CfgUtils;

/**
 * <link id=Main-File rel=Main-File href="../第一课.htm">
 * 
 * @author obullxl@gmail.com
 * @version $Id: PptValidationMain.java, V1.0.1 2013-4-4 下午9:14:35 $
 */
public class PptValidationMain {

    /**
     * 入口
     */
    public static void main(String[] args) {
        String path = CfgUtils.findConfigPath() + "/views/ppt";
        File root = new File(path);
        File[] dirs = root.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        for (File dir : dirs) {
            processDirectory(dir);
        }
    }

    private static void processDirectory(File dir) {
        File[] slides = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                String name = pathname.getName();
                if (StringUtils.startsWithIgnoreCase(name, "slide") && (StringUtils.endsWithIgnoreCase(name, ".htm") || StringUtils.endsWithIgnoreCase(name, ".html"))) {
                    return true;
                }

                return false;
            }
        });

        for (File slide : slides) {
            processPptFile(slide);
        }
    }

    private static void processPptFile(File file) {
        List<String> lines = null;
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            lines = IOUtils.readLines(input, "GBK");
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                lines.set(i, processLine(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }

        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            IOUtils.writeLines(lines, null, output, "GBK");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(output);
        }
    }

    private static String processLine(String line) {
        if (StringUtils.startsWithIgnoreCase(line, "<link id=Main-File rel=Main-File href=\"../第")) {
            if (StringUtils.endsWithIgnoreCase(line, "课.htm\">")) {
                return "<!--" + line + "-->";
            }
        }

        return line;
    }

}
