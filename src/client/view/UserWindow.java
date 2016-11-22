package client.view;

import client.RmiConnector;
import client.model.UserTableModel;

import javax.management.remote.rmi.RMIConnector;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class UserWindow extends JDialog {
    private JPanel contentPanel;
    private JTable userTable;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField findField;
    private JButton searchButton;
    private JButton backButton;
    private TaskManager parent;

    public UserWindow(TaskManager parent){
        this.parent = parent;
        setModal(true);
        setContentPane(contentPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startListeners();
        pack();
        setVisible(true);
    }

    public UserWindow getThis() {
        return this;
    }

    public JTable getUserTable() {
        return userTable;
    }

    public void setUserTable(JTable userTable) {
        this.userTable = userTable;
    }

    public void startListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RmiConnector rmiConnection = new RmiConnector();
                int[] array = userTable.getSelectedRows();
                for (int i = 0; i < array.length; i++) {
                    try {
                        rmiConnection.getUserInterface().deleteFromDB("user", userTable.getValueAt(array[i], 0).toString());
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
                userTable.setModel(new UserTableModel());
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFilter();
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

    private void newFilter() {
        TableRowSorter sorter
                = new TableRowSorter<UserTableModel>(new UserTableModel());
        userTable.setRowSorter(sorter);
        RowFilter<TableModel, Object> rf = null;
        if (findField.getText().equals("")) {
            rf = null;
        }
        else {
            try {
                System.out.println(findField.getText());
                rf = RowFilter.regexFilter(findField.getText(), 1,3);
            } catch (java.util.regex.PatternSyntaxException e) {
                e.printStackTrace();
            }
        }
        sorter.setRowFilter(rf);
        userTable.setRowSorter(sorter);
    }


    private void createUIComponents() {
        userTable = new JTable(new UserTableModel());
    }
}
