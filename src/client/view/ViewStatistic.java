package client.view;

import client.model.CompletedTasksTableModel;
import client.model.CurrentTasksTableModel;
import client.view.TaskStatusChart;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ViewStatistic extends JFrame {
    private JPanel contentPanel;
    private JTable completedTasksTable;
    private JButton dateFilterButton;
    private JButton employeeButton;
    private JButton statusChartButton;
    private JButton employeeChartButton;
    private JButton backButton;
    private TaskManager parent;
    private String[] taskTableFields = {"id", "time", "taskAssignedId", "taskEnd", "primaryStatus", "newStatus"};

    public ViewStatistic(TaskManager parent) {
        this.parent = parent;
        this.setResizable(true);
        this.setSize(1000, 1000);
        this.setContentPane(contentPanel);
        this.setLocationRelativeTo(null);
        this.startListeners();
        this.pack();
        this.setVisible(true);
    }

    ViewStatistic thisWindow() {
        return this;
    }

    public void startListeners() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });

        dateFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new DateFilter(thisWindow());
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new EmployeeFilter(thisWindow());
            }
        });

        statusChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskStatusChart chart = new TaskStatusChart();
            }
        });

        employeeChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskAssignedChart chart = new TaskAssignedChart();
            }
        });
    }

    public JTable getTabel() {
        return this.completedTasksTable;
    }

    public void tableSort() {
        RowSorter<CompletedTasksTableModel> sorter = new TableRowSorter<CompletedTasksTableModel>((CompletedTasksTableModel) completedTasksTable.getModel());
        completedTasksTable.setRowSorter(sorter);
    }

    private void createUIComponents() {
        completedTasksTable = new JTable(new CompletedTasksTableModel());
        tableSort();
    }
}
