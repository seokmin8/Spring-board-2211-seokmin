package com.seokmin.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seokmin.board.dto.HelloDto;

// Response로 HTML을 반환하는 Controller가 아닌
// Response Body에 직접 데이터를 담아 응답하는 Controller
// @RestController = @Controller + @ResponseBody
@RestController

// @RequestMapping(pattern) : http://localhost:4040/(end-point)/
// end-point의 패턴을 지정하여 해당 패턴의 end-point 일 때 해당 Controller를 실행
@RequestMapping("apis/")
public class MainController {

	// 문자열 수정 시 반복작업의 간편화 위해 상수선언
	static final String HELLO = "hello";

	/*
	 * http방식://호스트:포트/~(/~ 슬러시부터 end point) http://localhost:4040/ <-여기부터 해당
	 * 
	 * @GetMapping(end-point(path)) : 해당 end-point로 Get방식의 Request가 왔을 때 동작
	 */
	@GetMapping("")
	public String hello() {
		return "Hello Spring Boot World!";
	}

	// 메소드가 같을 때 똑같은 end-point가 잡혀있으면 동작안함

	@GetMapping(HELLO)
	// @RequestParam(name="", value="", required=true, defaultValue="")
	// : URL로 데이터를 받는 경우 (GET, Delete) 쿼리 형태로 데이터를 받음
	// http://호스트:포트/end-point?name=value&...
	public String getHello(@RequestParam(name = "name", required = false, defaultValue = "james") String name) {
		return "This is get method , end-point '/hello' " + name;
	}
	// 하나의 키만 넣어줄 땐 name 하나만 넣어줘도 되지만 여러가지 들어갈 땐 어떤게 어떤값인지 명시 해줘야한다

	@GetMapping(HELLO + "/{name}/spring")
	// name이라는 변수로 받겠다
	// @PathVariable(path) : URL 데이터를 받는 경우(Get, Delete) path 형태로 데이터를 받음
	// http://호스트:포트/end-point/VARIABLE
	public String getHelloName(@PathVariable("name") String name) {
		return "This is get method , end-point '/hello' " + name;
	}

	// @PostMapping(end-point) : 해당 end-point로 Post방식의 Request가 왔을 때 동작
	
	@PostMapping(HELLO)
	
//	@RequestBody : 해당 Request의 Body에서 Json을 인식해 인스턴스로 변경
//	public String postHello(@RequestBody HelloDto requestBody) {
//		return requestBody.toString();
	// 받은걸 바로 출력해줌
	public HelloDto postHello(@RequestBody HelloDto requestBody) {
		return requestBody;
		// json 형태로 다시 받음
	}

	// @PutMapping(end-point) : 해당 end-point로 Put 방식의 Request가 왔을 때 동작
	@PutMapping(HELLO)
	public String putHello() {
		return "This is put method , end-point '/hello'";
	}

	// @PatchMapping(end-point) : 해당 end-point로 Patch 방식의 Request가 왔을 때 동작
	@PatchMapping(HELLO)
	public String patchHello() {
		return "This is patch method , end-point '/hello'";
	}

	// @DeleteMapping(end-point) : 해당 end-point로 Delete 방식의 Request가 왔을 때 동작
	@DeleteMapping(HELLO)
	public String deleteHello() {
		return "This is delete method , end-point '/hello'";
	}
}
