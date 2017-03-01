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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name = "getUserByID", query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "findUserName", query = "SELECT u FROM User u WHERE u.userUserName = :username"),
	@NamedQuery(name = "findUserByUserNameAndPass", query="SELECT c FROM User c WHERE c.userUserName = ?1 and c.userPassword = ?2"),
	@NamedQuery(name = "findUserByToken", query = "SELECT c FROM User c WHERE c.token = ?1 AND c.token is not null"),
})

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7351729135012380019L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "user_firstName", unique = false, nullable = false, length = 30)
	private String userFirstName;
	
	@Column(name = "user_lastName", unique = false, nullable = false, length=30)
	private String userLastName;
	
	@Column(name = "user_userName", unique = true, nullable = false, length = 10)
	private String userUserName;
	
	@Column(name = "user_password", unique = false, nullable = false, length=10)
	private String userPassword;
	
	@Column(name="user_def", unique=false, nullable =false, length=10)
	private RoleOfUser uloga;
	
	@Column(name="user_token", unique=false, nullable =true, length=100)
	private String token;
	
	@Column(name = "isDeleted", unique = false, nullable = false)
	private boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = true)
	private Category category;
	
	@JsonIgnore
	@OneToMany(cascade={ALL}, fetch = LAZY, mappedBy = "user")
	private Set<EBook> eBooksSet = new HashSet<EBook>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserUserName() {
		return userUserName;
	}

	public void setUserUserName(String userUserName) {
		this.userUserName = userUserName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public RoleOfUser getUloga() {
		return uloga;
	}

	public void setUloga(RoleOfUser uloga) {
		this.uloga = uloga;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Set<EBook> geteBooksSet() {
		return eBooksSet;
	}

	public void seteBooksSet(Set<EBook> eBooksSet) {
		this.eBooksSet = eBooksSet;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public User(Integer id, String userFirstName, String userLastName, String userUserName, String userPassword,
			RoleOfUser uloga, String token, boolean isDeleted, Category category, Set<EBook> eBooksSet) {
		super();
		this.id = id;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userUserName = userUserName;
		this.userPassword = userPassword;
		this.uloga = uloga;
		this.token = token;
		this.isDeleted = isDeleted;
		this.category = category;
		this.eBooksSet = eBooksSet;
	}

	public User() {
		super();
	}

	public void addEBook(EBook eBookEntity) {
//		if (m.getCategory() != null)
//			m.getCategory().getEBooks().remove(m);
//		m.setCategory(this);
//		EBooks.add(m);
	}
	
	public void removeEBook(EBook eBookEntity) {
//		m.setCategory(null);
//		movies.remove(m);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userUserName=" + userUserName + ", userPassword=" + userPassword + ", uloga=" + uloga + ", token="
				+ token + ", isDeleted=" + isDeleted + ", category=" + category + ", eBooksSet=" + eBooksSet + "]";
	}
	
	
}
