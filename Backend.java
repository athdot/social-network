/**
 * Account - CS180 PJ04
 * This is a simple class to manage a user Account
 * @author Charles Graham
 * @version 07/17/2021
 *
 **/

public class Backend {
	DataManagement data;
	Account loggedAccount;
	
	public Backend() {
		data = new DataManagement();
	}
	
	//How to call something
	//Inside requests
	//
	
	//Returns a return stream
	public String[] streamReader(String[] requests) {
		//Requests are
		//login[dnsda,dansdnasn]
	}
	
	private boolean login(String username, String password) {
		try {
			Account temp = data.getAccount(username);
			if (temp.correctPassword(CryptoHash.getHash(password))) {
				loggedAccount = temp;
				return true;
			}
		} catch(Exception e) {
			//Incorrect username or password
		}
		return false;
	}
}
