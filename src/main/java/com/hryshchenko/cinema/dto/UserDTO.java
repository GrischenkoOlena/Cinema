package com.hryshchenko.cinema.dto;

import com.hryshchenko.cinema.constant.enums.UserRole;

public class UserDTO implements ISimpleDTO{
    private static final long serialVersionUID = 1L;
    private final String login;
    private final transient String password;
    private final String name;
    private final double balance;
    private final UserRole role;

    private UserDTO(UserDTOBuilder builder){
        login = builder.login;
        name = builder.name;
        password = builder.password;
        balance = builder.balance;
        role = builder.role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                '}';
    }

    public static class UserDTOBuilder{
        private String login;
        private transient String password;
        private String name = "";
        private double balance = 0.0;
        private UserRole role = UserRole.CLIENT;

        public UserDTOBuilder(String login, String password){
            this.login = login;
            this.password = password;
        }

        public UserDTOBuilder login (String val){
            login = val;
            return this;
        }

        public UserDTOBuilder password (String val){
            password = val;
            return this;
        }

        public UserDTOBuilder name(String val){
            name = val;
            return this;
        }

        public UserDTOBuilder balance(double val){
            balance = val;
            return this;
        }

        public UserDTOBuilder userRole(UserRole val){
            role = val;
            return this;
        }

        public UserDTO build(){
            return new UserDTO(this);
        }

    }
}
