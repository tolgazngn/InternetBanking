package org.internetbanking.business.service.user;

import org.internetbanking.business.entity.person.CustomerAddress;
import org.internetbanking.dataaccsess.address.AddressManager;
import org.internetbanking.dataaccsess.address.IAddress;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.internetbanking.business.utils.inputCharacters.isLetters;
import static org.internetbanking.business.utils.inputCharacters.isSpecialCharacters;

public class AddressService {
    Scanner scan = new Scanner(System.in);
    IAddress addressManager = new AddressManager();
    public CustomerAddress add(CustomerAddress customerAddress){

        for(;;){
            //User Input
            System.out.print("Country:");
            customerAddress.setCountry(scan.nextLine());

            System.out.print("Disctrict:");
            customerAddress.setDistrict(scan.nextLine());

            System.out.print("Neighbourhood:");
            customerAddress.setNeighbourhood(scan.nextLine());

            System.out.print("Street:");
            customerAddress.setStreet(scan.nextLine());

            System.out.print("Apartment No:");
            customerAddress.setApartment_no(scan.nextLine());

            System.out.print("Door No:");
            customerAddress.setDoor_no(scan.nextLine());

            //Character Control Proccess
            if(!isLetters(customerAddress.getCountry() + customerAddress.getDistrict() + customerAddress.getNeighbourhood() + customerAddress.getStreet())){
                System.out.println("Ivalid Character");
                continue;
            } else if (isSpecialCharacters(customerAddress.getApartmentNo() + customerAddress.getDoorNo())){
                System.out.println("Ivalid Character");
                continue;
            }

            //Data Access
            if(addressManager.addAddress(customerAddress)){
                System.out.println("Address Added");
                return customerAddress;
            } else {
                System.out.println("ERROR: No Address Added");
                return null;
            }

        }
    }

    public void delete(String tc){

        //Data Access
        if(addressManager.deleteAddress(tc)){
            System.out.println("Address Deleted");
        } else {
            System.out.println("ERROR: No Address Deleted");
        }

    }

    public CustomerAddress set(String tc, CustomerAddress customerAddress) throws SQLException {

        //Data Access
        ResultSet resultSet = addressManager.select(tc);
        if(resultSet != null){
            while(resultSet.next()) {
                customerAddress.setTc(resultSet.getString("tc"));
                customerAddress.setCountry(resultSet.getString("country"));
                customerAddress.setDistrict(resultSet.getString("district"));
                customerAddress.setNeighbourhood(resultSet.getString("neighbourhood"));
                customerAddress.setStreet(resultSet.getString("street"));
                customerAddress.setApartment_no(resultSet.getString("apartment_no"));
                customerAddress.setDoor_no(resultSet.getString("door_no"));
            }
            return customerAddress;
        } else {
            System.out.println("ERROR: No Address Set");
            return null;
        }

    }
}
