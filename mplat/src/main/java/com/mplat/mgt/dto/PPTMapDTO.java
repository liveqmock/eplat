/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.mplat.mgt.dto;

import com.atom.core.lang.ToString;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author Kitty
 */
public class PPTMapDTO extends ToString {

    public static final String NAME = "name";
    public static final String FEXT = "fext";
    private String name;
    private String fext;

    public PPTMapDTO(String file) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(br);
        }
    }

    public String getName() {
        return name;
    }

    public String getFext() {
        return fext;
    }
}
