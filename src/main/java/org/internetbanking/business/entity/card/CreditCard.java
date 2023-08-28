package org.internetbanking.business.entity.card;

public class CreditCard extends Card{
	private int limit;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void showInfos(){
		System.out.println("Card Number: " + getCardNumber() + "\n" +
				"Valid Thru: "+ getValidThru() + "\n" +
				"Balance: "+ getBalance() + "\n" +
				"CVC: "+ getCvc() + "\n" +
				"Limit: "+ this.limit + "\n");
	}
}