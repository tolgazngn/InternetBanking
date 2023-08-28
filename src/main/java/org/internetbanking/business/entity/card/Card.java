package org.internetbanking.business.entity.card;


import java.util.Date;

public class Card{
	private String tc;
	private String cardNumber;
	private String password;
	private String validThru;
	private int balance;
	private String cvc;


	public String getTc() { return tc;}
	public void setTc(String tc) { this.tc = tc;}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getValidThru() {
		return validThru;
	}
	public void setValidThru(String validThru) {
		this.validThru = validThru;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
}