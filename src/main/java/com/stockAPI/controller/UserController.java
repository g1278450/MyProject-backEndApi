package com.stockAPI.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockAPI.model.ReturnMessage;
import com.stockAPI.model.StockUser;
import com.stockAPI.model.User;
import com.stockAPI.service.JWTService;
import com.stockAPI.service.UserInfoService;

/**
 * <p>
 * 目前這隻API:處理用戶登入事項
 * 1.用戶登入 (已完成)
 * 2.用戶新增 (已完成)
 * 3.用戶查詢 (已完成)
 * </p>
 * 
 * @author anthony
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserInfoService stockUserService;

	@Autowired
	JWTService jWTService;

	@PostMapping("login")
	public ReturnMessage login(@RequestBody User user) {
		ReturnMessage result = new ReturnMessage();
		Map<String, Object> data = new HashMap<>();
		String token = jWTService.generateToken(user);
		result.setMessage("登入成功，取得token");
		data.put("token", token);
		result.setData(data);
		return result;
	}

	@PostMapping("search")
	public ReturnMessage searchMemberInfo(@RequestBody User user) {
		ReturnMessage result = new ReturnMessage();
		Map<String, Object> data = new HashMap<>();
		StockUser stockUser = stockUserService.getAccountInfo(user.getAccount());
		data.put("userData", stockUser.getUser());
		result.setMessage("用戶資料查詢成功");
		result.setData(data);
		return result;

	}

	@PostMapping("create")
	public ReturnMessage add(@RequestBody User user) {
		ReturnMessage result = new ReturnMessage();
		Map<String, Object> data = new HashMap<>();
		Integer userId = stockUserService.addUser(user);
		data.put("userId", userId);
		result.setMessage("用戶資料新增成功");
		result.setData(data);
		return result;
	}
}
