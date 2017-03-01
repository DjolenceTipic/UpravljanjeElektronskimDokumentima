package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dto.UserDTO;
import dto.UserDTOChangePassword;
import dto.UserDTOCreate;
import dto.UserDTOforDelete;
import dto.UserDTOforUpdate;
import model.Category;
import model.RoleOfUser;
import model.User;
import session.CategoryDaoLocal;
import session.UserDaoLocal;

@Path("/users")
public class UserService {

	@EJB
	private UserDaoLocal userDao;
	
	@EJB
	private CategoryDaoLocal categoryDao;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> findAll() {
		List<User> userList = null; 
		List<UserDTO> retVal = new ArrayList<UserDTO>();
		try {
			userList = userDao.findAll();
			for(User user : userList){
				UserDTO userDTO = fromObjectToDTO(user);
				retVal.add(userDTO);
			}
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	public User findUserByID(int id){
		User user = null;
		try{
			user = userDao.findUserByID(id);
		}catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return user;
	}
	
	public UserDTO fromObjectToDTO(User user){
		UserDTO userDTO = new UserDTO();
		userDTO.setCategory(user.getCategory());
		userDTO.setId(user.getId());
		userDTO.setUserFirstName(user.getUserFirstName());
		userDTO.setUserLastName(user.getUserLastName());
		userDTO.setUserUserName(user.getUserUserName());
		
		if(user.getUloga().equals(RoleOfUser.administrator)){
			userDTO.setUloga(0);
		}else{
			userDTO.setUloga(1);
		}
		return userDTO;
	}
	
	public User fromDtoToObject(UserDTOforUpdate userDTO){
		User user = findUserByID(userDTO.getId());
		if(userDTO.getCategoryInt()!=0){
			Category cat = categoryDao.getCategoryByID(userDTO.getCategoryInt());
			user.setCategory(cat);
		}else{
			user.setCategory(null);
		}
		
		user.setUserFirstName(userDTO.getFirstName());
		user.setUserLastName(userDTO.getLastName());
		user.setUserUserName(userDTO.getUsername());
		if(userDTO.getUlogaInt()==0){
			user.setUloga(RoleOfUser.administrator);
		}else{
			user.setUloga(RoleOfUser.pretplatnik);
		}
		
		return user;
	}
	
	@PUT
	@Path("/delete")
	@Produces("application/json")
	public Response delete(UserDTOforDelete entity){
		User user = null;
		try{
			user = findUserByID(entity.getId());
			user.setDeleted(true);
			userDao.merge(user);
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@PUT
	@Path("/changePassword")
	@Produces("application/json")
	public Response changePassword(UserDTOChangePassword entity){
		try{
			User user = findUserByID(entity.getId());
			user.setUserPassword(entity.getPassword());
			userDao.merge(user);
			return Response.ok(MediaType.APPLICATION_JSON).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@PUT	
	@Path("/updateUser")
	@Produces("application/json")
	public Response update(UserDTOforUpdate entity){
		try{
			User user = fromDtoToObject(entity);
			userDao.merge(user);
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces("application/json")
	@Path("/add")
	public Response add(UserDTOCreate entity){
		try{
			User user = fromCreateDtoToObject(entity);
			userDao.merge(user);
			return Response.ok(MediaType.APPLICATION_JSON).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces("application/json")
	@Path("/checkUsername")
	public Response checkUsername(String username){
		int number = userDao.findUsername(username);
		if(number==0){
			return Response.ok(MediaType.APPLICATION_JSON).build();
		}else{
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
		
	
	public User fromCreateDtoToObject(UserDTOCreate entity){
		User user = new User();
		user.setUserFirstName(entity.getFirstName());
		user.setUserLastName(entity.getLastName());
		user.setUserUserName(entity.getUserName());
		user.setUserPassword(entity.getPassword());
		
		if(entity.getCategoryInt()!=0){
			Category cat = categoryDao.getCategoryByID(entity.getCategoryInt());
			user.setCategory(cat);
		}else{
			Category cat = null;
			user.setCategory(cat);
		}
		
		if(entity.getRoleInt()==0){
			user.setUloga(RoleOfUser.administrator);
		}else{
			user.setUloga(RoleOfUser.pretplatnik);
		}
		user.setDeleted(false);
		return user;
	}
}
