/**
 * obullxl@gmail.com
 */
package mplat.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * @author obullxl@gmail.com
 */
public class UISize {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private double width;
    private double height;

    public UISize() {
    }

    public UISize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public static UISize toSize(double width, double height) {
        return new UISize(width, height);
    }

    public static UISize toDefault() {
        return toSize(WIDTH, HEIGHT);
    }

    public static UISize toMaxSize() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        return toSize(screen.getWidth() * 0.8, screen.getHeight() * 0.8);
    }

    public static UISize toScreenSize() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        return toSize(screen.getWidth(), screen.getHeight());
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
