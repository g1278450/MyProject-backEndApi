package com.stockAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.stockAPI.model.entity.StockEntity;

public interface StockeDataRepositoryJPA extends CrudRepository<StockEntity, String>{
	

}
