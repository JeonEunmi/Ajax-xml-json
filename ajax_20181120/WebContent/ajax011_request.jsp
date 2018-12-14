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
	var endPage = 0;
	var totalCount = 0;

	function loadDoc(pageStart) {
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);

				var doc = JSON.parse(this.responseText);

				var object = doc.guestbooks;
				var array = object.guestbook;

				totalCount = object.totalCount;
				endPage = Math.ceil(totalCount / 10);

				var tmp = document.getElementById("btnMore").value;

				//동적 <tr><td> 만들기
				var txt = "";

				for (var a = 0; a < array.length; ++a) {
					var guestbook = array[a];
					var gid = guestbook.gid;
					var name_ = guestbook.name_;
					var content = guestbook.content;
					var regDate = guestbook.regDate;
					txt += "<tr><td>" + gid + "</td><td>" + name_ + "</td><td>"
							+ content + "</td><td>" + regDate + "</td></tr>"
				}

				// 누적된 문자열을 화면에 누척 출력
				// 주의) = 연산자는 덮어쓰기 액션 진행
				// 주의) += 연산자는 누적 출력 액션 진행
				document.querySelector("#demo table tbody").innerHTML += txt;

				++tmp;
				document.getElementById("btnMore").value = tmp;

				if (tmp > endPage) {
					document.getElementById("btnMore").setAttribute("disabled",
							"disabled");
				}
				console.log("Ajax 요청 끝");

			}
		};
		//요청 주소 및 요청 방식(GET or POST, 비동기 or 동기) 지정
		xhttp.open("GET", "ajax011_response.jsp?pageStart=" + pageStart, true);

		//서버 요청 시작
		xhttp.send();
		console.log("Ajax 요청 시작");
	}
</script>

<script>
	$(document).ready(function() {

	});
</script>

</head>
<body>

	<div class="container">

		<h1>Ajax Test</h1>
		<button onclick="loadDoc('1')">Ajax 요청</button>
		<div id="demo">

			<table class="table">
				<thead>
					<tr>
						<th>gid</th>
						<th>name</th>
						<th class="col-md-8">content</th>
						<th>regDate</th>
					</tr>
				</thead>
				<tbody>
					<!-- Ajax 응답 결과를 출력할 부분 -->

				</tbody>
			</table>

			<button onclick="loadDoc(this.value)" id="btnMore" value="2"
				style="width: 100%">더보기</button>
		</div>

	</div>

</body>
</html>