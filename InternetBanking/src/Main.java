import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Customer customer = new Customer("Tolga", "Zengin", "tolga@gmail.com", "5316252457", "12345678", "10000000000", "123456");
		String tc = customer.getTc();
		String cNo = customer.getNo();
		System.out.println("Welcome to Internet Banking Program");
		
		
		
		//tc-mNo kontrol
		
		while(true) {
			System.out.print("TC No/Customer No: ");
			String userNo = scan.nextLine();
			
			boolean control = control1(userNo, tc, cNo);
			if (control == true) {
				break;
			}
		
		}
	}
	
	public static boolean control1(String userNo, String tc, String cNo) {
		boolean r;
		
		if(userNo.equals(tc)) {
			System.out.println("Welcome");
			r = true;
		}
		else if(userNo.equals(cNo)){
			System.out.println("Welcome");
			r = true;
		}
		else {
			System.out.println("Try Again");
			r = false;
		}
		return r;
	}
	
}
