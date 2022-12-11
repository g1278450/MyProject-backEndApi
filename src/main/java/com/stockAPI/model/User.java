package com.stockAPI.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.stockAPI.model.entity.UserEntity;

public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private UserEntity user;

	public User(UserEntity user) {
		this.user = user;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	// 取得此帳號的權限
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority(user.getAuthority()));
		return authorityList;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getAccount();
	}

	// 驗證此帳號是否未過期，目前沒有要用到先設return true
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 驗證此帳號是否未被封鎖，目前沒有要用到先設return true
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 驗證此帳號憑證是否未過期，目前沒有要用到先設return true
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 驗證此帳號是否可以使用，目前沒有要用到先設return true
	@Override
	public boolean isEnabled() {
		return true;
	}

}
