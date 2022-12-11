package com.stockAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockAPI.model.RspMessage;
import com.stockAPI.model.entity.StockEntity;
import com.stockAPI.service.OpenAPIService;

/**
 * <p>
 * 目前這隻API:處理股票相關事情
 * 1.查詢 (已完成)
 * 2.用戶新增 自選股票至選單內 (未完成)
 * 3.用戶刪除 自選股票至選單內 (未完成)
 * </p>
 * 
 * @author anthony
 *
 */
@RestController
@RequestMapping("stock")
public class StockController extends BaseController{

	@Autowired
	OpenAPIService tWSIOpenService;

	@GetMapping("/search/public/getStockData")
	@Description("所有權限都可以查")
	public RspMessage getStockData() {
		RspMessage result = new RspMessage();
		Map<String, Object> stockDataMap = new HashMap<>();
		
		// 原生陣列
//		StockEntity[] dailyStockData = tWSIOpenService.getDailyTranctionStockData();
		
		// 轉成List 方便JPA to save
		List<StockEntity> stockDataLists = tWSIOpenService.getDailyTranctionStockData2();
		
		result.setMessage("上市個股日成交資訊-取得成功");
//		stockData.put("dailyStockData", dailyStockData);
		stockDataMap.put("stockDataLists", stockDataLists);
		result.setData(stockDataMap);

		
		return result;
	}

}
