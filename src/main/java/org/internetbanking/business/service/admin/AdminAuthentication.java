package org.internetbanking.business.service.admin;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.internetbanking.dataaccsess.authentication.AuthenticationManager;
import org.internetbanking.dataaccsess.authentication.IAuthentication;
import org.internetbanking.dataaccsess.customer.CustomerManager;
import org.internetbanking.dataaccsess.customer.ICustomer;
import org.internetbanking.business.utils.MessageDigestUtil;
import org.internetbanking.business.entity.person.Admin;

import static org.internetbanking.business.utils.inputCharacters.isLetters;
import static org.internetbanking.business.utils.inputCharacters.isSpecialCharacters;

public class AdminAuthentication{
	Scanner scan = new Scanner(System.in);
	
	MessageDigestUtil md = new MessageDigestUtil();
	
	IAuthentication authenticationManager = new AuthenticationManager();

	ICustomer customerManager = new CustomerManager();

	Admin admin = new Admin();
	
	
	public Admin login() throws NoSuchAlgorithmException, SQLException {
		for(;;) {
			//---User Input
			System.out.print("--->Username: ");
			String username = scan.next();
			
			System.out.print("--->Password: ");
			String password =scan.next();

			//---Null Control Proccess
			if(username.isEmpty() || password.isEmpty()){
				continue;
			}

			//---Character Control Proccess
			if (!isLetters(username)){
				System.out.println("--USERNAME MUST BE LETTERS");
				continue;
			}

			if(isSpecialCharacters(password)){
				System.out.println("--PASSWORD MUST NOT BE SPECIAL CHARACTERS");
				continue;
			}

			//---Database Control Proccess

			ResultSet resultSet = authenticationManager.loginAdmin(username, md.encryptPassword(password));

			if(resultSet != null) {
				System.out.println("\n---LOGIN SUCCESSFUL---\n");

				while(resultSet.next()){
					admin.setUsername(resultSet.getString("username"));
					admin.setPassword(resultSet.getString("password"));
					admin.setName(resultSet.getString("name"));
					admin.setSurname(resultSet.getString("surname"));
				}

				return admin;
			} else {
				System.out.println("--ERROR: INCORRECT ENTRY!!");
			}
			
		}
	}
}
