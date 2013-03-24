package test.test.monitor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;

/**
 * @author hanxi
 * 
 */
public class LoginPanel extends Parent {

	private SystemMonitor systemMonitor;
	private Node mouseBlocker;
	double width = 220;
	double height = 150;

	public LoginPanel(SystemMonitor systemMonitor, Node mouseBlocker) {
		this.systemMonitor = systemMonitor;
		this.mouseBlocker = mouseBlocker;
		this.getChildren().add(create());
	}

	private Node create() {
		Group group = new Group();
		Rectangle rec = RectangleBuilder.create().width(width).height(height)
				.arcHeight(10).arcWidth(10).fill(Color.WHITE)
				.stroke(Color.WHITE).build();
		GridPane grid = new GridPane();
		grid.setTranslateX(20);
		grid.setTranslateY(20);
		grid.setHgap(10);
		grid.setVgap(12);
		Label hostLabel = new Label("主机地址:");
		GridPane.setHalignment(hostLabel, HPos.RIGHT);
		final TextField hostText = new TextField();
		hostText.setPrefWidth(120);
		Label userLabel = new Label("用户名:");
		GridPane.setHalignment(userLabel, HPos.RIGHT);
		final TextField userText = new TextField();
		userText.setPrefWidth(120);
		Label passLabel = new Label("密码:");
		GridPane.setHalignment(passLabel, HPos.RIGHT);
		final PasswordField passText = new PasswordField();
		passText.setPrefWidth(120);
		Button btnSubmit = new Button("确定");
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (mouseBlocker != null) {
					mouseBlocker.setVisible(false);
				}
				setVisible(false);
				systemMonitor.start(hostText.getText(), userText.getText(),
						passText.getText());
			}
		});
		grid.add(hostLabel, 0, 0);
		grid.add(hostText, 1, 0);
		grid.add(userLabel, 0, 1);
		grid.add(userText, 1, 1);
		grid.add(passLabel, 0, 2);
		grid.add(passText, 1, 2);
		grid.add(btnSubmit, 0, 3, 2, 1);
		// 设置btnSubmit按钮水平居中
		GridPane.setHalignment(btnSubmit, HPos.CENTER);
		group.setTranslateX((Main.width - width) / 2);
		group.setTranslateY((Main.height - height) / 2);
		group.getChildren().addAll(rec, grid);
		return group;
	}

}
