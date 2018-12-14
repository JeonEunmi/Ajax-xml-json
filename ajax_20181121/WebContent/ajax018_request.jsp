<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.nio.charset.*"%>
<%-- json-20180130.jar 라이브러리 필요 --%>
<%@ page import="org.json.*"%>
<%
	//절대경로 확인
	String path = request.getContextPath();

	request.setCharacterEncoding("UTF-8");
	String key = request.getParameter("key");
	String value = request.getParameter("value");
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

<style>
div #count {
	margin: 10px;
}
</style>

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
	
	var count = 1;

	var keyTmp = "<%=key%>";
	var valueTmp = "<%=value%>
	";

	function loadDoc(pageStart) {

		//<select>, <input> 태그에 대한 상태값 설정
		$("#key option[value='" + keyTmp + "']").attr("selected", "selected");
		$("#value").val(valueTmp);

		if (key != "" && value != "") {

			$("#demo table tbody")
					.load(
							"ajax018_response.jsp",
							{
								pageStart : page,
								value : valueTmp,
								key : keyTmp
							},
							function(a) {

								var doc = JSON.parse(a);

								var array = doc.item;
								var totalResults = doc.totalResults;

								//동적 <tr><td> 태그 생성

								for (var a = 0; a < array.length; ++a) {
									var item = array[a];
									var title = item.title;
									var description = item.description;
									var publisher = item.publisher;
									var author = item.author;
									var isbn = item.isbn;
									var pubDate = item.pubDate;
									var coverSmallUrl = item.coverSmallUrl;
									txt += "<tr><td>" + count + "</td>";
									txt += "<td><img src=\"" + coverSmallUrl + "\"></td>";
									txt += "<td><ul><li><strong>title</strong> "
											+ title
											+ " <br></li><li><strong>description</strong> "
											+ description
											+ " <br></li><li><strong>publisher</strong "
											+ publisher
											+ " <br></li><li><strong>author</strong> "
											+ author
											+ " <br></li><li><strong>isbn</strong> "
											+ isbn
											+ " <br></li><li><strong>pubDate</strong> "
											+ pubDate + " </li></ul></td>";
									txt += "<td><button type=\"button\" class=\"btn btn-default btn-xs btnView\">인터파크 상세보기</button></td></tr>";
									++count;
								}

								//결과 문자열을 화면에 누적 출력
								//주의) 덮어쓰기가 아닌 누적출력이 이루어지도록 할 것

								$(this).html(txt);
								$("#totalcount").html(totalResults);

								//비활성 버튼 
								endPage = Math.ceil(totalResults / 10);
								if (page >= endPage) {
									$("#btnMore").attr("disabled", "disabled");
								}

							});
			return endPage;
		} else {
			alert("검색어 지정 필요합니다.");
		}
	}
</script>

</head>
<body>

	<div class="container">
		<a href="<%=path%>/ajax018_response.jsp"><img
			src="<%=path%>/resources/img/sist_logo.png" alt="sist_logo.png"></a>
		<h1>
			인터파크 도서 검색 <small>v1.0 by XML</small>
		</h1>
		<div id="demo">
			<div class="panel-group">

				<div class="panel panel-default">

					<div class="panel-heading">도서 검색</div>
					<div class="panel-body">
						<form role="form" class="form-inline" method="POST">
							<select class="form-control" id="key" name="key">
								<option value="title" selected="selected">책 제목</option>
								<option value="isbn">ISBN</option>
							</select>
							<!-- 검색 단어 입력 폼 -->
							<div class="input-group">
								<input type="text" class="form-control" id="value" name="value"
									placeholder="Search">
							</div>
							<!-- 검색 진행 버튼 -->
							<button type="submit" class="btn btn-default" id="btnSearch">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</form>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">도서 검색 결과</div>
					<div class="panel-body">
						<div id="count">
							<button type="button" class="btn btn-default">
								TotalCount <span class="badge" id="totalcount">0</span>
							</button>
						</div>
						<table class="table">
							<tbody>
								<!-- Ajax 응답 결과를 출력할 부분 -->

							</tbody>
						</table>

						<button type="button" class="btn btn-default btn-block"
							id="btnMore" value="2">더보기</button>

					</div>
				</div>
			</div>
		</div>



	</div>

</body>
</html>
