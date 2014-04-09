package net.daum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		return new UserDao();
	}
	@Bean
	public ConnectionMaker connctionMaker() {
		return new DConnectionMaker();
	}

}
