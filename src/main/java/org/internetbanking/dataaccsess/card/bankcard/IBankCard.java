package org.internetbanking.dataaccsess.card.bankcard;

import org.internetbanking.business.entity.card.BankCard;
import org.internetbanking.dataaccsess.card.ICard;

public interface IBankCard extends ICard {
    public boolean addBankCard(BankCard bankCard);

}
