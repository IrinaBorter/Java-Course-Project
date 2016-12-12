package client.model;

import client.RmiConnector;
import common.model.User;

import javax.swing.*;
import java.util.ArrayList;


public class TaskAssignedIdModel extends AbstractListModel implements ComboBoxModel {
    private int[] id;
    int selection;

    public TaskAssignedIdModel() {
        RmiConnector rmiConnection = new RmiConnector();
        ArrayList<User> userArrayList = null;
        try {
            userArrayList = rmiConnection.getUserInterface().getAllUsers();
            id = new int[userArrayList.size()];
            for (int i = 0; i < userArrayList.size(); i++) {
                id[i] =  userArrayList.get(i).getId();
            }
        }
        catch (Exception E) {
            E.printStackTrace();
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (int)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public Object getElementAt(int index) {
        return id[index];
    }
}
