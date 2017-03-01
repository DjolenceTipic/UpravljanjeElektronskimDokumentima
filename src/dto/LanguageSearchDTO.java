package dto;

public class LanguageSearchDTO {

	private String name;
	private String occur;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getoccur() {
		return occur;
	}
	public void setoccur(String occur) {
		this.occur = occur;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LanguageSearchDTO(String name, String occur, String type) {
		super();
		this.name = name;
		this.occur = occur;
		this.type = type;
	}
	public LanguageSearchDTO() {
		super();
	}
	@Override
	public String toString() {
		return "LanguageSearchDTO [name=" + name + ", occur=" + occur + ", type=" + type + "]";
	}
	
}
