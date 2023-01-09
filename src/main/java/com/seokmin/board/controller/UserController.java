package com.seokmin.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seokmin.board.dto.response.ResponseDto;
import com.seokmin.board.dto.user.PostUserDto;
import com.seokmin.board.dto.user.PostUserResponseDto;
import com.seokmin.board.service.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {
	
	@Autowired UserService userService;
	
	@PostMapping("")
	// 추가 end-point는 없음
	public ResponseDto<PostUserResponseDto> postUser(@RequestBody PostUserDto requestBody) {
		return userService.postUser(requestBody);
	}
}
