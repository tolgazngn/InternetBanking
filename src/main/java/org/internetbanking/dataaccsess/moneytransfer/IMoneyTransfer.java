package org.internetbanking.dataaccsess.moneytransfer;

public interface IMoneyTransfer {
    public String moneyTransfer(String senderIban, String receiverIban, int amount);
    public String ownerOfTheIban(String iban);
}
