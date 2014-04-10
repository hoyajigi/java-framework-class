package net.daum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetUserStatementStrategy implements StatementStrategy {

	String id;
	public GetUserStatementStrategy(String id) {
		this.id=id;
	}

	@Override
	public PreparedStatement makeStatement(Connection connection)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
		preparedStatement.setString(1, id);
		return preparedStatement;
	}

}
