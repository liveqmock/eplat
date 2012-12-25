/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.util;

import java.io.PrintStream;
import org.apache.log4j.Logger;

/**
 *
 * @author shizihu
 */
public class LogUtils {

    private static final Logger logger = Logger.getLogger("COMMON-LOGGER");
    private static final PrintStream out = System.out;
    private static final PrintStream err = System.err;

    /**
     * INFO
     */
    public static final void info(String text) {
        if (logger.isInfoEnabled()) {
            logger.info(text);
        }
        out.println(text);
    }

    /**
     * ERROR
     */
    public static final void error(String text) {
        logger.error(text);
        err.println(text);
    }

    /**
     * ERROR
     */
    public static final void error(String text, Throwable e) {
        logger.error(text, e);

        err.println(text);
        e.printStackTrace(err);
    }
}
