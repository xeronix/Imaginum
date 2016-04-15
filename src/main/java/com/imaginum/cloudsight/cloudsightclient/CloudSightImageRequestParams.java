package com.imaginum.cloudsight.cloudsightclient;

import java.io.File;
import java.security.InvalidParameterException;

public class CloudSightImageRequestParams {
	private static final String DEFAULT_LOCALE = "en-US";
	private static final String DEFAULT_LANGUAGE = "en";
	
	//Image to be attached as a multipart-form-request part
	private File imageFile;
	
	// URL to a remote image to use (either this or imageFile should be set)
	private String remoteImageUrl;
	
	// The locale of the request
	private String locale;
	
	// The language of the request. Return the response in this language
	private String language;
	
	// A unique ID generated for the device sending the request
	private String deviceId;    
	
	// Geolocation-latitude information for additional context
	private double latitude;
	
	// Geolocation-longitude information for additional context
	private double longitude;

	// Geolocation-altitude information for additional context
	private double altitude;
	
	// Deadline in seconds before expired state is set. Use a high ttl for low-priority image requests
	private int ttl;
	
	// Focal point on image (x-coordinate) for specificity
	private double xFocus;
	
	// Focal point on image (x-coordinate) for specificity
	private double yFocus;
	
	private CloudSightImageRequestParams(
			File imageFile,
			String remoteImageUrl,
			String locale,
			String language,
			String deviceId,
			double latitude,
			double longitude,
			double altitude,
			int ttl,
			double xFocus,
			double yFocus) {
		
		this.imageFile = imageFile;
		this.remoteImageUrl = remoteImageUrl;
		this.locale = locale;
		this.language = language;
		this.deviceId = deviceId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.ttl = ttl;
		this.xFocus = xFocus;
		this.yFocus = yFocus;
	}
	
	public File getImageFile() {
		return imageFile;
	}
	
	public String getRemoteImageUrl() {
		return remoteImageUrl;
	}
	
	public String getLocale() {
		return locale;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getAltitude() {
		return altitude;
	}
	
	public int getTtl() {
		return ttl;
	}
	
	public double getXFocus() {
		return xFocus;
	}
	
	public double getYFocus() {
		return yFocus;
	}
	
	public static CloudSightImageRequestParamBuilder builder() {
		return new CloudSightImageRequestParamBuilder();
	}
	
	public static class CloudSightImageRequestParamBuilder {
		private File imageFile;
		
		private String remoteImageUrl;
		private String locale;
		private String language;
		private String deviceId;    
		
		private double latitude;
		private double longitude;
		private double altitude;
		
		private int ttl;
		
		private double xFocus;
		private double yFocus;
		
		private CloudSightImageRequestParamBuilder() {}
		
		public CloudSightImageRequestParams build() {
			if ((remoteImageUrl != null && imageFile != null) ||
				(remoteImageUrl == null && imageFile == null)) {
				throw new InvalidParameterException(
						"[Remote Image URL], [Image File] : Exactly one of these two parameters must be specified.");
			}
			
			if (locale == null) {
				locale = DEFAULT_LOCALE;
			}
			
			if (language == null) {
				language = DEFAULT_LANGUAGE;
			}
			
			return new CloudSightImageRequestParams(
					imageFile,
					remoteImageUrl,
					locale,
					language,
					deviceId,
					latitude,
					longitude,
					altitude,
					ttl,
					xFocus,
					yFocus);
		}
		
		public CloudSightImageRequestParamBuilder setImageFile(File imageFile) {
			this.imageFile = imageFile;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setRemoteImageUrl(String remoteImageUrl) {
			this.remoteImageUrl = remoteImageUrl;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setLocale(String locale) {
			this.locale = locale;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setLanguage(String language) {
			this.language = language;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setLatitude(double latitude) {
			this.latitude = latitude;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setLongitude(double longitude) {
			this.longitude = longitude;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setAltitude(double altitude) {
			this.altitude = altitude;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setTtl(int ttl) {
			this.ttl = ttl;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setXFocus(double xFocus) {
			this.xFocus = xFocus;
			return this;
		}
		
		public CloudSightImageRequestParamBuilder setYFocus(double yFocus) {
			this.yFocus = yFocus;
			return this;
		}
	}
}
