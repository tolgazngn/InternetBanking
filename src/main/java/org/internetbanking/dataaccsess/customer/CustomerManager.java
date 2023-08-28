package org.internetbanking.dataaccsess.customer;

import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;
import org.internetbanking.dataaccsess.database.IDatabase;
import org.internetbanking.business.entity.person.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManager implements ICustomer {
    IDatabase database = new PostgreSqlDatabase();

    @Override
    public boolean addCustomer(Customer customer) {
        try {
            database.connectionOpen();

            String sql = "INSERT INTO public.customer(tc, name, surname, email, phone_number, online_password) VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement;
            preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, customer.getTc());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getSurname());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getOnlinePassword());

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ResultSet selectCustomer(String tc) {
        try {
            database.connectionOpen();

            String sql = "Select * From customer Where tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tc);

            ResultSet rs = preparedStatement.executeQuery();

            database.connectionClose();

            return rs;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean changePassword(String tc, String password) {
        try {
            database.connectionOpen();

            String sql = "UPDATE customer SET online_password = (?) WHERE tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, tc);

            preparedStatement.executeUpdate();

            database.connectionClose();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean changeEmail(String tc, String email){
        try {
            database.connectionOpen();

            String sql = "UPDATE customer SET email = (?) WHERE tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, tc);

            preparedStatement.executeUpdate();

            database.connectionClose();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changePhoneNumber(String tc, String phoneNumber) {
        try {
            database.connectionOpen();

            String sql = "UPDATE customer SET phone_number = (?) WHERE tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, tc);

            preparedStatement.executeUpdate();

            database.connectionClose();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
