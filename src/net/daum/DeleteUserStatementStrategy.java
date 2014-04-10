package net.daum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserStatementStrategy implements StatementStrategy {
	String id;
	public DeleteUserStatementStrategy(String id) {
		this.id=id;
	}

	@Override
	public PreparedStatement makeStatement(Connection connection)
			throws SQLException {
		PreparedStatement preparedStatement;
		preparedStatement = connection
				.prepareStatement("delete from userinfo where id = ?");
		preparedStatement.setString(1, id);
		return preparedStatement;
	}

}
