package com.nls.service.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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
