package org.internetbanking.dataaccsess.moneytransfer;

import org.internetbanking.dataaccsess.accountactivities.AccountActivitiesManager;
import org.internetbanking.dataaccsess.database.IDatabase;
import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyTransferManager implements IMoneyTransfer{
    IDatabase database = new PostgreSqlDatabase();
    @Override
    public String moneyTransfer(String senderIban, String receiverIban, int amount) {
        try {
            database.connectionOpen();

            String sql = "BEGIN;\n" +
                    "UPDATE bank_card SET balance = balance - (?)\n" +
                    "    WHERE iban = (?);\n" +
                    "UPDATE bank_card SET balance = balance + (?)\n" +
                    "    WHERE iban = (?);\n" +
                    "COMMIT;";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, senderIban);
            preparedStatement.setInt(3, amount);
            preparedStatement.setString(4, receiverIban);

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        AccountActivitiesManager accountActivitiesManager = new AccountActivitiesManager();
        accountActivitiesManager.addActivity(senderIban, receiverIban, amount);
        return ownerOfTheIban(receiverIban);
    }

    @Override
    public String ownerOfTheIban(String iban) {
        String fullName = null;
        try {
            database.connectionOpen();

            String sql = "SELECT tc FROM bank_card WHERE iban = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, iban);

            ResultSet resultSet = preparedStatement.executeQuery();

            String tc = null;
            while(resultSet.next()){
                tc = resultSet.getString("tc");
            }
            /*
            Select CONCAT(name, ' ', surname) from customer
            inner join bank_card
            on customer.tc = bank_card.tc
            where bank_card.iban = '1';
            * */
            String sql2 = "SELECT CONCAT(name, ' ', surname) AS nameSurname FROM customer WHERE tc = (?)";
            preparedStatement = database.getConnection().prepareStatement(sql2);
            preparedStatement.setString(1, tc);

            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                fullName = resultSet.getString("nameSurname");    
            }
            
            database.connectionClose();

        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return fullName;
    }

}
