<%@ page contentType="text/html; charset=UTF-8"%>
<%-- 뷰페이지 확장자가 .jsp이면 반드시 page contentType = "text/html;charset=UTF-8"을 지정해야 한다.
이 속성의 기능은 웹브라우저에 출력되는 문자와 태그 , 언어코딩 타입을 utf-8로 지정한다. 이 부분을 빼면 jsp파일 내용 중에서 
한글 출력부분이 다 깨져서 출력된다. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>스프링 MVC 이진파일 업로드</h1>
	<form method = "post" action = "uploadFormAction" enctype="multipart/form-data">
	<%--
	파일 첨부기능인 자료실 기능을 만들 때 주의 사항
		1. method = post 방식으로 해야한다. method속성을 생략하면 기본값이 get방식이다. 생략하면 안됨.
		결국 get방식으로 하면 파일 첨부기능을 만들 수 없다. 
		2. form 태그 내에 enctype = "multipart/form-data" 속성을 지정해야 한다. 
	 --%>
	 <input type = "file" name = "uploadFile" multiple>
	 <!-- 최근 브라우저에서는 multiple 속성을 지원하는데 이를 사용하면 하나의 input type = "file"로 다중 첨부파일을 
	 동시에 업로드 할 수 있다. 이 속성은 IE(인터넷 익스플로어)  10 이상 버전에서만 지원된다 .-->
	  <input type = "submit" value = "파일 업로드" > 
	</form>
</body>
</html>