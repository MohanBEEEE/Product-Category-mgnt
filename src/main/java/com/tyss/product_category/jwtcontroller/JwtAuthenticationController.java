package com.tyss.product_category.jwtcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.product_category.jwtconfig.JwtTokenUtil;
import com.tyss.product_category.jwtdto.JwtRequest;
import com.tyss.product_category.jwtdto.JwtResponse;
import com.tyss.product_category.jwtservice.JwtUserDetailsService;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil tokenUtil;
	
	@Autowired
	private JwtUserDetailsService service;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping(path = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) {
		System.out.println("Hit Succesfull.!!!");
		authenticate(request.getUserName(), request.getPassword());
		UserDetails loadUserByUsername = this.service.loadUserByUsername(request.getUserName());
		String generateToken = this.tokenUtil.generateToken(loadUserByUsername);
		return ResponseEntity.ok(new JwtResponse(generateToken));
	}
	
	private void authenticate(String useName, String password) {
		try {
			this.manager.authenticate(new UsernamePasswordAuthenticationToken(useName, password));
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			throw new DisabledException("USER_DISABLED!!", e);
		} catch (BadCredentialsException e) {
			// TODO Auto-generated catch block
			throw new BadCredentialsException("INVALID CREDENTIALS", e);
		}
	}
}
