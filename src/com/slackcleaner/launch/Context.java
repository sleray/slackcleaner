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
	private  String apiList ;
	private  String baseUrl ;
	
	public Context() {
		ResourceBundle bundle = ResourceBundle.getBundle("com.slackcleaner.config.config");
		this.token = bundle.getString("slack.api.token");
		this.channel = bundle.getString("slack.api.channeltoclean");
		this.baseUrl = bundle.getString("slack.api.baseurl");
		this.apiDelete = bundle.getString("slack.api.chat.delete");
		this.apiList = bundle.getString("slack.api.channels.history");
		
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getApiDelete() {
		return apiDelete;
	}
	public void setApiDelete(String apiDelete) {
		this.apiDelete = apiDelete;
	}
	public String getApiList() {
		return apiList;
	}
	public void setApiList(String apiList) {
		this.apiList = apiList;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
