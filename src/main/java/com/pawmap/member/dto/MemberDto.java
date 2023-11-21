package com.pawmap.member.dto;

import java.text.SimpleDateFormat;

import com.pawmap.member.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// ȸ������ �� �Է� ����, �α��� �� �Է� ����, ȸ�� Ż�� �� �Է� ����
// ȸ���� ȸ�� ���� �ǿ��� ȸ�� ���� ��ȸ �� ���� ����, �����ڰ� ȸ�� ���� �ǿ��� ��ȸ �� ���� ����

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {

	private String memberId;
	private String pw;
	private String nickname;
	private String email;
	private String banDate;
	
	public MemberDto() {
		
	}
	
	// �����ڰ� ��� ȸ���� ������ ��ȸ�ϴ� �� ����� ��
	// ��� ȸ���� ��ƼƼ�� �Ű������� �޾� ���̵�, �г���, ���� �ּҸ� �����ϰ� ���� ��¥�� null�� �ƴ� ��� ���� ��¥�� �����ؼ� ����
	public MemberDto(MemberEntity memberEntity) {
		this.memberId = memberEntity.getMemberId();
		this.nickname = memberEntity.getNickname();
		this.email = memberEntity.getEmail();
		
		if(memberEntity.getBanDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // ��¥ ���� ����
			String formattedTime = sdf.format(memberEntity.getBanDate()); // entity�� date�� ���ܳ�¥ ������ ���˿� �°� String���� ����ȯ
			this.banDate = formattedTime; // ����
		}else {
			this.banDate = null; // ���ܵ� ȸ���� �ƴϸ� null ����
		}
	}
	
}