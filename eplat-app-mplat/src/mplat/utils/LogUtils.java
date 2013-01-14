/**
 * obullxl@gmail.com
 */
package mplat.utils;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author obullxl@gmail.com
 */
public class LogUtils {

    private static final PrintStream out = System.out;
    private static final PrintStream err = System.err;
    private static final Logger logger = Logger.getLogger("MplatLog");

    public static void info(String msg) {
        out.println(msg);

        logger.log(Level.INFO, msg);
    }

    public static void warn(String msg) {
        err.println(msg);

        logger.log(Level.WARNING, msg);
    }

    public static void warn(String msg, Throwable e) {
        err.println(msg);
        e.printStackTrace();

        logger.log(Level.WARNING, msg, e);
    }

    public static void error(String msg, Throwable e) {
        err.println(msg);
        e.printStackTrace();

        logger.log(Level.SEVERE, msg, e);
    }
}
