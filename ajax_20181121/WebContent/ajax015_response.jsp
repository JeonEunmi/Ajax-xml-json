<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- json-20180130.jar 라이브러리 필요 --%>
<%@ page import="org.json.*"%>
<%
	//절대경로 확인
	String path = request.getContextPath();

	//동적 JSON 포맷 문자열 생성 과정 추가
	/*
	{
		"members": [
			{
				"name_": "HONG",
				"phone": "010-1234-1234"
			},
			{
				"name_": "PARK",
				"phone": "010-1234-5678"
			}
		]
	}
	*/

	JSONObject jsonObject01 = new JSONObject();
	jsonObject01.put("name_", "HONG");
	jsonObject01.put("phone", "010-1234-1234");
	/*
	{
		"name_": "HONG",
		"phone": "010-1234-1234"
	}
	*/

	JSONObject jsonObject02 = new JSONObject();
	jsonObject02.put("name_", "PARK");
	jsonObject02.put("phone", "010-1234-5678");
	/*
	{
		"name_": "PARK",
		"phone": "010-1234-5678"
	}	
	*/

	JSONArray jsonArray = new JSONArray();
	jsonArray.put(jsonObject01);
	jsonArray.put(jsonObject02);
	/*
	[
		{
			"name_": "HONG",
			"phone": "010-1234-1234"
		},
		{
			"name_": "PARK",
			"phone": "010-1234-5678"
		}
	]	
	*/

	JSONObject jsonObject03 = new JSONObject();
	jsonObject03.put("members", jsonArray);
	/*
	{
		"members": [
			{
				"name_": "HONG",
				"phone": "010-1234-1234"
			},
			{
				"name_": "PARK",
				"phone": "010-1234-5678"
			}
		]
	}	
	*/

	//동적 생성된 JSON 포맷 문자열을 서버 콘솔에 출력
	System.out.println(jsonObject03.toString());

	//out - JSP 내장 객체 중에서 출력 담당
	//Ajax 응답 결과
	out.write(jsonObject03.toString());
%>