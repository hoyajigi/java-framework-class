package net.daum;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
	private JdbcTemplate jdbcTemplate;

	public User get(final String id) throws ClassNotFoundException, SQLException {
		final String query = "select * from userinfo where id = ?";
		final String[] params = new String[] {id};

		User queryForObject=null;
		try {
			queryForObject=getJdbcTemplate().queryForObject(query, params, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rownum) throws SQLException {
					User user= new User();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					return user;
				}
			});
		} catch (EmptyResultDataAccessException e) {}
		return queryForObject;
	}

	public void add(final User user) throws SQLException, ClassNotFoundException {
		String query = "insert into userinfo(id,name,password) values(?,?,?)";
		String[] params = new String[] {user.getId(),user.getName(),user.getPassword()};
		getJdbcTemplate().update(query, (Object[])params);
	}

	public void delete(final String id) throws SQLException {
		String query = "delete from userinfo where id = ?";
		String[] params = new String[] {id};
		getJdbcTemplate().update(query, (Object[])params);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}