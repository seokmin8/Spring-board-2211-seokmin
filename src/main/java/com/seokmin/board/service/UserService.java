package com.seokmin.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seokmin.board.dto.response.ResponseDto;
import com.seokmin.board.dto.user.GetUserResponseDto;
import com.seokmin.board.dto.user.PostUserDto;
import com.seokmin.board.dto.user.PostUserResponseDto;
import com.seokmin.board.entity.MemberEntity;
import com.seokmin.board.repository.MemberRepository;

@Service
public class UserService {

	@Autowired
	MemberRepository memberRepository;
	// 엔터티명을 따라감
	MemberEntity member;
	
	public ResponseDto<GetUserResponseDto> getUser(String email) {
		// controller에서 검증이 이뤄지니 여기선 검증필요 없음
		// 해당 email의 정보를 DB에서 검색
		MemberEntity member;
	      
	      try {
	         member = memberRepository.findById(email).get();
	      } 
	      // 존재하지 않으면 "Not Exist User" 메세지를 포함한 Failed Response 반환
	      catch (Exception e) {
	         return ResponseDto.setFailed("Not Exist User");
	      }
		// 존재하면 User정보 반환
		// 1
//      GetUserResponseDto responseData = new GetUserResponseDto();
//      responseData.setEmail(member.getEmail());
//      responseData.setNickname(member.getNickname());
//      responseData.setProfile(member.getProfile());
//      responseData.setTelNumber(member.getTelNumber());
//      responseData.setAddress(member.getAddress());
//      
//      return ResponseDto.setSuccess("Get User Success", responseData);
		
		 // 2 GetUserResponse Class에 Builder 추가
//      GetUserResponseDto responseData = 
//            GetUserResponseDto
//            .builder()
//            .email(email)
//            .nickname(email)
//            .profile(email)
//            .telNumber(email)
//            .address(email)
//            .build();
//      
//      return ResponseDto.setSuccess("Get User Success", responseData);
		
		// 3 => 이걸 변형해서 아래에 다시 정의
//		return ResponseDto.setSuccess("Get User Success",
//				new GetUserResponseDto(
//						member.getEmail(),
//						member.getNickname(),
//						member.getProfile(),
//						member.getTelNumber(),
//						member.getAddress()
//						)
//				);
	      return ResponseDto.setSuccess("Get User Success", new GetUserResponseDto(member));
	      // 위의 3가지 로직들과 같지만, 결국 생성자를 생성해서 간단하게 정리해버림
	}
	
	public ResponseDto<PostUserResponseDto> postUser(PostUserDto dto) {

		// DB에 해당 이메일이 존재하는지 체크
		// 존재한다면 Failed Response를 반환
		// 이걸 수행하려면???
		String email = dto.getEmail();
		MemberEntity member;
		// dto에 존재하는 email 꺼내오기
		
		try {
			if(memberRepository.existsById(email))
				return ResponseDto.setFailed("이미 존재하는 email 입니다");
		} catch (Exception e) {
			return ResponseDto.setFailed("데이터베이스 오류입니다");
		}
		
		// 아래에서 안된 이유는?? 
		
		
//		try {
//			member = memberRepository.findById(email).get();
//			// repository가 사용되면 try catch문 안에 넣어준다 
//			// 위 메서드에서 예외가 발생하면!
//		} catch (Exception e) {
//			return ResponseDto.setFailed("이미 존재하는 email 입니다");
//		}
		// 위 try catch문은 해당 email이 존재할때 실행되어야 함
		// 선언이 2번되어 컴파일 에러발생. 선언을 1번만 하게하자
		// db에서 해당email 검색 후 꺼내온다

		String password = dto.getPassword();
		String password2 = dto.getPassword2();

		if (!password.equals(password2)) {
			return ResponseDto.setFailed("비밀번호가 서로 다릅니다");
		}
		member = MemberEntity
				.builder()
				.email(dto.getEmail()).password(dto.getPassword())
				.nickname(dto.getNickname())
				.telNumber(dto.getTelNumber())
				.address(dto.getAddress() + " " + dto.getAddressDetail()).build();

		// JpaRepository.save(Entity) 메서드
		// 해당 Entity Id가 DB table에 존재하지 않으면
		// Entity INSERT 작업을 수행
		// !!하지만!! 해당 Entity Id가 DB table에 존재하면!!
		// 존재하는 Entity UPDATE 작업을 수행
		memberRepository.save(member);

		return ResponseDto.setSuccess("회원가입에 성공했습니다.", new PostUserResponseDto(true));
	}
}
