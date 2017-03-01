package dto;

import javax.ejb.EJB;

import model.Category;
import session.CategoryDaoLocal;

public class CategoryDTO {

	@EJB
	private CategoryDaoLocal categoryDao;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoryDTO(int id) {
		super();
		this.id = id;
	}

	public CategoryDTO() {
		super();
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + "]";
	}

	public Category toCategory(CategoryDTO catDTO){
		Category categoryEntity = null;
		try{
			System.out.println("u DTO");
			categoryEntity = categoryDao.findById(catDTO.getId());
			System.out.println(categoryEntity);
			System.out.println("try prosao");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return categoryEntity;
	}
}
