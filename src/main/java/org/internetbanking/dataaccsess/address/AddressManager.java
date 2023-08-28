package org.internetbanking.dataaccsess.address;

import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;
import org.internetbanking.dataaccsess.database.IDatabase;
import org.internetbanking.business.entity.person.CustomerAddress;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressManager implements IAddress {
    IDatabase database = new PostgreSqlDatabase();

    @Override
    public boolean addAddress(CustomerAddress address) {
        try {
            database.connectionOpen();
            String addSql = "INSERT INTO public.address(tc, country, district, neighbourhood, street, apartment_no, door_no) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(addSql);
            preparedStatement.setString(1, address.getTc());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getDistrict());
            preparedStatement.setString(4, address.getNeighbourhood());
            preparedStatement.setString(5, address.getStreet());
            preparedStatement.setString(6, address.getApartmentNo());
            preparedStatement.setString(7, address.getDoorNo());

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAddress(String tc) {
        try {
            database.connectionOpen();

            String addSql = "DELETE FROM customer_address WHERE tc = (?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(addSql);
            preparedStatement.setString(1, tc);

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ResultSet select(String tc) {
        database.connectionOpen();
        ResultSet resultSet;
        try{
            String sql = "SELECT * FROM customerAddress WHERE tc = (?)";
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
}
