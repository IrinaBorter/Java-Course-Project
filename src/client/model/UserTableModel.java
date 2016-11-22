package client.model;

import client.RmiConnector;
import common.model.User;
import common.UserInterface;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserTableModel extends AbstractTableModel {
    String[] columnNames = {"ID", "Логин","Пароль", "Права доступа", "Имя", "Фамилия", "Должность", "Возраст"};
    String[] columnNamesOrigin = {"id", "login","password", "access", "firstname", "surname", "post", "age"};

    private ArrayList<User> userArrayList = new ArrayList<User>();
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public UserTableModel() {
        RmiConnector rmiConnection = new RmiConnector();
        ArrayList<User> userArrayList = null;
        try {
            userArrayList = rmiConnection.getUserInterface().getAllUsers();
            System.out.println(userArrayList);
        }
        catch (Exception E) {
            E.printStackTrace();
        }
        this.userArrayList = userArrayList;
    }

    public int getRowCount() {
        return userArrayList.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userArrayList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getLogin();
            case 2:
                return "********";
            case 3:
                return user.getAccess();
        }
        return "";
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        User user = userArrayList.get(rowIndex);
        RmiConnector rmiConnection = new RmiConnector();
        UserInterface userInterface = rmiConnection.getUserInterface();
        try {
            userInterface.updateRow(columnNamesOrigin[columnIndex], aValue.toString(), user.getId());
            userArrayList = rmiConnection.getUserInterface().getAllUsers();

        } catch (RemoteException e) {}
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        User user = userArrayList.get(rowIndex);
        if (columnIndex == 0)
            return false;
        return true;
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0 || columnIndex == 3)
            return Integer.class;
        else return String.class;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
}
