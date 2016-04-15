package com.imaginum.cloudsight.cloudsightclient;

public class CloudSightClientException extends Exception {
	private static final long serialVersionUID = -5614115293989689038L;

	public CloudSightClientException() {}
	 
	 public CloudSightClientException(String errorMessage) {
		 super(errorMessage);
	 }
}
