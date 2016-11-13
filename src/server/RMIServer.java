package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.err.println("Запуск сервера выполнен");
        RemoteInterface impl = new RemoteInterface();
        Registry registry = LocateRegistry.createRegistry(8030);
        registry.bind("courseRMI", impl);
    }
}
