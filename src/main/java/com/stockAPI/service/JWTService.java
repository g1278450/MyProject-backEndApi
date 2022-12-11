package com.stockAPI.service;

import java.security.Key;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.stockAPI.model.User;
import com.stockAPI.model.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	@Autowired
	private AuthenticationManager authenticationManager;

	private final String KEY = "anthonyanthonyanthonyanthonyanthonyanthonyanthonyanthony";
	
	/**
	 * 產生令牌
	 * @param user
	 * @return
	 */
	public String generateToken(UserEntity user) {
		
		// Authentication: spring security 提供的驗證介面，他的功能是
		// 1.承載欲驗證資料(account/password)
		
		// step1. 將用戶輸入的帳號密碼轉換成 UsernamePasswordAuthenticationToken DTO (authentication)
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
		
        // step2. 利用 AuthenticationManager.authenticate(...) method 進行用戶認証
        //        認証失敗時丟出 Exception，認証成功時將用戶資料(指 UserDetails)、權限存
        //        成一個新的 Authentication DTO 
		
		// createSuccessAuthentication 實作 authenticate
		
		authentication = authenticationManager.authenticate(authentication);
		
		//step3. 認証成功前存放 "用戶登入id"，認証成功後存放 "用戶對應的 UserDetails"
		User userDetails = (User) authentication.getPrincipal();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 20);
		
		// 設置令牌內容
		Claims claims = Jwts.claims();
		claims.put("userId", userDetails.getUser().getId());
		claims.put("account", userDetails.getUsername());
		claims.put("name", userDetails.getUser().getName());
		claims.put("authority", userDetails.getUser().getAuthority());		
		// Expiration:設定過期時間(可以不加)
		claims.setExpiration(calendar.getTime());
		// Issuer: JWT發行人(可以不加)
		claims.setIssuer("web-Anthony");
		
		// 拿key的長度做成簽章的key
		Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

		return Jwts.builder().setClaims(claims).signWith(secretKey).compact();
	}
	
	/**
	 * 認證令牌
	 * @param token
	 * @return
	 */
	public Map<String, Object> parseToken(String token) {
		Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

		JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();

		Claims claims = parser.parseClaimsJws(token).getBody();

		return claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
