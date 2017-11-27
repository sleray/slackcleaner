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
	/**
	 * Loop until there is no more messages returned by slack channels.list method
	 * recursively calls itself
	 * 
	 * @param countMessageDeleted
	 *            to keep track of how many messages are deleted
	 * @throws Exception
	 */

	public void processClean(Integer countMessageDeleted, Context ctx) throws Exception {


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
			/*
			 * Slack api does not accept more than 1 request per second except a small
			 * burst. To allow us to clean a full channel of thousand message, we'll just
			 * let the batch run and be patient :)
			 */
			Thread.sleep(1001);

			getHTML(baseUrl + apiDelete + "?token=" + token + "&channel=" + channel + "&ts=" + message);
		}
		countMessageDeleted += jsonMessages.size();
		System.out.println("Ok ! (" + countMessageDeleted + " messages deleted yet)");
		boolean hasMore = jsonObject.get("has_more").getAsBoolean();
		if (hasMore) {

			processClean(countMessageDeleted,ctx); 

		} else {
			System.out.println("Job's done !");
		}

	}

	/**
	 * parse the response as a JsonArray to get the timestamp of each message in the
	 * list. this timestamp is the unique key used for delete method in slack
	 * 
	 * @param array
	 *            JSonArray from channels.list
	 * @return list of timestamps as String
	 * @throws Exception
	 */
	private static List<String> listMessages(JsonArray array) throws Exception {
		List<String> messages = new ArrayList<>();
		System.out.print(getTime() + " : Fetching " + array.size() + " messages to delete...");
		for (int i = 0; i < array.size(); i++) {
			JsonObject jsonobject = array.get(i).getAsJsonObject();
			String messageTimeStamp = jsonobject.get("ts").getAsString();
			messages.add(messageTimeStamp);
		}

		return messages;
	}

	/**
	 * @return today's date nicely formatted for logging
	 */
	private static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return sdf.format(new Date());

	}

	/**
	 * This method basically just call a url and get the response as a string
	 * 
	 * @param urlToRead
	 *            build the url from the baseUrl, the api familly and method and
	 *            parameters.
	 * @see slack doc for more info
	 * @return response as String
	 * @throws Exception
	 *             sometimes things go wrong
	 */
	private static String getHTML(String urlToRead) throws Exception {
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
