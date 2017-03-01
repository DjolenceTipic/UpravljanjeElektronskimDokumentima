package session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.Category;

@Stateless
@Local(CategoryDaoLocal.class)
public class CategoryDaoBean extends GenericDaoBean<Category, Integer>  implements CategoryDaoLocal {

	@Override
	public Category getCategoryByID(int id) {
		Query q = em.createNamedQuery("getCategoryByID");
		q.setParameter("id", id);
		Category cat = (Category) q.getSingleResult();
		return cat;
	}
}
