package net.daum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	public User get(String id) throws ClassNotFoundException, SQLException {
		// 사용자는 어디에 저장 되어 있는거지?
		// Database를 사용해 보자
		// 어떤 Database를 사용하지?
		// Mysql을 사용해보자..
		
		// Connection 을 맺고
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju?useUnicode=true&characterEncoding=utf8","jeju","jejupw");

		// 쿼리를 만들어서
		PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
		preparedStatement.setString(1, id);
		
		// 실행시키고
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		// 결과를 User에 잘 매핑하고
		User user=new User();
		user.setId(resultSet.getString("id"));
		user.setName(resultSet.getString("name"));
		user.setPassword(resultSet.getString("password"));
		
		// 자원을 해지한다.
		resultSet.close();
		preparedStatement.close();
		connection.close();
		
		return user;
	}

	public static void add(User user) throws SQLException, ClassNotFoundException {
		// Connection 을 맺고
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju?useUnicode=true&characterEncoding=utf8","jeju","jejupw");

		// 쿼리를 만들어서
		PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(id,name,password) values(?,?,?)");
		preparedStatement.setString(1, user.getId());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getPassword());
		
		// 실행시키고
		preparedStatement.executeUpdate();
		
		// 자원을 해지한다.
		preparedStatement.close();
		connection.close();
		
	}
	
}