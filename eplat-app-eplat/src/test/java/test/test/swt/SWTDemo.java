/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.util.Pair;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.sun.javafx.runtime.VersionInfo;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTDemo.java, V1.0.1 2013-3-31 下午5:17:11 $
 */
public class SWTDemo {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);

        // Print version info
        System.out.println("SWT " + SWT.getPlatform() + " " + SWT.getVersion());
        String runtimeVersion = VersionInfo.getRuntimeVersion();
        System.out.println("FX " + runtimeVersion);

        shell.setLayout(new FillLayout());

        // Create the fake TabPane guy
        final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
        TabItem chartTab = new TabItem(tabFolder, SWT.NULL);
        chartTab.setText("JavaFX Chart and SWT Table");
        TabItem browserTab = new TabItem(tabFolder, SWT.NULL);
        browserTab.setText("Web Browser");

        Canvas canvas = new Canvas(tabFolder, SWT.NULL);
        GridLayout layout = new GridLayout(1, true);
        canvas.setLayout(layout);
        chartTab.setControl(canvas);

        // Create the data (I'll use the Chart's data model for
        // both the chart and the table)
        ObservableList<BarChart.Series> bcData = FXCollections.observableArrayList();
        bcData.add(createSeries(new Pair("2007", new Double(567)), new Pair("2008", new Double(956)), new Pair("2009", new Double(1154))));
        bcData.add(createSeries(new Pair("2007", new Double(1292)), new Pair("2008", new Double(1665)), new Pair("2009", new Double(1927))));
        bcData.add(createSeries(new Pair("2007", new Double(1292)), new Pair("2008", new Double(2559)), new Pair("2009", new Double(2774))));

        Canvas chart = createChart(canvas, bcData);
        chart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        Table table = createTable(canvas, bcData);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        createTableEditor(table);

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    private static BarChart.Series<String, Number> createSeries(Pair<String, Double>... values) {
        ObservableList<BarChart.Data<String, Number>> series = FXCollections.<BarChart.Data<String, Number>> observableArrayList();
        for (int i = 0; i < values.length; i++) {
            series.add(new BarChart.Data<String, Number>(values[i].getKey(), values[i].getValue()));
        }
        return new BarChart.Series<String, Number>(series);
    }

    private static Canvas createChart(Composite shell, ObservableList<BarChart.Series> bcData) {
        final FXCanvas fxPanel = new FXCanvas(shell, SWT.NONE);
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String> observableArrayList("2007", "2008", "2009"));
        xAxis.setLabel("Year");
        double tickUnit = 1000.0;
        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickUnit(tickUnit);
        yAxis.setLabel("Units Sold");
        final BarChart chart = new BarChart(xAxis, yAxis, bcData);
        fxPanel.setScene(new Scene(chart));
        return fxPanel;
    }

    private static Table createTable(Composite parent, ObservableList<BarChart.Series> bcData) {
        Table table = new Table(parent, SWT.BORDER | SWT.HIDE_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        String[] titles = { "2007", "2008", "2009" };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
        }
        for (int i = 0; i < bcData.size(); i++) {
            createRow(table, bcData.get(i));
        }
        for (int i = 0; i < titles.length; i++) {
            table.getColumn(i).setWidth(400 / 3);
        }
        return table;
    }

    private static TableItem createRow(Table table, BarChart.Series<String, Number> series) {
        TableItem item = new TableItem(table, SWT.NONE);
        item.setData(series);
        int count = 0;
        for (BarChart.Data<String, Number> data : series.getData()) {
            item.setText(count++, data.getYValue().toString());
        }
        return item;
    }

    private static void createTableEditor(final Table table) {
        final TableEditor editor = new TableEditor(table);
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;
        table.addListener(SWT.MouseDown, new Listener() {
            public void handleEvent(Event event) {
                Rectangle clientArea = table.getClientArea();
                Point pt = new Point(event.x, event.y);
                int index = table.getTopIndex();
                while (index < table.getItemCount()) {
                    boolean visible = false;
                    final TableItem item = table.getItem(index);
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        Rectangle rect = item.getBounds(i);
                        if (rect.contains(pt)) {
                            final int column = i;
                            final Text text = new Text(table, SWT.NONE);
                            final String oldText = item.getText(i);
                            Listener textListener = new Listener() {
                                public void handleEvent(final Event e) {
                                    switch (e.type) {
                                        case SWT.FocusOut:
                                            item.setText(column, text.getText());
                                            text.dispose();
                                            break;
                                        case SWT.Traverse:
                                            switch (e.detail) {
                                                case SWT.TRAVERSE_ESCAPE:
                                                    text.setText(oldText);
                                                    //FALL THROUGH
                                                case SWT.TRAVERSE_RETURN:
                                                    item.setText(column, text.getText());
                                                    text.dispose();
                                                    e.doit = false;
                                            }
                                            break;
                                        case SWT.Dispose:
                                            if (item.isDisposed() || (text.getText().equals(oldText)))
                                                break;
                                            BarChart.Data<String, Number> oldData = ((BarChart.Series<String, Number>) item.getData()).getData().get(column);
                                            try {
                                                double result = Double.parseDouble(text.getText().trim());
                                                oldData.setYValue(result);
                                            } catch (NumberFormatException ex) {
                                                item.setText(column, oldText);
                                            }
                                            break;
                                    }
                                }
                            };
                            text.addListener(SWT.FocusOut, textListener);
                            text.addListener(SWT.Traverse, textListener);
                            text.addListener(SWT.Dispose, textListener);
                            editor.setEditor(text, item, i);
                            text.setText(item.getText(i));
                            text.selectAll();
                            text.setFocus();
                            return;
                        }
                        if (!visible && rect.intersects(clientArea)) {
                            visible = true;
                        }
                    }
                    if (!visible)
                        return;
                    index++;
                }
            }
        });
    }
}
