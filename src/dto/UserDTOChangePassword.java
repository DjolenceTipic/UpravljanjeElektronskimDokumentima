package dto;

public class UserDTOChangePassword {
	private int id;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDTOChangePassword(int id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public UserDTOChangePassword() {
		super();
	}
	@Override
	public String toString() {
		return "UserDTOChangePassword [id=" + id + ", password=" + password + "]";
	}
	
}
