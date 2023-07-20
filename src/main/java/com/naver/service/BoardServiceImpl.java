package com.naver.service;

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

		
		
}
