package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 어노테이션 명시
public class DemoController {

	@GetMapping("/hello") // 전송 방식 GET
	public String hello(Model model) {
		model.addAttribute("data", "반갑습니다."); // model 설정
		return "hello"; // hello.html 연결
	}

	// 2주차 연습문제 : 속성 5개 추가
	@GetMapping("/hello2")
	public String hello2(Model model) {
		model.addAttribute("name", "홍길동");      // TODO: 본인 이름으로 수정
		model.addAttribute("studentId", "20250000"); // TODO: 본인 학번으로 수정
		model.addAttribute("major", "컴퓨터공학과");
		model.addAttribute("subject", "자바웹프로그래밍(2)");
		model.addAttribute("week", "2주차");
		return "hello2"; // hello2.html 연결
	}
}
