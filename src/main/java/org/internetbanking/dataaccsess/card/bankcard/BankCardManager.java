package org.internetbanking.dataaccsess.card.bankcard;

import org.internetbanking.business.entity.card.BankCard;
import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;
import org.internetbanking.dataaccsess.database.IDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankCardManager implements IBankCard {
    IDatabase database = new PostgreSqlDatabase();

    @Override
    public boolean addBankCard(BankCard bankCard) {

        try {
            database.connectionOpen();

            String addSql = "INSERT INTO public.bank_card(tc, password, balance) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(addSql);
            preparedStatement.setString(1, bankCard.getTc());
            preparedStatement.setString(2, bankCard.getPassword());
            preparedStatement.setInt(3, bankCard.getBalance());

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePassword(String tc, String password) {
        try {
            database.connectionOpen();

            String addSql = "UPDATE bank_card SET password = (?) WHERE tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(addSql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, tc);

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int selectBalance(String tc) {
        try {
            database.connectionOpen();

            String sql = "Select balance From bank_card Where tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tc);

            ResultSet resultSet = preparedStatement.executeQuery();

            database.connectionClose();

            int balance = -1;

            while (resultSet.next()){
                balance = resultSet.getInt("balance");
            }

            return balance;
        } catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ResultSet select(String tc) {
        database.connectionOpen();
        ResultSet resultSet;
        try{
            String sql = "SELECT * FROM bank_card WHERE tc = (?)";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tc);

            resultSet = preparedStatement.executeQuery();

            database.connectionClose();

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultSet;
    }

    @Override
    public boolean deleteCard(String tc) {
        try {
            database.connectionOpen();

            String addSql = "DELETE FROM bank_card WHERE tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(addSql);
            preparedStatement.setString(1, tc);

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
