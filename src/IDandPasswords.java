import java.util.Map;
import java.util.TreeMap;

public class IDandPasswords {
	// initialize TreeMap to store IDs & passwords
	Map<String, String> loginInfo = new TreeMap<>();
	
	// add IDs & passwords
	IDandPasswords() {
		loginInfo.put("John", "000");
		loginInfo.put("Jane", "123");
		loginInfo.put("Bob", "456");
		loginInfo.put("Becky", "789");
	}
	
	// retrieves the TreeMap
	// protected so that login info is not publicly available
	protected Map<String, String> getLoginInfo() {
		return loginInfo;
	}
	
}
