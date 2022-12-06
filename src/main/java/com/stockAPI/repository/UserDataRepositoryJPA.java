package com.stockAPI.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stockAPI.model.User;

public interface UserDataRepositoryJPA extends CrudRepository<User, String>{
	
	public List<User> getByAccount(String account);

}
