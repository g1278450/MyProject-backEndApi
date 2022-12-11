package com.stockAPI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stockAPI.model.entity.StockEntity;
import com.stockAPI.service.OpenAPIService;

@SpringBootTest
class MyProjectStockApiApplicationTests {

	static Logger logger = LogManager.getLogger();

	@Autowired
	OpenAPIService tWSIOpenService;

	@Test
	void contextLoads() {
		StockEntity[] resultArray = tWSIOpenService.getDailyTranctionStockData();
		if (resultArray != null) {
			for (StockEntity data : resultArray) {
				logger.info("name:" + data.getName());
			}
		} else {
			logger.error("資料取得失敗");
		}

	}

}
