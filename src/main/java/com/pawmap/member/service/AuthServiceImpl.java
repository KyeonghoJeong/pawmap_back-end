package com.pawmap.member.service;

import java.util.Collection;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.member.dao.AuthDao;
import com.pawmap.member.dto.AuthDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.entity.RefreshTokenEntity;
import com.pawmap.security.JwtTokenProvider;

@Service
@Transactional // Ʈ����� ����
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JavaMailSender javaMailSender; // application.yml�� smtp ����
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// �̸��� �����ڵ� �߼� �� ���� �޼ҵ�
	@Override
	public String getEmailAuthCode(String email) {
		// TODO Auto-generated method stub
		Random random = new Random(); // ���� �߻�
		int number = random.nextInt(8889) + 1111; // nextInt(n) + a => n�� ���� X (0 ~ n-1), a�� ���� ������ ������ų �� => 0~8888 => 1111~9999 ������ ���� �߻� 
		String emailAuthCode = Integer.toString(number); // ����ȯ
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // Mime ���� �޽��� ��ü ����

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // ���� ���� �޽��� ��ü, multipart ����, ���ڵ� Ÿ��
			
			helper.setFrom("kyeonghojeong@naver.com"); // �߽��� => application.yml�� ������ ���ϰ� ���� ����
			helper.setTo(email); // ������ => ȸ���� �Է��� ����
			helper.setSubject("ȸ������ ���� �����Դϴ�."); // ���� ����
			helper.setText("<h2>pawmap ȸ�������� ���� �Ʒ��� ���ڸ� �Է����ּ���.</h2><h1>"+emailAuthCode+"</h1>", true); // true�� html ����
			
			javaMailSender.send(mimeMessage); // ���� ���� �߼�
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emailAuthCode; // ���� ���� �߼� �� �����ڵ� ����
	}
	
	// ȸ������ ���� �޼ҵ�
	@Override
	public void postMember(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String encodedPw = passwordEncoder.encode(memberDto.getPw()); // ��й�ȣ ��ȣȭ
		
		// ȸ�� ��ƼƼ ��ü ����
		MemberEntity memberEntity = new MemberEntity(
				memberDto.getMemberId(), // �Է¹��� ���̵�
				encodedPw, // ��ȣȭ �� �Է� ��й�ȣ
				memberDto.getNickname(), // �Է¹��� �г���
				memberDto.getEmail(), // �Է¹��� �̸���
				"ROLE_MEMBER", // ���� => ȸ��
				null, // Ż�� ��¥ null
				null // ���� ��¥ null
		);
		
		authDao.postMember(memberEntity);
	}
	
	// �α��� ���� �޼ҵ�
	@Override
	public AuthDto signIn(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String memberId = memberDto.getMemberId(); // �Է¹��� ȸ�� ���̵� ��������
		String pw = memberDto.getPw(); // �Է¹��� ��й�ȣ ��������
		
		// authentication ���� ��ü ����
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberId, pw);
		
		// CustomAuthenticationProvider ȸ�� ��ȿ�� �˻�
		Authentication authenticatedMember = authenticationManager.authenticate(authentication); // ��ȿ���� ���� ��� ���ܹ߻�
		
		String accessToken = jwtTokenProvider.generateAccessToken(authenticatedMember); // accessToken ����
		String refreshToken = jwtTokenProvider.generateRefreshToken(authenticatedMember); // refreshToken ����
		
		// authentication ��ü���� ���� �����ͼ� ���� �ϳ� ���� => �� ȸ���� �ϳ��� ���Ѹ� ���� 
		Collection<? extends GrantedAuthority> authorities = authenticatedMember.getAuthorities();
		GrantedAuthority authority = authorities.iterator().next();
		String role = authority.getAuthority();
		
		AuthDto authDto = new AuthDto(role, accessToken, refreshToken); // AuthDto ��ü ����

		return authDto;
	}
	
	// accessToken ��߱� ����
	@Override
	public String getAccessToken(String refreshToken) {
		// TODO Auto-generated method stub
		// jwtTokenProvider���� ��ȿ�� �˻� �� ��ū�� ��ȿ�� ���
		if(jwtTokenProvider.validateToken(refreshToken)) {
			// refreshToken ���̺��� �ش� refreshToken�� ������ �ִ� ��ƼƼ(row) ��������
			// refreshToken ��ƼƼ�� refreshToken id, ȸ�� id, refreshToken, ���� �ð� �Ӽ��� ������ ������ 1�ð� ���� �����ٷ��� ���� ���� �ð��� ���� row�� ������
			RefreshTokenEntity refreshTokenEntity = authDao.getRefreshToken(refreshToken);
			
			String memberId = refreshTokenEntity.getMemberId(); // �ش� refreshToken�� ������ �ִ� ȸ���� ���̵� ��������
			UserDetails userDetails = userDetailsService.loadUserByUsername(memberId); // ȸ�� ���̵�� ȸ�� �˻� �� userDetails ��ü �����
			
			// ������ ȸ�� Authentication ��ü ����
			// �Ķ���� ���� ȸ�� ��ƼƼ, ��й�ȣ (���� ���� �ʿ� �����Ƿ� null), ȸ�� ����
			Authentication authenticatedMember = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			String accessToken = jwtTokenProvider.generateAccessToken(authenticatedMember); // accessToken ��߱�
			
			return accessToken; // accessToken ����
		}else {
			// ��ū�� ��ȿ���� ������ null ����
			return null;
		}
	}

}
