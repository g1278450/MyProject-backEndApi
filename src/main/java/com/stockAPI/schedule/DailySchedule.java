package com.stockAPI.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stockAPI.service.OpenAPIService;

/**
 * <p>
 *  定時器說明: 每日訂時抓取資料
 *  @Scheduled("秒 分 時 日 月 周 (年)" )
 *  *	表達任意值
 * 	?	只用在 日 跟 周 的值域，有點表達 don’t care 的概念
 *	-	指定範圍，前後接數字: 10-12
 *	,	指定離散的選項: 1,5,6,8
 *	/	指定增量，表達 每 的概念: 0/5 意旨從 0 開始每 5 單位
 *	L	用在 月 跟 周 的值域。在月的話表達最後一天，在周的話前面可以加上數字 3L 表示該月最後一個星期二
 *	W	用在日的值域表示距離最近的該月工作日: 15W，距離 15 號最近的工作日，可能往前也可能往後
 *	LW	用在日的值域，表示最後一周的工作日
 *	#	用在周的值域，指定特定周的特定日: “4#2” 表示第二周的星期三
 *	C	用在日跟周的值域，指某特定個日期的後一天: 在日中寫 3C 指該月 3 號的後一天，在周中寫 2C 指該周星期一的後一天
 * </p>
 * 
 * @author anthony
 *
 */
@Component
public class DailySchedule {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 @Autowired
	 OpenAPIService openService;
	 
	 //上市個股日成交資訊-存入 下午五點寫入
	 @Scheduled(cron = "0 05 17 * * ?")
//	 @Scheduled(fixedDelay = 5000)
	 public void saveDailyTranctionStockData() {
		 openService.addStockData();
		 logger.info("上市個股日成交資訊-存入 下午五點寫入");
	 }
	 
	 @Scheduled(fixedDelay = 5000)
	 public void testTask() {
//	 logger.info("測試定時任務");
	 }

}
