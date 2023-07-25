package com.naver.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.vo.SampleVO;

@RestController //스프링 4.0 이후부터는 @RestController 애노테이션을 사용함으로써 jsp와 같은 
//뷰페이지를 만들지 않고도, REST방식의 데이터 처리를 할 수 있게 되었다. 만들어지는 데이터는 JSON(키, 값 쌍의 사전적인 자료구조)
//XML, 문자열등
@RequestMapping("/sample/*") //컨트롤 자체 매핑주소 등록
public class Sample6Controller {
	@RequestMapping("/rest_begin") //get or post로 접근하는 매핑주소 처리, rest_begin매핑주소 등록
	public String rest_begin() {
		return "REST API Start"; //문자열 객체 반환
	}//rest_begin
	
	@RequestMapping(value="/sendVO",produces="application/json")
	public SampleVO sendVO() {
		//메서드 리턴타입이 SampleVo이면 빈클래스의 변수명이 JSON데이터의 키이름이 된다.
		SampleVO vo = new SampleVO();
		vo.setMno(10);
		vo.setFirstName("홍");
		vo.setLastName("길동");
		return vo;
	}//sendVO()
	
	//컬렉션 List타입의 JSON데이터 생성
	@RequestMapping(value="/sendList",produces="application/json") //sendList 매핑주소 등록
	public List<SampleVO> sendList(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i = 1; i<=5; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("신");
			vo.setLastName("사임당님");
			
			list.add(vo); //컬렉션에 추가
		}
		return null;
	}//sendList()
	
	//키, 값 쌍의 사전적인 자료구조 컬렉션 Map타입 JSON생성
	@RequestMapping(value="/sendMap",produces="application/json")
	public Map<Integer,SampleVO> sendMap(){
		Map<Integer,SampleVO> map = new HashMap<>();
		for(int i=1;i<=5;i++) {
			SampleVO vo = new SampleVO();
			
			vo.setMno(i);
			vo.setFirstName("홍");
			vo.setLastName("길동");
			
			map.put(i, vo); //컬렉션 맵에 키, 값 쌍으로 저장
		}
		return map;
	}//sendMap()
	
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendError(){
		return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		/*
		 * @RestCOntroller는 별도의 JSP뷰페이지를 만들지 않고도 Rest api 서비스를 실행하기 때문에
		 * 결과 데이터에 대한 예외 오류가 많이 발생한다. 이런 경우에는 스프링에서 제공하는 내장 api인
		 * ResponseEntity 타입을 사용해서 개발자에게 문제가 되는 HTTP 에러 상태코드인 404, 500등과
		 * 같은 나쁜 상태코드를 데이터와 함꼐 브라우저로 전송되어져 좀 더 세밀한 에러 제어를 할 수 있게 한다.
		 * 
		 * BAD_REQUEST는 400 나쁜 상태 코드를 브라우저로 전송한다.
		 */
	}//sendError()
	
	//컬렉션 List 제네릭 타입 json정상적인 데이터와 404(해당 경로에 파일을 찾을 수 없음 에러) 
	//나쁜 상태 코드를 동시에 브라우저로 전송
	@RequestMapping(value="/sendListErrorNot",produces="application/json")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=3;i>=1;i--) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("이");
			vo.setLastName("순신");
			
			list.add(vo);
		}
		return new ResponseEntity<List<SampleVO>>(list,HttpStatus.NOT_FOUND);
		//NOT_FOUND => 404 에러 상태 코드
	}
}
