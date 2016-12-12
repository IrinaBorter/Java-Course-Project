package client.view;

import client.model.CompletedTasksTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;


public class DateFilter extends JFrame {
    private JPanel contentPanel;
    private JTextField date1Field;
    private JButton okButton;
    private JButton cancelButton;
    private ViewStatistic parent;

    public DateFilter(ViewStatistic parent) {
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
            if (date1Field.getText().equals("")) {
                throw new Exception("Проверьте вводимые данные");
            }
            try {
                System.out.println(date1Field.getText());
                rf = RowFilter.regexFilter(date1Field.getText(), 3);
                sorter.setRowFilter(rf);
                parent.getTabel().setRowSorter(sorter);
                parent.setVisible(true);
                dispose();
            } catch (java.util.regex.PatternSyntaxException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            new ErrorWindow(e.getMessage());
        }
    }
}
