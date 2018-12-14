<%@ page language="java" contentType="text/html; charset=UTF-8"  
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="javax.xml.parsers.*" %>	
<%@ page import="org.w3c.dom.*" %>	
<%@ page import="org.xml.sax.*" %>	
<%@ page import="java.net.*" %>	
<%@ page import="javax.xml.xpath.*" %>	
<%@ page import="javax.xml.transform.*" %>	
<%@ page import="javax.xml.transform.dom.*" %>	
<%@ page import="javax.xml.transform.stream.*" %>	
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
	 
	//인터파크 도서 검색에 대한 XML 요청 및 분석, 결과 출력
	//http://book.interpark.com/api/search.api?key=개인키&query=검색어&queryType=검색기준&maxResults=50&inputEncoding=utf-8

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document doc = null;

	String key = "title";
	String value = "jQuery";

	String str = String.format(
			"http://book.interpark.com/api/search.api?key=개인키&query=%s&queryType=%s&maxResults=50&inputEncoding=utf-8",
			URLEncoder.encode(value, "UTF-8"), key);
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
		
	});

</script>

</head>
<body>

	<div class="container">
	
		<h1>XML Parsing Test</h1>
		
		<div id="title">
			<p>인터파크 도서 검색에 대한 XML 요청 및 분석, 결과 출력</p>
			<p>http://book.interpark.com/api/search.api?key=개인키&query=검색어&queryType=검색기준&maxResults=50&inputEncoding=utf-8</p>
		</div>
		<div id="demo">

		</div>
	
	
	</div>

</body>
</html>