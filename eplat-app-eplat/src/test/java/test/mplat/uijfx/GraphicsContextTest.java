/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.apache.commons.lang.math.RandomUtils;

import com.atom.core.uijfx.UISize;

/**
 * GraphicsContext测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: GraphicsContextTest.java, V1.0.1 2013-2-4 下午1:39:49 $
 */
public class GraphicsContextTest extends Application {
    public static final UISize SIZE = UISize.to(600, 500);

    private static double      X    = 0.0;
    private static double      Y    = SIZE.getHeight() / 2;

    private static boolean     loop = true;

    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(final Stage stage) throws Exception {
        Canvas canvas = new Canvas(SIZE.getWidth(), SIZE.getHeight());
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.moveTo(0, SIZE.getHeight() / 2);
        gc.setLineWidth(2); //设置线的宽度  
        gc.strokeLine(0, SIZE.getHeight() / 2, SIZE.getWidth(), SIZE.getHeight() / 2);

        gc.setLineWidth(1);
        gc.save();

        Thread timer = new Thread() {
            public void run() {
                try {
                    while (loop) {
                        double tmpX = X;
                        double tmpY = Y;

                        double newX = X + 20;
                        double newY = (RandomUtils.nextDouble() * 10 + (SIZE.getHeight() / 2)) * (((RandomUtils.nextDouble() * 10) > 4.5) ? 1 : -1);

                        gc.strokeLine(tmpX, tmpY, newX, newY);

                        if (newX > SIZE.getWidth() || newY > SIZE.getHeight()) {
                            newX = 0.0;
                            newY = SIZE.getHeight() / 2;

                            gc.clearRect(0, 0, SIZE.getWidth(), SIZE.getHeight());
                            gc.strokeLine(0, SIZE.getHeight() / 2, SIZE.getWidth(), SIZE.getHeight() / 2);
                        }

                        X = newX;
                        Y = newY;

                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

        /*
        canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                double x = evt.getX();
                double y = evt.getY();
                stage.setTitle("GraphicsContextTest- [" + x + ", " + y + "]");

                gc.strokeLine(X, Y, x, y);

                X = x;
                Y = y;
            }
        });
        */

        Group root = new Group();
        root.getChildren().add(canvas);

        stage.setTitle("GraphicsContextTest");
        stage.setScene(new Scene(root, SIZE.getWidth(), SIZE.getHeight(), Color.WHITE));
        stage.show();
    }

    public void stop() {
        loop = false;
    }

}
