package common;

import common.model.User;
import common.model.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserInterface extends Remote{
    public int authorisation(String login, String password) throws RemoteException;
    public void addNewUser(String login, String password, int access) throws RemoteException;
    public ArrayList<User> getAllUsers() throws RemoteException;
    public boolean deleteFromDB(String tableName, String lineId) throws  RemoteException;
    public ArrayList<Task> getAllCurrentTasks() throws RemoteException;
    public boolean updateTaskRow(String field, String value, int id) throws RemoteException;
}
