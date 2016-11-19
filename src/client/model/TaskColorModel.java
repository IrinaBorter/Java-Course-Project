package client.model;

import javax.swing.*;

/**
 * Created by User on 19.11.2016.
 */
public class TaskColorModel extends AbstractListModel implements ComboBoxModel {
    private String[] colors;
    String selection = null;

    public TaskColorModel(String status) {
        TaskStatusAndColorModel taskStatusAndColorModel = TaskStatusAndColorModel.getInstance();
        colors = taskStatusAndColorModel.getColorArray(status);
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
        return colors.length;
    }

    @Override
    public Object getElementAt(int index) {
        return colors[index];
    }
}
