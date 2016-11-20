package server.database;

import common.model.User;
import common.model.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class ConnectionDB {
    private final static String USER_DELETE = "DELETE FROM user WHERE id = ?";
    private final static String USER_ADD = "INSERT INTO user(login, password, access) VALUES(?,?,?)";
    private final static String TASK_ADD = "INSERT INTO task(taskName, taskDescription, taskAssignedId, taskStart, taskEnd, taskStatus) VALUES(?,?,?,?,?,?)";
    private final static String TASK_DELETE = "DELETE FROM task WHERE id = ?";
    private final static String COMPLETE_TASK_ADD = "INSERT INTO completedtasks  (id, time, taskAssignedId, taskEnd, primaryStatus, newStatus) VALUES (?,?,?,?,?,?)";
    private final static String UPDATE_TASK_STATUS = "UPDATE task SET taskStatus = ? WHERE carId = ?";


    private Connection cn = null;
    private Statement st = null;
    private ResultSet rs = null;

    public ConnectionDB() {
        try {
            cn = ConnectionDB.getConnection();
            st = cn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        FileInputStream prop = null;
        try {
            prop = new FileInputStream("F:\\_Univer\\3 course\\java\\src\\server\\dbconfig-properties");
            properties.load(prop);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }

    public int checkAuthorisation(String login,  String password) {
        try {
            rs = st.executeQuery("SELECT * FROM user WHERE login ='" + login + "'");
            System.out.println(rs.toString());
            rs.next();
            if(rs.getString(2).equals(login) && rs.getString(3).equals(password))
                return rs.getInt(4);
        } catch (SQLException e) {
            System.err.println("не удалось обработать запрос!");
        }
        return 0;
    }

    public void addNewUser(String login, String password, int access) {
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(USER_ADD);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setInt(3, access);
            System.out.println(ps.toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error in SQL Query!");
        }
    }

    public boolean deleteFromDB(String tableName, String lineId) {
        PreparedStatement ps = null;
        try {
            if (tableName.equals("user")) {
                ps = cn.prepareStatement(USER_DELETE);
            }
            if (tableName.equals("task")) {
                ps = cn.prepareStatement(TASK_DELETE);
            }
            ps.setString(1, lineId);
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userArrayList = new ArrayList<User>();
        try {
            rs = st.executeQuery("SELECT  * FROM user");
            while (rs.next()) {
                userArrayList.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            System.out.println(rs.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userArrayList);
        return userArrayList;
    }

    public ArrayList<Task> getAllCurrentTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        try {
            rs = st.executeQuery("SELECT  * FROM task");
            while (rs.next()) {
                if(!rs.getBoolean(7)) {
                    taskArrayList.add(new Task(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                }
            }
            System.out.println(rs.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskArrayList;
    }

    public boolean updateTaskRow(String field, String value, int id) {
        String TASK_UPDATE = "UPDATE task SET "+field+" = ? WHERE id = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(TASK_UPDATE);
            ps.setString(1, value);
            ps.setInt(2, id);
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void addNewTask(String taskName, String taskDescription, int taskAssignedId,
                           String taskStart, String taskEnd, String taskStatus) {
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(TASK_ADD);
            ps.setString(1, taskName);
            ps.setString(2, taskDescription);
            ps.setInt(3, taskAssignedId);
            ps.setString(4, taskStart);
            ps.setString(5, taskEnd);
            ps.setString(6, taskStatus);
            System.out.println(ps.toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error in SQL Query!");
        }
    }

    public void addCompletedTask(int id, String time, int taskAssignedId, String taskStart, String primaryStatus, String newStatus) {
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(COMPLETE_TASK_ADD);
            ps.setInt(1, id);
            ps.setString(2, time);
            ps.setInt(3, taskAssignedId);
            ps.setString(4, taskStart);
            ps.setString(5, primaryStatus);
            ps.setString(6, newStatus);
            System.out.println(ps.toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error on SQL Query!");
        }

    }

    public void close() {
        try {
            cn.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
