package com.stockAPI.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.stockAPI.model.entity.UserEntity;

@Repository
public class UserRepository {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	UserDataRepositoryJPA userDataRepositoryJPA;

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	private BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

	public Integer add(UserEntity user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
		String sql = " INSERT INTO users ( ACCOUNT, NAME, PASSWORD ) " + " VALUE ( :account, :name, :password ) ";
		namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
		return keyHolder.getKey().intValue();

	}

	public UserEntity getDataByAccount(String account) {
		/**
		 * JdbcTemplate 
		 */
//		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
//		String sql = "SELECT * FROM users WHERE ACCOUNT = :account";
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("account", account);		
//		List<User> query = namedParameterJdbcTemplate.query(sql, params, rowMapper);
//		return query.stream().findFirst().orElse(null);
		
		/**
		 * JPA 
		 */
		return userDataRepositoryJPA.getByAccount(account).stream().findFirst().orElse(null);

	}

}
