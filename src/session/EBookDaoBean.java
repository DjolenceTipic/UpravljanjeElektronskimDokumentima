package session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.Category;
import model.EBook;

@Stateless
@Local(EBookDaoLocal.class)
public class EBookDaoBean extends GenericDaoBean<EBook, Integer> implements EBookDaoLocal {

	@Override
	public EBook findEBookById(String id) {
		Query q = em.createNamedQuery("getEbookByID");
		q.setParameter("id", Integer.parseInt(id));
		EBook book = (EBook) q.getSingleResult();
		return book;
	}

}
