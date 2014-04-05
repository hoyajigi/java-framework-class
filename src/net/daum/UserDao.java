package net.daum;

public class UserDao {

	public User get(String id) {
		User user=new User();
		user.setId("1");
		user.setName("호야지기");
		user.setPassword("1234");
		return user;
	}

}
