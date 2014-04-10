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
		return jdbcContext.jdbcContextWithStatementStrategyForQuery(new StatementStrategy() {
			
			@Override
			public PreparedStatement makeStatement(Connection connection)
					throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
				preparedStatement.setString(1, id);
				return preparedStatement;
			}
		});
	}

	public void add(final User user) throws SQLException, ClassNotFoundException {
		jdbcContext.jdbcContextWithStatementStrategyForUpdate(new StatementStrategy() {
			
			@Override
			public PreparedStatement makeStatement(Connection connection)
					throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into userinfo(id,name,password) values(?,?,?)");
				preparedStatement.setString(1, user.getId());
				preparedStatement.setString(2, user.getName());
				preparedStatement.setString(3, user.getPassword());
				return preparedStatement;
			}
		});
	}

	public void delete(final String id) {
		jdbcContext.jdbcContextWithStatementStrategyForUpdate(new StatementStrategy() {
			
			@Override
			public PreparedStatement makeStatement(Connection connection)
					throws SQLException {
				PreparedStatement preparedStatement;
				preparedStatement = connection
						.prepareStatement("delete from userinfo where id = ?");
				preparedStatement.setString(1, id);
				return preparedStatement;
			}
		});
	}
}