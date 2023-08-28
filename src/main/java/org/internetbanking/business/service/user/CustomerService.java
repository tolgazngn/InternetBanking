package org.internetbanking.business.service.user;

import org.internetbanking.business.entity.person.Customer;
import org.internetbanking.business.utils.MessageDigestUtil;
import org.internetbanking.dataaccsess.accountactivities.AccountActivitiesManager;
import org.internetbanking.dataaccsess.customer.CustomerManager;
import org.internetbanking.dataaccsess.moneytransfer.MoneyTransferManager;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.internetbanking.business.utils.inputCharacters.isNumber;

public class CustomerService {
    Scanner scan = new Scanner(System.in);
    Customer customer = new Customer();
    CustomerManager customerManager = new CustomerManager();
    MessageDigestUtil md = new MessageDigestUtil();

    public void addCustomer() throws NoSuchAlgorithmException {
        Customer customer = new Customer();

        //User Input
        System.out.println("--------ADD CUSTOMER---------");
        System.out.print("TC: ");
        customer.setTc(scan.next());

        System.out.print("Name: ");
        customer.setName(scan.next());

        System.out.print("Surname: ");
        customer.setSurname(scan.next());

        System.out.print("Email: ");
        customer.setEmail(scan.next());

        System.out.print("Phone Number: ");
        customer.setPhoneNumber(scan.next());

        System.out.print("Online Password: ");
        customer.setOnlinePassword(md.encryptPassword(scan.next()).toString()); // alınan şifreyi md5 yapar

        //Data Access
        if(customerManager.addCustomer(customer)){
            System.out.print("Customer Added");
        } else {
            System.out.print("ERROR: No Customer Added");
        }
    }

    public Customer set(String tc, Customer customer) throws SQLException {

        //Data Access
        ResultSet resultSet = customerManager.selectCustomer(tc);
        if(resultSet != null){
            while(resultSet.next()) {
                customer.setTc(resultSet.getString("tc"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setOnlinePassword(resultSet.getString("online_password"));
                customer.setCustomerNo(resultSet.getString("customer_no"));
            }
            return customer;
        } else {
            System.out.println("ERROR: No Address Set");
            return null;
        }

    }

    public String changePassword(String tc) throws NoSuchAlgorithmException {
        System.out.println("--------CHANGE CUSTOMER ONLINE PASSWORD---------");

        for(;;){
            //User Input
            System.out.print("New Password: ");
            String password = scan.next();

            //Character Control Proccess
            if (!isNumber(password) || password.length() != 6) {
                System.out.println("Invalid Character");
                continue;
            }

            //MD5 Convert
            md.encryptPassword(password.toString());

            //Data Access
            if (customerManager.changePassword(tc, password)) {
                System.out.println("Online Password Changed");
                return password;
            } else {
                System.out.println("ERROR: Failed Changing");
            }
        }
    }

    public String changeEmail(String tc){
        System.out.println("--------CHANGE EMAIL---------");

        for(;;){
            //User Input
            System.out.print("--->Email: ");
            String email = scan.next();

            //Character Control Proccess

            //Data Access
            if (customerManager.changeEmail(tc, email)) {
                System.out.println("---Email Changed---");
                return email;
            } else {
                System.out.println("--ERROR: Failed Changing");
            }
        }
    }

    public String changePhoneNumber(String tc){
        System.out.println("--------CHANGE PHONE NUMBER---------");

        for(;;){
            //User Input
            System.out.print("--->Phone Number: ");
            String phoneNumber = scan.next();

            //Character Control Proccess
            if (!isNumber(phoneNumber) || phoneNumber.length() != 11) {
                System.out.println("Invalid Character");
                continue;
            }

            //Data Access
            if (customerManager.changePhoneNumber(tc, phoneNumber)) {
                System.out.println("---Phone Number Changed---");
                return phoneNumber;
            } else {
                System.out.println("--ERROR: Failed Changing");
            }
        }
    }

    public void showAccountActivity(String iban) throws SQLException {
        //Data Access
        AccountActivitiesManager accountActivitiesManager = new AccountActivitiesManager();

        MoneyTransferManager moneyTransferManager = new MoneyTransferManager();

        ResultSet resultSet = accountActivitiesManager.showActivity(iban);

        while (resultSet.next()){
            System.out.println("---Sender---");
            System.out.println("Name: " + moneyTransferManager.ownerOfTheIban(resultSet.getString("sender_iban")));
            System.out.println("Iban: " + resultSet.getString("sender_iban"));

            System.out.println("---Receiver---");
            System.out.println("Name: " + moneyTransferManager.ownerOfTheIban(resultSet.getString("receiver_iban")));
            System.out.println("Iban: " + resultSet.getString("receiver_iban"));

            System.out.println("---Amount Send: " + resultSet.getInt("amount"));

            System.out.println("---Activity Date: " + resultSet.getString("activity_date"));
        }
    }
}
