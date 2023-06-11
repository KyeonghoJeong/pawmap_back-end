package com.pawmap.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.member.repository.InfoRepository;
import com.pawmap.member.service.EmailService;

@RestController
@RequestMapping("api")
public class InfoController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private InfoRepository infoRepository;
	
	@GetMapping("/validation/memberid")
	public Long checkMemberId(@RequestParam String memberId) {
		Long count = infoRepository.countByMemberId(memberId);

		return count;
	}
	
	@GetMapping("/validation/nickname")
	public Long checkNickname(@RequestParam String nickname) {
		Long count = infoRepository.countByNickname(nickname);

		return count;
	}
	
	@GetMapping("/validation/email")
	public Long checkEmail(@RequestParam String email) {
		Long count = infoRepository.countByEmailAndDeletionDateAndBanDate(email, null, null);

		return count;
	}
	
	@GetMapping("/certification/email")
	public String certifyEmail(@RequestParam String email) {
		String code = emailService.sendEmail(email);

		return code;
	}
	
}
