package com.test009;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) {

		JSONParser parser = new JSONParser();
		
		String jsonStr = "{\"name\" : \"HONG\", \"phone\":\"010-1234-1234\"}";
		
		try {
			JSONObject jsonObj = (JSONObject)parser.parse(jsonStr);
			
			
			String name = (String)jsonObj.get("name");
			String phone = (String)jsonObj.get("phone");
			
			System.out.println("name:"+name+" / phone:"+phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
