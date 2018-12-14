<%@ page language="java" contentType="text/xml; charset=UTF-8"  
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="javax.xml.parsers.*" %>   
<%@ page import="javax.xml.transform.*" %>   
<%@ page import="javax.xml.transform.dom.*" %>   
<%@ page import="javax.xml.transform.stream.*" %>   
<%@ page import="org.w3c.dom.*" %> 
<%
	//절대경로 확인
	String path = request.getContextPath();

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.newDocument();

	Element rootElement = doc.createElement("members");
	doc.appendChild(rootElement);
	
	Element member1 = doc.createElement("member");
    rootElement.appendChild(member1);
    
    Attr attr = doc.createAttribute("mid");
    attr.setValue("M01");
    member1.setAttributeNode(attr);
    
    Element name1 = doc.createElement("name_");
    name1.appendChild(doc.createTextNode("HONG"));
	member1.appendChild(name1);
    
    Element phone1 = doc.createElement("phone");
    phone1.appendChild(doc.createTextNode("010-1234-1234"));
	member1.appendChild(phone1);
	
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	
	StreamResult result = new StreamResult(out);
	transformer.transform(source, result);

%>