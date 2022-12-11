package com.stockAPI.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.stockAPI.model.entity.StockEntity;

@Repository
public class StockDataRepository {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int[] batchAdd(StockEntity[] dailyTranctionStockData_array) {

		List<StockEntity> dailyTranctionStockData_list = new ArrayList<StockEntity>();

		dailyTranctionStockData_list.stream().forEach(System.out::println);

		for (StockEntity dailyTranctionStockData : dailyTranctionStockData_array) {
			dailyTranctionStockData_list.add(dailyTranctionStockData);
		}

		String sql = " INSERT INTO stock01.stock_data "
				+ " ( CODE, NAME, TRADE_VOLUME, TRADE_VALUE, OPENING_PRICE, HIGHEST_PRICE, "
				+ " LOWEST_PRICE, CLOSING_PRICE, CHANGE_GAP, TRANSACTION_COUNT ) "
				+ " VALUES ( :code, :name, :trade_volume, :trade_value, :opening_price, :highest_price, :lowest_price , :closing_price, :change_gap, :transaction_count) ";
		
		String sql2 = " UPDATE  stock01.stock_data"
				+ "	SET CODE = :code, NAME = :name, TRADE_VOLUME = :trade_volume, TRADE_VALUE = :trade_value, OPENING_PRICE = :opening_price, HIGHEST_PRICE = :highest_price, "
				+ " LOWEST_PRICE = :lowest_price, CLOSING_PRICE = :closing_price, CHANGE_GAP = :change_gap, TRANSACTION_COUNT = :transaction_count " ;

		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(dailyTranctionStockData_list.toArray());

		int[] updateCounts = namedParameterJdbcTemplate.batchUpdate(sql2, batch);

		return updateCounts;

	}

}
