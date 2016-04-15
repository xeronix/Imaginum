package com.imaginum.cloudsight.cloudsightclient;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * JSON response for CloudSight GET request
 * @author vmehta
 *
 */
public class CloudSightGetResult extends GenericJson {
	@Key
	private String token;

	@Key
	private String url;

	@Key
	private String status;
	
	@Key
	private String name;
	
	@Key
	// reason is set when status is returned as 'skipped'
	private String reason;
	
	public String getToken() {
		return token;
	}

	public String getUrl() {
		return url;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getName() {
		return name;
	}
	
	public String getReason() {
		return reason;
	}
}
