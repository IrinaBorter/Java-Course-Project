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
        this.setContentPane(this.contentPanel);
        this.setSize(800, 500);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.startListeners();
        this.pack();
        this.setVisible(true);
    }

    TaskManager thisWindow() {
        return this;
    }

    public void startListeners() {
        this.currentTasksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CurrentTasks(thisWindow());
                setVisible(false);
            }
        });

        this.newTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.taskPerformanceStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.myProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parent.setVisible(true);
                dispose();
            }
        });
    }

}
