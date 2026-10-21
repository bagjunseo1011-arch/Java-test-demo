package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// [7주차 연습문제 ②] 오류 처리 한 곳에 모으기
// @RestController(JSON API)에서 던진 예외만 처리한다. 화면(@Controller)은 기존처럼 각자 처리.
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

	// 중복 아이디, 빈 값, 허용되지 않는 권한, 없는 회원, 본인 보호 … → 400 {"error": "메시지"}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> badRequest(IllegalArgumentException e) {
		return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
	}

	// 따옴표·쉼표가 빠진 JSON 등 → 400. 기본 오류 응답은 개발 중 trace(서버 내부 정보)까지 내보내므로 메시지만 준다
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, String>> unreadableJson(HttpMessageNotReadableException e) {
		return ResponseEntity.badRequest().body(Map.of("error", "요청 JSON 형식이 올바르지 않습니다."));
	}
}
