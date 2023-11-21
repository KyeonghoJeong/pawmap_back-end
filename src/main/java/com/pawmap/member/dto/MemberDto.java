package com.pawmap.member.dto;

import java.text.SimpleDateFormat;

import com.pawmap.member.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 회원가입 시 입력 정보, 로그인 시 입력 정보, 회원 탈퇴 시 입력 정보
// 회원이 회원 정보 탭에서 회원 정보 조회 시 리턴 정보, 관리자가 회원 정보 탭에서 조회 시 리턴 정보

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
	
	// 관리자가 모든 회원의 정보를 조회하는 데 사용할 때
	// 모든 회원의 엔티티를 매개변수로 받아 아이디, 닉네임, 메일 주소를 설정하고 차단 날짜가 null이 아닌 경우 차단 날짜를 포맷해서 설정
	public MemberDto(MemberEntity memberEntity) {
		this.memberId = memberEntity.getMemberId();
		this.nickname = memberEntity.getNickname();
		this.email = memberEntity.getEmail();
		
		if(memberEntity.getBanDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷 설정
			String formattedTime = sdf.format(memberEntity.getBanDate()); // entity의 date형 차단날짜 데이터 포맷에 맞게 String으로 형변환
			this.banDate = formattedTime; // 저장
		}else {
			this.banDate = null; // 차단된 회원이 아니면 null 저장
		}
	}
	
}