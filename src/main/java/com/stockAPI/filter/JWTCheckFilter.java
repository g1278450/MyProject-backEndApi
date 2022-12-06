package com.stockAPI.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stockAPI.model.StockUser;
import com.stockAPI.model.User;
import com.stockAPI.service.JWTService;

@Component
public class JWTCheckFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 取得標頭的authorization屬性
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		System.out.println("authHeader = " + authHeader);
		
		if (authHeader != null) {
			String accessToken = authHeader.replace("Bearer ", "");
			
			System.out.println("accessToken = " + accessToken);

			Map<String, Object> claims = jwtService.parseToken(accessToken);

			Integer user_id = (Integer) claims.get("user_id");
			String account = (String) claims.get("account");
			String name = (String) claims.get("name");
			String authority = (String) claims.get("authority");

			User user = new User(account, name, null, authority);
			user.setId(user_id);
			StockUser stockUser = new StockUser(user);

			Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, stockUser.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		
		filterChain.doFilter(request, response);
		

	}

}
