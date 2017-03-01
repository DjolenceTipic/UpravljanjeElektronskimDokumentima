package dto;

public class UserDTOforUpdate {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private int ulogaInt;
	private int categoryInt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUlogaInt() {
		return ulogaInt;
	}
	public void setUlogaInt(int ulogaInt) {
		this.ulogaInt = ulogaInt;
	}
	public int getCategoryInt() {
		return categoryInt;
	}
	public void setCategoryInt(int categoryInt) {
		this.categoryInt = categoryInt;
	}
	public UserDTOforUpdate(int id, String firstName, String lastName, String username, int ulogaInt, int categoryInt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.ulogaInt = ulogaInt;
		this.categoryInt = categoryInt;
	}
	public UserDTOforUpdate() {
		super();
	}
	@Override
	public String toString() {
		return "UserDTOforUpdate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", ulogaInt=" + ulogaInt + ", categoryInt=" + categoryInt + "]";
	}
	
	
}
