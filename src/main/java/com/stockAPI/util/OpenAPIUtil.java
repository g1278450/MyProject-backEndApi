package com.stockAPI.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *  Util說明: 處理發送請求至外部網址
 *  1.參數1:  請求的網址
 *  2.參數2:  請求的方法 
 *  3.參數3:  裝載的物件.class
 * </p>
 * 
 * @author anthony
 *
 */
public class OpenAPIUtil {

	static Logger logger = LogManager.getLogger();

	public static <T> T send(String url, HttpMethod method, Class<T> t) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			ResponseEntity<T> responseEntity = restTemplate.exchange(url, method, httpEntity, t);
			T result = responseEntity.getBody();
			logger.info(responseEntity.getStatusCode());
			return result;
		} catch (HttpClientErrorException httpClientErrorException) {
			logger.error(httpClientErrorException.getResponseBodyAsString());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
