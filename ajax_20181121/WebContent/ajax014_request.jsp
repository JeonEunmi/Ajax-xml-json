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
	$(document).ready(function() {

		//jQuery 버전 Ajax 요청 및 응답 처리

		/*
		 Ajax(Asynchronous JavaScript And XML)
		 - 서버와 (비동기)통신을 하는 기술
		 - Javascript 기반으로 서버 통신 가능
		 - 서버에서의 응답은 XML 포맷의 응답을 받는다. 
		 현재는 JSON 포맷 응답을 주로 사용한다.
		 - IE7 이상에서 작동 가능
		 - 크로스 도메인 정책(Ajax 코드가 제공된 서버만 통신 가능) 준수

		 동기 통신 및 비동기 통신의 차이
		 - 동기 통신은 보내고, 받는 과정이 순차적으로 이루어진다.
		 보내는쪽에서는 응답을 받을때까지 다음 액션 진행 불가.
		 서로 한 번씩 액션을 진행할 수 있는 게임 방식과 유사.
		 - 비동기 통신은 보내고, 받는 과정이 개별적으로 이루어진다.
		 보내는쪽에서는 응답 수신 여부와 관계없이 다음 액션 진행 가능. 
		 서로 동시에 액션을 진행할 수 있는 게임 방식과 유사.
		 응답 수신 후 다음 액션 진행을 위해서 콜백(callback) 함수 필수.
		 */

		// load() 메소드
		// $(selector).load(URL);
		// $(selector).load(URL, data);
		// $(selector).load(URL, data, callback);
		// 버튼에 대한 click 이벤트 등록
		$("#btnAjax").on("click", function() {
			// jQuery load() 메소드를 이용한 Ajax 요청 및 응답 처리
			$("#demo").load("members.json", function(a) {
				// callback 함수 매개변수에 의해서 결과값 확인 가능 
				// 결과값이 json 문자열이므로 json 문자열을 JavaScript 변환
				var doc = JSON.parse(a);
				// javaScript Object 에서 특정 항목 탐색 및 결과 출력
				var array = doc.members;

				var txt = "";
				for (var a = 0; a < array.length; ++a) {
					var member = array[a];
					var name = member.name;
					var phone = member.phone;
					txt += name + "/" + phone + "<br>";
				}
				
				$(this).html(txt);

			});
		});

	});
</script>

</head>
<body>

	<div class="container">

		<h1>Ajax Test</h1>
		<button id="btnAjax">Ajax 요청</button>
		<div id="demo"></div>


	</div>

</body>
</html>