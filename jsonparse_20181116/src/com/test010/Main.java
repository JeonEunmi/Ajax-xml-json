package com.test010;

//json-20180130.jar 라이브러리 필요
import org.json.*;

public class Main {

	public static void main(String[] args) {

		String jsonStr = "{\"name\" : \"HONG\", \"phone\":[\"010-1234-1234\", \"010-1111-1111\"]}";

		//문자열에 대한 JSON 문법 분석 및 Object
		JSONObject jsonObj = new JSONObject(jsonStr);

		try {

			//key를 이용한 value 탐색
			String name = jsonObj.getString("name");
			JSONArray phone = jsonObj.getJSONArray("phone");

			System.out.println("name:" + name + " / phone:" + phone.get(1));
			
			for(int i=0; i<phone.length(); ++i) {
				Object temp = phone.get(i);
				//String temp = phone.getString(i);
				System.out.println(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

