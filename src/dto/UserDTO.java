package dto;

import model.Category;
import model.RoleOfUser;
import model.User;
import service.UserService;

public class UserDTO {

	private Integer id;
	private String userFirstName;
	private String userLastName;
	private String userUserName;
	private int ulogaInt;
	private Category category;
	
	public UserService service;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserUserName() {
		return userUserName;
	}

	public void setUserUserName(String userUserName) {
		this.userUserName = userUserName;
	}

	public int getUloga() {
		return ulogaInt;
	}

	public void setUloga(int ulogaInt) {
		this.ulogaInt = ulogaInt;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public UserDTO(Integer id, String userFirstName, String userLastName, String userUserName, int ulogaInt,
			Category category) {
		super();
		this.id = id;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userUserName = userUserName;
		this.ulogaInt = ulogaInt;
		this.category = category;
	}

	public UserDTO() {
		super();
	};

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userUserName=" + userUserName + ", ulogaInt=" + ulogaInt + ", category=" + category + "]";
	}
	
	
}
