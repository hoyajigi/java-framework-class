package net.daum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUserStatementStrategy implements StatementStrategy {

	User user;
	public AddUserStatementStrategy(User user) {
		this.user=user;
	}

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

}
