package client.model;


import javax.swing.*;

public class TaskStatusModel extends AbstractListModel implements ComboBoxModel {
    private String[] status;
    private TaskStatusAndColorModel taskStatusAndColorModel;
    String selection = null;

    public TaskStatusModel() {
        taskStatusAndColorModel = TaskStatusAndColorModel.getInstance();
        status = taskStatusAndColorModel.getStatusArray();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (String) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return status.length;
    }

    @Override
    public Object getElementAt(int index) {
        return status[index];
    }
}
