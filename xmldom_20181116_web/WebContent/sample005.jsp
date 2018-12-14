<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="javax.xml.parsers.*"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="org.xml.sax.*"%>
<%@ page import="java.net.*"%>
<%@ page import="javax.xml.xpath.*"%>
<%@ page import="javax.xml.transform.*"%>
<%@ page import="javax.xml.transform.dom.*"%>
<%@ page import="javax.xml.transform.stream.*"%>
<%
	//절대경로 확인
	String path = request.getContextPath();

	/*
	 * XML Parsing 
	 * 1. XML document loading -> XML Object 
	 * 2. root element 
	 * 3. child element 
	 * 4. text node 
	 * 5. print
	 */

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document doc = null;

	String key = request.getParameter("key");
	String value = request.getParameter("value");

	if (key == null || value == null) {
		key = "title";
		value = "jQuery";
	}

	String str = String.format(
			"http://book.interpark.com/api/search.api?key=CB55C010CDD63D184ABD6B61AB5E0FF6FC28C70BDDEFF40645681BB6393F61E8&query=%s&queryType=%s&maxResults=50&inputEncoding=utf-8&start=2",
			URLEncoder.encode(value, "UTF-8"), key);

	//queryType=title (책 제목 기준 검색)
	//maxResults=10 (최대 검색 결과 10건)
	//inputEncoding=utf-8
	URL url = new URL(str);
	InputSource is = new InputSource(url.openStream());
	doc = builder.parse(is);

	//XML 형식으로 콘솔 출력
	//주의) Eclipse Workspace에서 한글 인코딩 형식 지정 필요
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult consoleResult = new StreamResult(System.out);
	transformer.transform(source, consoleResult);

	XPath xpath = XPathFactory.newInstance().newXPath();

	StringBuilder sb = new StringBuilder();

	//특정 엘리먼트의 텍스트 접근
	String totalResults = xpath.compile("/channel/totalResults").evaluate(doc);

	NodeList itemNodeList = (NodeList) xpath.compile("/channel/item").evaluate(doc, XPathConstants.NODESET);
	sb.append("<table class=\"table\"><tbody>");

	for (int b = 1; b <= itemNodeList.getLength(); b++) {
		Node itemNode = (Node) xpath.compile(String.format("/channel/item[%d]", b)).evaluate(doc,
				XPathConstants.NODE);

		String title_ = xpath.compile("title").evaluate(itemNode);
		String description = xpath.compile("description").evaluate(itemNode);
		String publisher = xpath.compile("publisher").evaluate(itemNode);
		String author = xpath.compile("author").evaluate(itemNode);
		String priceStandard = xpath.compile("priceStandard").evaluate(itemNode);
		String isbn = xpath.compile("isbn").evaluate(itemNode);
		String pubDate = xpath.compile("pubDate").evaluate(itemNode);
		String coverSmallUrl = xpath.compile("coverSmallUrl").evaluate(itemNode);

		sb.append("<tr><td>" + b + "</td>");
		sb.append("<td><img src=\"" + coverSmallUrl + "\"></td>");
		sb.append(String.format(
				"<td><ul><li><strong>title</strong>  %s<br></li><li><strong>description</strong>  %s <br></li><li><strong>publisher</strong>  %s<br></li><li><strong>author</strong>  %s <br></li><li><strong>priceStandard</strong>  %s <br></li><li><strong>isbn</strong>  %s <br></li><li><strong>pubDate</strong>  %s</li></ul></td>",
				title_, description, publisher, author, priceStandard, isbn, pubDate));
		sb.append(
				"<td><button type=\"button\" class=\"btn btn-default btn-xs btnView\">인터파크 상세보기</button></td></tr>");
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
					<%=sb.toString()%>

				</div>
			</div>
		</div>

	</div>

</body>
</html>