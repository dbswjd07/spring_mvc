package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.vo.ProductVO;

@Controller
public class Sample4Controller {
	@RequestMapping("/doE") //GET OR POST로 접근하는 매핑주소를 처리, doE매핑주소 등록
	public String doE(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg","부산시"); 
		//다른 url-pattern 매핑주소 msg키이름에 부산시 값을 담아서 전달한다.
		//백엔드 서버상에서 실행되기 때문에 웹주소창에서 전달되는 값이 노출되지 않는다. 보안상 좋음
		return "redirect:/doF"; 
		// /doE 매핑주소가 실행되면 다른 매핑주소인 /doF로 이동 => 
		//저장, 수정, 삭제 후 새롭게 테이블 컬럼 레코드값이 변화된 것을 정확히 확인하고자 하는 경우 사용한다.
		
	}
	
	@GetMapping("/doF") //get으로 접근하는 매핑주소를 처리 => redirect는 get방식이다.
	public void doF(@ModelAttribute("msg") String value) {
		//리턴 타입이 없는 void 형이면 매핑주소인 doF가 뷰페이지 파일명이 된다. 
		System.out.println("전달되어지는 값:"+value);
	}
	
	//키,값 쌍의 JSON 데이터를 만드는 실습
	@RequestMapping(value="/doJSON",produces="application/json")
	public @ResponseBody ProductVO doJSON() {
		/* @ResponseBody 애노테이션을 사용하면 JSP파일을 만든지 않고도 브라우저에 키, 값 쌍의 사전적인 json 데이터를 
		 * 쉽게 만들 수 있다. 리턴 타입이 ProductVO 빈클래스 타입이면 JSON 데이터 의 키이름은 해당 클래스의 변수명이 된다. 
		 */
		
		ProductVO p = new ProductVO("에어컨",1500000);
		return p;
	}
}

