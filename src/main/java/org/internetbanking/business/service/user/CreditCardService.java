package org.internetbanking.business.service.user;

import org.internetbanking.business.entity.card.CreditCard;
import org.internetbanking.business.utils.MessageDigestUtil;
import org.internetbanking.dataaccsess.card.creditcard.CreditCardManager;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.internetbanking.business.utils.inputCharacters.isNumber;

public class CreditCardService {
    Scanner scan = new Scanner(System.in);
    MessageDigestUtil md = new MessageDigestUtil();
    CreditCardManager creditCardManager = new CreditCardManager();
    public CreditCard add(String tc) throws SQLException, NoSuchAlgorithmException {
        CreditCard creditCard = new CreditCard();

        System.out.println("--------ADD CREDIT CARD---------");

        for(;;){

            System.out.print("Card Number: ");
            creditCard.setCardNumber(scan.next());

            System.out.print("Balance: ");
            creditCard.setBalance(scan.nextInt());

            System.out.print("Limit: ");
            creditCard.setLimit(Integer.parseInt(scan.next()));

            //Character Control Proccess
            if(!isNumber(creditCard.getCardNumber()) || creditCard.getBalance() <= 0 || creditCard.getLimit() <= 0){
                System.out.print("Invalid Value");
                continue;
            }

            //MD5 Convert
            md.encryptPassword(creditCard.getPassword().toString());

            //Data Access
            if (creditCardManager.addCreditCard(creditCard)) {
                System.out.println("Credit Card Added Added");
                return set(tc, creditCard);
            } else {
                System.out.println("ERROR: No Bank Card Added");
                return null;
            }
        }
    }

    public CreditCard set(String tc, CreditCard creditCard) throws SQLException {

        //Data Access
        ResultSet resultSet = creditCardManager.select(tc);
        if(resultSet != null){
            while(resultSet.next()) {
                creditCard.setTc(resultSet.getString("tc"));
                creditCard.setCardNumber(resultSet.getString("number"));
                creditCard.setPassword(resultSet.getString("password"));
                creditCard.setValidThru(resultSet.getString("valid_thru"));
                creditCard.setBalance(resultSet.getInt("balance"));
                creditCard.setCvc(resultSet.getString("cvc"));
                creditCard.setLimit(resultSet.getInt("limit"));
            }
            return creditCard;
        } else {
            System.out.println("ERROR: No Getting Credit Card Infos");
            return null;
        }

    }

    public String changePassword(String tc) throws NoSuchAlgorithmException {

        System.out.println("--------CHANGE CREDIT CARD PASSWORD---------");

        for(;;){
            //User Input
            System.out.print("New Password: ");
            String password = scan.next();

            //Character Control Proccess
            if (!isNumber(password) || password.length() != 4) {
                System.out.println("Ivalid Character");
                continue;
            }

            //MD5 Convert
            md.encryptPassword(password.toString());

            //Data Access
            if (creditCardManager.updatePassword(tc, password)) {
                System.out.println("Password Changed");
                return password;
            } else {
                System.out.println("ERROR: Failed Changing");
            }
        }
    }

    public int increaseLimit(String tc){
        System.out.println("--------INCREASE CREDIT CARD LIMIT---------");

        for(;;){
            //User Input
            System.out.print("Limit: ");
            int amount = scan.nextInt();

            //Limit Value Control Proccess
            if(amount <= 0){
                System.out.print("Invalid Value");
                continue;
            }

            //Data Access
            if(creditCardManager.increaseLimit(tc, amount)){
                System.out.print("Limit Increased");
                return creditCardManager.selectLimit(tc);
            } else {
                System.out.print("ERROR: No Limit Increased");
            }
        }

    }


    public void currentBalance(String tc){
        //Data Access
        int balance = creditCardManager.selectBalance(tc);
        System.out.println("Current Balance: " + balance);
    }

    public void delete(){
        System.out.println("--------DELETE CREDIT CARD---------");
        System.out.print("TC: ");
        creditCardManager.deleteCard(scan.next());
    }
}
