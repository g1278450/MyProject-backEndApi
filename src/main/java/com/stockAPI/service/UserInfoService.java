package com.stockAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stockAPI.model.StockUser;
import com.stockAPI.model.User;
import com.stockAPI.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	// 載入套件的加密器
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	// 目前還沒要用到，先不實作
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		User user = userRepository.getDataByAccount(account);
		return new StockUser(user);

	}

	public Integer addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Integer userId = userRepository.add(user);
		return userId;
	}

	public StockUser getAccountInfo(String account) {
		User user = userRepository.getDataByAccount(account);
		// 資料內不可以含有密碼資訊
		user.setPassword(null);
		return new StockUser(user);
	}

}
