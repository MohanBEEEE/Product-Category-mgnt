package com.tyss.product_category.jwtconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tyss.product_category.jwtservice.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUserDetailsService service;
	
	@Autowired
	private JwtTokenUtil util;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reqHeaderToken = request.getHeader("Authorization");
		
		String userName = null;
		String jwtToken = null;
		
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		
		if(reqHeaderToken != null && reqHeaderToken.startsWith("Bearer ")) {
			jwtToken = reqHeaderToken.substring(7);
			try {
				userName = util.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException  e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException   e) {
				// TODO Auto-generated catch block
				System.out.println("JWT Token has expired");
			}
		}else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		// Once we get the token validate it.
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails loadUserByUsername = this.service.loadUserByUsername(userName);
			// if token is valid configure Spring Security to manually set
			// authentication
			if(util.validateToken(jwtToken, loadUserByUsername)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loadUserByUsername, null, loadUserByUsername.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
