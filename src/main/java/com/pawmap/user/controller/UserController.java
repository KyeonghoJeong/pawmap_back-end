package com.pawmap.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.user.dto.UserDto;

@RestController
@RequestMapping("api")
public class UserController {

	@PostMapping("/user/signup")
	public void SignUp(@RequestBody UserDto request) {
		
	}
	
}
