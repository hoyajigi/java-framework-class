package net.daum;

import java.sql.SQLException;

public class UserDao {
	private JdbcContext jdbcContext;
	
	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
		return jdbcContext.jdbcContextWithStatementStrategyForQuery(statementStrategy);
	}

	public void add(User user) throws SQLException, ClassNotFoundException {
		StatementStrategy statementStrategy=new AddUserStatementStrategy(user);
		jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
	}

	public void delete(String id) {
		StatementStrategy statementStrategy=new DeleteUserStatementStrategy(id);
		jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
	}
}