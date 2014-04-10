package net.daum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
	private JdbcContext jdbcContext;
	
	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public User get(final String id) throws ClassNotFoundException, SQLException {
		final String query = "select * from userinfo where id = ?";
		final String[] params = new String[] {id};
		return jdbcContext.jdbcContextWithStatementStrategyForQuery(new StatementStrategy() {
			
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

	private void update(final String query, final String[] params) throws SQLException {
		jdbcContext.jdbcContextWithStatementStrategyForUpdate(new StatementStrategy() {
			
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

	public void add(final User user) throws SQLException, ClassNotFoundException {
		String query = "insert into userinfo(id,name,password) values(?,?,?)";
		String[] params = new String[] {user.getId(),user.getName(),user.getPassword()};
		update(query, params);
	}

	public void delete(final String id) throws SQLException {
		String query = "delete from userinfo where id = ?";
		String[] params = new String[] {id};
		update(query, params);
	}
}