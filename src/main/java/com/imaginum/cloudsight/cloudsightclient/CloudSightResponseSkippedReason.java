package com.imaginum.cloudsight.cloudsightclient;

import java.util.HashMap;
import java.util.Map;

/**
 * If a response has a status of skipped, one of the following reason types will be 
 * populated in the reason field. 
 * @author vmehta
 *
 */
public enum CloudSightResponseSkippedReason {
	// Offensive image content
	OFFENSIVE("offensive"),

	// Too blurry to identify
	BLURRY("blurry"),

	// Too close to identify
	CLOSE("close"),

	// Too dark to identify
	DARK("dark"),

	// Too bright to identify
	BRIGHT("bright"),

	// Content could not be identified
	UNSURE("unsure");

	static final Map<String, CloudSightResponseSkippedReason> cloudSightResponseSkippedReason = new HashMap<String, CloudSightResponseSkippedReason>();

	static {
		for (CloudSightResponseSkippedReason status : CloudSightResponseSkippedReason.values()) {
			cloudSightResponseSkippedReason.put(status.getValue(), status);
		}
	}
	
	private String value;

	private CloudSightResponseSkippedReason(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static CloudSightResponseSkippedReason getCloudSightResponseStatus(String value) {
		return cloudSightResponseSkippedReason.get(value);
	}
}
