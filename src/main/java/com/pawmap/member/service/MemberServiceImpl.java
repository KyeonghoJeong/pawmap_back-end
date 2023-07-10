package com.pawmap.member.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.dto.MemberBanDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.entity.MemberEntity;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDao memberDao;

	// ȸ������ ���� ���� �޼ҵ�
	@Override
	public MemberDto getMember() {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� �������� 
		
		MemberEntity memberEntity = memberDao.getMember(memberId); // ȸ�� ���̵�� ��ƼƼ ��������
		
		MemberDto memberDto = modelMapper.map(memberEntity, MemberDto.class);
		memberDto.setPw(null);
		
		return memberDto;
	}
	
	@Override
	public void putMemberPw(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
		String pw = passwordEncoder.encode(memberDto.getPw()); // ��й�ȣ ��ȣȭ
		
		memberDao.putMemberPw(memberId, pw); // dao ȣ��
	}

	// ���Ѹ� ���� �޼ҵ�
	@Override
	public String getMemberRole() {
		// TODO Auto-generated method stub
		
		// SecurityContextHolder���� ���� ��������
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		GrantedAuthority authority = authorities.iterator().next(); // �� ���ø����̼ǿ��� ȸ�� �� ������ �ϳ�
		String role = authority.getAuthority(); // role(���Ѹ�) ��������
		
		return role; // role ����
	}
	
	// ȸ��Ż�� (Ż��¥ ����) �޼ҵ�
	@Override
	public void putMemberDeletionDate(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
		String pw = memberDto.getPw(); // ��й�ȣ ��������
		
		// ��ȿ�� �˻縦 ���� authentication ��ū ��ü ����
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberId, pw);
		
		// CustomAuthenticationProvider ȸ�� ��ȿ�� �˻�
		// DB�� ��ϵ� ȸ�� ���̵� �ƴϰų� �Է��� ��й�ȣ ���ڵ� ����� DB�� ��ϵ� ��й�ȣ�� ���� ������ ���� �߻�  
		Authentication authenticatedMember = authenticationManager.authenticate(authentication); // authentication ��ü ����
		memberId = authenticatedMember.getName(); // �����Ϸ� �� ȸ�� ���̵�
		
		Date deletionDate = Calendar.getInstance().getTime(); // ���� ��¥
		
		memberDao.putMemberDeletionDate(memberId, deletionDate);
	}
	
	// ȸ������ (���ܳ�¥ ����) �޼ҵ�
	@Override
	public void putMemberBanDate(MemberBanDto memberBanDto) {
		// TODO Auto-generated method stub
		String memberId = memberBanDto.getMemberId(); // ȸ�� ���̵�
		String order = memberBanDto.getOrder(); // ���� or ���� ���� ���
		
		Date banDate = null;
		
		if(order.equals("ban")) {
			banDate = Calendar.getInstance().getTime(); // order�� ���� ban�̸� ���� ��¥ ���� �ƴϸ� (unban) null ����
		}
		
		memberDao.putMemberBanDate(memberId, banDate); // dao ȣ��
	}
	
	// Ż�� ȸ���� ��� �ϸ�ũ ���� �޼ҵ�
	@Override
	public void deleteBookmarks() {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
		
		memberDao.deleteBookmarks(memberId); // dao ȣ��
	}

	@Override
	public Page<MemberDto> getMembers(String memberId, String nickname, String email, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<MemberEntity> memberEntities = memberDao.getMembers(memberId, nickname, email, pageable); // dao�� ȸ�� ��ƼƼ ������ ��û
		
		// Page �� �����ϸ鼭 MemberEntity�� �Ű������� MemberDto�� ������ �����ڴ�� ����
		Page<MemberDto> memberDtos = memberEntities.map(MemberDto::new);
		
		return memberDtos;
	}

}
