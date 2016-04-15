package com.imaginum.cloudsight.cloudsightclient;

import java.util.HashMap;
import java.util.Map;

public enum CloudSightResponseStatus {
	// Recognition has not yet been completed for this image. Continue polling
	// until response has been marked completed.
	NOT_COMPLETED("not completed"),
	
	// Recognition has been completed. Annotation can be found in name element
	// of the JSON response.
	COMPLETED("completed"),
	
	// Token supplied on URL does not match an image.
	NOT_FOUND("not found"),

	// Image couldn't be recognized because of a specific reason. Check the
	// reason element of the JSON response.
	SKIPPED("skipped"),
	
	// Recognition process exceeded the allowed TTL setting.
	TIMEOUT("timeout");
	
	static final Map<String, CloudSightResponseStatus> cloudSightResponseStatusMap = new HashMap<String, CloudSightResponseStatus>();

	static {
		for (CloudSightResponseStatus status : CloudSightResponseStatus.values()) {
			cloudSightResponseStatusMap.put(status.getValue(), status);
		}
	}
	    
	private String value;
	
	private CloudSightResponseStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static CloudSightResponseStatus getCloudSightResponseStatus(String value) {
		return cloudSightResponseStatusMap.get(value);
	}
}
