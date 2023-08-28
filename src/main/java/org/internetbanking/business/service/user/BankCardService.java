package org.internetbanking.business.service.user;

import org.internetbanking.business.entity.card.BankCard;
import org.internetbanking.business.utils.MessageDigestUtil;
import org.internetbanking.dataaccsess.card.bankcard.BankCardManager;
import org.internetbanking.dataaccsess.moneytransfer.MoneyTransferManager;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.internetbanking.business.utils.inputCharacters.*;

public class BankCardService {
    Scanner scan = new Scanner(System.in);
    BankCardManager bankCardManager = new BankCardManager();
    MessageDigestUtil md = new MessageDigestUtil();
    public BankCard add(String tc) throws SQLException, NoSuchAlgorithmException {
        BankCard bankCard = new BankCard();

        System.out.println("--------ADD BANK CARD---------");

        for (; ; ) {
            //User Input
            System.out.print("Bank Card Password: ");
            bankCard.setPassword(scan.next());

            System.out.print("Bank Card Balance: ");
            bankCard.setBalance(scan.nextInt());


            //Character Control Proccess
            if (!isNumber(bankCard.getPassword()) || bankCard.getPassword().length() != 4) {
                System.out.println("Ivalid Character");
                continue;
            }

            //MD5 Convert
            md.encryptPassword(bankCard.getPassword().toString()); //Şifre karakter kontrolünden geçerse md5 ile şifreler

            //Data Access
            if (bankCardManager.addBankCard(bankCard)) {
                System.out.println("Bank Card Added Added");
                return set(tc);
            } else {
                System.out.println("ERROR: No Bank Card Added");
                return null;
            }

        }
    }

    public BankCard set(String tc) throws SQLException {

        BankCard bankCard = new BankCard();

        //Data Access
        ResultSet resultSet = bankCardManager.select(tc);
        if(resultSet != null){
            while(resultSet.next()) {
                bankCard.setTc(resultSet.getString("tc"));
                bankCard.setCardNumber(resultSet.getString("number"));
                bankCard.setPassword(resultSet.getString("password"));
                bankCard.setValidThru(resultSet.getString("valid_thru"));
                bankCard.setBalance(resultSet.getInt("balance"));
                bankCard.setCvc(resultSet.getString("cvc"));
                bankCard.setIban(resultSet.getString("iban"));
            }
            return bankCard;
        } else {
            System.out.println("ERROR: No Bank Card Set");
            return null;
        }

    }

    public String changePassword(String tc) throws NoSuchAlgorithmException {

        System.out.println("--------CHANGE BANK CARD PASSWORD---------");

        for(;;){
            //User Input
            System.out.print("New Password: ");
            String password = scan.next();

            //Character Control Proccess
            if (!isNumber(password) || password.length() != 4) {
                System.out.println("Invalid Character");
                continue;
            }

            //MD5 Convert
            md.encryptPassword(password.toString());

            //Data Access
            if (bankCardManager.updatePassword(tc, password)) {
                System.out.println("Password Changed");
                return password;
            } else {
                System.out.println("ERROR: Failed Changing");
            }
        }
    }

    public void currentBalance(String tc){
        //Data Access
        int balance = bankCardManager.selectBalance(tc);
        System.out.println("Current Balance: " + balance);
    }

}
