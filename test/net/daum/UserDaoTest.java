package net.daum;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserDaoTest {
	@Test
	public void get() {
		UserDao userDao = new UserDao();
		String id = "1";
		User user = userDao.get(id);
		assertEquals("1", user.getId());
	}
}
