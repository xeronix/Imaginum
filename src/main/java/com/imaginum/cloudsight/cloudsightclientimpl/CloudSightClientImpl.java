package com.imaginum.cloudsight.cloudsightclientimpl;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.imaginum.cloudsight.cloudsightclient.CloudSightClient;
import com.imaginum.cloudsight.cloudsightclient.CloudSightClientException;
import com.imaginum.cloudsight.cloudsightclient.CloudSightGetResult;
import com.imaginum.cloudsight.cloudsightclient.CloudSightImageRequest;
import com.imaginum.cloudsight.cloudsightclient.CloudSightPostResult;

public class CloudSightClientImpl implements CloudSightClient {
	public static final String CLOUD_SIGHT_BASE_URL = "https://api.cloudsightapi.com";
	private static final String CLOUD_SIGHT_IMAGE_REQUESTS_URL = CLOUD_SIGHT_BASE_URL + "/image_requests";
	private static final String CLOUD_SIGHT_IMAGE_RESPONSES_URL = CLOUD_SIGHT_BASE_URL + "/image_responses";
	private static final String REPOST = "repost";
	
	private static final String CLOUD_SIGHT_AUTH_FORMAT = "CloudSight %s";
	private static final String URL_SEPARATOR = "/";
	  
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	private final String authKey;
	
	private final HttpRequestFactory httpRequestFactory;
	 
	private final GenericUrl cloudSightPostUrl;
	private final GenericUrl cloudSightGetUrl;
	  
	public CloudSightClientImpl(String authKey) {
		this.authKey = authKey;
		httpRequestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			
			public void initialize(HttpRequest request) throws IOException {
				request.setParser(new JsonObjectParser(JSON_FACTORY));
			}
		});
		
		cloudSightPostUrl = new GenericUrl(CLOUD_SIGHT_IMAGE_REQUESTS_URL);
		cloudSightGetUrl = new GenericUrl(CLOUD_SIGHT_IMAGE_RESPONSES_URL);
	}
	
	public CloudSightPostResult post(CloudSightImageRequest imageRequest) throws CloudSightClientException {
		try {
			HttpRequest request = httpRequestFactory.buildPostRequest(cloudSightPostUrl,

					imageRequest.getMultiPartImageRequestContent());

			request.getHeaders().setContentType(CloudSightImageRequest.CONTENT_TYPE)
					.setAuthorization(String.format(CLOUD_SIGHT_AUTH_FORMAT, authKey));
			
			return request.execute().parseAs(CloudSightPostResult.class);
		} catch (IOException e) {
			throw new CloudSightClientException(e.getMessage());
		}
	}
	
	public CloudSightGetResult getOnPostTimeout(String token) throws CloudSightClientException {
		try {
			GenericUrl repostUrl = new GenericUrl(
					cloudSightPostUrl.toString() + URL_SEPARATOR + token + URL_SEPARATOR + REPOST);

			HttpRequest request = httpRequestFactory.buildGetRequest(repostUrl);

			request.getHeaders().setContentType(CloudSightImageRequest.CONTENT_TYPE)
					.setAuthorization(String.format(CLOUD_SIGHT_AUTH_FORMAT, authKey));

			return request.execute().parseAs(CloudSightGetResult.class);
		} catch (IOException e) {
			throw new CloudSightClientException(e.getMessage());
		}
	}
	
	public CloudSightGetResult get(String token) throws CloudSightClientException {
		try {
			HttpRequest request = httpRequestFactory
					.buildGetRequest(new GenericUrl(cloudSightGetUrl.toString() + URL_SEPARATOR + token));
			request.getHeaders().setContentType(CloudSightImageRequest.CONTENT_TYPE)
					.setAuthorization(String.format(CLOUD_SIGHT_AUTH_FORMAT, authKey));

			return request.execute().parseAs(CloudSightGetResult.class);
		} catch (IOException e) {
			throw new CloudSightClientException(e.getMessage());
		}
	}
	
	public CloudSightGetResult get(CloudSightPostResult result) throws CloudSightClientException {
		return get(result.getToken());
	}
}
