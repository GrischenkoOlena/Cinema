package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.constant.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

/**
 * User entity query executor.
 *
 * @author Olena Hryshchenko.
 */
public class UserQueryExecutor extends QueryExecutor<User> {

    /**
     * @return the {@link com.hryshchenko.cinema.model.entity.User}
     */
    @Override
    public User getResult(ResultSet rs) throws SQLException {
        User user = new User();
        while (rs.next()){
            user.setId(rs.getInt(USER_ID));
            user.setLogin(rs.getString(USER_LOGIN));
            user.setName(rs.getString(USER_NAME));
            user.setPassword(rs.getString(USER_PASSWORD));
            user.setBalance(rs.getDouble(USER_BALANCE));
            Integer roleId = rs.getInt(USER_ROLE_ID);
            user.setRole(UserRole.getValueFromId(roleId));
        }
        return user;
    }

    /**
     * @return the list of {@link com.hryshchenko.cinema.model.entity.User}
     */
    @Override
    public List<User> getListOfResult(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt(USER_ID));
            user.setLogin(rs.getString(USER_LOGIN));
            user.setName(rs.getString(USER_NAME));
            user.setPassword(rs.getString(USER_PASSWORD));
            user.setBalance(rs.getDouble(USER_BALANCE));
            Integer roleId = rs.getInt(USER_ROLE_ID);
            user.setRole(UserRole.getValueFromId(roleId));
            users.add(user);
        }
        return users;
    }
}
