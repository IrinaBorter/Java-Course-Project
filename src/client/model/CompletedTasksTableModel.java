package client.model;

import client.RmiConnector;
import client.control.CompletedTaskWriter;
import common.UserInterface;
import common.model.CompletedTask;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CompletedTasksTableModel extends AbstractTableModel {
    String[] columnNames = {
            "ID",
            "Время",
            "ID назначенного",
            "Дата окончания",
            "Начальный статус",
            "Новый статус"
    };
    String[] columnNamesOrigin = {
            "id",
            "time",
            "taskAssignedId",
            "taskEnd",
            "primaryStatus",
            "newStatus"
    };
    private ArrayList<CompletedTask> taskArrayList = new ArrayList<CompletedTask>();
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public CompletedTasksTableModel() {
        RmiConnector rmiConnection = new RmiConnector();
        ArrayList<CompletedTask> taskArrayList = null;
        try {
            taskArrayList = rmiConnection.getUserInterface().getAllCompletedTasks();
            CompletedTaskWriter.writeStatistic(taskArrayList);
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
        return 6;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        CompletedTask task = taskArrayList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return task.getId();
            case 1:
                return task.getTime();
            case 2:
                return task.getTaskAssignedId();
            case 3:
                return task.getTaskEnd();
            case 4:
                return task.getPrimaryStatus();
            case 5:
                return task.getNewStatus();
        }
        return "";
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CompletedTask task = taskArrayList.get(rowIndex);
        RmiConnector rmiConnection = new RmiConnector();
        UserInterface userInterface = rmiConnection.getUserInterface();
        try {
            userInterface.updateRow("completedtasks", columnNamesOrigin[columnIndex], aValue.toString(), task.getId());
            taskArrayList = rmiConnection.getUserInterface().getAllCompletedTasks();

        } catch (RemoteException e) {}
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return !(columnIndex == 0);
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1 || columnIndex == 3 || columnIndex == 4 || columnIndex == 5)
            return String.class;
        if  (columnIndex == 0 || columnIndex == 2)
            return Integer.class;
        else return Double.class;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
}
