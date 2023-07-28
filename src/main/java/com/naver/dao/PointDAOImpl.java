package com.naver.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updatePoint(String sender, int point) {
		/*
		 * DAOImpl에서 MyBatis 매퍼태그로 족수개의 피라미터값(인자값)을 전달할 때는 java.util패키지의 
		 * 컬렉션 Map 사전적인 자료구조를 사용한다.
		 */
		Map<String,Object> pm = new HashMap<>();
		pm.put("sender", sender); //왼쪽의 문자열 sender키이름에 오른쪽의 보낸사람을 저장. mybatis매퍼태그에서
		//키이름을 참조해서 값을 가져온다.
		pm.put("point",point);//키, 값 쌍으로 저장
		
		this.sqlSession.update("pointUp",pm);
		//mybatis에서 update()메서드로 레코드를 수정한다. pointUp은 mybatis point.xml에서 설정할 유일한 아이디명
	}//메시지를 보낸 사람에게 포인터 점수 10점 업데이트
}
