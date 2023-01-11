package com.seokmin.board.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	// 환경변수(application.properties)값 설정
	@Value("${file.dir}")
	private String dir;
	
	public String fileUpload(MultipartFile file) {
		// 해당 file이 있는지 검사
		if(file.isEmpty())
			return null;
		
		// Original file name을 가져옴
		String originalFileName = file.getOriginalFilename();
		// 확장자를 가져옴 (ex = image.png >> .png 가 확장자)
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		// .substring(여기서부터 잘라서 가져오겠다) last는 뒤에서부터 "안" 찾아 거기서부터 자름
		
		// 저장할 때 이름으로 사용되는 UUID 형식의 이름생성
		// uuid는 랜덤식으로 만들어지는 문자
		String uuid = UUID.randomUUID().toString();
		
		// 실제로 저장되는 이름 생성
		String saveName = uuid + extension;
		
		// 해당 파일이 저장되는 실제경로
		String savePath = dir + saveName;
		
		// 해당 파일을 실제로 해당 경로에 저장
		try {
			file.transferTo(new File(savePath));
		} catch (Exception e) {
			return null;
		}
		return saveName;
	}
	
	// 이미지 출력
	public Resource getImage(String imageName) {
		try {
			return new UrlResource("file:" + dir + imageName);
		} catch (Exception e) {
			return null;
		}
	}
	
	// 파일 다운로드
	public Resource fileDownload(String fileName) {
		try {
			return new UrlResource("file:" + dir + fileName);
		} catch (Exception e) {
			return null;
		}
	}
	
}
