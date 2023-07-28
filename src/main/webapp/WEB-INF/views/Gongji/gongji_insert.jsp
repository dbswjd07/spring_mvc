<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 공지 작성</title>
<script src="../resources/js/jquery.js"></script>
<script>
 function insert_check(){
	 if($.trim($("#gongji_name").val())==""){
		 alert("작성자를 입력하세요!");
		 $("#gongji_name").val("").focus();
		 return false;
	 }
	 
	 if($.trim($("#gongji_title").val())==""){
		 alert("공지 제목을 입력하세요!");
		 $("#gongji_title").val("").focus();
		 return false;
	 }
	 
	 if($.trim($("#gongji_cont").val())==""){
		 alert("공지 내용을 입력하세요!");
		 $("#gongji_cont").val("").focus();
		 return false;
	 }
 }
</script>
</head>
<body>
	<form method="post" onsubmit="return insert_check();">
	<H2>스프링 MVC 공지 입력폼</H2>
	작성자 : <input name="gongji_name" id="gongji_name" size="14"><br><br>
	공지 제목: <input name="gongji_title" id="gongji_title" size="14"><br><br>
	공지 내용 : <textarea name="gongji_cont" id="gongji_cont" rows="10" cols="36"></textarea>
	<hr>
	<input type="submit" value="공지 등록">
	<input type="Reset" value="취소" onclick="$('#gongji_name').focus();">
	<input type="button" value="목록" onclick="location='/controller/gongji/gongji_list?page=${page}';">
	
	
	</form> 
</body>
</html>