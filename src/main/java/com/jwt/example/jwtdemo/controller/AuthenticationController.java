package com.jwt.example.jwtdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.jwtdemo.model.AuthenticationRequest;
import com.jwt.example.jwtdemo.model.AuthenticationResponse;
import com.jwt.example.jwtdemo.service.UserDetailsService;
import com.jwt.example.jwtdemo.util.JwtUtil;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/hello-world")
	public String helloWorld() {
		return "Success";
	}
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {	
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPasword())
			);			
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw e;
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		String jwt= jwtUtil.generateToken(userDetails);
		System.out.println("jwt: "+jwt);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));		
	}
}
