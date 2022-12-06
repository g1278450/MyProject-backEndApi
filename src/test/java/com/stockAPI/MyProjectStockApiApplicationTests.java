package com.stockAPI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stockAPI.model.StockInfo;
import com.stockAPI.service.TWSIOpenService;

@SpringBootTest
class MyProjectStockApiApplicationTests {

	static Logger logger = LogManager.getLogger();

	@Autowired
	TWSIOpenService tWSIOpenService;

	@Test
	void contextLoads() {
		StockInfo[] resultArray = tWSIOpenService.getDailyTranctionStockData();
		if (resultArray != null) {
			for (StockInfo data : resultArray) {
				logger.info("name:" + data.getName());
			}
		} else {
			logger.error("資料取得失敗");
		}

	}

}
