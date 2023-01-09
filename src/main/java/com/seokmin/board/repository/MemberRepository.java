package com.seokmin.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seokmin.board.entity.MemberEntity;

// 해당 인터페이스가 Repository임을 명시
@Repository
// Repository는 인터페이스로 작성해야함
// JpaRepository 인터페이스를 상속 받아야 함
// JpaRepository<Table(Entity Class), Primary key type>
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

	// @쿼리는 커스텀 ORM 메소드를 작성
	// ?1, ?2, ... -> 매개변수로 받아온 변수를 해당 위치로 넣기 위한 구문
	@Query("select m from MEMBER m where m.email = ?1")
	// select * 을 하게되면 무조건 list로 나옴
	// @query문 작성 시 테이블명을 alias로 지정해야 사용가능
	List<MemberEntity> myFindAll(String email);
}
