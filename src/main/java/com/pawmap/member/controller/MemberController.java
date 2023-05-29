package com.pawmap.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.service.MemberService;

@RestController
@RequestMapping("api")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@PostMapping("/member/signup")
	public void signUp(@RequestBody MemberDto memberInfo) {
		memberService.signUp(memberInfo);
	}
	
}
