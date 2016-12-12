package client.view;

import client.RmiConnector;
import client.model.CurrentTasksTableModel;
import client.model.TaskAssignedIdModel;
import client.model.TaskStatusModel;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.regex.Pattern;

public class AddTaskWindow extends JDialog{
    private JPanel contentPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField taskNameField;
    private JTextField taskDescriptionField;
    private JTextField taskStartField;
    private JTextField taskEndField;
    private JComboBox taskStatusBox;
    private JComboBox assignedBox;
    private CurrentTasks parent;

    public AddTaskWindow(CurrentTasks parent) {
        this.parent = parent;
        this.parent.setVisible(false);
        setModal(true);
        setResizable(true);
        setContentPane(contentPanel);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(okButton);
        startListeners();
        pack();
        setVisible(true);
    }

    public void startListeners() {
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

        RmiConnector rmiConnection = new RmiConnector();
        String taskName = taskNameField.getText();
        String taskDescription = taskDescriptionField.getText();
        int taskAssigned = (int)assignedBox.getSelectedItem();
        String taskStart = taskStartField.getText();
        String taskEnd = taskEndField.getText();
        String taskStatus = (String) taskStatusBox.getSelectedItem();
        String pattern = "\\d{2}.\\d{2}.\\d{4}";
        try {
            if (taskName.equals("") ||
                    taskDescription.equals("") ||
                    taskAssigned == 0 ||
                    taskStart.equals("") ||
                    !taskStart.matches(pattern) ||
                    taskEnd.equals("") ||
                    !taskEnd.matches(pattern) ||
                    taskStatus.equals("")) {
                throw new Exception("Проверьте вводимые данные");
            }
            try {
                rmiConnection.getUserInterface().addNewTask(taskName, taskDescription, taskAssigned, taskStart, taskEnd, taskStatus);
            } catch (RemoteException e) {
                new ErrorWindow("Не получилось подключиться к серверу");
            }
            parent.getTable().setModel(new CurrentTasksTableModel());
            parent.tableSort();
            parent.setVisible(true);
            dispose();
        }
        catch (Exception e) {
            new ErrorWindow(e.getMessage());
        }
    }

    private void onCancel() {
        dispose();
        this.parent.setVisible(true);
    }

    private void createUIComponents() {
        taskStatusBox = new JComboBox(new TaskStatusModel());
        assignedBox = new JComboBox(new TaskAssignedIdModel());
    }
}
