package com.epam.couriers.entity;

import java.util.Objects;

public class User extends Entity {
    private String login;
    private String password;
    private RoleEnum role;

    public User() {
    }

    public User(String login, String password, RoleEnum role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(int id) {
        super(id);
    }

    public User(String login) {
        this.login = login;
    }

    public User(int id, String login) {
        super(id);
        this.login = login;
    }

    public User(int id, String login, String password, RoleEnum role) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, login, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
