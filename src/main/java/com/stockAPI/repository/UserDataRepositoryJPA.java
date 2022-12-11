package com.stockAPI.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stockAPI.model.entity.UserEntity;

public interface UserDataRepositoryJPA extends CrudRepository<UserEntity, String>{
	
	public List<UserEntity> getByAccount(String account);

}
