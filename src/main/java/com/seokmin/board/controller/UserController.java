package com.seokmin.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seokmin.board.dto.response.ResponseDto;
import com.seokmin.board.dto.user.GetUserResponseDto;
import com.seokmin.board.dto.user.PatchUserDto;
import com.seokmin.board.dto.user.PostUserDto;
import com.seokmin.board.dto.user.ResultResponseDto;
import com.seokmin.board.repository.MemberRepository;
import com.seokmin.board.service.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {
	
	@Autowired UserService userService;
	
	
	
	@PostMapping("")
	// 추가 end-point는 없음
	public ResponseDto<ResultResponseDto> postUser(@RequestBody PostUserDto requestBody) {
		return userService.postUser(requestBody);
	}
	
	@GetMapping("{email}")
	// end-point를 받는다, path value 값으로
	public ResponseDto<GetUserResponseDto> getUser(@PathVariable("email") String email) {
		// path가 와야함
		return userService.getUser(email);
	}
	// response로 받을것, 제네릭은 dto를 새 클래스로 만들어서 받음
	
	
	@PatchMapping("")
	//end-point는 존재하지 않음
	public ResponseDto<GetUserResponseDto> patchUser(@RequestBody PatchUserDto requestBody) {
	// <>안에 responseDto의 포맷을 넣어준다
		return userService.patchUser(requestBody);
	}
	
	@DeleteMapping("{email}")
	public ResponseDto<ResultResponseDto> deleteUser(@PathVariable("email")String email) {
		// ?는 개발시엔 편하지만 유지보수 때엔 불편함을 가져옴! 
		// 기존에는 PostUserResponseDto 를 제네릭으로 사용했지만->Result
		// 해당(사용)되는 곳이 2메서드 이상이면 공통된 이름으로 사용하도록 바꾼다
		// deleteUser값은 path variable
		return userService.deleteUser(email);
	}
	// 0112 추가
		// get -> api/user/~ 모든 유저정보를 다 받아올 수 있도록 ( GET )
		// 컨트롤러의 requestmapping 에서 api~/~ 까지의 모든 정보를 찾음
		// 서비스로 넘어감, DB에서 모든 유저를 찾음(db.find) -> repository(call함)를 거치게 됨
		// 결과로 넘겨주고 다시 받은값이 또 위로 반환됨
		@GetMapping("")
		public ResponseDto<List<GetUserResponseDto>> getAlluser() {
			return userService.getAllUser();
		}
}
