/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.ToString;
import com.atom.core.lang.utils.LogUtils;

/**
 * PPT映射信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: PPTMapDTO.java, V1.0.1 2013-1-31 下午1:21:41 $
 */
public class PPTMapDTO extends ToString {
    private static final long  serialVersionUID = 3077484348081438869L;

    public static final String NAME             = "name";
    public static final String FEXT             = "fext";

    private String             name;
    private String             fext;
    private List<String>       files            = new ArrayList<String>();

    /**
     * CTOR
     */
    public PPTMapDTO(String path) {
        // 目录检查
        File fpath = new File(path);
        if (!fpath.exists() || !fpath.isDirectory()) {
            String msg = "初始化PPT映射规则失败，目前不存在或不是一个有效目录[" + path + "].";
            LogUtils.error(msg, new RuntimeException(msg));
            throw new RuntimeException(msg);
        }

        // 映射解析
        String file = FilenameUtils.normalize(path + "/tot.map");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line = br.readLine();
            while (line != null) {
                line = StringUtils.trimToEmpty(line);
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                if (StringUtils.startsWith(line, NAME)) {
                    this.name = StringUtils.substringAfterLast(line, "=");
                } else if (StringUtils.startsWith(line, FEXT)) {
                    this.fext = StringUtils.substringAfterLast(line, "=");
                }

                // 下一行
                line = br.readLine();
            }

            // 文件列表
            fpath.list(new FilenameFilter() {
                public boolean accept(File dir, String fname) {
                    filterPptFile(dir, fname);
                    return false;
                }
            });

            // 排序
            Collections.sort(this.files);

            LogUtils.info("PPT[" + path + "]共有文件[" + this.files.size() + "]个。");
        } catch (Exception e) {
            String msg = "初始化PPT映射规则失败[" + path + "].";
            LogUtils.error(msg, e);
            throw new RuntimeException(msg, e);
        } finally {
            IOUtils.closeQuietly(br);
        }
    }

    /**
     * 过滤PPT文件
     */
    private void filterPptFile(File dir, String fname) {
        if (StringUtils.startsWith(fname, this.name) && StringUtils.endsWith(fname, this.fext)) {
            this.files.add(fname);
        }
    }

    // ~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ //

    public String getName() {
        return name;
    }

    public String getFext() {
        return fext;
    }

    public List<String> getFiles() {
        return this.files;
    }

}
