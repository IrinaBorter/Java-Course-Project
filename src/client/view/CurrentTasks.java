package client.view;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import common.model.Task;

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
    private TaskManager parent;

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

    public void startListeners() {
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });
    }

    private void createUIComponents() throws RemoteException {
        currentTasksTable = new JTable(new CurrentTasksTableModel());
    }

    public JTable getTable() {
        return currentTasksTable;
    }
}
