package common.model;


import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String taskName;
    private String taskDescription;
    private int taskAssignedId;
    private String taskStart;
    private String taskEnd;
    private String taskStatus;

    public Task() {
        this.id = 1;
        this.taskName = "";
        this.taskDescription = "";
        this.taskAssignedId = 1;
        this.taskStart = "";
        this.taskEnd = "";
        this.taskStatus = "";
    }

    public Task(int id, String taskName, String taskDescription, int taskAssignedId,
                String taskStart, String taskEnd, String taskStatus) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskAssignedId = taskAssignedId;
        this.taskStart = taskStart;
        this.taskEnd = taskEnd;
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", Name='" + taskName + '\'' +
                ", Description='" + taskDescription + '\'' +
                ", Assigned Id=" + taskAssignedId + '\n' +
                ", Start=" + taskStart + '\n' +
                ", End=" + taskEnd + '\n' +
                ", Status=" + taskStatus + '\n' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getTaskAssignedId() {
        return taskAssignedId;
    }

    public void setTaskAssignedId(int taskAssignedId) {
        this.taskAssignedId = taskAssignedId;
    }

    public String getTaskStart() {
        return taskStart;
    }

    public void setTaskStart(String taskStart) {
        this.taskStart = taskStart;
    }

    public String getTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(String taskEnd) {
        this.taskEnd = taskEnd;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
