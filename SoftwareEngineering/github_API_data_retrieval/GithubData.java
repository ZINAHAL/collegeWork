import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.Request;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.*;
import com.google.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GithubData {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		List<Integer> nums = new ArrayList<Integer>();
		nums.add(14);
		nums.add(24);
		nums.add(44);
		
		Gson gson = new Gson();
		String str = gson.toJson(nums);
		System.out.println(str);
	}

}
