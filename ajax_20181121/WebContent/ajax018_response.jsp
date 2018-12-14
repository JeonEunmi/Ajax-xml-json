<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.nio.charset.*"%>
<%
	//절대경로 확인
	String path = request.getContextPath();

	request.setCharacterEncoding("UTF-8");
	
	String key = request.getParameter("key");
	String value = request.getParameter("value");
	String pageStart = request.getParameter("pageStart");

	String str = String.format(
			"http://book.interpark.com/api/search.api?key=CB55C010CDD63D184ABD6B61AB5E0FF6FC28C70BDDEFF40645681BB6393F61E8&query=%s&queryType=%s&maxResults=10&start=%s&inputEncoding=utf-8&output=json",
			URLEncoder.encode(value, "UTF-8"), key, pageStart);

	InputStream is = new URL(str).openStream();
	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	StringBuilder sb = new StringBuilder();

	int cp;
	while ((cp = rd.read()) != -1) {
		sb.append((char) cp);
	}

	JSONObject json = new JSONObject(sb.toString());

	out.write(json.toString());
%>