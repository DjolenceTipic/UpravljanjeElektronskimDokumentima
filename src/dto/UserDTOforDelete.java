package dto;

public class UserDTOforDelete {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDTOforDelete(Integer id) {
		super();
		this.id = id;
	}

	public UserDTOforDelete() {
		super();
	}

	@Override
	public String toString() {
		return "UserDTOforDelete [id=" + id + "]";
	}
	
	
}
