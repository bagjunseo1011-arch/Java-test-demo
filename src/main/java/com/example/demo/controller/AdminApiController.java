package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.domain.Member;
import com.example.demo.model.dto.MemberForm;
import com.example.demo.model.dto.MemberResponse;
import com.example.demo.model.dto.RoleRequest;
import com.example.demo.model.service.MemberService;

// [7주차 연습문제 ②] 등록·수정·삭제마다 반복되던 try-catch 는 ApiExceptionHandler 한 곳으로 모았다.
//   IllegalArgumentException(중복 아이디, 빈 값, 허용 안 되는 권한, 본인 보호 …) → 400 {"error": "..."}
@RestController // 관리자 회원 관리 API (JSON)
@RequestMapping("/api/admin/members") // 주소는 하나, 할 일은 메서드로 구분
public class AdminApiController {

	@Autowired
	MemberService memberService;

	// 회원 목록 : GET /api/admin/members
	// [7주차 연습문제 ①] 검색 : GET /api/admin/members?keyword=student (아이디·이름에 검색어 포함)
	@GetMapping
	public List<MemberResponse> list(@RequestParam(required = false) String keyword) {
		List<Member> members = memberService.search(keyword); // 검색어가 없으면 전체
		return members.stream().map(MemberResponse::from).toList(); // 엔티티 목록 → DTO 목록
	}

	@GetMapping("/{id}") // 회원 1명 : GET /api/admin/members/3
	public ResponseEntity<MemberResponse> get(@PathVariable Long id) {
		return memberService.findById(id)
				.map(member -> ResponseEntity.ok(MemberResponse.from(member))) // 있으면 200 OK
				.orElse(ResponseEntity.notFound().build());                     // 없으면 404
	}

	@PostMapping // 회원 등록 : POST /api/admin/members  (본문 JSON → MemberForm)
	public ResponseEntity<MemberResponse> create(@RequestBody MemberForm form) {
		Member saved = memberService.signup(form); // 5주차 회원가입 로직 재사용 (BCrypt)
		return ResponseEntity
				.created(URI.create("/api/admin/members/" + saved.getId())) // 201 + Location
				.body(MemberResponse.from(saved));
	}

	@PatchMapping("/{id}/role") // 권한 변경 : PATCH /api/admin/members/3/role  {"role":"ADMIN"}
	public ResponseEntity<MemberResponse> changeRole(@PathVariable Long id, @RequestBody RoleRequest request) {
		Member member = memberService.changeRole(id, request.role());
		return ResponseEntity.ok(MemberResponse.from(member)); // 200 OK + 바뀐 회원
	}

	@DeleteMapping("/{id}") // 회원 삭제 : DELETE /api/admin/members/3
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		memberService.deleteMember(id);
		return ResponseEntity.noContent().build(); // 204 No Content (돌려줄 본문 없음)
	}
}
