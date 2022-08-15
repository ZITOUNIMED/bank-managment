package bank.managment.backend.constants;

public enum FonctionalitiesTypes {
	MANAGE_USERS("/users", "Manage Users"), 
	MANAGE_LOGS("/logs", "Manage Logs"),
	MANAGE_PERMISSIONS("/permissions", "Manage Permissions");
	
	private String path;
	private String label;
	
	FonctionalitiesTypes(String path, String label){
		this.path = path;
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getPath() {
		return path;
	}
}
