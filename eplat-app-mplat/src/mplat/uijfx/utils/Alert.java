/**
 * obullxl@gmail.com
 */
package mplat.uijfx.utils;

import com.atom.core.uijfx.UIBtnMsg;
import com.atom.core.uijfx.UIConfig;
import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.UITipMsg;
import com.atom.core.uijfx.event.EventAdapter;
import com.atom.core.uijfx.utils.StageHolder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author obullxl@gmail.com
 */
public class Alert {

    private final Button btn;

    public Alert(final UIConfig cfg) {
        this.btn = new Button();

        final Stage stage = new Stage();
        //Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);

        //Set the owner of the Stage
        stage.initOwner(cfg.getStage(StageHolder.get()));
        stage.setTitle(cfg.getTipMsg(UITipMsg.to()).getTitle());

        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.hide();
                cfg.getAdapter(EventAdapter.get()).handle(event);
            }
        });

        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText(cfg.getBtnMsg(UIBtnMsg.get()).getSure());

        root.getChildren().add(btn);
        stage.setScene(scene);
        stage.show();
    }

    public static void alert() {
        new Alert(UIConfig.get().setSize(UISize.to(400, 300)).setTipMsg(UITipMsg.to("标题", "提示内容")).setBtnMsg(UIBtnMsg.get().setSure("我确定")));
    }
}
