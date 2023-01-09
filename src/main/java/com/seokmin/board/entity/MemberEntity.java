package com.seokmin.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
Member
이메일
비밀번호
닉네임
프로필 사진
전화번호
주소
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 해당 클래스가 entity 클래스임을 명시!
// 인자로 Entity의 이름을 지정할 수 있음
@Entity(name = "MEMBER")
// 해당 Entity 클래스와 데이터베이스 Table을 인자로 지정한 이름으로 맵핑

// db의 이름은 member , 여기 java에서 이것을 사용 할 땐 members로 검색되고 사용한다
// 없을 땐?
@Table(name = "MEMBER")
public class MemberEntity {
	
	// id는 해당 필드가 Primary임을 명시
	@Id
	// GV는 해당Primary키의 value 자동생성을 지시함
//	@GeneratedValue
	private String email;
	private String password;
	private String nickname;
	private String profile;
	private String telNumber;
	private String address;

}
