package jsonTest;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONFileWrite {

	public static void main(String[] args)
	{
		JSONObject objJson = new JSONObject();
		
		obj.put("Name", "crunchify.com");
		obj.put("Author", "App Shah");
		
		JSONArray company = new JSONArray();
		company.add("Company: eBay");
		company.add("Company: PayPal");
		company.add("Company: Google");
		
		objJson.put("Company List", company);
		
		try (FileWriter file = new FileWriter("/Users/<username>/Documents/file1.txt"))
		{
				file.write(obj.toJSONString());
				System.out.println("Successfully copied JSON Object to File...");
				System.out.println("\nJSON Object: " + obj);
			
		}
	}
}
