package session;

import java.util.HashSet;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Category;
import model.EBook;
import model.Language;
import model.RoleOfUser;
import model.User;

@Stateless
@Remote(Init.class)
public class InitBean implements Init {

	@PersistenceContext(unitName = "ProjekatUES")
	EntityManager em;
	
	@Override
	public void init() {

		EBook eBook = new EBook();
		
		eBook.seteBookAuthor("Adela Zamudio");
		eBook.seteBookTitle("Essayos poeticos");
		eBook.seteBookPublicationYear("1887");
		eBook.seteBookKeywords("potika bolivije");
		eBook.setDeleted(false);
		
		HashSet<EBook> eBookSet = new HashSet<>();
		eBookSet.add(eBook);
		
		Language language = new Language();
		language.setLanguageName("bolivijanski");
		language.setDeleted(false);
		
		Category category = new Category();
		category.setName("bolivijanska romantika");
		category.addEBook(eBook);
		category.seteBooksSet(eBookSet);
		category.setDeleted(false);
		
		eBook.setFileMIME("aplication/pdf");
		eBook.setFileName("bolivijanska poezija vol 1");
		
		RoleOfUser uloga = RoleOfUser.administrator;
		User user = new User(null,"admin", "admin", "admin", "admin", uloga, "", false, category, eBookSet);

		category.addUser(user);
		eBook.setUser(user);
		eBook.setLanguage(language);
		eBook.setCategory(category);
		
		em.persist(language);
		em.persist(category);
		em.persist(user);
		em.persist(eBook);
	}

}
