package common.model;

import java.io.Serializable;

public class CompletedTask implements Serializable {
    private int id;
    private String time;
    private int taskAssignedId;
    private String taskEnd;

    public CompletedTask() {
        this.id = 1;
        this.time = "";
        this.taskAssignedId = 1;
        this.taskEnd = "";
    }

    public CompletedTask(int id, String time, int taskAssignedId, String taskEnd) {
        this.id = id;
        this.time = time;
        this.taskAssignedId = taskAssignedId;
        this.taskEnd = taskEnd;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", Time='" + time + '\'' +
                ", Assigned Id=" + taskAssignedId + '\n' +
                ", End=" + taskEnd + '\n' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTaskAssignedId() {
        return taskAssignedId;
    }

    public void setTaskAssignedId(int taskAssignedId) {
        this.taskAssignedId = taskAssignedId;
    }

    public String getTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(String taskEnd) {
        this.taskEnd = taskEnd;
    }
}
