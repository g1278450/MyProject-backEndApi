package com.stockAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.stockAPI.model.StockInfo;

public interface StockeDataRepositoryJPA extends CrudRepository<StockInfo, String>{
	

}
