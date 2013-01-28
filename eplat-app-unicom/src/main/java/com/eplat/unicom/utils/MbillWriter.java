/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eplat.unicom.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author shizihu
 */
public class MbillWriter {

    private final Writer differentWriter;

    public MbillWriter(String path) throws Exception {
        path = FilenameUtils.normalizeNoEndSeparator(path);
        this.differentWriter = new FileWriter(path + "/" + "差异明细.txt");
    }
    
    public void writeTitle(String txt) throws Exception {
        IOUtils.write(txt, this.differentWriter);
    }

    public void writeDifferent(String txt) throws Exception {
        IOUtils.write(txt, this.differentWriter);
    }

    public void finish() {
        IOUtils.closeQuietly(this.differentWriter);
    }
}
