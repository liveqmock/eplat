package test.test.monitor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;

public class SystemMonitor extends Parent implements UpdateListener {

	public double width;
	public double height;
	public String interval = "1";

	ObservableList<LineChart.Series<Number, Number>> cpuLineChartSeries = FXCollections
			.observableArrayList();
	ObservableList<LineChart.Series<Number, Number>> memLineChartSeries = FXCollections
			.observableArrayList();

	LineChart<Number, Number> cpuLineChart;
	LineChart<Number, Number> memLineChart;

	/**
	 * 
	 */
	public SystemMonitor(double width, double height) {
		this.width = width;
		this.height = height;
		initMenmLineChart();
		initCPUChart();
		getChildren().add(create());
	}

	private void initMenmLineChart() {
		NumberAxis xAxis = new NumberAxis("Time", 0, 60, 10);
		xAxis.setTickLabelFont(new Font(10));
		xAxis.setTickLabelGap(10);
		xAxis.setTickLength(10);
		xAxis.setTickMarkVisible(true);

		NumberAxis yAxis = new NumberAxis("Memory-GB", 0, 100, 20);
		yAxis.setTickLabelFont(new Font(10));
		yAxis.setTickLabelGap(10);
		yAxis.setTickLength(10);
		yAxis.setTickMarkVisible(true);
		LineChart.Series<Number, Number> usedSeries = new LineChart.Series<>();
		usedSeries.setName("Used");
		LineChart.Series<Number, Number> freeSeries = new LineChart.Series<>();
		freeSeries.setName("Free");
		memLineChartSeries.add(usedSeries);
		memLineChartSeries.add(freeSeries);
		memLineChart = new LineChart<>(xAxis, yAxis);
		memLineChart.setCreateSymbols(false);
		memLineChart.setTranslateX(10);
		memLineChart.setTranslateY(height / 2.0 + 15);
		memLineChart.setTitle("Memory");
		memLineChart.setData(memLineChartSeries);
		memLineChart.setLegendSide(Side.RIGHT);
		memLineChart.setPrefHeight(height / 2.0 - 20);
		memLineChart.setPrefWidth(width - 20);
	}

	private void initCPUChart() {
		NumberAxis xAxis = new NumberAxis("Time", 0, 60, 10);
		xAxis.setTickLabelFont(new Font(10));
		xAxis.setTickLabelGap(10);
		xAxis.setTickLength(10);
		xAxis.setTickMarkVisible(true);

		NumberAxis yAxis = new NumberAxis("CPU %", 0, 100, 20);
		yAxis.setTickLabelFont(new Font(10));
		yAxis.setTickLabelGap(10);
		yAxis.setTickLength(10);
		yAxis.setTickMarkVisible(true);
		LineChart.Series<Number, Number> userSeries = new LineChart.Series<>();
		userSeries.setName("User");
		LineChart.Series<Number, Number> systemSeries = new LineChart.Series<>();
		systemSeries.setName("System");
		LineChart.Series<Number, Number> idleSeries = new LineChart.Series<>();
		idleSeries.setName("Idle");
		cpuLineChartSeries.add(userSeries);
		cpuLineChartSeries.add(systemSeries);
		cpuLineChartSeries.add(idleSeries);

		cpuLineChart = new LineChart<>(xAxis, yAxis, cpuLineChartSeries);
		cpuLineChart.setCreateSymbols(false);
		cpuLineChart.setTranslateX(10);
		cpuLineChart.setTranslateY(10);
		cpuLineChart.setTitle("CPU");
		cpuLineChart.setLegendSide(Side.RIGHT);
		cpuLineChart.setPrefHeight(height / 2.0 - 20);
		cpuLineChart.setPrefWidth(width - 20);
	}

	private Node create() {
		Rectangle bgRect = RectangleBuilder.create().width(width)
				.height(height).fill(Color.IVORY).arcWidth(5).arcHeight(5)
				.build();
		Rectangle borderCUPRect = RectangleBuilder.create()
				.x(cpuLineChart.getTranslateX() - 5)
				.y(cpuLineChart.getTranslateY() - 5)
				.width(cpuLineChart.getWidth() + 10)
				.height(cpuLineChart.getHeight() + 5).stroke(Color.GRAY)
				.fill(Color.TRANSPARENT).arcWidth(5).arcHeight(5).build();
		Rectangle borderMEMRect = RectangleBuilder.create()
				.x(memLineChart.getTranslateX() - 5)
				.y(memLineChart.getTranslateY() - 5)
				.width(memLineChart.getWidth() + 10)
				.height(memLineChart.getHeight() + 5).stroke(Color.GRAY)
				.fill(Color.TRANSPARENT).arcWidth(5).arcHeight(5).build();
		Group group = new Group();
		group.setTranslateX(10);
		group.setTranslateY(10);
		group.getChildren().addAll(bgRect, borderCUPRect, cpuLineChart,
				borderMEMRect, memLineChart);
		return group;
	}

	public void start(String hostname, String username, String password) {
		RemoteTOPMonitor topMonitor = new RemoteTOPMonitor(this, interval);
		topMonitor.setHostname(hostname);
		topMonitor.setPassword(password);
		topMonitor.setUsername(username);
		topMonitor.start();
	}

	int cpuIndex = 0;

	@Override
	public void updateCPU(String user, String sys, String idle) {
		cpuLineChartSeries.get(0).getData()
				.add(new LineChart.Data<Number, Number>(cpuIndex, Float
						.parseFloat(user)));
		cpuLineChartSeries.get(1).getData()
				.add(new LineChart.Data<Number, Number>(cpuIndex, Float
						.parseFloat(sys)));
		cpuLineChartSeries.get(2).getData()
				.add(new LineChart.Data<Number, Number>(cpuIndex, Float
						.parseFloat(idle)));
		NumberAxis xAxis = (NumberAxis) cpuLineChart.getXAxis();
		if (cpuIndex < xAxis.getUpperBound()) {
			cpuIndex += 1;
		} else {
			xAxis.setLowerBound(xAxis.getLowerBound() + 1);
			xAxis.setUpperBound(xAxis.getUpperBound() + 1);
			cpuLineChartSeries.get(0).getData().remove(0);
			cpuLineChartSeries.get(1).getData().remove(0);
			cpuLineChartSeries.get(2).getData().remove(0);
		}

	}

	int memIndex = 0;

	@Override
	public void updateMemory(String used, String free) {
		memLineChartSeries.get(0).getData()
				.add(new LineChart.Data<Number, Number>(memIndex, Float
						.parseFloat(used) / 1024.0));
		memLineChartSeries.get(1).getData()
				.add(new LineChart.Data<Number, Number>(memIndex, Float
						.parseFloat(free) / 1024.0));
		NumberAxis xAxis = (NumberAxis) memLineChart.getXAxis();
		if (memIndex < xAxis.getUpperBound()) {
			memIndex += 1;
		} else {
			xAxis.setLowerBound(xAxis.getLowerBound() + 1);
			xAxis.setUpperBound(xAxis.getUpperBound() + 1);
			memLineChartSeries.get(0).getData().remove(0);
			memLineChartSeries.get(1).getData().remove(0);
		}
	}

}
