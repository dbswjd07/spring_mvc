package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {

	@RequestMapping("/doC") //doC 매핑주소 등록
	public String doC(@ModelAttribute("msg2") String msg) {
		/* @ModeAttibute("msg2")는 msg2 피라미터 이름에 인자값을 담아서 전달한다. 주소창에 노출되는 방식인
		 * doC?msg2=인자값 형태도 전달한다. 
		 */
		return "result"; //  /WEB-INF/views/result.jsp
	}
}
