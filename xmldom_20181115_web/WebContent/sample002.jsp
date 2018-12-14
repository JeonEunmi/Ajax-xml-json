<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
 
	//[문제]기상청 육상 중기예보 XML 분석 및 결과 출력. Web버전
	String stnId = request.getParameter("stnId");
	if (stnId == null) {
		stnId = "108";
	}
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document doc = null;

	String str = String.format("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=" + stnId);
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

	String title_ = xpath.compile("/rss/channel/item/title").evaluate(doc);
	sb.append("<strong>");
	sb.append(title_);
	sb.append("</strong>");
	sb.append("<br>");
	String wf = xpath.compile("/rss/channel/item/description/header/wf").evaluate(doc);
	sb.append(wf);

	NodeList locationNodeList = (NodeList) xpath.compile("/rss/channel/item/description/body/location")
	.evaluate(doc, XPathConstants.NODESET);
	
    for(int a=1; a<=locationNodeList.getLength(); a++) {
    	Node locationNode = (Node) xpath.compile(String.format("/rss/channel/item/description/body/location[%s]", a)).evaluate(doc, XPathConstants.NODE);
	    String city = xpath.compile("city").evaluate(locationNode);
		sb.append(String.format("<h3>%s</h3>", city));
		
		NodeList dataNodeList = (NodeList)xpath.compile("data").evaluate(locationNode, XPathConstants.NODESET);
		sb.append("<table class=\"table\"><tbody>");
		sb.append("<tr><th>날짜</th><th>날씨</th><th>최저/최고 기온</th><th>신뢰도</th></tr>");
		for (int b=1; b<=dataNodeList.getLength(); b++) {
			Node dataNode = (Node) xpath.compile(String.format("data[%s]", b)).evaluate(locationNode, XPathConstants.NODE);
			String tmEf = xpath.compile("tmEf").evaluate(dataNode);
			String wf_ = xpath.compile("wf").evaluate(dataNode);
			String tmn = xpath.compile("tmn").evaluate(dataNode);
			String tmx = xpath.compile("tmx").evaluate(dataNode);
			String reliability = xpath.compile("reliability").evaluate(dataNode);
			sb.append(String.format("<tr><td>%s</td>", tmEf));
			sb.append("<td class=\"wf\">");
			String img = "";
			switch(wf_){
			case "흐림": img = "W_DB04.png"; break;
			case "비": img = "W_DB05.png"; break;
			case "비,눈": img = "W_DB06.png"; break;
			case "눈": img = "W_DB08.png"; break;
			case "구름조금": img = "W_NB02.png"; break;
			case "구름많음": img = "W_NB03.png"; break;
			case "흐리고 비": img = "W_NB08.png"; break;
			case "구름많고 비": img = "W_NB20.png"; break;
			}
			sb.append(String.format("<img src=\"" + path + "/resources/img/%s\">", img));
			sb.append(String.format("%s</td>", wf_));
			sb.append(String.format("<td>%s~%s</td>", tmn, tmx));
			sb.append(String.format("<td>%s</td></tr>", reliability));
		}
		sb.append("</tbody></table>");

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

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		
		$(":radio[value='<%=stnId%>']").attr("checked", "checked");

	});
</script>

</head>
<body>

	<div class="container">
		<h1>기상청 육상 중기예보 <small>v1.0 by XML</small></h1>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">지역 선택</div>
				<div class="panel-body">
					<form role="form" method="post">
						<input type="radio" name="stnId" value="108" checked="checked">
						전국 <input type="radio" name="stnId" value="109"> 서울,경기 <input
							type="radio" name="stnId" value="105"> 강원 <input
							type="radio" name="stnId" value="131"> 충청북도 <input
							type="radio" name="stnId" value="133"> 충청남도 <input
							type="radio" name="stnId" value="146"> 전라북도 <input
							type="radio" name="stnId" value="156"> 전라남도 <input
							type="radio" name="stnId" value="143"> 경상북도 <input
							type="radio" name="stnId" value="159"> 경상남도 <input
							type="radio" name="stnId" value="184"> 제주특별자치도

						<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">기상 정보 출력</div>
				<div class="panel-body">
					<%-- 
					<p>
						<b>서울,경기도 육상 중기예보 - 2016년 05월 13일 (금)요일 06:00 발표</b>
					</p>

					<p>
						기압골의 영향으로 15~16일은 비가 오겠고, 그 밖의 날은 고기압의 영향으로 대체로 맑은 날이 많겠습니다.<br />기온은
						평년(최저기온 : 10~13도, 최고기온 : 21~25도)과 비슷하거나 조금 높겠습니다.<br />강수량은
						평년(2~5mm)보다 많겠습니다.<br />서해중부해상의 물결은 15일은 1.0~2.5m로 높게 일겠고, 그 밖의
						날은 0.5~2.0m로 일겠습니다.<br>
					</p>

					<h3>서울</h3>
					<table class="table">
						<thead>
							<tr>
								<th>날짜</th>
								<th>날씨</th>
								<th>최저/최고 기온</th>
								<th>신뢰도</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>2016-05-16 00:00</td>
								<td><img src="<%=path%>/resources/img/W_NB08.png"> 흐리고
									비</td>
								<td>12~20</td>
								<td>높음</td>
							</tr>
							<tr>
								<td>2016-05-16 12:00</td>
								<td><img src="<%=path%>/resources/img/W_NB02.png"> 구름
									많음</td>
								<td>12~20</td>
								<td>높음</td>
							</tr>
						</tbody>
					</table>


					<h3>수원</h3>
					<table class="table">
						<thead>
							<tr>
								<th>날짜</th>
								<th>날씨</th>
								<th>최저/최고 기온</th>
								<th>신뢰도</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>2016-05-16 00:00</td>
								<td><img src="<%=path%>/resources/img/W_NB08.png"> 흐리고
									비</td>
								<td>12~20</td>
								<td>높음</td>
							</tr>
							<tr>
								<td>2016-05-16 12:00</td>
								<td><img src="<%=path%>/resources/img/W_NB02.png"> 구름
									많음</td>
								<td>12~20</td>
								<td>높음</td>
							</tr>
						</tbody>
					</table>
 					--%>
 					<%=sb.toString()%>
				</div>
			</div>
		</div>

	</div>

</body>
</html>