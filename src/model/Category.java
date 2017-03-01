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
@Table(name = "category")
@NamedQuery(name = "getCategoryByID", query = "SELECT c FROM Category c WHERE c.id = :id")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6796738792444570180L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "category_name", unique = false, nullable = false, length = 30)
	private String name;
	
	@Column(name = "isDeleted", unique = false, nullable = false)
	private boolean isDeleted;
	
	@JsonIgnore
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "category")
	private Set<User> users = new HashSet<User>();
		
	@JsonIgnore
	@OneToMany(cascade={ALL}, fetch = LAZY, mappedBy = "category")
	private Set<EBook> eBooksSet = new HashSet<EBook>();
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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

	public Category() {
		super();
	}

	public Category(int id, String name, boolean isDeleted, Set<User> users, Set<EBook> eBooksSet) {
		super();
		this.id = id;
		this.name = name;
		this.isDeleted = isDeleted;
		this.users = users;
		this.eBooksSet = eBooksSet;
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
	
	public void addUser(User userEntity) {
//		if (u.getCategory() != null)
//			u.getCategory().getUsers().add(u);
//		u.setCategory(this);
//		users.add(u);
	}

	public void removeUser(User userEntity) {
//		u.setCategory(null);
//		users.remove(u);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", isDeleted=" + isDeleted + ", users=" + users
				+ ", eBooksSet=" + eBooksSet + "]";
	}
	
}
