package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Autowired //자동 의존성 주입
	private SqlSession sqlSession; //mybatis 쿼리문 수행 sqlSession
}
