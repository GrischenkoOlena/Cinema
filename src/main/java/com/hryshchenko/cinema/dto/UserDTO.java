package com.hryshchenko.cinema.dto;

import com.hryshchenko.cinema.constant.enums.UserRole;

import java.util.Objects;

public class UserDTO implements ISimpleDTO{
    private static final long serialVersionUID = 1L;
    private final long id;
    private final String login;
    private final transient String password;
    private final String name;
    private final double balance;
    private final UserRole role;

    private UserDTO(UserDTOBuilder builder){
        id = builder.id;
        login = builder.login;
        name = builder.name;
        password = builder.password;
        balance = builder.balance;
        role = builder.role;
    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id
                && Double.compare(userDTO.balance, balance) == 0
                && Objects.equals(login, userDTO.login)
                && Objects.equals(password, userDTO.password)
                && Objects.equals(name, userDTO.name) && role == userDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, balance, role);
    }

    public static class UserDTOBuilder{
        private final long id;
        private String login = "";
        private transient String password = "";
        private String name = "";
        private double balance = 0.0;
        private UserRole role = UserRole.CLIENT;

        public UserDTOBuilder(long id){
            this.id = id;
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
