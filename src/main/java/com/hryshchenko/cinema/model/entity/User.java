package com.hryshchenko.cinema.model.entity;

public class User extends Entity{
    private static final long serialVersionUID = 1L;
    private String login;
    private transient String password;
    private String name;
    private double balance;
    private UserRole role;
    public User(){ };

    public User(long id, String login, String password, String name, double balance, UserRole role) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.balance = balance;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                '}';
    }
}
