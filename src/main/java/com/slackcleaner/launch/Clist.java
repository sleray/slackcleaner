package com.slackcleaner.launch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
public class Clist {
	/**
	 * Loop until there is no more messages returned by slack channels.list method
	 * recursively calls itself
	 * 
	 * @param countMessage
	 *            to keep track of how many messages are counted
	 * @throws Exception
	 */

	public void processClist(Integer countMessage, Context ctx, String channelId, String oldest_ts) throws Exception {


		String baseUrl = ctx.getBaseUrl();
		String token = ctx.getToken();
		String apiHistory = ctx.getApiHistory();
		String apiInfo = ctx.getApiInfo();
		String completeListUrl = "";
		if (oldest_ts == null) {
			//first call we get the 1000 most recents messages.
		 completeListUrl = baseUrl + apiHistory + "?token=" + token + "&channel=" + channelId+"&count=1000";
		} else {
			//next calls we get the next 1000 messages
			completeListUrl = baseUrl + apiHistory + "?token=" + token + "&channel=" + channelId+"&count=1000&latest="+oldest_ts;	
		}
		String jsonResponse = getHTML(completeListUrl);
		JsonObject jsonObject = new JsonParser().parse(jsonResponse).getAsJsonObject();
		JsonArray jsonMessages = jsonObject.get("messages").getAsJsonArray();


		
		countMessage += jsonMessages.size();
		//get the timestamp of the oldest message to iterate through the collection with a pagination of 1000
		
		boolean hasMore = jsonObject.get("has_more").getAsBoolean();
		/*
		 * Slack api does not accept more than 1 request per second except a small
		 * burst. To allow us to clean a full channel of thousand message, we'll just
		 * let the batch run and be patient :)
		 */
		Thread.sleep(1001);
		if (hasMore) {
			String actualOldestTs = jsonMessages.get(jsonMessages.size()-1).getAsJsonObject().get("ts").getAsString();
			//For channels above 10K messages, a little animation to wait.
			System.out.print(".");
			processClist(countMessage,ctx,channelId,actualOldestTs); 

		} else {
			//TODO insert basic infos on the channel (title, purpose...)
			completeListUrl = baseUrl + apiInfo + "?token=" + token + "&channel=" + channelId;
			jsonResponse = getHTML(completeListUrl);
			jsonObject = new JsonParser().parse(jsonResponse).getAsJsonObject();
			
			String channelName = jsonObject.get("channel").getAsJsonObject().get("name").getAsString();
			
			System.out.println("Channel Info for '"+channelName+"' ("+channelId+")");
			System.out.println(countMessage+" messages counted");
		}

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
