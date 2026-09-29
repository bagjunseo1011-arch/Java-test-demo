package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.domain.TestDB;
import com.example.demo.model.repository.TestRepository;

@Service // 서비스등록, 자동등록됨
public class TestService {

	@Autowired // 객체의존성 주입 DI(컨테이너 내부등록)
	private TestRepository testRepository;

	// 기존 코드 : 이름 1건 찾기
	// public TestDB findByName(String name) { // 이름찾기
	// 	return (TestDB) testRepository.findByName(name);
	// }

	// 다수 사용자 출력 : 전체 조회 (SELECT * FROM testdb)
	public List<TestDB> findAll() {
		return testRepository.findAll();
	}
}
