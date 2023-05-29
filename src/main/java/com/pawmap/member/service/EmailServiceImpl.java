package com.pawmap.member.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String sendEmail(String email) {
		// TODO Auto-generated method stub
		String code = "";
		
		Random random = new Random();
		int number = random.nextInt(8889) + 1111;
		code = Integer.toString(number);
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			helper.setFrom("kyeonghojeong@naver.com");
			helper.setTo(email);
			helper.setSubject("회원가입 인증 메일입니다.");
			helper.setText("<h2>pawmap 회원가입을 위해 아래의 숫자를 입력해주세요.</h2><h1>"+code+"</h1>", true);
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}

}
