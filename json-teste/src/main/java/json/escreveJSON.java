package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Crunchify.com
 */

public class escreveJSON {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
	
		
		//
		JSONObject obj_Escrita = new JSONObject();
		obj_Escrita.put("Name", "crunchify.com");
		obj_Escrita.put("Author", "App Shah");
 
		JSONArray company = new JSONArray();
		company.add("Compnay: eBay");
		company.add("Compnay: Paypal");
		company.add("Compnay: Google");
		obj_Escrita.put("Company List", company);
 
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("file1.txt")) {
			file.write(obj_Escrita.toJSONString());
			System.out.println("Successfully Copied JSON obj_Escrita to File...");
			System.out.println("\nJSON obj_Escrita: " + obj_Escrita);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///
		
		JSONParser parser = new JSONParser();

		try {

			Object obj_Leitura = parser.parse(new FileReader("file1.txt"));

			JSONObject jsonObject = (JSONObject) obj_Leitura;

			String name = (String) jsonObject.get("Name");
			String author = (String) jsonObject.get("Author");
			JSONArray companyList = (JSONArray) jsonObject.get("Company List");

			System.out.println("Name: " + name);
			System.out.println("Author: " + author);
			System.out.println("\nCompany List:");
			Iterator<String> iterator = companyList.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}