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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.lang.StringUtils;

/**
 * @author obullxl@gmail.com
 */
public class Alert {

    public Alert(final UIConfig cfg) {
        final Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(cfg.getStage(StageHolder.get()));
        stage.setTitle(cfg.getTipMsg(UITipMsg.to()).getTitle());

        Parent root = this.initView(cfg, stage);
        UISize size = cfg.getSize(UISize.toDefault());
        Scene scene = new Scene(root, size.getWidth(), size.getHeight(), Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }

    private Parent initView(final UIConfig cfg, final Stage stage) {
        BorderPane border = new BorderPane();

        // 窗口关闭事件
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                cfg.getAdapter(EventAdapter.get()).onHidden(event);
            }
        });

        // 中间区域
        Label center = new Label();
        center.setWrapText(true);
        center.setFont(new Font(14D));
        center.setText(cfg.getTipMsg(UITipMsg.to()).getMessage());
        border.setCenter(center);

        // 底部区域
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setPadding(new Insets(15, 12, 15, 12));
        buttons.setSpacing(10);
        buttons.setStyle("-fx-background-color: #336699;");

        UIBtnMsg btnCfg = cfg.getBtnMsg(UIBtnMsg.get());
        if (StringUtils.isNotBlank(btnCfg.getSure())) {
            Button btnSure = new Button(btnCfg.getSure());
            buttons.getChildren().add(btnSure);

            btnSure.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    stage.hide();
                    cfg.getAdapter(EventAdapter.get()).onSure(event);
                }
            });
        }

        if (StringUtils.isNotBlank(btnCfg.getCancel())) {
            Button btnCancel = new Button(btnCfg.getCancel());
            buttons.getChildren().add(btnCancel);

            btnCancel.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    stage.hide();
                    cfg.getAdapter(EventAdapter.get()).onCancel(event);
                }
            });
        }

        border.setBottom(buttons);

        return border;
    }

    public static void alert(final UIConfig cfg) {
        new Alert(cfg);
    }
}
