package common.model;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 2L;
    private int id;
    private String login;
    private String password;
    private int access;
    private String firstname;
    private String surname;
    private String post;
    private int age;

    public User() {
        this.login = "admin";
        this.password = "admin";
        this.id = 1;
        this.access = 1;
        this.firstname = "";
        this.surname = "";
        this.post = "";
        this.age = 0;

    }

    public User(int id, String login, String password, int access, String firstname, String surname, String post, int age) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.access = access;
        this.firstname = firstname;
        this.surname = surname;
        this.post = post;
        this.age = age;
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

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rights=" + access +
                ", firstnmae='" + firstname + '\n' +
                ", surname='" + surname + '\n' +
                ", post='" + post + '\n' +
                ", age='" + age + '\n' +
                '}';
    }
}
