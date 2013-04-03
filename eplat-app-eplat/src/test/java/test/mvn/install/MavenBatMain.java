/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mvn.install;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.ToString;

/**
 * Maven批处理命令工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: MavenBatMain.java, V1.0.1 2013-4-3 上午9:31:02 $
 */
public class MavenBatMain {
    private static File jfacelibs;

    /**
     * Entry point.
     */
    public static void main(String[] args) throws Throwable {
        File root = new File(".");
        jfacelibs = new File(root, "/docs/jfacelibs");
        File[] libs = jfacelibs.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return StringUtils.endsWithIgnoreCase(pathname.getName(), ".jar");
            }
        });

        Map<String, JARInfo> jars = new ConcurrentHashMap<String, JARInfo>();

        for (File lib : libs) {
            String fname = lib.getName();
            String name = StringUtils.substringBefore(fname, "_");
            String key = StringUtils.substringBefore(name, ".source");

            JARInfo jar = jars.get(key);
            if (jar == null) {
                jar = new JARInfo();
                jars.put(key, jar);
            }

            jar.name = key;
            jar.version = StringUtils.substringBetween(fname, "_", ".v");

            if (StringUtils.endsWith(name, "source")) {
                jar.source = fname;
            } else {
                jar.target = fname;
            }
        }

        for (JARInfo jar : jars.values()) {
            makeInstallFile(jar);
        }
        
        makeAllInOneFile(jars);
        
        makeDependencyFile(jars);
    }

    /**
     * 写mvn:install命令文件
     */
    private static void makeInstallFile(JARInfo jar) throws Throwable {
        StringBuilder txt = new StringBuilder();

        txt.append("mvn install:install-file");
        if (StringUtils.isNotBlank(jar.target)) {
            txt.append(" -Dfile=").append(jar.target);
        }
        if (StringUtils.isNotBlank(jar.source)) {
            txt.append(" -Dsources=").append(jar.source);
        }

        txt.append(" -DgroupId=").append(findGroupID(jar));
        txt.append(" -DartifactId=").append(findArtifactID(jar));
        txt.append(" -Dversion=").append(jar.version);
        txt.append(" -Dpackaging=jar -DgeneratePom=true");

        StringReader input = new StringReader(txt.toString());
        String file = FilenameUtils.normalizeNoEndSeparator(jfacelibs.getAbsolutePath()) + "/" + jar.batFileName();
        OutputStream output = new FileOutputStream(file);
        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 获取GorpuID
     */
    private static String findGroupID(JARInfo jar) {
        return "org.eclipse";
    }

    /**
     * 获取ArtifactID
     */
    private static String findArtifactID(JARInfo jar) {
        String aid = StringUtils.substringAfter(jar.name, "org.");
        aid = StringUtils.replace(aid, ".", "-");
        return aid;
    }

    /**
     * 写总执行命令文件
     */
    private static void makeAllInOneFile(Map<String, JARInfo> jars) throws Throwable {
        StringBuilder txt = new StringBuilder();

        txt.append("@echo off");
        for(JARInfo jar : jars.values()) {
            txt.append("\r\n").append("call ").append(jar.batFileName());
        }
        txt.append("\r\n");
        
        StringReader input = new StringReader(txt.toString());
        String file = FilenameUtils.normalizeNoEndSeparator(jfacelibs.getAbsolutePath()) + "/" + "all-安装JAR依赖.bat";
        OutputStream output = new FileOutputStream(file);
        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 写POM依赖文件
     */
    private static void makeDependencyFile(Map<String, JARInfo> jars) throws Throwable {
        StringBuilder txt = new StringBuilder();

        for(JARInfo jar : jars.values()) {
            txt.append("\r\n").append("<dependency>");
            txt.append("\r\n\t<groupId>").append(findGroupID(jar)).append("</groupId>");
            txt.append("\r\n\t<artifactId>").append(findArtifactID(jar)).append("</artifactId>");
            txt.append("\r\n\t<version>").append(jar.version).append("</version>");
            txt.append("\r\n").append("</dependency>");
        }
        txt.append("\r\n");
        
        System.out.println(txt.toString());
    }

    /**
     * JAR包信息
     */
    private static class JARInfo {
        public String name;
        public String version;
        public String source;
        public String target;

        public String toString() {
            return ToString.toString(this);
        }
        
        public String batFileName() {
            return this.name + "-MvnInstall.bat";
        }
    }

}
