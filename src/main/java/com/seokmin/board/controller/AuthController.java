package com.seokmin.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seokmin.board.dto.auth.AuthPostDto;
import com.seokmin.board.dto.auth.LoginDto;
import com.seokmin.board.dto.response.ResponseDto;
import com.seokmin.board.service.AuthService;

@RestController
@RequestMapping("api/auth/")
// api/auth라는 그룹을 만듬

public class AuthController {

	// @Autowired : 해당하는 클래스 인스턴스를 자동으로 생성(주입) 해준다
	@Autowired AuthService authService;
	
	@PostMapping("")
	// end-point는 빈 문자열
	public ResponseDto<LoginDto> login(@RequestBody AuthPostDto requestBody) {
//		LoginDto result = new LoginDto("JWT", 3600000);
//		return ResponseDto.setSuccess("login success", result);
		// 위 postmapping 부터 service에 들어가야한다		
		
		return authService.login(requestBody);

 
		// login import는 수작업으로
		// login 앞 뒤의 괄호들을 채워주자
	}
}
