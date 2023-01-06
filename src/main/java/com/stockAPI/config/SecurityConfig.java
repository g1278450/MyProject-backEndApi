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
import com.stockAPI.service.UserInfoService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	JWTCheckFilter jWTCheckFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userInfoService).passwordEncoder(new BCryptPasswordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 新增帳號用
				// 管理員可以新增使用者資料
				.antMatchers("/user/create").hasAuthority("ADMIN")
				
				// 登入
				.antMatchers("/user/login").permitAll()
				
				// 查詢使用者資料 (目前前端尚未實作)
				.antMatchers("/user/search").hasAnyAuthority("NORMAL","ADMIN")
				
				// 取得資料
				.antMatchers("/stock").hasAnyAuthority("NORMAL","ADMIN")
				
				.and()
				
				// 新增token過濾器
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
