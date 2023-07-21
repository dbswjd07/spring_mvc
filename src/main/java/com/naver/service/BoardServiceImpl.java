package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.BoardDAO;
import com.naver.vo.BoardVO;

@Service //@Service 애노테이션을 추가함으로써 스프링에서 서비스로 인식하게 한다.
public class BoardServiceImpl implements BoardService {

		@Autowired //자동의존성 주입
		private BoardDAO boardDao;

		@Override
		public void insertBoard(BoardVO b) {
			this.boardDao.insertBoard(b);
		}

		@Override
		public int getTotalCount() {
			return this.boardDao.getTotalCount();
		}

		@Override
		public List<BoardVO> getBoardList(BoardVO b) {
			// TODO Auto-generated method stub
			return this.boardDao.getBoardList(b);
		}
		
		//내용보기 + 조회수 증가 => 스프링의 AOP를 통한 트랜잭션을 적용함으로써 데이터 불일치 현상을 제거해서 사이트의 신뢰도를 높임
		@Override
		public BoardVO getBoardCont(int bno) {
			this.boardDao.updateHit(bno); //조회수 증가
			return null;
		}


		
		
}
