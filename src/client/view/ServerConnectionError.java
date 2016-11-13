package client.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerConnectionError extends JDialog{
    private JPanel ContentPanel;
    private JButton okButton;
    private JLabel errorMessage;

    public ServerConnectionError(String message) {
        setContentPane(ContentPanel);
        setModal(true);
        setSize(250, 150);
        getRootPane().setDefaultButton(okButton);
        setLocationRelativeTo(null);
        initListener();
        errorMessage.setText(message);
        setVisible(true);
        pack();
    }

    private void  initListener() {
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
    }
}
