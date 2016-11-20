package client.view;

import client.RmiConnector;
import client.model.CurrentTasksTableModel;
import client.model.TaskStatusModel;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class SubmitCompletedTask extends JDialog {
    private JPanel contentPanel;
    private JButton cancelButton;
    private JButton okButton;
    private JTextField timeField;
    private JTextField taskEndField;
    private JComboBox taskStatusBox;
    private CurrentTasks parent;
    private int id, taskAssignedId;
    private String primaryStatus;

    public SubmitCompletedTask(CurrentTasks parent, int id, int taskAssignedId, String status) {
        this.parent = parent;
        this.parent.setVisible(false);
        this.id = id;
        this.taskAssignedId = taskAssignedId;
        this.primaryStatus = status;
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
        String time = timeField.getText();
        String taskEnd = taskEndField.getText();
        String newStatus = (String)taskStatusBox.getSelectedItem();
        try {

        }
        catch (NumberFormatException e) {
            new ServerConnectionError("Проверьте вводимые данные");
        }
        try {
            rmiConnection.getUserInterface().completeTask(id, time, taskAssignedId, taskEnd, primaryStatus, newStatus);
        } catch (RemoteException e) {
            new ServerConnectionError("Не получилось подключиться к серверу");
        }
        parent.getTable().setModel(new CurrentTasksTableModel());
        parent.setVisible(true);
        dispose();
    }

    private void onCancel() {
        parent.setVisible(true);
        dispose();
    }

    private void createUIComponents() {
        taskStatusBox = new JComboBox(new TaskStatusModel());
    }
}
