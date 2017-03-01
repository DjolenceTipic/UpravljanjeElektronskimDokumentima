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

import model.Category;
import model.Language;
import session.LanguageDaoLocal;

@Path("/languages")
public class LanguageService {

	@EJB
	private LanguageDaoLocal languageDao;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Language> findAll() {
		List<Language> retVal = null; 
		try {
			retVal = languageDao.findAll();
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
	public Language create(Language lan) {
		Language retVal;
		try {
			retVal = languageDao.persist(lan);
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
	public Language update(Language lan) {
    	Language retVal = null;
        try {
        	retVal = languageDao.merge(lan);
        } catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
}
