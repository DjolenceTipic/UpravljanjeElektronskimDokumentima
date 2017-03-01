package dto;

public class EBookDeleteDTO {
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EBookDeleteDTO(String id) {
		super();
		this.id = id;
	}

	public EBookDeleteDTO() {
		super();
	}

	@Override
	public String toString() {
		return "EBookDeleteDTO [id=" + id + "]";
	}
	
	
}
