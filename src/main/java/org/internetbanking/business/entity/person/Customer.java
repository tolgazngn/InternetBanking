package org.internetbanking.business.entity.person;

//sub class, child class, derived class

public class Customer extends Person{

	private String tc;

	private String customerNo;

	private String onlinePassword;

	private String email;

	private String phoneNumber;
	
	public Customer() {
		
	}
	
	public Customer(String name, String surname, String email, String phoneNumber, String customerNo, String tc, String onlinePassword, String iban) {
		super(name, surname);
		this.customerNo = customerNo;
		this.tc = tc;
		this.onlinePassword = onlinePassword;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String no) {
		this.customerNo = no;
	}

	public String getTc() {
		
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}
	
	public String getOnlinePassword() {
		return onlinePassword;
	}

	public void setOnlinePassword(String onlinePassword) {
		this.onlinePassword = onlinePassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String fullName() {
		return this.getName()+" "+ this.getSurname();
	}

	public void showInfos() {
		System.out.println("--------------------------- \n"
				+ "Name: "+super.getName()+"\n"
				+ "Surname: "+super.getSurname()+"\n"
				+ "Customer Number: "+this.customerNo+"\n"
				+ "TC: "+this.tc+"\n"
				+ "--------------------------- \n");
	}
}