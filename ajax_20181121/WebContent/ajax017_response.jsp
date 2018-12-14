<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.*"%>
<%@ page import="com.guestbook.*"%>
<%@ page import="java.util.*"%>
<%
	//절대경로 확인
	String path = request.getContextPath();

	//동적으로 JSON 포맷 문자열 생성 과정 추가

	//출력 및 검색 메소드 호출 -> 컬렉션 반환
	String pageStart_ = request.getParameter("pageStart");
	String pageCount_ = "10";

	int pageCount = Integer.parseInt(pageCount_);
	int pageStart = pageCount * (Integer.parseInt(pageStart_) - 1);

	GuestbookDAO dao = new GuestbookDAO();
	List<Guestbook> list = dao.pageList(pageStart, pageCount);

	JSONArray jsonArray = new JSONArray();

	int totalCount = dao.notBlindCount();

	for (Guestbook g : list) {
		JSONObject jsonObject01 = new JSONObject();

		jsonObject01.put("gid", g.getGid());
		jsonObject01.put("name_", g.getName_());
		jsonObject01.put("content", g.getContent());
		jsonObject01.put("regDate", g.getRegDate());

		jsonArray.put(jsonObject01);
	}

	JSONObject jsonObject02 = new JSONObject();
	jsonObject02.put("guestbook", jsonArray);
	jsonObject02.put("totalCount", totalCount);

	JSONObject jsonObject03 = new JSONObject();
	jsonObject03.put("guestbooks", jsonObject02);

	//동적 생성된 JSON 문자열을 client에게 return
	out.write(jsonObject03.toString());
%>