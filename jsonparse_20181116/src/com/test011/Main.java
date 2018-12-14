package com.test011;

import java.io.*;

import org.json.*;

public class Main {

	public static void main(String[] args) {

		try {
			// JSON 포맷 문자열을 가진 물리적 파일(.json) 준비
			JSONTokener tokener = new JSONTokener(new FileReader("members.json"));

			// 문자열에 대한 JSON 문법 분석 및 Object 등록
			JSONObject jobj = new JSONObject(tokener);
			
			
			JSONArray membersArray = jobj.getJSONArray("members");
			
			for(int i=0; i<membersArray.length(); ++i) {
				JSONObject member = membersArray.getJSONObject(i);
				String name = member.getString("name");
				String phone = member.getString("phone");
				//String temp = phone.getString(i);
				System.out.println("name:" + name + " / phone:" + phone);
			}

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
