package com.naver.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.service.GuestService;
import com.naver.vo.GuestVO;

@Controller
@RequestMapping("/guestbook/*")
public class GuestController {
 /*
  *  문제) 1. 다음과 같은 컬럼 정보를 가진 방명록 테이블 tbl_guest를 생성한다.
  *  		컬럼명	자료형	크기		제약조건
  *  		gno		int				기본키		번호
  *  		gname	varchar  20		not null	글쓴이
  *  		gtitle 	varchar2 200	not null	글제목
  *  		gcont	varchar2 4000	not null 	글내용
  *  		ghit 	int				default0         조회수
  *  		gdate 	date			default sysdate		
  *  
  *  	2. 1부터 시작해서 1씩 증가하고, 임시 메모리를 사용하지 않는 gno_seq2 시퀀스를 생성한다.
  *  	3. GuestService.java,GuestServiceImpl.java를 com.naver.service패키지에 생성한다.
  *  	4. GuestDAO.java, GuestDAOImpl.java를 com.naver.dao패키지에 생성한다.
  *  	5. com.naver.mappers.mybatis 패키지에 guest.xml을 만들어서 mybatis매퍼태그로 sql문을 작성한다.
  *  	6. /WEB-INF/views/guestbook폴더에 뷰페이지 파일을 만든다.
  *  		가. 먼저 방명록 글쓴이, 글제목, 글내용을 입력하는 입력폼을 만들고 자바스크립트와 jQuery를 사용한 유효성 검증 메시지를 띄우게 한다. 
  * 			입력폼 매핑주소는 guest_write로 한다.
  * 		나. 방명록에 저장되는 매핑주소 guest_write_ok를 작성한다.
  * 		다. 페이징과 책갈피 기능이 되는 guest_list 방명록 목록보기 매핑주소를 작성한다.
  * 		라. 조회수 증가와 내용보기가 되는 guest_cont 매핑주소를 작성한다. 
  * 			조회수 증가와 내용보기는 GuestServiceImpl.java에서 나중에 스프링을 AOP를 통한 트랙젹션을 적용할 수 있게 논리적 단위로 묶는다.
  * 		마. 방명록 수정폼 guest_edit 매핑주소를 작성하고, 수정완료 되게 guest_edit_ok 매핑주소로 작성한다.
  * 		바. 번호를 기준으로 삭제되게 guest_del 매핑주소를 작성한다. 모든 매핑주소를 처리할 때 책갈피 기능이 구현되게 get방식으로 페이지 번호를 전달한다.
  * 		사. com.naver.vo 패키지에 GuestVO.java 빈클래스 작성
  */
	
	@Autowired
	private GuestService guestService;
	
	
	//방명록 글쓰기 폼
	@RequestMapping(value="/guestbook_write",method=RequestMethod.GET) 
	public void guest_write(Model wm, HttpServletRequest request) {
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		wm.addAttribute("page",page);
	}//guest_write()
	
	//방명록 저장
	@RequestMapping(value="/guestbook_write",method=RequestMethod.POST)
	public ModelAndView guest_write(GuestVO g, RedirectAttributes rttr) {
		this.guestService.insertGuest(g);
		rttr.addFlashAttribute("msg","success");
		
		return new ModelAndView("redirect:/guest/guestbook_list");
	}//guest_write() => POST방식
	
	//방명록 목록
	@RequestMapping("/guestbook_list")
	public String guestbook_list(Model listM, HttpServletRequest request,
			@ModelAttribute GuestVO g) {
		
		/*페이징 관련*/
			int page = 1;
			int limit = 10; 
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			g.setStartrow((page-1)*10+1);
			g.setEndrow(g.getStartrow()+limit-1);
		/*끝*/
			
			int totalCount = this.guestService.getTotalCount();
			
			List<GuestVO> glist = this.guestService.getGuestList(g);
		
		/*페이징 연산*/
			int maxpage = (int)((double)totalCount/limit+0.95);
			int startpage = (((int)((double)page/10+0.9))-1)*10+1;
			int endpage = maxpage;
			
			if(endpage > startpage+10-1) endpage=startpage+10-1;
		/*연산 끝*/
			listM.addAttribute("totalCount", totalCount);
			listM.addAttribute("glist", glist);
			listM.addAttribute("startpage", startpage);
			listM.addAttribute("endpage", endpage);
			listM.addAttribute("maxpage", maxpage);
			listM.addAttribute("page", page);
		
		return "guestbook/guestbook_list";
	}
	
}
