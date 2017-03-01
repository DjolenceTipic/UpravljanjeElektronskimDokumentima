package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import dto.CategoryDTO;
import model.Category;
import session.CategoryDaoLocal;

@Path("/categories")
public class CategoryService {

	@EJB
	private CategoryDaoLocal categoryDao;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> findAll() {
		List<Category> retVal = null; 
		try {
			retVal = categoryDao.findAll();
			
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	@POST
	@Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Category create(Category cat) {
		Category retVal;
		try {
			System.out.println(cat);
			retVal = categoryDao.persist(cat);
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
    
    @PUT 
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Category update(Category cat) {
    	System.out.println(cat);
    	Category retVal = null;
        try {
        	retVal = categoryDao.merge(cat);
        	System.out.println(retVal.toString());
        } catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
}
