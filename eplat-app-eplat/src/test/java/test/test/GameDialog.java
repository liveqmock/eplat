/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.util.Duration;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: GameDialog.java, V1.0.1 2013-3-23 下午3:51:06 $
 */
public class GameDialog extends Parent {
    private String          content      = "I was trying to get the text to wrap, but the text layout engine would keep putting a character on the previous line until the full word was visible.";
    
    private final double    BASE_WIDTH   = 800;
    private final double    BASE_HEIGHT  = 600;
    private final double    MARGIN       = 20;
    
    private final Timeline  timeline     = new Timeline();
    // 背景框
    private final Rectangle bgRect       = RectangleBuilder.create().x(0).y(0).width(BASE_WIDTH).height(BASE_HEIGHT).build();
    // 对话显示框
    private final Rectangle boundingRect = RectangleBuilder.create().x(MARGIN * 2).y(BASE_HEIGHT * 2 / 3 - MARGIN).width(BASE_WIDTH - MARGIN * 4).height(BASE_HEIGHT - (BASE_HEIGHT * 2 / 3))
                                             .fill(Color.rgb(255, 255, 255, 0.2)).stroke(Color.WHITE).strokeWidth(2).build();

    public GameDialog() {
        create();
    }

    public void create() {
        IntegerProperty charCount = new SimpleIntegerProperty();
        KeyFrame startFrame = new KeyFrame(Duration.ZERO, new KeyValue(charCount, 0));
        KeyFrame endFrame = new KeyFrame(Duration.seconds(3), new KeyValue(charCount, content.length()));
        timeline.getKeyFrames().addAll(startFrame, endFrame);
        bgRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                timeline.playFromStart();
            }
        });
        final Text displayedText = TextBuilder.create().x(boundingRect.getX() + MARGIN).y(boundingRect.getY() + MARGIN).wrappingWidth(boundingRect.getWidth() - MARGIN * 2).font(new Font(36))
            .textOrigin(VPos.TOP).fill(Color.WHITE).effect(new DropShadow()).build();
        charCount.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String textToDisplay = content.substring(0, newValue.intValue());
                displayedText.setText(textToDisplay);
            }
        });
        getChildren().addAll(bgRect, boundingRect, displayedText);
        
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
