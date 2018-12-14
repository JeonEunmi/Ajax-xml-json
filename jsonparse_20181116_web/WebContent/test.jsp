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
	
	System.out.println(key);
	System.out.println(value);
	
	String str = String.format("http://book.interpark.com/api/search.api?key=CB55C010CDD63D184ABD6B61AB5E0FF6FC28C70BDDEFF40645681BB6393F61E8&query=%s&queryType=%s&maxResults=10&inputEncoding=utf-8&output=json",
			URLEncoder.encode(value, "UTF-8"), key);

	InputStream is = new URL(str).openStream();
	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	StringBuilder sb01 = new StringBuilder();

	int cp;
	while ((cp = rd.read()) != -1) {
		sb01.append((char) cp);
	}
	
	JSONObject json = new JSONObject(sb01.toString());
	
	StringBuilder sb = new StringBuilder();

	Object totalResults = json.get("totalResults");
	System.out.println(json.get("totalResults"));
	System.out.println(json.toString());
	JSONArray itemArray = json.getJSONArray("item");


	sb.append("<table class=\"table\"><tbody>");
	
	for (int a = 0; a < itemArray.length(); a++) {

		JSONObject item = itemArray.getJSONObject(a);
		
		String coverLargeUrl = item.getString("coverLargeUrl");
		String title = item.getString("title");
		String author = item.getString("author");
		String publisher = item.getString("publisher");
		//String priceStandard = item.getString("priceStandard");
		String description = item.getString("description");
		String pubDate = item.getString("pubDate");
		String isbn = item.getString("isbn");
		String link = item.getString("link");

		sb.append(String.format("<div class='row result'>"));
		sb.append(String.format("<div class='col-md-1'><span>%s</span></div>", a));
		sb.append(String.format("<div class='col-md-3'><img src='%s'></div>", coverLargeUrl));
		sb.append(String.format("<div class='col-md-6'><ul>"));
		sb.append(String.format("<li><strong>title</strong> : %s</li>", title));
		sb.append(String.format("<li><strong>description</strong> : %s</li>", description));
		sb.append(String.format("<li><strong>publisher</strong> : %s</li>", publisher));
		sb.append(String.format("<li><strong>author</strong> : %s</li>", author));
		//sb.append(String.format("<li><strong>priceStandard</strong> : %s</li>", priceStandard));
		sb.append(String.format("<li><strong>isbn</strong> : %s</li>", isbn));
		sb.append(String.format("<li><strong>pubDate</strong> : %s</li>", pubDate));
		sb.append(String.format("</ul></div>"));
		sb.append(String.format(
				"<div><a href=\"%s\" class=\"btn btn-default btn-xs\" role=\"button\">인터파크 상세보기</a></div>",
				link));
		sb.append(String.format("</div>"));

	}

	sb.append("</tbody></table>");
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
    
   $("#key option[value='<%=key%>']").attr("selected", "selected");
   $("#value").val('<%=value%>
	');
	});
</script>

</head>
<body>


	<div class="container">

		<div class="panel page-header" style="text-align: center;">
			<h1 style="font-size: xx-large;">

				<!-- a href -> 서버에게 특정주소 연결하는 역할 -->
				<a href="sample004_.jsp"> <img src="resources/img/sist_logo.png"
					alt="sist_logo.png"></a>인터파크 도서 검색<small> v2.0</small>
			</h1>
		</div>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">도서 검색</div>
				<div class="panel-body">
					<form role="form" class="form-inline" method="POST">
						<select class="form-control" id="key" name="key">
							<option value="title">TITLE</option>
							<option value="isbn">ISBN</option>
						</select> <input type="text" class="form-control" id="value" name="value">
						<button type="submit" class="btn btn-default">
							<i class="glyphicon glyphicon-search"></i>Search
						</button>
					</form>
				</div>
			</div>
		</div>


		<div class="panel panel-default" id="output">
			<div class="panel-heading">도서 검색 결과</div>
			<div class="panel-body">
				<%--        <button type="button" class="btn btn-default">
                    TotalCount <span class="badge" id="totalcount"><%=totalResults%></span>
                </button> --%>

				<button type="button" class="btn btn-default">
					TotalCount <span class="badge" id="count"> <%-- <%=totalCount%> --%>
					</span>
				</button>

				<button type="button" class="btn btn-default">
					Count <span class="badge" id="count"> <%-- <%=count%> --%>
					</span>
				</button>
				<%=sb.toString()%>

			</div>
		</div>
	</div>

</body>
</html>