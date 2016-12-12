package common;

import common.model.CompletedTask;
import common.model.User;
import common.model.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserInterface extends Remote{
    public int authorisation(String login, String password) throws RemoteException;
    public void addNewUser(String login, String password, int access, String firstname, String surname, int age, String post) throws RemoteException;
    public ArrayList<User> getAllUsers() throws RemoteException;
    public boolean deleteFromDB(String tableName, String lineId) throws  RemoteException;
    public ArrayList<Task> getAllCurrentTasks() throws RemoteException;
    public boolean updateRow(String taskName, String field, String value, int id) throws RemoteException;
    public void addNewTask(String taskName, String taskDescription, int taskAssignedId,
                          String taskStart, String taskEnd, String taskStatus) throws RemoteException;
    public void completeTask(int id, String time, int taskAssignedId, String taskEnd, String primaryStatus, String newStatus) throws RemoteException;
    public ArrayList<CompletedTask> getAllCompletedTasks() throws RemoteException;
}
