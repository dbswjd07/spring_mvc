package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class GuestVO {
	private int gno; //방명록 글번호
	private String gname; //글쓴이
	private String gtitle; //방명록 글제목
	private String gcont; //방명록 글내용
	private int ghit; //조회수
	private String gdate; //작성날짜
	
	//페이징 => 쪽나누기
	private int startrow; //시작행 번호
	private int endrow; //끝행 번호
}
