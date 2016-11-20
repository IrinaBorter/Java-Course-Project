package client.view;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import common.model.Task;
import client.RmiConnector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import client.model.CurrentTasksTableModel;


public class CurrentTasks extends JFrame{
    private JTable currentTasksTable;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel contentPanel;
    private JButton backButton;
    private JButton addButton;
    private JButton submitCompleteButton;
    private TaskManager parent;
    private String[] taskTableFields = {"id", "taskName", "taskDescription", "taskAssignedId", "taskStart", "taskEnd", "taskStatus"};

    public CurrentTasks(TaskManager parent) {
        this.parent = parent;
        this.setResizable(true);
        this.setSize(500, 1000);
        this.setContentPane(contentPanel);
        this.setLocationRelativeTo(null);
        this.startListeners();
        this.pack();
        this.setVisible(true);
    }

    CurrentTasks thisWindow() {
        return this;
    }

    public void startListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTaskWindow(thisWindow());
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RmiConnector rmiConnection = new RmiConnector();
                int row = currentTasksTable.getEditingRow();
                int column = currentTasksTable.getEditingColumn();

                if (currentTasksTable.isEditing()) {
                    currentTasksTable.getCellEditor().stopCellEditing();
                }
                try {
                    rmiConnection.getUserInterface().updateTaskRow(
                            taskTableFields[column],
                            currentTasksTable.getValueAt(row, column).toString(),
                            Integer.parseInt(currentTasksTable.getValueAt(row, 0).toString()));
                } catch(RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RmiConnector rmiConnection = new RmiConnector();
                int[] array = currentTasksTable.getSelectedRows();
                for (int i = 0; i < array.length; i++) {
                    try {
                        rmiConnection.getUserInterface().deleteFromDB("task", currentTasksTable.getValueAt(array[i], 0).toString());
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
                currentTasksTable.setModel(new CurrentTasksTableModel());
                tableSort();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });

        submitCompleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowId = currentTasksTable.getSelectedRow();
                int id = (int)currentTasksTable.getValueAt(selectedRowId, 0);
                int taskAssignedId = (int)currentTasksTable.getValueAt(selectedRowId, 3);
                String status = (String)currentTasksTable.getValueAt(selectedRowId, 6);
                new SubmitCompletedTask(thisWindow(), id, taskAssignedId, status);

                currentTasksTable.setModel(new CurrentTasksTableModel());
                tableSort();
            }
        });
    }

    private void createUIComponents() throws RemoteException {
        currentTasksTable = new JTable(new CurrentTasksTableModel());
    }

    public JTable getTable() {
        return currentTasksTable;
    }

    public void tableSort() {
        RowSorter<CurrentTasksTableModel> sorter = new TableRowSorter<CurrentTasksTableModel>((CurrentTasksTableModel) currentTasksTable.getModel());
        currentTasksTable.setRowSorter(sorter);
    }
}
