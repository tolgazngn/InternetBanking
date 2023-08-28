package org.internetbanking.dataaccsess.authentication;

import org.internetbanking.dataaccsess.customer.CustomerManager;
import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;
import org.internetbanking.dataaccsess.customer.ICustomer;
import org.internetbanking.dataaccsess.database.IDatabase;
import org.internetbanking.business.entity.person.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationManager implements IAuthentication {
    IDatabase database = new PostgreSqlDatabase();
    ICustomer customerManager = new CustomerManager();

    @Override
    public ResultSet loginUser(String tc, String password, Customer customer) throws SQLException{

        ResultSet resultSet = null;

        try {
            database.connectionOpen();

            String sql = "Select * From customer Where tc = (?) and online_password = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tc);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            database.connectionClose();

        } catch(SQLException e){
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet loginAdmin(String username, String password) throws SQLException{

        ResultSet resultSet = null;

        try {
            database.connectionOpen();

            String sql = "Select * From public.admin Where username = (?) and password = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

        } catch(SQLException e){
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }
}
