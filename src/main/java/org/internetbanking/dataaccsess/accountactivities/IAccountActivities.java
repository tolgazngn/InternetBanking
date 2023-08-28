package org.internetbanking.dataaccsess.accountactivities;

import java.sql.ResultSet;

public interface IAccountActivities {
    public boolean addActivity(String senderIban, String receiverIban, int amount);

    ResultSet showActivity(String iban);
}
