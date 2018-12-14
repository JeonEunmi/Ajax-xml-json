<%@ page language="java" contentType="text/html; charset=UTF-8"  
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	//절대경로 확인
	String path = request.getContextPath();
	//서버 콘솔창에 메시지 출력
	System.out.println("Ajax 요청 수신");

	// out - JSP 내장 객체 중에서 출력 담당
	// Ajax 응답 결과
	out.write("Hello, Ajax World!");
%>