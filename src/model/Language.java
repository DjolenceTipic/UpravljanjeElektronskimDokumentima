package model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "language")
@NamedQuery(name = "getLanguageByID", query = "SELECT l FROM Language l WHERE l.language_id = :id")
public class Language implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7984877228536804722L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "language_id", unique = true, nullable = false)
	private Integer language_id;

	@Column(name = "language_name", unique = false, nullable = false, length = 30)
	private String languageName;
	
	@JsonIgnore
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "language")
	private Set<EBook> eBookSet = new HashSet<EBook>();
	
	@Column(name = "isDeleted", unique = false, nullable = false)
	private boolean isDeleted;
	
	
	public Integer getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(Integer language_id) {
		this.language_id = language_id;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public Set<EBook> geteBookSet() {
		return eBookSet;
	}

	public void seteBookSet(Set<EBook> eBookSet) {
		this.eBookSet = eBookSet;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Language() {
		super();
	}

	public Language(Integer language_id, String languageName, Set<EBook> eBookSet, boolean isDeleted) {
		super();
		this.language_id = language_id;
		this.languageName = languageName;
		this.eBookSet = eBookSet;
		this.isDeleted = isDeleted;
	}

	public void add(EBook m) {
//		 if (m.getLangauge() != null)
//			 m.getLangauge().getMovies().add(m);
//		 m.setLangauge(this);
//		 eBookSet.add(m);
	 }
		
	 public void remove(EBook m) {
//		 m.setLangauge(null);
//		 eBookSet.remove(m);
	 }

	@Override
	public String toString() {
		return "Language [language_id=" + language_id + ", languageName=" + languageName + ", eBookSet=" + eBookSet
				+ ", isDeleted=" + isDeleted + "]";
	}
	 
}
