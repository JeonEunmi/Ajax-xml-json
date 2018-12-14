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

	//인터파크 도서 검색에 대한 JSON 요청 및 분석, 결과 출력
	//http://book.interpark.com/api/search.api?key=개인키&query=검색어&queryType=검색기준&maxResults=10&inputEncoding=utf-8&output=json
	
	String key = request.getParameter("key");
	String value = request.getParameter("value");

	if (key == null || value == null) {
		key = "title";
		value = "jQuery";
	}
	
	String str = String.format(
			"http://book.interpark.com/api/search.api?key=CB55C010CDD63D184ABD6B61AB5E0FF6FC28C70BDDEFF40645681BB6393F61E8&query=%s&queryType=%s&maxResults=10&inputEncoding=utf-8&output=json",
			URLEncoder.encode(value, "UTF-8"), key);
	InputStream is = new URL(str).openStream();
	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	StringBuilder sb = new StringBuilder();

	int cp;
	while ((cp = rd.read()) != -1) {
		sb.append((char) cp);
	}
	JSONObject json = new JSONObject(sb.toString());

	Object totalResults = json.get("totalResults");
	System.out.println(json.get("totalResults"));
	JSONArray itemArray = json.getJSONArray("item");

	StringBuilder sb01 = new StringBuilder();
	sb01.append("<table class=\"table\"><tbody>");
	for (int i = 0; i < itemArray.length(); ++i) {

		JSONObject itemObject = itemArray.getJSONObject(i);
		
		String title = itemObject.getString("title");
		String description = itemObject.getString("description");
		String publisher = itemObject.getString("publisher");
		String author = itemObject.getString("author");
		//String priceStandard = itemObject.getString("priceStandard");
		String isbn = itemObject.getString("isbn");
		String pubDate = itemObject.getString("pubDate");
		String coverSmallUrl = itemObject.getString("coverSmallUrl");
		
		sb01.append("<tr><td>" + (i+1) + "</td>");
		sb01.append("<td><img src=\"" + coverSmallUrl + "\"></td>");
		sb01.append(String.format(
				"<td><ul><li><strong>title</strong>  %s<br></li><li><strong>description</strong>  %s <br></li><li><strong>publisher</strong>  %s<br></li><li><strong>author</strong>  %s <br></li><li><strong>isbn</strong>  %s <br></li><li><strong>pubDate</strong>  %s</li></ul></td>",
				title, description, publisher, author, isbn, pubDate));
		sb01.append(
				"<td><button type=\"button\" class=\"btn btn-default btn-xs btnView\">인터파크 상세보기</button></td></tr>");
	}
	sb01.append("</tbody></table>");
	
	
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
	$(document).ready(function() {

	});
</script>

</head>
<body>

	<div class="container">

		<h1>
			인터파크 도서 검색 <small>v1.0 by XML</small>
		</h1>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">도서 검색</div>
				<div class="panel-body">
					<form role="form" class="form-inline" method="POST">
						<select class="form-control" id="key" name="key">
							<option value="title">책 제목</option>
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
							TotalCount <span class="badge" id="totalcount"><%=totalResults%></span>
						</button>
					</div>
					<%=sb01.toString()%>

				</div>
			</div>
		</div>



	</div>

</body>
</html>