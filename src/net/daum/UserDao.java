package net.daum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class UserDao {
	private DataSource dataSource;
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		User user=null;
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			user = new User();
			user.setId(resultSet.getString("id"));
			user.setName(resultSet.getString("name"));
			user.setPassword(resultSet.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			// 자원을 해지한다.
			if (resultSet!=null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			if (preparedStatement!=null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
		return user;
	}

	

	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection connection=null;
		// 쿼리를 만들어서
		PreparedStatement preparedStatement=null;
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement("insert into userinfo(id,name,password) values(?,?,?)");
			preparedStatement.setString(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getPassword());
			
			// 실행시키고
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 자원을 해지한다.
			if (preparedStatement!=null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}





	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}