package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BoardVO;

@Repository //@Repository 애노테이션은 DAO를 스프링에서 인식하게 한다.
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession; //mybatis 쿼리문을 수행할 sqlSession생성
	
	@Override
	public void insertBoard(BoardVO b) {
		this.sqlSession.insert("board_in",b);
		//mybatis에서 insert()메서드는 레코드를 저장시키는 기능을 한다. 
		//board_in은 board.xml에서 설정할 유일한 아이디명이디.
		
	}//스프링 MVC 게시판 저장
	
}
