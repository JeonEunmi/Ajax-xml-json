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
		
		$("#btnAjax").on("click", function() {
			//jQuery load() 메소드를 이용한 Ajax 요청 및 응답 처리
			$("#demo").load("ajax016_response.jsp", function(a) {
				//callback 함수 매개변수에 의해서 결과값 확인 가능
				//결과값이 json 문자열이므로 json 문자열을 Javascript Object로 변환
				var doc = JSON.parse(a);
				
				var object = doc.guestbooks;
				var array = object.guestbook;
				
				var txt = "";

				txt += "<table class=\"table\"><tbody><tr><th>gid</th><th>name</th><th class=\"col-md-8\">content</th><th>regDate</th></tr>";

				for (var a = 0; a < array.length; ++a) {
					var guestbook = array[a];
					var gid = guestbook.gid;
					var name_ = guestbook.name_;
					var content = guestbook.content;
					var regDate = guestbook.regDate;
					txt += "<tr><td>" + gid + "</td><td>" + name_ + "</td><td>"
							+ content + "</td><td>" + regDate + "</td></tr>"
				}
				txt += "</tbody></table>";
				
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