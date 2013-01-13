/**
 * obullxl@gmail.com
 */
package com.mplat.controls;

/**
 * @author obullxl@gmail.com
 */
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalDialog {

    private Button btn;

    public ModalDialog(final Stage stg) {
        btn = new Button();

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
                DataMap.fetch().put("KEY", "Value");
                stage.hide();
                System.out.println("设值：" + DataMap.fetch());
            }
        });

        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("OK");

        root.getChildren().add(btn);
        stage.setScene(scene);
        stage.show();
    }
}
