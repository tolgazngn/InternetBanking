//sub class, child class, derived class
public class Customer extends Person{
	private String no;
	private String tc;
	private String password;
	
	public Customer(String name, String surname, String email, String phoneNumber, String no, String tc, String password) {
		super(name, surname, email, phoneNumber);
		this.no = no;
		this.tc = tc;
		this.password = password;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void showInfos() {
		System.out.println("--------------------------- \n"
				+ "Name: "+super.getName()+"\n"
				+ "Surname: "+super.getSurname()+"\n"
				+ "Customer Number: "+this.no+"\n"
				+ "TC: "+this.tc+"\n"
				+ "Email: "+super.getEmail()+"\n"
				+ "Phone Number: "+super.getPhoneNumber()+"\n"
				+ "--------------------------- \n");
	}

	
}