package session;

import model.Category;

public interface CategoryDaoLocal extends GenericDaoLocal<Category, Integer> {

	public Category getCategoryByID(int id);
}
