package com.developersweb.entities;

public class User {
    private String id;
    private String login;
    private String password;
    private ROLE role;

    public User(String login, String password, ROLE role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}
