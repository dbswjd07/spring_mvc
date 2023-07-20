<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- JSTL 코어 태그립 추가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 mvc 게시판 목록</title>
<script src="../resources/js/jquery.js"></script>
</head>
<body>
<script type="text/javascript">
	$msg = "${msg}"; //자바스크립트에서 JSP문법인 el(표현언어) 또는 jstl사용 가능.
	//BoardController.jsva 스프링 컨트롤러에서 msg 키이름에 저장된 success문자를 EL로 가져온다.
	
	alert($msg);
	
	if($msg == 'success'){
		alert('게시물 처리가 완료되었습니다!');
	}
</script>
</body>
</html>