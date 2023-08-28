package org.internetbanking.business.entity.card;

public class BankCard extends Card{
	private String iban;

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	public void showInfos(){
		System.out.println("Card Number: " + getCardNumber() + "\n" +
				"Valid Thru: "+ getValidThru() + "\n" +
				"Balance: "+ getBalance() + "\n" +
				"CVC: "+ getCvc() + "\n" +
				"IBAN: "+ this.iban + "\n");
	}
}