package org.internetbanking.dataaccsess.authentication;

import org.internetbanking.business.entity.person.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IAuthentication {
    public ResultSet loginUser(String tc, String password, Customer customer) throws SQLException;

    public ResultSet loginAdmin(String username, String password) throws SQLException;
}
