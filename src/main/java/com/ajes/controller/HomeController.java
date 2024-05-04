package com.ajes.controller;

import com.ajes.security.AuthenticationRequest;
import com.ajes.security.AuthenticationResponse;
import com.ajes.service.UserService;
import com.ajes.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
@Component
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtility jwtUtility;

	@GetMapping("/home/")
	public String getUserService(String userService) {

		return "getUserService";

	}

	@GetMapping("/about/")
	public String addUserService(String userService) {

		return "addUserService";

	}


	@PostMapping("/logIn1")
	public ResponseEntity<?> logIn(@RequestBody() AuthenticationRequest authenticationRequest) {


		try {

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword());
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);


		} catch (BadCredentialsException e) {


			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid");

		}

		UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());


		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		String jwtToken = jwtUtility.generateJwtToken(userDetails);
		authenticationResponse.setJwtToken(jwtToken);

		return ResponseEntity.ok(authenticationResponse);

	}

}
