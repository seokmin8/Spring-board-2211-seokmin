package com.seokmin.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seokmin.board.dto.response.ResponseDto;
import com.seokmin.board.dto.user.GetUserResponseDto;
import com.seokmin.board.dto.user.PostUserDto;
import com.seokmin.board.dto.user.PostUserResponseDto;
import com.seokmin.board.service.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {
	
	@Autowired UserService userService;
	
	@GetMapping("{email}")
	// end-point를 받는다, path value 값으로
	public ResponseDto<GetUserResponseDto> getUser(@PathVariable("email") String email) {
		// path가 와야함
		return userService.getUser(email);
	}
	// response로 받을것, 제네릭은 dto를 새 클래스로 만들어서 받음
	
	@PostMapping("")
	// 추가 end-point는 없음
	public ResponseDto<PostUserResponseDto> postUser(@RequestBody PostUserDto requestBody) {
		return userService.postUser(requestBody);
	}
}
