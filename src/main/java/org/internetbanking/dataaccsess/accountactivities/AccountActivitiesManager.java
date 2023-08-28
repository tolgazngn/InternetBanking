package org.internetbanking.dataaccsess.accountactivities;

import org.internetbanking.dataaccsess.database.IDatabase;
import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class AccountActivitiesManager implements IAccountActivities{
    IDatabase database = new PostgreSqlDatabase();
    @Override
    public boolean addActivity(String senderIban, String receiverIban, int amount) {
        try {
            database.connectionOpen();

            String addSql = "INSERT INTO public.account_activities(sender_iban, receiver_iban, activity_date, amount) VALUES (?, ?, (?), ?)";

            Date date = new Date();
            LocalDateTime now = LocalDateTime.now();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(addSql);
            preparedStatement.setString(1, senderIban);
            preparedStatement.setString(2, receiverIban);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(now));
            preparedStatement.setInt(4, amount);

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ResultSet showActivity(String iban) {
        try {
            database.connectionOpen();

            String sql = "Select * From account_activities Where sender_iban = (?) or receiver_iban = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, iban);
            preparedStatement.setString(2, iban);

            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
