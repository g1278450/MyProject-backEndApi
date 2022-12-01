package com.stockAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.stockAPI.filter.JWTCheckFilter;
import com.stockAPI.service.StockUserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	StockUserService stockUserService;

	@Autowired
	JWTCheckFilter jWTCheckFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(stockUserService).passwordEncoder(new BCryptPasswordEncoder());

//		auth.userDetailsService(stockUserService).
//		passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 測試用
				.antMatchers("/user/testBlock").authenticated().antMatchers("/user/testUnblock").permitAll()

				// 新增帳號用
				.antMatchers("/user/create").hasAuthority("ADMIN") // 管理員可以新增使用者資料
				.antMatchers("/user/login").permitAll()
				
				// 查詢資料
				.antMatchers("/user/search").hasAnyAuthority("NORMAL","ADMIN")
				
				//取得股市資料
				.antMatchers("user/stock").hasAnyAuthority("NORMAL","ADMIN") 
				.and()
				
				// 新增過濾器設定
				.addFilterBefore(jWTCheckFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement()
				// 關閉HttpSession的建立狀態
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf()
				.disable();

	}

	// 加密器注入容器
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 驗證類別註冊容器
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}

/**
 * SpringSecurity 5.4.x以上新用法配置 为避免循环依赖，仅用于配置HttpSecurity Created by macro on
 * 2022/5/19.
 */
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        //省略HttpSecurity的配置
//        return httpSecurity.build();
//    }
//
//}
