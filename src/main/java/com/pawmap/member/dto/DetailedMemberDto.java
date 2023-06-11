package com.pawmap.member.dto;

import java.text.SimpleDateFormat;

import com.pawmap.member.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetailedMemberDto {

	private String memberId;
	
	private String nickname;

	private String email;
		
	private String banDate;
	
	public DetailedMemberDto(MemberEntity memberEntity) {
		this.memberId = memberEntity.getMemberId();
		this.nickname = memberEntity.getNickname();
		this.email = memberEntity.getEmail();
		
		if(memberEntity.getBanDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedTime = sdf.format(memberEntity.getBanDate());
			this.banDate = formattedTime;
		}else {
			this.banDate = null;
		}
	}
	
}
