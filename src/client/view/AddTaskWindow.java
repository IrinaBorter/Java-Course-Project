package client.view;

import client.RmiConnector;
import client.model.CurrentTasksTableModel;
import client.model.TaskStatusModel;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class AddTaskWindow extends JDialog{
    private JPanel contentPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField taskNameField;
    private JTextField taskDescriptionField;
    private JComboBox taskAssignedBox;
    private JTextField taskStartField;
    private JTextField taskEndField;
    private JComboBox taskStatusBox;
    private JTextField taskAssignedField;
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
        int taskAssigned = Integer.parseInt(taskAssignedField.getText());
        String taskStart = taskStartField.getText();
        String taskEnd = taskEndField.getText();
        String taskStatus = (String) taskStatusBox.getSelectedItem();
        try {

        }
        catch (NumberFormatException e) {
            new ServerConnectionError("Проверьте вводимые данные");
        }
        try {
            rmiConnection.getUserInterface().addNewTask(taskName, taskDescription, taskAssigned, taskStart, taskEnd, taskStatus);
        } catch (RemoteException e) {
            new ServerConnectionError("Не получилось подключиться к серверу");
        }
        dispose();
        parent.setVisible(true);
        parent.getTable().setModel(new CurrentTasksTableModel());
    }

    private void onCancel() {
        dispose();
        this.parent.setVisible(true);
    }

    private void createUIComponents() {
        taskStatusBox = new JComboBox(new TaskStatusModel());
    }
}
