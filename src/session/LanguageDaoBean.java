package session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.Category;
import model.Language;

@Stateless
@Local(LanguageDaoLocal.class)
public class LanguageDaoBean extends GenericDaoBean<Language, Integer> implements LanguageDaoLocal {

	@Override
	public Language getLanguageByID(int id) {
		Query q = em.createNamedQuery("getLanguageByID");
		q.setParameter("id", id);
		Language lan = (Language) q.getSingleResult();
		return lan;
	}

}
