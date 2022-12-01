package com.stockAPI.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockAPI.model.DailyTranctionStockData;
import com.stockAPI.model.ReturnMessage;
import com.stockAPI.service.TWSIOpenService;

@RestController
@RequestMapping("stock/search")
public class StockSearchController {

	@Autowired
	TWSIOpenService tWSIOpenService;

	@GetMapping("exchange/getStockDayAll")
	@Description("所有人都可以查")
	public ReturnMessage getStockDayAll() {
		ReturnMessage message = new ReturnMessage();
		Map<String, Object> data = new HashMap<String, Object>();
		DailyTranctionStockData[] dailyTranctionStockData = tWSIOpenService.getDailyTranctionStockData();
		message.setMessage("上市個股日成交資訊-取得成功");
		data.put("dailyTranctionStockData", dailyTranctionStockData);
		message.setData(data);
		return message;
	}

}
