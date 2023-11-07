package com.pawmap.member.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.board.dao.ArticleDao;
import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.dto.MemberBanDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.dto.MemberPwDto;
import com.pawmap.member.entity.MemberEntity;

@Service
@Transactional // Ʈ����� ����
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ArticleDao articleDao;

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
	
	// ȸ���� �������������� �ڽ��� �Խñ۵��� �����ϴ� �޼ҵ�
	@Override
	public void deleteMemberArticles(List<Long> articleIds) {
		// TODO Auto-generated method stub
		articleDao.deleteArticles(articleIds);
	}
	
	@Override
	public boolean putMemberPw(MemberPwDto memberPwDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
		UserDetails userDetails = userDetailsService.loadUserByUsername(memberId); // �ش� ���̵��� ��ƼƼ(ȸ������) ��������
		
    	// ����ڰ� �Է��� ��й�ȣ�� DB ���̺� ��ϵǾ� �ִ� ��й�ȣ ��ġ Ȯ��
		if(!passwordEncoder.matches(memberPwDto.getPw(), userDetails.getPassword())) {
			return false; // ����ġ �� false ����
		}
		
		String newPw = passwordEncoder.encode(memberPwDto.getNewPw()); // �� ��й�ȣ ��ȣȭ
		
		memberDao.putMemberPw(memberId, newPw); // dao ȣ��
		
		return true; // ��й�ȣ ���� ���� �� true ����
	}
	
	// ȸ��Ż�� (Ż��¥ ����) �޼ҵ�
	@Override
	public boolean putMemberDeletionDate(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
		String pw = memberDto.getPw(); // Ż�� �� �Է��� ��й�ȣ ��������
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(memberId); // �ش� ���̵��� ��ƼƼ(ȸ������) ��������
		
		if(passwordEncoder.matches(pw, userDetails.getPassword())){ // �Է��� ��й�ȣ�� ��� ��й�ȣ�� ���� ���
			Date deletionDate = Calendar.getInstance().getTime(); // ���� ��¥
			
			memberDao.putMemberDeletionDate(memberId, deletionDate); // dao ȣ��
			
			return true; // ��й�ȣ�� �°� Ż�� ó���� �Ϸ�� �� true ����
		}else {
			return false; // ��й�ȣ�� Ʋ���� false ����
		}
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

	// ȸ������ �� ��û �̸����� �ߺ� Ȯ�� �޼ҵ�
	@Override
	public Long getEmailNumber(String email) {
		// TODO Auto-generated method stub
		Long number = memberDao.getEmailNumber(email); // dao ȣ��

		return number;
	}

}
