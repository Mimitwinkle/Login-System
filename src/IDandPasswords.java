import java.util.HashMap;

public class IDandPasswords {
	// initialize HashMap to store IDs & passwords
	HashMap<String, String> loginInfo = new HashMap<String, String>();
	
	// add IDs & passwords
	IDandPasswords() {
		loginInfo.put("John", "000");
		loginInfo.put("Jane", "123");
		loginInfo.put("Bob", "456");
		loginInfo.put("Becky", "789");
	}
	
	// retrieves the HashMap
	// protected so that login info is not publicly available
	protected HashMap getLoginInfo() {
		return loginInfo;
	}
	
}
