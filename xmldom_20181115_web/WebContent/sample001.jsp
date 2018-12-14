<%@ page language="java" contentType="text/html; charset=UTF-8"  
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="javax.xml.parsers.*" %>	
<%@ page import="org.w3c.dom.*" %>	
<%@ page import="org.xml.sax.*" %>	
<%@ page import="java.net.*" %>	
<%@ page import="javax.xml.xpath.*" %>	
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
		 
		//한국일보 IT뉴스 기사에 대한 XML 요청 및 분석, 결과 출력
		//http://rss.hankooki.com/daily/dh_it_tech.xml

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = null;

		String str = String.format("http://rss.hankooki.com/daily/dh_it_tech.xml");
		URL url = new URL(str);
		InputSource is = new InputSource(url.openStream());
		doc = builder.parse(is);

		//서버의 콘솔창에 메시지 출력
		System.out.println(doc.toString());

		StringBuilder sb = new StringBuilder();
		Element root = doc.getDocumentElement();
		XPath xpath = XPathFactory.newInstance().newXPath();

		String title_ = xpath.compile("/rss/channel/title").evaluate(doc);
		sb.append(String.format("<h2>오늘의 주요 뉴스(%s)</h2>",title_));
		
		String lastBuildDate = xpath.compile("/rss/channel/lastBuildDate").evaluate(doc);
		sb.append(String.format("LastBuildDate : %s",lastBuildDate));

		NodeList itemNodeList = (NodeList)xpath.compile("/rss/channel/item").evaluate(doc, XPathConstants.NODESET);
		
		for(int i = 1; i < itemNodeList.getLength(); i++) {
			
			Node itemNode  = (Node)xpath.compile(String.format("/rss/channel/item[%s]",i)).evaluate(doc, XPathConstants.NODE);
			
			String title = xpath.compile("title").evaluate(itemNode);
			String link = xpath.compile("link").evaluate(itemNode);
			String description = xpath.compile("description").evaluate(itemNode);
			String pubDate = xpath.compile("pubDate").evaluate(itemNode);
			
			sb.append("<div class = \"news\">");
			sb.append(String.format("<div>%s</div>", link));
			sb.append(String.format("<div>%s</div>", description));
			sb.append(String.format("<div>%s</div>", pubDate));
			sb.append(String.format("<div>%s</div>", title));
			sb.append("</div>");

		}
		
		
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

div.news {
	border-top : solid 1px;
}

div.news div{
	padding : 3px;
	margin : 10px;
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
	
		<h1>XML Parsing Test</h1>
		
		<div id="title">
			<p>한국일보 IT뉴스 기사에 대한 XML 요청 및 분석, 결과 출력</p>
			<p>http://rss.hankooki.com/daily/dh_it_tech.xml</p>
		</div>
		<div id="demo">
			<%=sb.toString()%>
		</div>
	
	</div>

</body>
</html>