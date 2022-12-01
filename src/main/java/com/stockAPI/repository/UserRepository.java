package com.stockAPI.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.stockAPI.model.User;

@Repository
public class UserRepository {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

	public Integer add(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
		String sql = " INSERT INTO users ( ACCOUNT, NAME, PASSWORD ) " + " VALUE ( :account, :name, :password ) ";
		namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
		return keyHolder.getKey().intValue();

	}

	public User getDataByAccount(String account) {

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		String sql = "SELECT * FROM users WHERE ACCOUNT = :account";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("account", account);		
		List<User> query = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		return query.stream().findFirst().orElse(null);

	}

}
