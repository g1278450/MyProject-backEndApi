package com.stockAPI.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockAPI.eumsave.OpenAPIUrlEum;
import com.stockAPI.model.entity.StockEntity;
import com.stockAPI.repository.StockDataRepository;
import com.stockAPI.repository.StockeDataRepositoryJPA;
import com.stockAPI.util.OpenAPIUtil;

@Service
public class OpenAPIService {

	@Autowired
	StockDataRepository stockDataRepository;
	
	@Autowired
	StockeDataRepositoryJPA stockeDataRepositoryJPA;
	
	/**
	 * <p>
	 * 1.從 https://openapi.twse.com.tw/v1/exchangeReport/STOCK_DAY_ALL 取回JSON資料
	 * (https://openapi.twse.com.tw)
	 * 2.組裝成 List
	 * </p>
	 * 
	 * @author anthony
	 *
	 */
	public StockEntity[] getDailyTranctionStockData() {
		return OpenAPIUtil.send(OpenAPIUrlEum.EXCHANGE_REPORT_STOCK_DAY_ALL.getUrl(), OpenAPIUrlEum.EXCHANGE_REPORT_STOCK_DAY_ALL.getMethod(), StockEntity[].class);
	}
	
	public List<StockEntity> getDailyTranctionStockData2() {
		StockEntity[] send = OpenAPIUtil.send(OpenAPIUrlEum.EXCHANGE_REPORT_STOCK_DAY_ALL.getUrl(), OpenAPIUrlEum.EXCHANGE_REPORT_STOCK_DAY_ALL.getMethod(), StockEntity[].class);
		
		List<StockEntity> stockList = new ArrayList<>();
		for(int i = 0 ; i<send.length; i++) {
			StockEntity info = new StockEntity();
			info.setCode(send[i].getCode());
			info.setName(send[i].getName());
			info.setTrade_volume(send[i].getTrade_volume());
			info.setTrade_value(send[i].getTrade_value());
			info.setOpening_price(send[i].getOpening_price());
			info.setHighest_price(send[i].getHighest_price());
			info.setLowest_price(send[i].getLowest_price());
			info.setChange_gap(send[i].getChange_gap());
			info.setClosing_price(send[i].getClosing_price());
			info.setTransaction_count(send[i].getTransaction_count());
			info.setCreate_time(new Date());
			stockList.add(info);
		}

		return stockList;
	}
	
	// 新增transaction註解可以防止出錯時資料還繼續寫到資料庫內
	@Transactional(rollbackFor = Exception.class)
	public void addStockData() {
		
		// NamedParameterJdbcTemplate 組裝SQL
//		stockDataRepository.batchAdd(getDailyTranctionStockData());
	
		// JPA 處理
		stockeDataRepositoryJPA.saveAll(this.getDailyTranctionStockData2());
		
	}

}