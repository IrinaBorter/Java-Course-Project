package client.view;

import client.RmiConnector;
import client.model.TaskStatusAndColorModel;
import common.model.Task;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskStatusChart extends JDialog {

    private static final long serialVersionUID = 1L;
    private static TaskStatusAndColorModel taskStatusAndColorModel;

    public TaskStatusChart() {
        CategoryDataset dataset = createDataset();
        setModal(true);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setBackground(null);
        chartPanel.setFillZoomRectangle(false);
        chartPanel.setMouseWheelEnabled(false);
        chartPanel.setDismissDelay(Integer.MAX_VALUE);
        chartPanel.setPreferredSize(new Dimension(1000, 500));
        setContentPane(chartPanel);
        pack();
        setVisible(true);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Task> taskArrayList = null;
        Task task = null;
        taskStatusAndColorModel = TaskStatusAndColorModel.getInstance();
        String[] taskStatus = taskStatusAndColorModel.getStatusArray();
        int[] typeCount = new int[taskStatus.length];
        for (int i = 0; i < typeCount.length; i++) {
            typeCount[i] = 0;
        }
        RmiConnector rmiCon = new RmiConnector();

        try {
            taskArrayList = rmiCon.getUserInterface().getAllCurrentTasks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Iterator it  = taskArrayList.iterator();
        while (it.hasNext()) {
            task = (Task) it.next();
            for (int i = 0; i < taskStatus.length ; i++) {
                if (taskStatus[i].equals(task.getTaskStatus())) {
                    typeCount[i]++;
                }
            }
        }

        for (int i = 0; i < taskStatus.length; i++) {
            dataset.addValue(typeCount[i], taskStatus[i], "");
        }
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Распределение задач по статусам", null /* x-axis label*/,
                "Количество задач" /* y-axis label */, dataset);
        chart.setBackgroundPaint(null);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }
}
