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

import com.stockAPI.model.User;
import com.stockAPI.model.entity.UserEntity;
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

			Map<String, Object> loginUser = jwtService.parseToken(accessToken);

			Integer userId = (Integer) loginUser.get("user_id");
			String account = (String) loginUser.get("account");
			String name = (String) loginUser.get("name");
			String authority = (String) loginUser.get("authority");

			UserEntity userEntity = new UserEntity(account, name, null, authority);
			userEntity.setId(userId);
			User user = new User(userEntity);

			Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity, null, user.getAuthorities());
			
			// step3. SecurityContextHolder 存放授權成功的 Authentication DTO
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		
		filterChain.doFilter(request, response);
		

	}

}
