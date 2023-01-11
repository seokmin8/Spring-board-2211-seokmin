package com.seokmin.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seokmin.board.service.FileService;

@RestController
@RequestMapping("file")
// api가 아니기 때문에
public class FileController {
// was서버와 별개이지만 여기서는 따로 분류하지 않음!
// 먼저 맞춰줘야 할 부분. resources -> properties 세팅
	
	@Autowired FileService fileService;
	
	
	// 파일을 서버에 업로드 ( Post )
	
	@PostMapping("upload")
	
	// @RequestParam(field명) : requestBody에서 특정 필드만 받아옴
	// 전체를 받을 땐 body, 하나하나 받을땐 Param으로 받는다
	// RequestBody의 파일을 받아올 땐 MultipartFile 인스턴스로 받음! 약속
	public String fileUpload(@RequestParam("file") MultipartFile file) {
		return fileService.fileUpload(file);
	}
		
		
	
	
	// 파일을 서버에서 다운로드 ( Get )
	@GetMapping("download/{fileName}")
	public ResponseEntity<Resource> fileDownload(@PathVariable("fileName") String fileName) {
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.body(fileService.fileDownload(fileName));
				// 다운로드 생성 시 위처럼 작성
				// ok = status 200을 의미함
	}
	
	
	// 이미지파일 일 경우 해당 이미지를 출력 ( Get )
	// {path variable} 와 (@pV"path variable") 같아야 함
	@GetMapping(value="image/{imageName}", produces= {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	public Resource getImage(@PathVariable("imageName") String imageName) {
		return fileService.getImage(imageName);
	
	}
	
}
