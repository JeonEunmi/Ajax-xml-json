<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	//절대경로 확인
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>쌍용교육센터</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	// JavaScript 버전 Ajax 요청 및 응답 처리

	/*
	Ajax(Asynchronous JavaScript And XML)
	- 서버와 (비동기) 통신을 하는 기술
	- Javascript 기반으로 서버 통신 가능 
	- 서버에서의 응답은 XML 포맷의 응답을 받는다. -> 현재는 JSON 포맷의 응답을 주로 사용한다.
	
	동기 통신 및 비동기 통신의 차이
	- 동기 통신은 보내고, 받는 과정이 순차적으로 이루어진다.
	보내는쪽에서는 응답을 받을때까지 다음 액션 진행 불가.
	서로 한 번씩 액션을 진행할 수 있는 게임 방식과 유사.
	- 비동기 통신은 보내고, 받는 과정이 개별적으로 이루어진다.
	보내는쪽에서는 응답 수신 여부와 관계없이 다음 액션 진행 가능. 
	서로 동시에 액션을 진행할 수 있는 게임 방식과 유사.
	응답 수신 후 다음 액션 진행을 위해서 콜백 함수 필수.
	 */

	function loadDoc() {
		//Ajax 요청을 위한 XMLHttpRequest Object 생성
		var xhttp = new XMLHttpRequest();
		//이벤트 등록
		//-> readyState 상태값이 변경될 때를 감지
		//-> readyState 상태값이 병경될 때마다 함수 호출
		xhttp.onreadystatechange = function() {
			//readyState 상태값 확인
			//상태값 4는 서버로부터 응답이 돌아온 상태
			if (this.readyState == 4 && this.status == 200) {
				//서버로부터의 응답을 화면에 출력
				document.getElementById("demo").innerHTML = this.responseText;
				console.log("Ajax 요청끝", true);
			}
		};
		//요청 주소 및 요청 방식(GET or POST, 비동기 or 동기 지정
		xhttp.open("GET", "ajax002_response.jsp", true);
		//서버 요청 시작
		xhttp.send();
		console.log("Ajax 요청시작", true);

	}
</script>

<script>
	$(document).ready(function() {

	});
</script>

</head>
<body>

	<div class="container">

		<h1>Ajax Request</h1>
		<button onclick="loadDoc()">Ajax 요청</button>
		<div id="demo"></div>


	</div>

</body>
</html>