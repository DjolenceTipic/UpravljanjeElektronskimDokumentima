package dto;

public class EBookUpdateDTO {

	private Integer id;
	private String eBookTitle;
	private String eBookAuthor;
	private String eBookKeywords;
	private String eBookPublicationYear;
	private String fileName;
	private String language;
	private String category;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String geteBookTitle() {
		return eBookTitle;
	}
	public void seteBookTitle(String eBookTitle) {
		this.eBookTitle = eBookTitle;
	}
	public String geteBookAuthor() {
		return eBookAuthor;
	}
	public void seteBookAuthor(String eBookAuthor) {
		this.eBookAuthor = eBookAuthor;
	}
	public String geteBookKeywords() {
		return eBookKeywords;
	}
	public void seteBookKeywords(String eBookKeywords) {
		this.eBookKeywords = eBookKeywords;
	}
	public String geteBookPublicationYear() {
		return eBookPublicationYear;
	}
	public void seteBookPublicationYear(String eBookPublicationYear) {
		this.eBookPublicationYear = eBookPublicationYear;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public EBookUpdateDTO(Integer id, String eBookTitle, String eBookAuthor, String eBookKeywords,
			String eBookPublicationYear, String fileName, String language, String category) {
		super();
		this.id = id;
		this.eBookTitle = eBookTitle;
		this.eBookAuthor = eBookAuthor;
		this.eBookKeywords = eBookKeywords;
		this.eBookPublicationYear = eBookPublicationYear;
		this.fileName = fileName;
		this.language = language;
		this.category = category;
	}
	public EBookUpdateDTO() {
		super();
	}
	@Override
	public String toString() {
		return "EBookUpdateDTO [id=" + id + ", eBookTitle=" + eBookTitle + ", eBookAuthor=" + eBookAuthor
				+ ", eBookKeywords=" + eBookKeywords + ", eBookPublicationYear=" + eBookPublicationYear + ", fileName="
				+ fileName + ", language=" + language + ", category=" + category + "]";
	}
		
	
}
