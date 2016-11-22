package client.view;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.regex.PatternSyntaxException;

public class TaskManager extends JFrame {
    private JPanel contentPanel;
    private JButton currentTasksButton;
    private JButton newTasksButton;
    private JButton taskPerformanceStatisticsButton;
    private JButton exitButton;
    private JButton myProfileButton;
    private AuthorisationDialog parent;


    public TaskManager(AuthorisationDialog parent) {
        this.parent = parent;
        this.setResizable(true);
        this.setSize(500, 1000);
        this.setContentPane(contentPanel);
        this.setLocationRelativeTo(null);
        this.startListeners();
        this.pack();
        this.setVisible(true);
    }

    TaskManager thisWindow() {
        return this;
    }

    public void startListeners() {
        currentTasksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CurrentTasks(thisWindow());
                setVisible(false);
            }
        });

        newTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddTaskWindow(new CurrentTasks(thisWindow()));
            }
        });

        taskPerformanceStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        myProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UserWindow(thisWindow());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                AuthorisationDialog authDlg = new AuthorisationDialog();
            }
        });
    }

}
