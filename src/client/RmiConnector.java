package client;

import client.view.ErrorWindow;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import common.UserInterface;

public class RmiConnector {
    private Registry registry;
    private UserInterface userInterface;

    public RmiConnector(){
        try {
            registry = LocateRegistry.getRegistry(8030);
            userInterface = (UserInterface)registry.lookup("courseRMI");
        } catch (RemoteException e) {
            new ErrorWindow("Сервер недоступен!");
        } catch (NotBoundException e) {
            new ErrorWindow("Сервер недоступен!");
        }
    }
    public UserInterface getUserInterface() {
        return userInterface;
    }
}
