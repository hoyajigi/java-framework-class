package net.daum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	User jdbcContextWithStatementStrategyForQuery(
			StatementStrategy statementStrategy) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = dataSource.getConnection();

			preparedStatement=statementStrategy.makeStatement(connection);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getString("id"));
				user.setName(resultSet.getString("name"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 자원을 해지한다.
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
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
	void jdbcContextWithStatementStrategyForUpdate(
			StatementStrategy statementStrategy) {
		Connection connection = null;
		// 쿼리를 만들어서
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			
			preparedStatement=statementStrategy.makeStatement(connection);
			
			// 실행시키고
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 자원을 해지한다.
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void update(final String query, final String[] params) throws SQLException {
		jdbcContextWithStatementStrategyForUpdate(new StatementStrategy() {
			
			@Override
			public PreparedStatement makeStatement(Connection connection)
					throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setString(i+1, params[i]);
				}
				return preparedStatement;
			}
		});
	}
}
