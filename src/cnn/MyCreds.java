package cnn;
//Ricardo Ortega 12/6
public class MyCreds {

	private static MyCreds defaultInstance;
	private String username;
	private String password;
		
	public MyCreds(String username, String password) {
		this.username = "obsidian";
		this.password = "SolidState?";
	}

	public static MyCreds getDefaultInstance() {
		return defaultInstance;
	}
	public static void setDefaultInstance(MyCreds defaultInstance) {
		MyCreds.defaultInstance = defaultInstance;
	}
	public  String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = "obsidian";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = "SolidState?";
	}
}
