package service;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.LoginDTo;
import model.User;
import session.UserDaoLocal;

@Path("/auth")
public class AuthService {

	@EJB
	private UserDaoLocal udl;
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response authenticateUser(LoginDTo login) {
		try {
			User entity = udl.Login(login.getUsername(), login.getPassword());
			
			if(entity.getToken()!=null)// Return the token on the response
				return Response.ok(entity,MediaType.APPLICATION_JSON).build();
			else
				return Response.status(Response.Status.NOT_FOUND).build();
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}      
	}
	
	@POST
	@Path("/checkToken")
	@Produces("application/json")
	public Response checkToken(String token) {
		System.out.println(token);
		String[] list = token.split("=");
		String tokenForReal = list[1];
		System.out.println(tokenForReal);
		try {
			User entity = udl.checkToken(tokenForReal);
			if(entity!=null)// Return the token on the response
				return Response.ok(entity,MediaType.APPLICATION_JSON).build();
			else
				return Response.status(Response.Status.NOT_FOUND).build();
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}      
	}
}
