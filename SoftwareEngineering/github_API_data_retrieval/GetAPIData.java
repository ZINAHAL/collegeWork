import java.io.*;

import java.net.*;
import java.util.Iterator;
import java.util.Scanner;

import javax.tools.JavaFileObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * 
 */

/**
 * @author zinah
 *
 */
public class GetAPIData {

	
	public static void main(String[] args) {
		
		URL github_url;
		HttpURLConnection request;
		JsonParser json_parser;
		
		try
		{
			
			int page_number = 1;
			while(page_number <= 10)
			{
				github_url = new URL("https://api.github.com/users/phadej/events?page=" + page_number++);
				request = (HttpURLConnection) github_url.openConnection();
			    request.connect();
			    
				json_parser = new JsonParser();
				JsonElement parsed_github_url = json_parser.parse(new InputStreamReader((InputStream) request.getContent()));
				JsonArray parsed_url_array = parsed_github_url.getAsJsonArray();
				
				for(JsonElement x: parsed_url_array)
				{
					JsonObject y = x.getAsJsonObject();
					if(y.get("type").getAsString().equals("PushEvent")) {
						String creation_date = y.get("created_at").getAsString();
						int day = getDay(creation_date);
						int month = getMonth(creation_date);
						if(month == 12 && (day >= 10 && day <= 15))
						{
							System.out.println("success");
						}
					}
				}
				
			}
			
			System.out.println("great it's working for now");
			
		}
		catch(IOException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
		
	}
	
	
	
	public static int getDay(String date)
	{
		String str_num = date.substring(8, 9);
		char x = date.charAt(9);
		if(x >= '0' && x <= '9')
		{
			str_num = str_num + x;
		} 
		
		return Integer.parseInt(str_num);
	}
	
	public static int getMonth(String date)
	{
		String str_num = date.substring(5, 6);
		char x = date.charAt(6);
		if(x >= '0' && x <= '9')
		{
			str_num = str_num + x;
		} 
		
		return Integer.parseInt(str_num);
	}
	

}
