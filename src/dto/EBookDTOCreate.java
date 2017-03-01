package dto;

import javax.ejb.EJB;

import model.Category;
import model.Language;
import session.CategoryDaoLocal;
import session.LanguageDaoLocal;

public class EBookDTOCreate {

	@EJB
	private CategoryDaoLocal cdl;
	
	@EJB
	private LanguageDaoLocal ldl;
	
	private String fileName;
	private String author;
	private String title;
	private String keywords;
	private String languageID;
	private String categoryID;
	private String publicationYear;
	private String userID;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
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
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getLanguageID() {
		return languageID;
	}
	public void setLanguageID(String languageID) {
		this.languageID = languageID;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public EBookDTOCreate(String fileName, String author, String title, String keywords, String languageID,
			String categoryID, String publicationYear, String userID) {
		super();
		this.fileName = fileName;
		this.author = author;
		this.title = title;
		this.keywords = keywords;
		this.languageID = languageID;
		this.categoryID = categoryID;
		this.publicationYear = publicationYear;
		this.userID = userID;
	}
	public EBookDTOCreate() {
		super();
	}
	@Override
	public String toString() {
		return "EBookDTOCreate [fileName=" + fileName + ", author=" + author + ", title=" + title + ", keywords="
				+ keywords + ", languageID=" + languageID + ", categoryID=" + categoryID + ", publicationYear="
				+ publicationYear + ", userID=" + userID + "]";
	}
	
	public String getCategoryForId(String id){
		int idForCategory = Integer.parseInt(id);
		Category cat = cdl.getCategoryByID(idForCategory);
		return cat.getName();
	}
	
	public String getlanguageForId(String id){
		int idForlanguage = Integer.parseInt(id);
		Language lan = ldl.getLanguageByID(idForlanguage);
		return lan.getLanguageName();
	}
}
