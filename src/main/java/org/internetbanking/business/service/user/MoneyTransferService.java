package org.internetbanking.business.service.user;

import org.internetbanking.dataaccsess.moneytransfer.MoneyTransferManager;

import java.util.Scanner;

import static org.internetbanking.business.utils.inputCharacters.isSpecialCharacters;

public class MoneyTransferService {
    Scanner scan = new Scanner(System.in);
    public int moneyTransfer(String senderIban){
        System.out.println("--------MONEY TRANSFER---------");

        MoneyTransferManager moneyTransferManager = new MoneyTransferManager();

        for(;;){
            //User Input
            System.out.print("Receiver Iban: ");
            String receiverIban = scan.next();

            System.out.print("Amount: ");
            int amount = scan.nextInt();

            //Character Control Proccess
            if(isSpecialCharacters(receiverIban)){
                System.out.println("Invalid Iban");
                continue;
            } else if (amount <= 0) {
                System.out.println("Invalid Amount");
                continue;
            }

            //Data Access
            if(moneyTransferManager.moneyTransfer(senderIban, receiverIban, amount) != null){
                System.out.println("Transfer Successful");
                return amount*(-1);
            } else {
                System.out.println("Transfer UNSUCCESSFUL");
            }

        }

    }
}
