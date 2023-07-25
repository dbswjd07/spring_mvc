package com.naver.vo;

import lombok.Data;

@Data //setter(), getter(), equals(), toString() 메서드 등과 기본생성자 등 자동제공
public class SampleVO {//데이터 저장빈 클래스

	private int mno; //빈클래스 변수명이 JSON 데이터의 키이름이 된다.
	private String firstName; //성
	private String lastName; //이름
}
