package client;

import client.view.AuthorisationDialog;
import client.view.TaskManager;

import javax.swing.*;

/**
 * Created by User on 24.10.2016.
 */
public class ClientApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AuthorisationDialog authDlg = new AuthorisationDialog();
    }
}
