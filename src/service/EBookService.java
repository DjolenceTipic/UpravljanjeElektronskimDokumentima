package service;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dto.EBookDTOCreate;
import dto.EBookDeleteDTO;
import dto.EBookUpdateDTO;
import luceneHelpClasses.LuceneIndexer;
import model.Category;
import model.EBook;
import model.Language;
import model.User;
import session.CategoryDaoLocal;
import session.EBookDaoLocal;
import session.LanguageDaoLocal;
import session.UserDaoLocal;

@Path("/ebooks")
public class EBookService {

	private static final String FILENAME = "resorces/app";
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME);
	
	@EJB
	private EBookDaoLocal eBookDao;
	
	@EJB
	private CategoryDaoLocal categoryDao;
	
	@EJB
	private LanguageDaoLocal languageDao; 
	
	@EJB
	private UserDaoLocal userDao;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EBook> findAll() {
		List<EBook> retVal = null; 
		try {
			retVal = eBookDao.findAll();
			
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(EBookDTOCreate entity){
		String storagePath = resourceBundle.getString("storage");
		EBook eBook;
		try{
			Category cat = categoryDao.getCategoryByID(Integer.parseInt(entity.getCategoryID()));
			Language lan = languageDao.getLanguageByID(Integer.parseInt(entity.getLanguageID()));
			eBook = toEBookFromCreateDTO(entity);
			entity.setCategoryID(cat.getName());
			entity.setLanguageID(lan.getLanguageName());
			eBookDao.persist(eBook);
			File uploadedFile = new File(storagePath, entity.getFileName());
			LuceneIndexer.getInstance().index(uploadedFile, entity);
			return Response.ok(eBook, MediaType.APPLICATION_JSON).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Path("/delete")
	@Consumes("application/json")
	@Produces("application/json")
	public Response delete(EBookDeleteDTO entity){
		System.out.println(entity);
		EBook eBook;
		try{
			eBook = eBookDao.findEBookById(entity.getId());
			eBook.setDeleted(true);
			eBookDao.merge(eBook);
			return Response.ok(eBook, MediaType.APPLICATION_JSON).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(EBookUpdateDTO entity){
		String storagePath = resourceBundle.getString("storage");
		try{
			String id = String.valueOf(entity.getId());
			System.out.println(id);
			EBook ebook = eBookDao.findEBookById(id);
			
			ebook.seteBookAuthor(entity.geteBookAuthor());
			ebook.seteBookPublicationYear(entity.geteBookPublicationYear());
			ebook.setFileName(entity.getFileName());
			ebook.seteBookKeywords(entity.geteBookKeywords());
			ebook.seteBookTitle(entity.geteBookTitle());
			
			int catID = Integer.parseInt(entity.getCategory());
			Category cat = categoryDao.getCategoryByID(catID);
			ebook.setCategory(cat);
			
			int lanID = Integer.parseInt(entity.getLanguage());
			Language lan = languageDao.getLanguageByID(lanID);
			ebook.setLanguage(lan);
			eBookDao.merge(ebook);
			File uploadedFile = new File(storagePath, entity.getFileName());
			LuceneIndexer.getInstance().index(uploadedFile, ebook);
			return Response.ok(ebook, MediaType.APPLICATION_JSON).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	private EBook toEBookFromCreateDTO(EBookDTOCreate entity) {
		EBook ebook = new EBook();
		ebook.setId(null);
		ebook.setDeleted(false);
		ebook.setFileMIME("application/pdf");
		
		int userID = Integer.parseInt(entity.getUserID());
		User user = userDao.findUserByID(userID);
		ebook.setUser(user);
		
		ebook.seteBookAuthor(entity.getAuthor());
		ebook.seteBookPublicationYear(entity.getPublicationYear());
		ebook.setFileName(entity.getFileName());
		ebook.seteBookKeywords(entity.getKeywords());
		ebook.seteBookTitle(entity.getTitle());
		
		int catID = Integer.parseInt(entity.getCategoryID());
		Category cat = categoryDao.getCategoryByID(catID);
		ebook.setCategory(cat);
		
		int lanID = Integer.parseInt(entity.getLanguageID());
		Language lan = languageDao.getLanguageByID(lanID);
		ebook.setLanguage(lan);
		
		return ebook;
	}
	
	private EBook toBookFromUpdate(EBookUpdateDTO entity){
		
		String id = String.valueOf(entity.getId());
		System.out.println(id);
		EBook ebook = eBookDao.findEBookById(id);
		
		ebook.seteBookAuthor(entity.geteBookAuthor());
		ebook.seteBookPublicationYear(entity.geteBookPublicationYear());
		ebook.setFileName(entity.getFileName());
		ebook.seteBookKeywords(entity.geteBookKeywords());
		ebook.seteBookTitle(entity.geteBookTitle());
		
		int catID = Integer.parseInt(entity.getCategory());
		Category cat = categoryDao.getCategoryByID(catID);
		ebook.setCategory(cat);
		
		int lanID = Integer.parseInt(entity.getLanguage());
		Language lan = languageDao.getLanguageByID(lanID);
		ebook.setLanguage(lan);
		
		return ebook;
	}
}
