package com.test012;

import java.io.*;

import org.json.*;

public class Main {

	public static void main(String[] args) {

		try {
			// 문제)
			JSONTokener tokener = new JSONTokener(new FileReader("vehicles.json"));

			JSONObject jobj = new JSONObject(tokener);

			JSONObject vehicleObject = jobj.getJSONObject("VEHICLES");

			JSONArray vehicleArray = vehicleObject.getJSONArray("VEHICLE");

			for (int i = 0; i < vehicleArray.length(); ++i) {

				JSONObject vehicles = vehicleArray.getJSONObject(i);

				String inventory_number = vehicles.getString("INVENTORY_NUMBER");
				String make = vehicles.getString("MAKE");
				String model = vehicles.getString("MODEL");
				String year = vehicles.getString("YEAR");
				String picture = vehicles.getString("PICTURE");

				JSONObject optionObject = vehicles.getJSONObject("OPTIONS");

				// String temp = phone.getString(i);
				System.out.println("-------------------------------");
				System.out.printf("%s / %s / %s / %s / %s%n", inventory_number, make, model, year, picture);
				System.out.println("OPTION---------------------");

				JSONArray key = optionObject.names();

				for (int j = 0; j < key.length(); ++j) {
					System.out.printf("%s: %s%n", key.get(j), key.getString(j));
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
