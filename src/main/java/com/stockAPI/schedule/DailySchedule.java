package com.stockAPI.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stockAPI.service.TWSIOpenService;

@Component
public class DailySchedule {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 @Autowired
	 TWSIOpenService tWSIOpenService;
	 
	 //上市個股日成交資訊-存入 下午五點寫入
	 @Scheduled(cron = "0 0 17 * * ?")
//	 @Scheduled(fixedDelay = 5000)
	 public void saveDailyTranctionStockData() {
		 tWSIOpenService.addStockData();
		 logger.info("上市個股日成交資訊-存入 下午五點寫入");
	 }
	 
	 @Scheduled(fixedDelay = 5000)
	 public void testTask() {
//	 logger.info("測試定時任務");
	 }

}
