package org.internetbanking.dataaccsess.card.creditcard;

import org.internetbanking.business.entity.card.CreditCard;
import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;
import org.internetbanking.dataaccsess.database.IDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardManager implements ICreditCard {
    IDatabase database = new PostgreSqlDatabase();


    @Override
    public boolean addCreditCard(CreditCard creditCard) {
        try {
            database.connectionOpen();

            String addSql = "INSERT INTO public.credit_card(tc, \"number\", balance, \"limit\") VALUES ( ?, ?, ?, ?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(addSql);
            preparedStatement.setString(1, creditCard.getTc());
            preparedStatement.setString(2, creditCard.getCardNumber());
            preparedStatement.setInt(3, creditCard.getBalance());
            preparedStatement.setInt(4, creditCard.getLimit());

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean increaseLimit(String tc, int amount) {
        try {
            database.connectionOpen();

            String addSql = "UPDATE credit_card SET limit += (?) WHERE tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(addSql);
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, tc);

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int selectLimit(String tc) {
        try {
            database.connectionOpen();

            String sql = "Select limit From credit_card Where tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tc);

            ResultSet resultSet = preparedStatement.executeQuery();

            database.connectionClose();

            return resultSet.getInt("limit");
        } catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean updatePassword(String tc, String password) {
        try {
            database.connectionOpen();

            String addSql = "UPDATE credit_card SET password = (?) WHERE tc = (?)";

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

            String sql = "Select balance From credit_card Where tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tc);

            ResultSet resultSet = preparedStatement.executeQuery();

            database.connectionClose();

            return resultSet.getInt("balance");
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
            String sql = "SELECT * FROM credit_card WHERE tc = (?)";
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

            String addSql = "DELETE FROM credit_card WHERE tc = (?)";

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
