package com.seokmin.board.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.seokmin.board.dto.auth.AuthPostDto;
import com.seokmin.board.dto.auth.LoginDto;
import com.seokmin.board.dto.response.ResponseDto;

// @Service : 해당 클래스가 Service 레이어 역할을 함
@Service
public class AuthService {

	public ResponseDto<LoginDto> login(AuthPostDto dto) {
		LoginDto result = new LoginDto("JWT", 3600000);
		return ResponseDto.setSuccess("", result);
		
	}
	
}
