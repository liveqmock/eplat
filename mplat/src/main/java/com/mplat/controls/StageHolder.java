/**
 * obullxl@gmail.com
 */
package com.mplat.controls;

import javafx.stage.Stage;

/**
 * @author obullxl@gmail.com
 */
public class StageHolder {

    private static final ThreadLocal<Stage> _holder = new ThreadLocal<>();

    public static void set(Stage stage) {
        _holder.set(stage);
    }

    public static void remove() {
        _holder.remove();
    }

    public static Stage get() {
        return _holder.get();
    }

}
