package com.stockAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockAPI.eumsave.TWSIOpenAPIUrl;
import com.stockAPI.model.DailyTranctionStockData;
import com.stockAPI.repository.DailyTranctionStockDataRepository;
import com.stockAPI.util.TWSIOpenAPIUtil;

@Service
public class TWSIOpenService {

	@Autowired
	DailyTranctionStockDataRepository dailyTranctionStockDataRepository;

	public DailyTranctionStockData[] getDailyTranctionStockData() {
		DailyTranctionStockData[] resultList = TWSIOpenAPIUtil.send(
				TWSIOpenAPIUrl.EXCHANGE_REPORT_STOCK_DAY_ALL.getUrl(),
				TWSIOpenAPIUrl.EXCHANGE_REPORT_STOCK_DAY_ALL.getMethod(), DailyTranctionStockData[].class);
		return resultList;
	}

	// 新增transaction註解可以防止出錯時資料還繼續寫到資料庫內
	@Transactional(rollbackFor = Exception.class)
	public void schedule_AddDailyTranctionStockData() {
		DailyTranctionStockData[] dailyTranctionStockData_array = getDailyTranctionStockData();
		dailyTranctionStockDataRepository.batchAdd(dailyTranctionStockData_array);
	}

}