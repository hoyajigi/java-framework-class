package net.daum;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

public class UserDaoTest {
	@Test
	public void get() throws ClassNotFoundException, SQLException {
		UserDao userDao = new DaoFactory().getUserDao();
		String id = "1";
		User user = userDao.get(id);
		assertEquals("1", user.getId());
		assertEquals("hoyajigi", user.getName());
		assertEquals("hoyaiscute", user.getPassword());
	}
	
	@Test
	public void add() throws ClassNotFoundException, SQLException{
		User user=new User();
		String id = String.valueOf(new Random().nextInt());
		user.setId(id);
		user.setName("아기호야");
		user.setPassword("1234");
		UserDao userDao=new DaoFactory().getUserDao();
		userDao.add(user);
		User addedUser=userDao.get(id);
		assertEquals(id, addedUser.getId());
		assertEquals("아기호야", addedUser.getName());
		assertEquals("1234", addedUser.getPassword());		
	}
}
/*
grant all privileges on *.* to 'jeju'@'localhost'
   identified by 'jejupw' with grant option;

create database jeju default character set utf8 collate utf8_general_ci;
use jeju
create table userinfo(
     id int(10) not null primary key,
     name varchar(50),
     password varchar(50),
     INDEX(id));
insert into userinfo Values('1','hoyajigi','hoyaiscute');
*/