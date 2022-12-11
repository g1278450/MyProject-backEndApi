package com.stockAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stockAPI.model.User;
import com.stockAPI.model.entity.UserEntity;
import com.stockAPI.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	// 載入套件的加密器
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		UserEntity user = userRepository.getDataByAccount(account);
		return new User(user);

	}

	public Integer addUser(UserEntity user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Integer userId = userRepository.add(user);
		return userId;
	}

	public User getAccountInfo(String account) {
		UserEntity user = userRepository.getDataByAccount(account);
		// 資料內不可以含有密碼資訊
		user.setPassword(null);
		return new User(user);
	}

}
