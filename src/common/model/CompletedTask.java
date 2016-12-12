package common.model;

import java.io.Serializable;

public class CompletedTask implements Serializable {
    private int id;
    private String time;
    private int taskAssignedId;
    private String taskEnd;
    private String primaryStatus;
    private String newStatus;

    public CompletedTask() {
        this.id = 1;
        this.time = "";
        this.taskAssignedId = 1;
        this.taskEnd = "";
        this.primaryStatus = "";
        this.newStatus = "";
    }

    public CompletedTask(int id, String time, int taskAssignedId, String taskEnd, String primaryStatus, String newStatus) {
        this.id = id;
        this.time = time;
        this.taskAssignedId = taskAssignedId;
        this.taskEnd = taskEnd;
        this.primaryStatus = primaryStatus;
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", Time='" + time + '\'' +
                ", Assigned Id=" + taskAssignedId + '\n' +
                ", End=" + taskEnd + '\n' +
                ", Primary status: " + primaryStatus + '\n' +
                ", New status: " + newStatus + '\n' +
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

    public String getPrimaryStatus() {
        return primaryStatus;
    }

    public void setPrimaryStatus(String primaryStatus) {
        this.primaryStatus = primaryStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setnewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
