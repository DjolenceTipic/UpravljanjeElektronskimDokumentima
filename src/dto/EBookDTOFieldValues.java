package dto;

import java.util.List;

public class EBookDTOFieldValues {
	private String fileName;
	private String author;
	private String title;
	private List<String> keywords;
	private String category;
	private String language;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public EBookDTOFieldValues() {
		super();
	}
	public EBookDTOFieldValues(String fileName, String author, String title, List<String> keywords, String category,
			String language) {
		super();
		this.fileName = fileName;
		this.author = author;
		this.title = title;
		this.keywords = keywords;
		this.category = category;
		this.language = language;
	}
	
	
}
