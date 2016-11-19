package client.model;

import java.util.HashMap;
import java.util.Map;

public class TaskStatusAndColorModel {
    private  static TaskStatusAndColorModel instance;
    private Map statusModel = new HashMap<String, String[]>();;

    public Map getStatusModel() {
        return statusModel;
    }

    public void setStatusModel(Map statusModel) {
        this.statusModel = statusModel;
    }

    public String[] getStatusArray() {
        return  (String[]) statusModel.keySet().toArray(new String[statusModel.size()]);
    }

    public String[] getColorArray(String status) {
        return (String[]) statusModel.get(status);
    }

    static public TaskStatusAndColorModel getInstance() {
        if (instance == null)
            instance = new TaskStatusAndColorModel();
        return instance;
    }

    public void put(String status, String color) {
        statusModel.put(status, color);
    }

}
