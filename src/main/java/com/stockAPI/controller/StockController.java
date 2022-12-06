package com.stockAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockAPI.model.StockInfo;
import com.stockAPI.model.ReturnMessage;
import com.stockAPI.service.TWSIOpenService;

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
@CrossOrigin("*")
@RestController
@RequestMapping("stock")
public class StockController {

	@Autowired
	TWSIOpenService tWSIOpenService;

	@GetMapping("/search/public/getStockData")
	@Description("所有權限都可以查")
	public ReturnMessage getStockData() {
		ReturnMessage message = new ReturnMessage();
		Map<String, Object> stockData = new HashMap<>();
		
		// 原生陣列
		StockInfo[] dailyStockData = tWSIOpenService.getDailyTranctionStockData();
		
		// 轉成List 方便JPA to save
		List<StockInfo> stockDataLists = tWSIOpenService.getDailyTranctionStockData2();
		
		message.setMessage("上市個股日成交資訊-取得成功");
		stockData.put("dailyStockData", dailyStockData);
		stockData.put("stockDataLists", stockDataLists);
		message.setData(stockData);

		
		return message;
	}

}
