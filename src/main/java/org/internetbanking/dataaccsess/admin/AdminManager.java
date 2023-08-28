package org.internetbanking.dataaccsess.admin;

import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;
import org.internetbanking.dataaccsess.database.IDatabase;
import org.internetbanking.business.entity.person.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminManager implements IAdmin {
    IDatabase database = new PostgreSqlDatabase();
    @Override
    public ResultSet selectAdmin(String username) {
        try {
            database.connectionOpen();

            String sql = "Select * From public.admin Where username = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            return rs;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setAdmin(String username, Admin admin) {
        database.connectionOpen();
        try{
            ResultSet resultSet = selectAdmin(username);

            while(resultSet.next()) {
                admin.setUsername(resultSet.getString("username"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("surname"));
                admin.setSurname(resultSet.getString("surname"));
            }

            database.connectionClose();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
