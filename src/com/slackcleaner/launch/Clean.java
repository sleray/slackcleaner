package com.slackcleaner.launch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 */

/**
 * @author sleray
 *
 */
public class Clean {



	public  void processClean(Integer countMessageDeleted) throws Exception {
		// get context
		Context ctx = new Context();
		
		String baseUrl = ctx.getBaseUrl();
		String token = ctx.getToken();
		String apiList = ctx.getApiList();
		String apiDelete = ctx.getApiDelete();
		String channel = ctx.getChannel();
		
		String completeListUrl = baseUrl + apiList + "?token=" + token + "&channel=" + channel;
		String jsonResponse = getHTML(completeListUrl);
		JsonObject jsonObject = new JsonParser().parse(jsonResponse).getAsJsonObject();
		JsonArray jsonMessages = jsonObject.get("messages").getAsJsonArray();
		for (String message : listMessages(jsonMessages)) {
			Thread.sleep(1100);

			getHTML(baseUrl + apiDelete + "?token=" + token + "&channel=" + channel + "&ts=" + message);
		}
		countMessageDeleted += jsonMessages.size();
		System.out.println("Ok ! ("+countMessageDeleted+" messages deleted yet)");
		boolean hasMore = jsonObject.get("has_more").getAsBoolean();
		if (hasMore) {
			processClean(countMessageDeleted); // et ça boucle, et ça boucle :)
		} else {
			System.out.println("Job's done !");
		}

	}

	public static List<String> listMessages(JsonArray array) throws Exception {
		List<String> messages = new ArrayList<>();
		System.out.print(getTime() + " : Fetching " + array.size() + " messages to delete...");
		for (int i = 0; i < array.size(); i++) {
			JsonObject jsonobject = array.get(i).getAsJsonObject();
			String messageTimeStamp = jsonobject.get("ts").getAsString();
			messages.add(messageTimeStamp);
		}

		return messages;
	}

	private static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return sdf.format(new Date());

	}

	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}
}
