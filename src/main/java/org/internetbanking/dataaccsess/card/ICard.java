package org.internetbanking.dataaccsess.card;

import org.internetbanking.business.entity.card.BankCard;
import org.internetbanking.business.entity.card.Card;

import java.sql.ResultSet;

public interface ICard {
    public boolean updatePassword(String tc, String password);
    public int selectBalance(String tc);
    public ResultSet select(String tc);
    public boolean deleteCard(String tc);
}
