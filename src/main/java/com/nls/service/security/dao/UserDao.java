package com.nls.service.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.nls.service.security.model.User;

@Repository
public class UserDao {
//	private List<User> data = new ArrayList<>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<User> getAllUser() {
		String sql = "select * from SEC_USER";
		List<User> users = jdbcTemplate.query(sql, new UserRowMaper());
		return users;
	}

	public User findUserById(Long id) {
		String sql = "select * from SEC_USER where ID= ?";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
		User user = new User();
		if (rs.getRow()>0) {
			rs.next();
			user.setId(rs.getLong("ID"));
			user.setFullname(rs.getString("FULLNAME"));
			user.setUsername(rs.getString("USERNAME"));
			return user;

		} else {
			return null;
		}

	}

	public void deleteUserById(Long id) {
		String sql = "DELETE FROM SEC_USER WHERE ID = ?";
		jdbcTemplate.update(sql, id);
	}

	public User addNew(User newUser) {
		newUser.setId(6L);
		String sql = "insert into SEC_USER(ID,USERNAME,FULLNAME) values(?,?,?)";
		jdbcTemplate.update(sql, newUser.getId(), newUser.getUsername(), newUser.getFullname());
		return newUser;
	}

	private static class UserRowMaper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			User user = new User();
			user.setId(rs.getLong("ID"));
			user.setUsername(rs.getString("USERNAME"));
			user.setFullname(rs.getString("FULLNAME"));
			return user;

		}
	}

}
