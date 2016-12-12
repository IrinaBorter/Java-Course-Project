package client.view;

import client.model.CompletedTasksTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EmployeeFilter extends JFrame{
    private JPanel contentPanel;
    private JTextField idField;
    private JButton cancelButton;
    private JButton okButton;
    private ViewStatistic parent;

    public EmployeeFilter(ViewStatistic parent) {
        this.parent = parent;
        setContentPane(contentPanel);
        getRootPane().setDefaultButton(okButton);
        setResizable(false);
        setLocationRelativeTo(null);
        startListeners();
        pack();
        setVisible(true);
    }

    public void startListeners() {
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newFilter();
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
    }

    private void onCancel() {
        dispose();
        this.parent.setVisible(true);
    }

    private void newFilter() {
        TableRowSorter sorter
                = new TableRowSorter<CompletedTasksTableModel>(new CompletedTasksTableModel());
        parent.getTabel().setRowSorter(sorter);
        RowFilter<TableModel, Object> rf = null;
        try {
            if (idField.getText().equals("")) {
                throw new Exception("Проверьте введенные данные");
            }
            try {
                rf = RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Integer.parseInt(idField.getText()), 2);
                sorter.setRowFilter(rf);
                parent.getTabel().setRowSorter(sorter);
                parent.setVisible(true);
                dispose();
            } catch (java.util.regex.PatternSyntaxException e) {
                e.printStackTrace();
            }
        } catch(Exception e) {

        }
    }
}
