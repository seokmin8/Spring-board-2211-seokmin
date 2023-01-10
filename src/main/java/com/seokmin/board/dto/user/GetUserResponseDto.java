package com.seokmin.board.dto.user;

import com.seokmin.board.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponseDto {
	
	private String email;
	private String nickname;
	private String profile;
	private String telNumber;
	private String address;
	
	// 필요한걸 받아오는 생성자를 만들어서 쓰면 편리
	public GetUserResponseDto(MemberEntity member) {
		this.email = member.getEmail();
		this.nickname = member.getNickname();
		this.profile = member.getProfile();
		this.telNumber = member.getTelNumber();
		this.address = member.getAddress();
	}
}
