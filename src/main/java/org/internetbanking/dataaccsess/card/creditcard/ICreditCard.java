package org.internetbanking.dataaccsess.card.creditcard;

import org.internetbanking.business.entity.card.CreditCard;
import org.internetbanking.dataaccsess.card.ICard;

public interface ICreditCard extends ICard {
    public boolean addCreditCard(CreditCard creditCard);
    public boolean increaseLimit(String tc, int amount);
    public int selectLimit(String tc);
}
