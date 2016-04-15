package com.imaginum.cloudsight.cloudsightclient;

import java.io.File;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.MultipartContent;

public class CloudSightImageRequest {
	public static final String CONTENT_TYPE = "multipart/form-data";

	private static final String BOUNDARY_KEY = "boundary";
	private static final String BOUNDARY_VALUE = "__END_OF_PART__";
	
	private static final String CONTENT_DISPOSITION = "Content-Disposition";
	
	private static final String FORM_DATA_FORMAT = "form-data; name=\"%s\"";
	private static final String FORM_DATA_FORMAT_FILE = FORM_DATA_FORMAT + "; filename=\"%s\"";
	
	private static final String IMAGE_REQUEST_KEY = "image_request[image]";
	private static final String IMAGE_REQUEST_REMOTE_URL_KEY = "image_request[remote_image_url]";
	private static final String LOCALE_KEY = "image_request[locale]";
	private static final String LANGUAGE_KEY = "image_request[language]";
	private static final String DEVICE_ID_KEY = "image_request[device_id]";
	private static final String LATITUDE_KEY = "image_request[latitude]";
	private static final String LONGITUDE_KEY = "image_request[longitude]";
	private static final String ALTITUDE_KEY = "image_request[altitude]";
	private static final String TTL_KEY = "image_request[ttl]";
	private static final String X_FOCUS_KEY = "focus[X]";
	private static final String Y_FOCUS_KEY = "focus[Y]";
	
	private MultipartContent multiPartImageRequestContent;
	
	public CloudSightImageRequest(CloudSightImageRequestParams params) {
		initMultiPartContent(params);
	}
	
	public MultipartContent getMultiPartImageRequestContent() {
		return multiPartImageRequestContent;
	}
	
	private void initMultiPartContent(CloudSightImageRequestParams params) {
		multiPartImageRequestContent = new MultipartContent().setMediaType(
		        new HttpMediaType(CONTENT_TYPE)
		                .setParameter(BOUNDARY_KEY, BOUNDARY_VALUE));
		
		// Add Image file OR Image Url
		if (params.getImageFile() != null) {
			File imageFile = params.getImageFile();
			FileContent fileContent = new FileContent(null , params.getImageFile());
			MultipartContent.Part part = new MultipartContent.Part(fileContent);
			part.setHeaders(new HttpHeaders().set(
					CONTENT_DISPOSITION, 
			        String.format(FORM_DATA_FORMAT_FILE, IMAGE_REQUEST_KEY, imageFile.getName())));
			multiPartImageRequestContent.addPart(part);
		} else {
			addPartToMultiPartContent(IMAGE_REQUEST_REMOTE_URL_KEY, params.getRemoteImageUrl());
		}
		
		// Add Other image request parameters 
		addPartToMultiPartContent(LOCALE_KEY, params.getLocale());
		addPartToMultiPartContent(LANGUAGE_KEY, params.getLanguage());
		addPartToMultiPartContent(DEVICE_ID_KEY, params.getDeviceId());
		addPartToMultiPartContent(LATITUDE_KEY, params.getLatitude());
		addPartToMultiPartContent(LONGITUDE_KEY, params.getLongitude());
		addPartToMultiPartContent(ALTITUDE_KEY, params.getAltitude());
		addPartToMultiPartContent(TTL_KEY, params.getTtl());
		addPartToMultiPartContent(X_FOCUS_KEY, params.getXFocus());
		addPartToMultiPartContent(Y_FOCUS_KEY, params.getYFocus());
	}
	
	private void addPartToMultiPartContent(String formKey, Object formValue) {
		if (formValue != null) {
			final MultipartContent.Part part = new MultipartContent.Part(
					 ByteArrayContent.fromString(null, formValue.toString()));
			part.setHeaders(new HttpHeaders().set(CONTENT_DISPOSITION, String.format(FORM_DATA_FORMAT, formKey)));
			multiPartImageRequestContent.addPart(part);
		}
	}
}
