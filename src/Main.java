
public class Main {
	public static void main(String[] args) {
		// initialize HashMap with login info
		IDandPasswords idandPasswords = new IDandPasswords();
		
		// send the HashMap with login info to the login page
		LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
	}
}
