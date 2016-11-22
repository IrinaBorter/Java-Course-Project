package client.model;


import client.RmiConnector;
import common.UserInterface;
import common.model.Task;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CurrentTasksTableModel extends AbstractTableModel {
    String[] columnNames = {"ID", "Название", "Описание", "ID назначенного", "Начало задания",
                            "Окончание задания", "Статус задания"};
    String[] columnNamesOrigin = {"id", "taskName", "taskDescription", "taskAssignedId", "taskStart",
                                  "taskEnd", "taskStatus"};

    private ArrayList<Task> taskArrayList = new ArrayList<Task>();
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public CurrentTasksTableModel() {
        RmiConnector rmiConnection = new RmiConnector();
        ArrayList<Task> taskArrayList = null;
        try {
            taskArrayList = rmiConnection.getUserInterface().getAllCurrentTasks();
        }
        catch (Exception E) {
            System.out.println(E.toString());
        }
        this.taskArrayList = taskArrayList;
    }

    public int getRowCount() {
        return taskArrayList.size();
    }

    public int getColumnCount() {
        return 7;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = taskArrayList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return task.getId();
            case 1:
                return task.getTaskName();
            case 2:
                return task.getTaskDescription();
            case 3:
                return task.getTaskAssignedId();
            case 4:
                return task.getTaskStart();
            case 5:
                return task.getTaskEnd();
            case 6:
                return task.getTaskStatus();
        }
        return "";
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Task task = taskArrayList.get(rowIndex);
        RmiConnector rmiConnection = new RmiConnector();
        UserInterface userInterface = rmiConnection.getUserInterface();
        try {
            userInterface.updateRow(columnNamesOrigin[columnIndex], aValue.toString(), task.getId());
            taskArrayList = rmiConnection.getUserInterface().getAllCurrentTasks();

        } catch (RemoteException e) {}
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return false;
        return true;
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1 || columnIndex == 2 || columnIndex == 4 || columnIndex == 5 || columnIndex == 6)
            return String.class;
        if  (columnIndex == 0 || columnIndex == 3)
            return Integer.class;
        else return Double.class;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
}
