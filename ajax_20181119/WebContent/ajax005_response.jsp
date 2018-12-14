<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="javax.xml.parsers.*"%>
<%@ page import="javax.xml.transform.*"%>
<%@ page import="javax.xml.transform.dom.*"%>
<%@ page import="javax.xml.transform.stream.*"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="com.guestbook.*"%>
<%@ page import="java.util.*"%>
<%
	//절대경로 확인
	String path = request.getContextPath();

	/*
	<?xml version="1.0" encoding="UTF-8" standalone="no"?>
	
	<guestbooks>
	<guestbook gid="G001">
		<name_>관리자</name_>
		<content><![CDATA[ Java & Python 과정 진행중입니다. ]]></content>
		<regDate>2018-11-13</regDate>
	</guestbook>
	<guestbook gid="G002">
		<name_>홍길동</name_>
		<content><![CDATA[ 방명록 테스트 ]]></content>
		<regDate>2018-11-13</regDate>
	</guestbook>
	<guestbook gid="G003">
		<name_>전은미</name_>
		<content>test합니다</content>
		<regDate>2018-11-14</regDate>
	</guestbook>
	</guestbooks>
	*/

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.newDocument();

	GuestbookDAO dao = new GuestbookDAO();
	List<Guestbook> list = dao.list();

	Element rootElement = doc.createElement("guestbooks");
	doc.appendChild(rootElement);

	for (Guestbook g : list) {

		// 자식 엘리먼트 동적 생성
		Element guestbook = doc.createElement("guestbook");
		rootElement.appendChild(guestbook);
		
		// 속성 추가
		Attr attr = doc.createAttribute("gid");
		attr.setValue(g.getGid());
		guestbook.setAttributeNode(attr);

		// 엘리먼트 추가
		Element name1 = doc.createElement("name_");
		name1.appendChild(doc.createTextNode(g.getName_()));
		guestbook.appendChild(name1);

		Element content1 = doc.createElement("content");
		content1.appendChild(doc.createTextNode(g.getContent()));
		guestbook.appendChild(content1);

		Element regDate1 = doc.createElement("regDate");
		regDate1.appendChild(doc.createTextNode(g.getRegDate()));
		guestbook.appendChild(regDate1);

	}

	// out - JSP 내장 객체 중에서 출력 담당 
	// Ajax 응답 결과
	// 주의) 브라우저가 xml 포맷을 인식하려면 콘텐츠 타입을 xml로 설정 필요
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(out);
	transformer.transform(source, result);

%>