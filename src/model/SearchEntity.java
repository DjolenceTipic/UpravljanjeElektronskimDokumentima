package model;

public class SearchEntity {

	private String text;
	private String textSearchType;
	private String textSearchCondition;
	private String author;
	private String auhtorSearchType;
	private String authorSearchCondition;
	private String keywords;
	private String keywordsSearchType;
	private String keywordsSearchCondition;
	private String title;
	private String titleSearchType;
	private String titleSearchCondition;
	private String language;
	private String languageSearchType;
	private String languageSearchCondition;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTextSearchType() {
		return textSearchType;
	}
	public void setTextSearchType(String textSearchType) {
		this.textSearchType = textSearchType;
	}
	public String getTextSearchCondition() {
		return textSearchCondition;
	}
	public void setTextSearchCondition(String textSearchCondition) {
		this.textSearchCondition = textSearchCondition;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuhtorSearchType() {
		return auhtorSearchType;
	}
	public void setAuhtorSearchType(String auhtorSearchType) {
		this.auhtorSearchType = auhtorSearchType;
	}
	public String getAuthorSearchCondition() {
		return authorSearchCondition;
	}
	public void setAuthorSearchCondition(String authorSearchCondition) {
		this.authorSearchCondition = authorSearchCondition;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getKeywordsSearchType() {
		return keywordsSearchType;
	}
	public void setKeywordsSearchType(String keywordsSearchType) {
		this.keywordsSearchType = keywordsSearchType;
	}
	public String getKeywordsSearchCondition() {
		return keywordsSearchCondition;
	}
	public void setKeywordsSearchCondition(String keywordsSearchCondition) {
		this.keywordsSearchCondition = keywordsSearchCondition;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleSearchType() {
		return titleSearchType;
	}
	public void setTitleSearchType(String titleSearchType) {
		this.titleSearchType = titleSearchType;
	}
	public String getTitleSearchCondition() {
		return titleSearchCondition;
	}
	public void setTitleSearchCondition(String titleSearchCondition) {
		this.titleSearchCondition = titleSearchCondition;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLanguageSearchType() {
		return languageSearchType;
	}
	public void setLanguageSearchType(String languageSearchType) {
		this.languageSearchType = languageSearchType;
	}
	public String getLanguageSearchCondition() {
		return languageSearchCondition;
	}
	public void setLanguageSearchCondition(String languageSearchCondition) {
		this.languageSearchCondition = languageSearchCondition;
	}
	public SearchEntity(String text, String textSearchType, String textSearchCondition, String author,
			String auhtorSearchType, String authorSearchCondition, String keywords, String keywordsSearchType,
			String keywordsSearchCondition, String title, String titleSearchType, String titleSearchCondition,
			String language, String languageSearchType, String languageSearchCondition) {
		super();
		this.text = text;
		this.textSearchType = textSearchType;
		this.textSearchCondition = textSearchCondition;
		this.author = author;
		this.auhtorSearchType = auhtorSearchType;
		this.authorSearchCondition = authorSearchCondition;
		this.keywords = keywords;
		this.keywordsSearchType = keywordsSearchType;
		this.keywordsSearchCondition = keywordsSearchCondition;
		this.title = title;
		this.titleSearchType = titleSearchType;
		this.titleSearchCondition = titleSearchCondition;
		this.language = language;
		this.languageSearchType = languageSearchType;
		this.languageSearchCondition = languageSearchCondition;
	}
	public SearchEntity() {
		super();
	}
	@Override
	public String toString() {
		return "SearchEntity [text=" + text + ", textSearchType=" + textSearchType + ", textSearchCondition="
				+ textSearchCondition + ", author=" + author + ", auhtorSearchType=" + auhtorSearchType
				+ ", authorSearchCondition=" + authorSearchCondition + ", keywords=" + keywords
				+ ", keywordsSearchType=" + keywordsSearchType + ", keywordsSearchCondition=" + keywordsSearchCondition
				+ ", title=" + title + ", titleSearchType=" + titleSearchType + ", titleSearchCondition="
				+ titleSearchCondition + ", language=" + language + ", languageSearchType=" + languageSearchType
				+ ", languageSearchCondition=" + languageSearchCondition + "]";
	}
	
	
}
