/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.atom.core.uijfx.UISize;

/**
 * GraphicsContext测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: GraphicsContextTest.java, V1.0.1 2013-2-4 下午1:39:49 $
 */
public class GraphicsContextTest extends Application {
    public static final UISize SIZE = UISize.to(400, 300);

    private static double      X    = 0.0;
    private static double      Y    = 0.0;

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
        // gc.fillRect(75, 75, 100, 100);
        gc.moveTo(0, 0);
        gc.setLineWidth(2); //设置线的宽度  
        gc.strokeLine(0, 0, X, Y);

        gc.save();

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

        Group root = new Group();
        root.getChildren().add(canvas);

        stage.setTitle("GraphicsContextTest");
        stage.setScene(new Scene(root, SIZE.getWidth(), SIZE.getHeight(), Color.WHITE));
        stage.show();
    }
}
