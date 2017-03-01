package session;

import model.EBook;

public interface EBookDaoLocal extends GenericDaoLocal<EBook, Integer> {

	public EBook findEBookById(String id);
}
