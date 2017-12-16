import java.io.*;


import java.net.*;
import java.time.MonthDay;
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
	
	
	private String user_name;
	static MonthDay date = MonthDay.of(11, 2);
	
	static private int n = date.getDayOfMonth();
	static int sd = n;
	
	public static void main(String[] args) throws Exception {
		
		//UsersPushData zinah = new UsersPushData("phadej", 1, MonthDay.of(12, 10), MonthDay.of(12, 14));
		//zinah.getFollowersUsernames("phadej", 1);
		
		UsersPushData x = new UsersPushData("reeze", 2, MonthDay.of(12, 10), MonthDay.of(12, 14));
		x.getFollowersUsernames("reeze", 1);
		int i = 0;
		Iterator it = x.followers_usernames.iterator();
		while(it.hasNext())
		{
			i++;
			System.out.println(it.next());
		}
		System.out.println(i);
		System.out.println(x.followers_usernames.size());
//		URL github_url;
//		HttpURLConnection request;
//		JsonParser json_parser;
//		
//		try
//		{
//			//String url_path = "https://api.github.com/users/phadej/events?page=";
//			int page_number = 1;
//			while(page_number <= 20)
//			{
//				github_url = new URL("https://api.github.com/users/phadej/events?page=" + page_number);
//				request = (HttpURLConnection) github_url.openConnection();
//				request.setRequestMethod("GET");
//			    request.connect();
//			    System.out.println(request.getResponseCode());
//			    if(request.getResponseCode() != 200)
//			    {
//			    	break;
//			    }
//			    
//				json_parser = new JsonParser();
//				JsonElement parsed_github_url = json_parser.parse(new InputStreamReader((InputStream) request.getContent()));
//				JsonArray parsed_url_array = parsed_github_url.getAsJsonArray();
//				
//				for(JsonElement element: parsed_url_array)
//				{
//					JsonObject x = element.getAsJsonObject();
//					if(x.get("type").getAsString().equals("PushEvent")) 
//					{
//						String creation_date = x.get("created_at").getAsString();
//						int day = getDay(creation_date);
//						int month = getMonth(creation_date);
//						if((month >= 12) && (month <= 12) && (day >= 10) && (day <= 15))
//						{
//							System.out.println(x.get("id").getAsString());
//							System.out.println("z");
//						}
//					}
//				}
//				
//				System.out.println(page_number);
//				page_number++;
//			}
//			
//			
//			System.out.println("great it's working for now");
//			//System.out.println(sd);
//			
//		}
//		catch(IOException e) { e.printStackTrace(); }
//		catch(Exception e) { e.printStackTrace(); }
		
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
	
	
	public static int getUserCommitsPerWeek(String user_name)
	{
		return 9;
	}
	

}
