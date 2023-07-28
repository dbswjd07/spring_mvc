package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter //setter() 메서드 자동 제공
@Getter //getter() 메서드 자동 제공
public class MessageVO { //tbl_meddage 테이블의 컬럼명과 일치하는 변수명을 가진 데이터 저장빈 클래스를 정의
	
	private int mid;
	private String targetid; //외래키 설정
	private String sender; //메시디를 보낸 사람
	private String message; //보낸 메시지
	private String senddate; //보낸 날짜

}
