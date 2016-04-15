package com.imaginum.cloudsight.cloudsightclient;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * JSON response for CloudSight POST request
 * @author vmehta
 *
 */
public class CloudSightPostResult extends GenericJson {
	@Key
	private String token;

	@Key
	private String url;

	@Key
	private String status;
	
	public String getToken() {
		return token;
	}

	public String getUrl() {
		return url;
	}
	
	public String getStatus() {
		return status;
	}
}
