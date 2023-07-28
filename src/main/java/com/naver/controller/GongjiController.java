package com.naver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.service.GongjiService;

@Controller
@RequestMapping("/gongji/*") //컨트롤러 자체 매핑주소 등록
public class GongjiController {

	/* 문제)
	 * 1. 다음과 같은 테이블 정보를 가진 tbl_gongji 공지 사항 테이블을 생성한다.
	 * 		컬럼명	            자료형 	   크기 	제약조건
	 * 		gongji_no    number    38	primary key
	 * 		gongji_name	 varchar2  50 	not null
	 * 		gongji_title varchar2  200	not null
	 * 		gongji_cont  varchar2  4000 not null
	 * 		gongji_hit	 number		38	sysdate 0
	 * 		gongji_date  date			sysdate sysdate
	 * 	
	 * 		1부터 시작하고 , 1씩 증가하고, 임시 메모리를 사용하지 않는 gongji_no_seq 시퀀스를 생성한다.
	 * 
	 * 2. gongji.xml을 com.naver.mappers.mybatis패키지에 매퍼태그 파일을 만든다.
	 * 3. 테이블 컬럼명과 일치하는 데이터 저장빈 클래스 GongjiVO.java를 만들고, Lombok라이브러리를 활용해서
	 * setter(), getter() 메서드를 자동 생성한다.
	 * 4. com.naver.dao패키지에 GongjiDAO.java인터페이스를 만들고 이를 구현 상속한 자손클래스 GongjiDAOImpl.java를 만든다
	 * 5. com.naver.service 패키지에 GongjiService.java 인터페이스를 만들고, 이를 구현 상속한 자손클래스
	 * GongjiServiceImpl.java를 만든다.
	 * 6. com.naver.controller 패키지의 스프링 컨트롤러 클래스 GongjiController.java를 통한 
	 * 뷰페이지를 다음과 같이 작성한다.
	 * 		가. 공지 작성 폼 뷰페이지 gongji_insert.jsp를 만든다. 공지작성자, 공지제목, 공지내용 입력폼을 만들고
	 * 		자바스크립트와 jQuery를 사용한 유효성 검증 처리를 한다.
	 * 		나. 공지 저장 후 공지 목록페이지를 gongji_list.jsp를 작성한다. 뷰리졸브 경로는 /WEB-INF/views/gongji로 한다. 
	 * 		공지 저장 매핑주소는 gongji_insert_ok로 한다. 공지 목록 매핑주소는 gongji_list로 한다. 
	 * 		다. 공지 수정폼 뷰페이지 gongji_update.jsp를 작성한다. 물론 유효성 검증 처리 한다. 공지 수정 완료
	 * 		매핑주소는 gongji_update_ok로 한다. 공지 수정폼 매핑주소는 gongji_update로 한다. 
	 * 		라. 공지 내용보기에서는 조회수증가와 함께 GongjiServiceImpl.java에서 스프링의 aop를 통한 트랜잭션 처리를
	 * 		한다. 내용보기 매핑주소는 gongji_cont로 한다. 
	 * 		마. gongji_cont.jsp에서 삭제 버튼을 클릭하면 자바스크립트 어떤 함수를 사용하여 삭제유무 창을 띄운다음 확인 버튼을
	 * 		클릭하면 삭제 처리한다. 취소를 클릭하면 현재창에 그대로 있게 한다. 삭제 매핑주소는 gonji_del로 한다. 
	 * 	7. 공지목록에서 페이징도 한다.
	 * 	8. 공지목록에서 등록날짜 출력할 때 JSTL를 사용해서 년월일만 나오게 한다.	
	 */
	
	@Autowired
	private GongjiService gongjiService;
	
	@RequestMapping(value="/gongji_insert",method=RequestMethod.GET)
	
	public void gongji_insert(Model wm, HttpServletRequest request) {
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		wm.addAttribute("page",page);
		
	}
}
