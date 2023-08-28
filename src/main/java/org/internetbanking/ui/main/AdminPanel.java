package org.internetbanking.ui.main;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

import org.internetbanking.business.service.admin.AdminAuthentication;
import org.internetbanking.business.entity.person.Admin;
import org.internetbanking.business.entity.person.Person;
import org.internetbanking.business.service.user.CreditCardService;
import org.internetbanking.dataaccsess.card.creditcard.CreditCardManager;

public class 	AdminPanel {
	public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
		Scanner scan = new Scanner(System.in);
		
		Admin admin = new Admin();
			
		AdminAuthentication adminAuthentication = new AdminAuthentication();
		CreditCardService creditCardService = new CreditCardService();
		
		System.out.print("-----------------");
		System.out.print("---ADMIN PANEL---");
		System.out.println("-----------------");
		
		admin = adminAuthentication.login();
		
		System.out.println("1-Customer Add\n"
				+ "2-Credit Card Add\n"
				+ "3-Credit Card Delete\n"
				+ "0-Exit System");

		System.out.print("Select: ");
		String select = scan.next();
		
		switch(select) {
		case "0":
			System.exit(0);
		case "1":
			//adminAuthentication.addCustomer();
			break;
			
		case "2":
			//creditCardService.add();
			break;
			
		case "3":
			creditCardService.delete();
			break;
			
		default:
			
		}
	}
}
