import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class UsersPushData {
	
	private String starting_username;				// the starting point from which the data will be gathered
	private int recursive_depth;					// the number of levels to move down the tree i.e if 0 the no. of push events for the specified user will be returned, 
													// and if 1 then the user+followers, etc.
	private String next_username;
	private MonthDay from_date;
	private MonthDay to_date;
	List<String> seen_usernames;
	List<Integer> result;
	List<String> followers_usernames;
	
	public UsersPushData(String starting_username, int recursive_depth, MonthDay from_date, MonthDay to_date)
	{
		this.starting_username = starting_username;
		this.recursive_depth = recursive_depth;
		this.from_date = from_date;
		this.to_date = to_date;
		this.result = new ArrayList<Integer>();
		this.followers_usernames = new ArrayList<String>();
		
		if(recursive_depth > 1)
		{
			this.seen_usernames = new ArrayList<String>();
			this.seen_usernames.add(starting_username);
		}
	}
	
	
	private int getDay(String date)
	{
		String str_num = date.substring(8, 9);
		char x = date.charAt(9);
		if(x >= '0' && x <= '9')
		{
			str_num = str_num + x;
		} 
		
		return Integer.parseInt(str_num);
	}
	
	
	private int getMonth(String date)
	{
		String str_num = date.substring(5, 6);
		char x = date.charAt(6);
		if(x >= '0' && x <= '9')
		{
			str_num = str_num + x;
		} 
		
		return Integer.parseInt(str_num);
	}
	
	
	private void countPushEvents(String username) throws Exception
	{
		String event_url_path = "https://api.github.com/users/" + username + "/events?page=";
		URL event_URL_obj;
		HttpURLConnection request;
		JsonParser json_parser;
		int push_counter = 0;
		int page_number = 1;
		
		while(true)
		{
			event_URL_obj = new URL(event_url_path + page_number);
			request = (HttpURLConnection) event_URL_obj.openConnection();
			request.setRequestMethod("GET");
		    request.connect();
		    if(request.getResponseCode() != 200)
		    {
		    	break;
		    }
		    
			json_parser = new JsonParser();
			JsonElement parsed_url = json_parser.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonArray parsed_url_array = parsed_url.getAsJsonArray();
			
			for(JsonElement element: parsed_url_array)
			{
				JsonObject x = element.getAsJsonObject();
				if(x.get("type").getAsString().equals("PushEvent")) 
				{
					String creation_date = x.get("created_at").getAsString();
					int day = getDay(creation_date);
					int month = getMonth(creation_date);
					if((month >= from_date.getMonthValue()) && (month <= to_date.getMonthValue()) && (day >= from_date.getDayOfMonth()) && (day <= to_date.getDayOfMonth()))
					{
						push_counter++;
					}
				}
			}
			page_number++;
		}
		
		result.add(push_counter);
	}
	
	
	
	public void getFollowersUsernames(String username, int recursive_no) throws Exception {
		
		if(recursive_no == 0)
		{
			return;
		}
		
		String followers_url_path = "https://api.github.com/users/" + username + "/followers?page=";
		URL followers_URL_obj;
		HttpURLConnection request;
		JsonParser json_parser;
		int page_number = 1;
		
		while(true)
		{
			followers_URL_obj = new URL(followers_url_path + page_number);
			request = (HttpURLConnection) followers_URL_obj.openConnection();
			request.setRequestMethod("GET");
			request.connect();
			if(request.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
				break;
			}
			
			json_parser = new JsonParser();
			JsonElement parsed_url = json_parser.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonArray parsed_url_array = parsed_url.getAsJsonArray();
			
			for(JsonElement element: parsed_url_array)
			{
				JsonObject follower = element.getAsJsonObject();
				String name = follower.get("login").getAsString();
				followers_usernames.add(name);
				getFollowersUsernames(name, recursive_no-1);
			}
			page_number++;
		}
		
	}
	
	
	
	
	
	
}
