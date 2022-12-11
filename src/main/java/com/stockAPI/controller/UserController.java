package com.stockAPI.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockAPI.model.RspMessage;
import com.stockAPI.model.User;
import com.stockAPI.model.entity.UserEntity;
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
@RestController
@RequestMapping("user")
public class UserController extends BaseController{

	@Autowired
	UserInfoService userService;

	@Autowired
	JWTService jWTService;

	@PostMapping("login")
	public RspMessage login(@RequestBody UserEntity user) {
		RspMessage result = new RspMessage();
		Map<String, Object> data = new HashMap<>();
		String token = jWTService.generateToken(user);
		result.setMessage("登入成功，取得token");
		data.put("token", token);
		result.setData(data);
		return result;
	}

	@PostMapping("search")
	public RspMessage searchMemberInfo(@RequestBody UserEntity user) {
		RspMessage result = new RspMessage();
		Map<String, Object> data = new HashMap<>();
		User stockUser = userService.getAccountInfo(user.getAccount());
		data.put("userData", stockUser.getUser());
		result.setMessage("用戶資料查詢成功");
		result.setData(data);
		return result;

	}

	@PostMapping("create")
	public RspMessage add(@RequestBody UserEntity user) {
		RspMessage result = new RspMessage();
		Map<String, Object> data = new HashMap<>();
		Integer userId = userService.addUser(user);
		data.put("userId", userId);
		result.setMessage("用戶資料新增成功");
		result.setData(data);
		return result;
	}
}
