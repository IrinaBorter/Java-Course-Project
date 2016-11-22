package server;

import common.UserInterface;
import common.model.User;
import common.model.Task;
import server.database.ConnectionDB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

public class RemoteInterface extends UnicastRemoteObject implements UserInterface{
    public RemoteInterface() throws RemoteException {
        super();
    }

    public int authorisation(String login, String password) throws RemoteException {
        ConnectionDB databaseConnector = new ConnectionDB();
        return  databaseConnector.checkAuthorisation(login, password);
    }

    public void addNewUser(String login, String password, int access, String firstname, String surname, int age, String post) throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.addNewUser(login, password, access, firstname, surname, age, post);
    }

    public ArrayList<User> getAllUsers() throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.getAllUsers();
    }

    public boolean deleteFromDB(String tableName, String lineId) throws RemoteException{
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.deleteFromDB(tableName, lineId);
    }

    public ArrayList<Task> getAllCurrentTasks() throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.getAllCurrentTasks();
    }

    public boolean updateRow(String field, String value,int id) throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.updateTaskRow(field, value, id);
    }

    public void addNewTask(String taskName, String taskDescription, int taskAssigned,
                          String taskStart, String taskEnd, String taskStatus) throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.addNewTask(taskName, taskDescription, taskAssigned, taskStart, taskEnd, taskStatus);
    }

    public void completeTask(int id, String time, int taskAssignedId, String taskEnd, String primaryStatus, String newStatus) throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.updateTaskRow("taskStatus", newStatus, id);
        connectionDB.addCompletedTask(id, time, taskAssignedId, taskEnd, primaryStatus, newStatus);
    }
}
