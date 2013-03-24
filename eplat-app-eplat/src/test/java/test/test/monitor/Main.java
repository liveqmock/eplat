package test.test.monitor;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 实现功能：
 * 1、监控远程机器(用到ganymed-ssh2-build210.jar包)。
 * 2、实时显示cpu使用情况(包括系统进程使用cpu、用户进程使用cpu以及空闲进程使用cpu)。
 * 3、实时系统内存使用情况(包括系统已使用内存以及空闲内存)。
 */
public class Main extends Application {
	static double width = 620;
	static double height = 640;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Rectangle mouseBlocker = RectangleBuilder.create().width(width)
				.height(height).fill(Color.BLACK).opacity(0.3).visible(true)
				.mouseTransparent(false).build();
		SystemMonitor systemMonitor = new SystemMonitor(600, 600);
		LoginPanel loginPanel = new LoginPanel(systemMonitor, mouseBlocker);
		root.getChildren().addAll(systemMonitor, mouseBlocker, loginPanel);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(
				getClass().getResource("chart.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("系统监控");
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				// Platform.exit();
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
