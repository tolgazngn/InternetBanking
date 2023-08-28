package org.internetbanking.business.service.user;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.internetbanking.business.entity.person.Customer;
import org.internetbanking.dataaccsess.authentication.AuthenticationManager;
import org.internetbanking.dataaccsess.authentication.IAuthentication;
import org.internetbanking.business.utils.MessageDigestUtil;

import static org.internetbanking.business.utils.inputCharacters.isNumber;

public class UserAuthentication {
	Scanner scan = new Scanner(System.in);
	
	IAuthentication authenticationManager = new AuthenticationManager();
	
	Customer customer = new Customer();
	
	MessageDigestUtil md = new MessageDigestUtil();
	
	public Customer login() throws NoSuchAlgorithmException, SQLException {

		for(;;) {

            //--User Input
			System.out.print("--->TC/Customer No:");
			String tc = scan.nextLine();

            System.out.print("--->Password:");
            String password = scan.nextLine();
			
			// Null And Character Control Proccess
			if(tc.isEmpty() || password.isEmpty()) {
				System.out.println("--CANNOT BE BLANK!! \n");
				continue;
			}else if(!isNumber(tc) || !isNumber(password)) {
				System.out.println("--TC AND PASSWORD MUST BE NUMBERS!! \n");
				continue;
			}else if(tc.length() != 11 || password.length() != 6){
				System.out.println("--MISSING OR EXTRA CHARACTERS \n");
				continue;
			}

            //---Database Control Proccess
            String convertPassword = md.encryptPassword(password).toString();

			ResultSet resultSet = authenticationManager.loginUser(tc, convertPassword, customer);

            if(resultSet != null){

				while(resultSet.next()){
					customer.setTc(resultSet.getString("tc"));
					customer.setCustomerNo(resultSet.getString("customer_no"));
					customer.setName(resultSet.getString("name"));
					customer.setSurname(resultSet.getString("surname"));
					customer.setOnlinePassword(resultSet.getString("online_password"));
					customer.setEmail(resultSet.getString("email"));
					customer.setPhoneNumber(resultSet.getString("phone_number"));
				}

                return customer;
            } else {
				System.out.println("--ERROR: Wrong TC or Password ");
			}
		}
	}
	
}
