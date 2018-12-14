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
	//더보기 버튼에 저장할 페이지 번호 설정용 임시 변수
	var page = 1;
	var endPage = 0;
	var totalCount = 0;

	$(document).ready(function() {

		lastPage = loadDoc(page);

		//페이지 게시물을 모두 보여준 경우 버튼에 대한 비활성 처리 필요
		//->총 게시물수를 가지고 가능한 페이지 수 계산
		//->다음 페이지가 가능한 페이지수를 벗어나는 경우 확인
		//->버튼 비활성 처리

		$("#btnMore").on("click", function() {

			++page;
			$(this).val(page);

			//Ajax 요청을 위한 함수 호출
			endPage = loadDoc($(this).val());
			
		});

	});

	var txt = "";
	function loadDoc(pageStart) {

		$("#demo table tbody").load(
				"ajax017_response.jsp",
				{
					pageStart : pageStart
				},
				function(a) {

					var doc = JSON.parse(a);

					var object = doc.guestbooks;
					var array = object.guestbook;
					totalCount = object.totalCount;

					//동적 <tr><td> 태그 생성

					for (var a = 0; a < array.length; ++a) {
						var guestbook = array[a];
						var gid = guestbook.gid;
						var name_ = guestbook.name_;
						var content = guestbook.content;
						var regDate = guestbook.regDate;
						txt += "<tr><td>" + gid + "</td><td>" + name_
								+ "</td><td>" + content + "</td><td>" + regDate
								+ "</td></tr>"
					}

					//결과 문자열을 화면에 누적 출력
					//주의) 덮어쓰기가 아닌 누적출력이 이루어지도록 할 것
					$(this).html(txt);
		
					//비활성 버튼 
					endPage = Math.ceil(totalCount / 10);
					if (page >= endPage) {
						$("#btnMore").attr("disabled", "disabled");
					}


				});
		return endPage;
	}
</script>

</head>
<body>

	<div class="container">

		<h1>Ajax Test</h1>
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

			<button class="btn btn-default btn-block" id="btnMore" value="2">더보기</button>
		</div>

	</div>


</body>
</html>