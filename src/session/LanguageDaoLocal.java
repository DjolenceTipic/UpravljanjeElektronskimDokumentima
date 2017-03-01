package session;

import model.Language;

public interface LanguageDaoLocal extends GenericDaoLocal<Language, Integer> {

	public Language getLanguageByID(int id);
}
