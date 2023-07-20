package com.naver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.service.BoardService;
import com.naver.vo.BoardVO;

@Controller //스프링 MVC 게시판 컨트롤러 클래스
@RequestMapping("/board/*") //경로 구분하기 위해서 컨트롤러 자체 매핑주소 /board등록
public class BoardController {

		@Autowired
		private BoardService boardService;
		
		//게시판 글쓰기 폼
		@RequestMapping(value="/board_write",method=RequestMethod.GET) //get으로 접근하는 매핑주소를 처리
		// /board_write매핑주소 등록
		public void board_write(){
			//리턴타입이 void형이면 매핑주소가 뷰페이지 파일명이 된다.
			//뷰리졸브 경로는 /WEB-INF/views/board/board_write.jsp
		}//board_write()
		
		 //게시판 저장
		@RequestMapping(value="/board_write",method=RequestMethod.POST) 
		//post로 접근하는 매핑주소를 처리 동일 컨트롤러에서 같은 매핑주소를 사용하는 경우 메서드 방식으로 구분한다.
		public ModelAndView board_write(BoardVO b,RedirectAttributes rttr) {//전달인자 개수가 다른 메서드 오버로딩
			/* BoardVO.java의 변수명과 board_write.jsp의 네임피라미터 이름이 같으면 Board b라고만 해도 
			 * b객체에 글쓴이, 글제목, 글내용이 저장되어 있다. 
			 * 
			 */
			this.boardService.insertBoard(b); //게시판 저장
			rttr.addFlashAttribute("msg","success");
			/*
			 * 백엔드 서버의 스프링 컨트롤러에서 다른 매핑주소로 msg 키이름에 success문자열을 담아서 값을 전달할 때
			 * 사용한다. 이 방벙은 웹주소창에 값 노출이 안된다. 보안상 좋다. 
			 */
			return new ModelAndView("redirect:/board/board_list"); //게시물 목록보기 매핑주소인 board_list로 이동
			/* ModelAndView 생성자 인자값으로 올 수 있는 것 종류)
			 * 	1. 뷰페이지 파일명과 경로
			 * 	2. redirect:/를 사용해서 매핑주소
			 * 
			 */
		}//board_write() => POST방식
		
		//게시판 목록
		@RequestMapping("/board_list") //get or post로 접근하는 매핑주소를 처리, board_list 매핑주소 등록
		public String board_list() {
			return "/board/board_list"; //뷰페이지 경로(뷰리졸브 경로) => /WEB-INF/views/board/board_list
		}//board_list()
}
