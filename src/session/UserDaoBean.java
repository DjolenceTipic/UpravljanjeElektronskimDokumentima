package session;

import java.util.UUID;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.User;

@Stateless
@Local(UserDaoLocal.class)
public class UserDaoBean extends GenericDaoBean<User, Integer> implements UserDaoLocal {

	@Override
	public User findUserByID(int id) {
		Query q = em.createNamedQuery("getUserByID");
		q.setParameter("id", id);
		User user = (User) q.getSingleResult();
		return user;
	}

	@Override
	public int findUsername(String username) {
		Query q = em.createNamedQuery("findUserName");
		q.setParameter("username", username);
		int userNumber = q.getMaxResults();
		return userNumber;
	}

	@Override
	public User Login(String username, String password) {
		try {
			System.out.println("usao u login");
            // Authenticate the user using the credentials provided
            User user = findUserByUserNameAndPass(username, password);
           
            // Issue a token for the user
            String token = issueToken(username);
            
            // Return the token on the response
            if(user!=null){
            	user.setToken(token);
            	
            	return user;
            }
            else
            	return null;
        } catch (Exception e) {
            return null;
        }
	}

	private String issueToken(String username) {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	private User findUserByUserNameAndPass(String username, String password) {
		Query q = em
				.createNamedQuery("findUserByUserNameAndPass");
		q.setParameter(1, username);
		q.setParameter(2, password);
		System.out.println("postavio parametre");
		try{
			User result = (User) q.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public User checkToken(String token) {
		Query q = em
				.createNamedQuery("findUserByToken");
		q.setParameter(1, token);
		try{
			User result = (User) q.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
	}

}
