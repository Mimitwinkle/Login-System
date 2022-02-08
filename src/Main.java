
public class Main {
	public static void main(String[] args) {
		// initialize TreeMap with login info
		IDandPasswords idandPasswords = new IDandPasswords();
		
		// send the TreeMap with login info to the login page
		LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
	}
}
