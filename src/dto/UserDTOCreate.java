package dto;

public class UserDTOCreate {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int roleInt;
	private int categoryInt;

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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleInt() {
		return roleInt;
	}
	public void setRoleInt(int roleInt) {
		this.roleInt = roleInt;
	}
	public int getCategoryInt() {
		return categoryInt;
	}
	public void setCategoryInt(int categoryInt) {
		this.categoryInt = categoryInt;
	}
	public UserDTOCreate(String firstName, String lastName, String userName, String password, int roleInt,
			int categoryInt) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.roleInt = roleInt;
		this.categoryInt = categoryInt;
	}
	
	public UserDTOCreate() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserDTOCreate [firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", roleInt=" + roleInt + ", categoryInt=" + categoryInt + "]";
	}
	
}
