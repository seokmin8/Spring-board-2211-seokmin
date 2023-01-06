package com.seokmin.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="set")
public class ResponseDto<D> {
	private boolean status;
	private String message;
	private D data;
	
	public static <D> ResponseDto<D> setSuccess(String message, D data) {
		// static뒤의 D는 Response가 D의 형태를 사용할 것 이라는걸 알려주는 것
		// 어디서나 클래스에서 접근 가능하다. 클래스의 멤버를 반환
		// 성공시 매개변수로 멤버변수를 할당 , 초기화
		return ResponseDto.set(true, message, data);
		// return new ResponseDto<>(true, message, data); 이렇게도 가능
		// set -> 생성자
	}
	
	public static <D> ResponseDto<D> setFailed(String message) {
		return ResponseDto.set(false, message, null);
	}
}
