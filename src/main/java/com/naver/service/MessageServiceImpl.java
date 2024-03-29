package com.naver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.dao.MessageDAO;
import com.naver.dao.PointDAO;
import com.naver.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {
	//다른 서비스하고 차이점은 스프링 AOP를  통한 트랜잭션을 적용하기 위해서 2개의 DAO로 나눠진다는 것이다.
	@Autowired
	private MessageDAO messageDao;
	
	@Autowired
	private PointDAO pointDao;

	//메시지 INSERT+포인터 점수 10점 UPDATE => 스프링의 AOP를 통한 트랜잭션 적용부분
	@Transactional //트렌젝션 적용
	@Override
	public void insertMessage(MessageVO vo) {
		this.messageDao.insertM(vo); //메시지 추가
		this.pointDao.updatePoint(vo.getSender(),10); 
		//메시지를 보낸 사람에게 포인터 점수 10점 업데이트
	}
}
