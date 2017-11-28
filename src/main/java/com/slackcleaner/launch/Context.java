/**
 * 
 */
package com.slackcleaner.launch;

import java.util.ResourceBundle;

/**
 * @author sleray
 *
 */
public class Context {

	private  String token;
	private  String channel;
	private  String apiDelete;
	private  String apiInfo ;
	private  String apiHistory ;
	private  String baseUrl ;
	private String version;
	



	/**
	 * Context constructor : pick info from the config.properties file.
	 */
	public Context() {
		ResourceBundle bundle = ResourceBundle.getBundle("config");

		this.baseUrl = bundle.getString("slack.api.baseurl");
		this.apiDelete = bundle.getString("slack.api.chat.delete");
		this.apiHistory = bundle.getString("slack.api.channels.history");
		this.apiInfo = bundle.getString("slack.api.channels.info");
		this.version = bundle.getString("version");
		
	}
	

	/**
	 * @return the apiHistory
	 */
	public String getApiHistory() {
		return apiHistory;
	}


	public void setToken(String token) {
		this.token = token;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}


	public String getToken() {
		return token;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public String getApiDelete() {
		return apiDelete;
	}
	
	public String getApiInfo() {
		return apiInfo;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public String getVersion() {
		return version;
	}
	

}
