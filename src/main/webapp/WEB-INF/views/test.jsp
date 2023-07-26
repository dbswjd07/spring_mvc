<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기식 아작스 댓글 연습</title>
<style type="text/css">
#modDiv{ /*댓글 수정폼 ui*/
	width: 300px; height: 100px;
	background-color: gray;
	position: absolute; /*절대 위치*/
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000; /*position 속성이 absolute 나 fixed(고정위치)로 설정된 곳에서 사용한다. 
	이 속성은 요소가 겹쳐지는 순서를 제어할 수 이싿. 숫자값이 큰 것이 먼저 앞에 나온다. */
}
</style>
</head>
<body>
<%-- 댓글 수정화면 --%>
<div id = "modDiv" style="display: none;">
	<%-- display:none; css로 최초 실행시 댓글 수정화면을 안나오게 한다. --%>
	<div class="model-title"></div><%--댓글 번호 --%>
	<div>
	 <textarea rows = "3" cols="30" id = "replytext"></textarea>
	</div>
<div>
<button type = "button" id="replyModBtn">댓글 수정</button>
<button type = "button" id="replyDelBtn">댓글 삭제</button>
<button type = "button" id="closeBtn" onclick="modDivClose();">닫기</button>
</div>
</div>

<h2>아작스 댓글 연습페이지</h2>
<div>
	<div>
	댓글 작성자:<input type="text" name="replyer" id="newReplyWriter">
	</div>
	<br>
	<div>
	댓글 내용: <textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
	</div>
	<br>
	
	<button type="button" id="replyAddBtn">댓글등록</button>
</div>

<br>
<hr>
<br>

<%-- 댓글 목록 --%>
<ul id="replies"></ul>

<%--jQuery 라이브러리 읽어옴 --%>
<script type="text/javascript" src="/controller/resources/js/jquery.js"></script>
<script type="text/javascript">
	$bno = 23;//게시판 번호 임시적으로 할당
	
	getAllList();//댓글 목록 함수 호출
	function getAllList(){
		$.getJSON("/controller/replies/all/"+$bno, function(date){
			//get 방식으로 JSON데이터를 처리하는 비동기식 jQuery 아작스 함수 => 서버로부터 가져온 json데이터 목록은
			//date 매개변수에 저장됨.
			$result="";
			$(date).each(function(){//jQuery each()함수로 반복한다.
				$result += "<li date-rno='"+this.rno+"'class='replyLi'>"
				+this.rno+":<span class='com' style='color:blue; font-weight:border;'>"
				+this.replytext+"</span> <button type='button'>댓글수정</button></li><br>";
			
			});
			$('#replies').html($result); //html() jQuery함수는 문자와 태그를 함께 변경 적용
			
			/*  문제) 매핑주소 test가 실행되면 웹브라우저로 댓글 목록이 나오게 만들어보자. */
		});
	}//getAllList()
</script>
</body>
</html>