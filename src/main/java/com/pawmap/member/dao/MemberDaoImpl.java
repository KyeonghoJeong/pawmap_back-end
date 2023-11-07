package com.pawmap.member.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.map.repository.BookmarkRepository;
import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.repository.MemberRepository;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BookmarkRepository bookmarkRepository;

	// ȸ������ ��ƼƼ�� �����ϴ� dao �޼ҵ�
	// deletionDate, banDate �Ӽ��� ��� null�̾�� ���� ��ȿ�� ȸ��
	// ArticleService, CommentService Ŭ���������� ���
	@Override
	public MemberEntity getMember(String memberId) {
		// TODO Auto-generated method stub
		
		// ȸ�� ��ƼƼ ����
		// ȸ�� ���̵�� ��ȸ
		// Ż�� ��¥, ���� ��¥ �� �� null => ���� �̿밡�� ȸ��
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberIdAndDeletionDateAndBanDate(memberId, null, null);
		
		MemberEntity memberEntity = null;
		
		if(optionalMemberEntity != null) {
			memberEntity = optionalMemberEntity.get(); // null�� �ƴϸ� �� ��������
		}

		return memberEntity;
	}
	
	@Override
	public void putMemberPw(String memberId, String pw) {
		// TODO Auto-generated method stub
		memberRepository.putMemberPw(memberId, pw); // ȸ�� ���̵�� ��ȸ�Ͽ� �ش� row�� ��й�ȣ �� ����
	}
	
	@Override
	public void putMemberDeletionDate(String memberId, Date deletionDate) {
		// TODO Auto-generated method stub
		// deletionDate Į�� ���� ��¥ �����Ͱ� ������ Ż�� ����, null�̸� �̿밡�� ȸ�� => �ش� ȸ�� ���̵�� ��ȸ�Ͽ� Ż��¥ Į���� �� ����
		memberRepository.putMemberDeletionDate(memberId, deletionDate);
	}

	@Override
	public void putMemberBanDate(String memberId, Date banDate) {
		// TODO Auto-generated method stub
		// banDate Į�� ���� ��¥ �����Ͱ� ������ ���� ���� null�̸� �̿밡�� ȸ�� => �ش� ȸ�� ���̵�� ��ȸ�Ͽ� ���ܳ�¥ Į���� �� ����
		memberRepository.putMemberBanDate(memberId, banDate);
	}
	
	@Override
	public void deleteBookmarks(String memberId) {
		// TODO Auto-generated method stub
		bookmarkRepository.deleteAllByMemberId(memberId); // �ش� ȸ�� ���̵�� ��ȸ�Ͽ� �ش��ϴ� ��� �ϸ�ũ row ����
	}

	@Override
	public Page<MemberEntity> getMembers(String memberId, String nickname, String email, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<MemberEntity> memberEntities = null;
		
		// �� �Ķ���� ������ �������� repository �޼ҵ� ȣ�� => ���� ""�� ���� �˻����� ���� ���
		
		// ��� ȸ�� ��ȸ
		if(memberId.equals("") && nickname.equals("") && email.equals("")) {
			// deletionDate ���� null�� ���� Ż�� ȸ������ ��ȸ �� �����ؾ� �ϹǷ� null�� row ��ȸ
			//memberEntities = memberRepository.findByDeletionDate(null, pageable);
			memberEntities = memberRepository.getMembers(pageable);
		}
		
		// ȸ�� ���̵�� �˻�
		if(!memberId.equals("") && nickname.equals("") && email.equals("")) {
			memberEntities = memberRepository.getMembersByMemberId(memberId, pageable);
		}
		
		// �г������� �˻�
		if(memberId.equals("") && !nickname.equals("") && email.equals("")) {
			memberEntities = memberRepository.getMembersByNickname(nickname, pageable);
		}
		
		// �̸��Ϸ� �˻�
		if(memberId.equals("") && nickname.equals("") && !email.equals("")) {
			memberEntities = memberRepository.getMembersByEmail(email, pageable);
		}
	
		return memberEntities;
	}

	@Override
	public Long getEmailNumber(String email) {
		// TODO Auto-generated method stub
		// ȸ������ ��û �̸����� �ߺ��� Ȯ���� �� ������ ���� ������ �̸��ϰ� �ߺ��Ǵ��� ���� Ȯ���ϱ�
		Long number = memberRepository.getAdminEmailNumber(email);
		
		// ������ ���� ���� �߿� �ش� �̸����� ����ϴ� ������ ���� ��쿡�� ȸ�� ���� �߿� �ִ��� Ȯ��
		if(number == 0) {
			// Ż�� ȸ�� �簡�� ��� O => deletionDate Į�� �� null�� �ƴϾ ��
			// ���� ȸ�� �簡�� ��� X => banDate Į�� �� null�̾�� �� (null�� �ƴϸ� ���� ȸ���̹Ƿ� �ش� �̸��� ��� �Ұ�)
			number = memberRepository.getEmailNumber(email); // ���� �ּ�, ���� ��¥�� ��ȸ�Ͽ� ī��Ʈ ����
			
			return number;
		}

		return number;
	}
	
}
