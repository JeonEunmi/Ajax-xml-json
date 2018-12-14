<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import ="org.json.*"%>
<%
	//절대경로 확인
	String path = request.getContextPath();

	//동적으로 JSON 포맷 문자열 생성 과정 추가
	/*
	{
	"members": [
		{
			"name": "HONG",
			"phone": "010-1234-1234"
		},
		{
			"name": "PARK",
			"phone": "010-1111-1111"
		}
	]
	}
	
	*/
	
	JSONObject jsonObject01 = new JSONObject();
	jsonObject01.put("name_", "HONG");
	jsonObject01.put("phone", "010-1234-1234");
	
	JSONObject jsonObject02 = new JSONObject();
	jsonObject02.put("name_", "PARK");
	jsonObject02.put("phone", "010-1111-1111");
	
	JSONArray jsonArray = new JSONArray();
	jsonArray.put(jsonObject01);
	jsonArray.put(jsonObject02);
	
	
	JSONObject jsonObject03 = new JSONObject();
	jsonObject03.put("members",jsonArray);
	
	System.out.println(jsonObject03.toString());
	
	//동적 생성된 JSON 문자열을 client에게 return
	out.write(jsonObject03.toString());
	
%>