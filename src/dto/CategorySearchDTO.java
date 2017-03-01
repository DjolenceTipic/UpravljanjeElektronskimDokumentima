package dto;

public class CategorySearchDTO {

	private String name;
	private String occur;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccur() {
		return occur;
	}
	public void setOccur(String occur) {
		this.occur = occur;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public CategorySearchDTO(String name, String occur, String type) {
		super();
		this.name = name;
		this.occur = occur;
		this.type = type;
	}
	public CategorySearchDTO() {
		super();
	}
	
}
