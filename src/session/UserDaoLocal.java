package session;

import model.User;

public interface UserDaoLocal extends GenericDaoLocal<User, Integer> {

	public User findUserByID(int id);
	public int findUsername(String username);
	public User Login(String username, String password);
	public User checkToken(String token);
}
