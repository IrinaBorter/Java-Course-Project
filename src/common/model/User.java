package common.model;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 2L;
    private int id;
    private String login;
    private String password;
    private int rights;

    public User() {
        this.login = "admin";
        this.password = "admin";
        this.id = 1;
        this.rights = 1;
    }

    public User(int id, String login, String password, int rights) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rights = rights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rights=" + rights +
                '}';
    }
}
