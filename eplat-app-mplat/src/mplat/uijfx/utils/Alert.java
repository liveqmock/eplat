/**
 * obullxl@gmail.com
 */
package mplat.uijfx.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mplat.utils.DataMap;
import mplat.utils.StageHolder;

/**
 * @author obullxl@gmail.com
 */
public class Alert {

    private final Button btn;

    public Alert(final Stage stg) {
        this.btn = new Button();

        final Stage stage = new Stage();
        //Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);

        //Set the owner of the Stage
        stage.initOwner(stg);
        stage.setTitle("Top Stage With Modality");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.hide();
            }
        });

        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText(" 确定 ");

        root.getChildren().add(btn);
        stage.setScene(scene);
        stage.show();
    }

    public static void alert() {
        new Alert(StageHolder.get());
    }
}
