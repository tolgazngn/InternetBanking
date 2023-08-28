package org.internetbanking.ui.main;

import org.internetbanking.business.entity.card.BankCard;
import org.internetbanking.business.entity.card.CreditCard;
import org.internetbanking.business.entity.exchangerates.ExchangeRates;
import org.internetbanking.business.entity.person.CustomerAddress;
import org.internetbanking.business.service.user.*;
import org.internetbanking.dataaccsess.database.PostgreSqlDatabase;
import org.internetbanking.dataaccsess.database.IDatabase;
import org.internetbanking.business.entity.person.Customer;
import org.internetbanking.dataaccsess.moneytransfer.MoneyTransferManager;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

import static org.internetbanking.business.utils.inputCharacters.isNumber;

public class UserInterface {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
		Scanner scan = new Scanner(System.in);
		
		UserAuthentication userAuth = new UserAuthentication(); // kullanıcı kimlik doğrulama nesnesi7

		Customer customer;
		BankCard bankCard = null;
		CreditCard creditCard = null;
		CustomerAddress customerAddress;
		ExchangeRates exchangeRates;

		CustomerService customerService = new CustomerService();
		AddressService addressService = new AddressService();
		BankCardService bankCardService = new BankCardService();
		CreditCardService creditCardService = new CreditCardService();
		MoneyTransferService moneyTransferService = new MoneyTransferService();
		LiveExchangeRateService liveExchangeRateService = new LiveExchangeRateService();
		
		System.out.println("----------------------\nWelcome to Internet Banking Program \n----------------------\n \n");

		customer = userAuth.login();

		bankCard = bankCardService.set(customer.getTc());


		System.out.println("\n---LOGIN SUCCESSFUL---\n");
		System.out.println("\n---Welcome Again " + customer.fullName() + "\n");

		System.out.println("---TRANSACTIONS---");
		System.out.println("-->> 1 - Personel Information");
		System.out.println("-->> 2 - Bank Card Transactions");
		System.out.println("-->> 3 - Credit Card Transactions");
		System.out.println("-->> 4 - Money Transfer");
		System.out.println("-->> 5 - Account Activities");
		System.out.println("-->> 6 - Live Exchange Rates");
		System.out.println("-->> 0 - Exit");

		for (;;){
			String select;
			for (;;){
				System.out.print(">> ");
				select = scan.next();

				if(!isNumber(select)){
					System.out.print("ERROR: Invalid Transaction");
				} else {
					break;
				}
			}

			switch (select){
				case "1":
					System.out.println("---PERSONEL TRANSACTIONS---");
					System.out.println("-->> 1 - Show My Personel Infos");
					System.out.println("-->> 2 - Online Password Change");
					System.out.println("-->> 3 - Email Change");
					System.out.println("-->> 4 - Phone Number Change");
					System.out.println("-->> 5 - Address Change");
					System.out.println("-->> 0 - Exit");

					String select1 = null;

					for(;;){
						System.out.print(">> ");
						select1 = scan.next();

						if(!isNumber(select1)){
							System.out.print("ERROR: Invalid Transaction");
						} else {
							break;
						}
					}

					switch(select1){
						case "1":
							customer.showInfos();
							break;
						case "2":
							customer.setOnlinePassword(customerService.changePassword(customer.getTc()));
							break;
						case "3":
							customer.setEmail(customerService.changeEmail(customer.getTc()));
							break;
						case "4":
							customer.setPhoneNumber(customerService.changePhoneNumber(customer.getTc()));
							break;
						case "5":
							//
							break;
						default:
							System.out.print("ERROR: Invalid Transaction");
					}
					break;

				case "2":
					System.out.println("---BANK CARD TRANSACTIONS---");
					System.out.println("-->> 1 - Show My Bank Card Infos");
					System.out.println("-->> 2 - Password Change");
					System.out.println("-->> 3 - Current Balance");
					System.out.println("-->> 0 - Exit");

					String select2 = null;

					for(;;){
						System.out.print(">> ");
						select2 = scan.next();

						if(!isNumber(select2)){
							System.out.print("ERROR: Invalid Transaction");
						} else {
							break;
						}
					}

					switch(select2){
						case "1":
							bankCard.showInfos();
							break;
						case "2":
							bankCardService.changePassword(customer.getTc());
							break;
						case "3":
							bankCardService.currentBalance(customer.getTc());
							break;
						case "0":
							System.exit(0);
						default:
							System.out.print("ERROR: Invalid Transaction");
					}

					break;

				case "3":
					System.out.println("---CREDIT CARD TRANSACTIONS---");
					System.out.println("-->> 1 - Show My Credit Card Infos");
					System.out.println("-->> 2 - Password Change");
					System.out.println("-->> 3 - Current Balance");
					System.out.println("-->> 4 - Increase Limit");
					System.out.println("-->> 5 - Add Credit Card");
					System.out.println("-->> 6 - Delete Credit Card");
					System.out.println("-->> 0 - Exit");

					String select3 = null;

					for(;;){
						System.out.print(">> ");
						select3 = scan.next();

						if(!isNumber(select3)){
							System.out.print("ERROR: Invalid Transaction");
						} else {
							break;
						}
					}

					switch(select3){
						case "1":
							if(creditCard.getTc() != null){
								creditCard.showInfos();
							}
							break;
						case "2":
							creditCardService.changePassword(customer.getTc());
							break;
						case "3":
							creditCardService.currentBalance(customer.getTc());
							break;
						case "4":
							creditCardService.increaseLimit(customer.getTc());
							break;
						case "5":
							creditCardService.add(customer.getTc());
							break;
						case "6":
							creditCardService.delete();
							break;
						case "0":
							System.exit(0);
						default:
							System.out.print("ERROR: Invalid Transaction");
					}
					break;

				case "4":
					if(bankCard.getBalance() <= 0){
						System.out.print("Your Balance: 0");
					} else {
						moneyTransferService.moneyTransfer(bankCard.getIban());
					}
					break;

				case "5":
					customerService.showAccountActivity(bankCard.getIban());
					break;

				case "6":
					//
					break;

				case "0":
					System.exit(0);
					break;

				default:
					System.out.print("ERROR: Invalid Transaction");
			}
		}

	}
	
}
