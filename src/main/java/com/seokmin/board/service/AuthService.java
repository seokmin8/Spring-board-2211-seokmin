package com.seokmin.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seokmin.board.dto.auth.AuthPostDto;
import com.seokmin.board.dto.auth.LoginDto;
import com.seokmin.board.dto.response.ResponseDto;
import com.seokmin.board.entity.MemberEntity;
import com.seokmin.board.repository.MemberRepository;

// @Service : 해당 클래스가 Service 레이어 역할을 함
@Service
public class AuthService {

	@Autowired MemberRepository memberRepository;
	
//	public ResponseDto<String> hello() {
	// 원래는 이런식으로 풀어 사용하나, 작업량이 많아 아래로 간소화 
	public String hello() {
		// Entity Class로 entity build
		MemberEntity memberEntity = MemberEntity
				.builder()
				.email("qwe@qwe.com")
				.password("qwe123")
				.nickname("mine")
				.telNumber("010-1234-1234")
				.address("busan")
				.build();
		// build한 entity를 데이터베이스에 저장
		memberRepository.save(memberEntity);
		
		// MemberRepository가 상속받은 JpaRepository 메소드를 사용하여 데이터검색
		MemberEntity savedMemberEntity =
				memberRepository.findById("qwe@qwe.com").get();
		
		// MemberRepository에 작성한 커스텀 메서드를 사용
		List<MemberEntity> list = memberRepository.myFindAll("qwe@qwe.com");
		// myFindAll(안에 param값을 넣어주기)
		// 특정한 조건을 찾고싶을 때 findBy를 사용 
		
		System.out.println(list.toString());
		
		return savedMemberEntity.getNickname();
	}
	
	public ResponseDto<LoginDto> login(AuthPostDto dto) {
		LoginDto result = new LoginDto("JWT", 3600000);
		return ResponseDto.setSuccess("", result);
		
	}
	
}
