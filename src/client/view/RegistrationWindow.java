package client.view;

import client.RmiConnector;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class RegistrationWindow extends JDialog {
    private JPanel contentPanel;
    private JButton cancelButton;
    private JButton okButton;
    private JTextField firstnameField;
    private JTextField surnameField;
    private JTextField ageField;
    private JTextField postField;
    private JTextField loginField;
    private JPasswordField passwordField;
    private AuthorisationDialog parent;

    public RegistrationWindow(AuthorisationDialog parent) {
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
                RmiConnector rmiConnection = new RmiConnector();
                String firstname = firstnameField.getText();
                String surname = surnameField.getText();
                String post = postField.getText();
                String login = loginField.getText();
                String password = passwordField.getText();
                int age = 0;
                try {
                    if (firstname.equals("") ||
                            surname.equals("") ||
                            ageField.getText().equals("") ||
                            post.equals("") ||
                            login.equals("") ||
                            password.equals("")) {
                        throw new Exception("Проверьте введенные данные");
                    } else {
                        age = Integer.parseInt(ageField.getText());
                    }
                    try {
                        rmiConnection.getUserInterface().addNewUser(login, password, 0, firstname, surname, age, post);
                    } catch (RemoteException obj) {
                        new ErrorWindow("Не получилось подключиться к серверу");
                    }
                    dispose();
                    AuthorisationDialog authorisationDialog = new AuthorisationDialog();
                }
                catch (Exception exc) {
                    new ErrorWindow(exc.getMessage());
                }
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

            }
        });

        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void onCancel() {
        System.exit(0);
    }
}
