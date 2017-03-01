package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "eBook")
@NamedQuery(name = "getEbookByID", query = "SELECT c FROM EBook c WHERE c.id = :id")
public class EBook implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -237562324473290580L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "eBook_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "eBook_title", unique = false, nullable = false, length = 80)
	private String eBookTitle;
	
	@Column(name = "eBook_author", unique = false, nullable = false, length = 80)
	private String eBookAuthor;
	
	@Column(name = "eBook_keywords", unique = false, nullable = false, length = 80)
	private String eBookKeywords;
	
	@Column(name = "eBook_publicationYear", unique = false, nullable = false, length = 80)
	private String eBookPublicationYear;
	
	@Column(name = "file_name", unique = false, nullable = false, length = 80)
	private String fileName;
	
	@Column(name = "file_MIME", unique = false, nullable = false, length = 80)
	private String fileMIME;
	
	@ManyToOne
	@JoinColumn(name = "language_id", referencedColumnName = "language_id", nullable = false)
	private Language language;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;
	
	@Column(name = "isDeleted", unique = false, nullable = false)
	private boolean isDeleted;
	
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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileMIME() {
		return fileMIME;
	}

	public void setFileMIME(String fileMIME) {
		this.fileMIME = fileMIME;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public EBook() {
		super();
	}

	public EBook(Integer id, String eBookTitle, String eBookAuthor, String eBookKeywords, String eBookPublicationYear,
			String fileName, String fileMIME, Language language, Category category, User user, boolean isDeleted) {
		super();
		this.id = id;
		this.eBookTitle = eBookTitle;
		this.eBookAuthor = eBookAuthor;
		this.eBookKeywords = eBookKeywords;
		this.eBookPublicationYear = eBookPublicationYear;
		this.fileName = fileName;
		this.fileMIME = fileMIME;
		this.language = language;
		this.category = category;
		this.user = user;
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "EBook [id=" + id + ", eBookTitle=" + eBookTitle + ", eBookAuthor=" + eBookAuthor + ", eBookKeywords="
				+ eBookKeywords + ", eBookPublicationYear=" + eBookPublicationYear + ", fileName=" + fileName
				+ ", fileMIME=" + fileMIME + ", language=" + language + ", category=" + category + ", user=" + user
				+ ", isDeleted=" + isDeleted + "]";
	}
}
