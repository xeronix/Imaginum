package com.imaginum.cloudsight.cloudsightclientimpl;

import java.io.File;

import com.imaginum.cloudsight.cloudsightclient.CloudSightClient;
import com.imaginum.cloudsight.cloudsightclient.CloudSightClientException;
import com.imaginum.cloudsight.cloudsightclient.CloudSightClientWrapper;
import com.imaginum.cloudsight.cloudsightclient.CloudSightGetResult;
import com.imaginum.cloudsight.cloudsightclient.CloudSightImageRequest;
import com.imaginum.cloudsight.cloudsightclient.CloudSightImageRequestParams;
import com.imaginum.cloudsight.cloudsightclient.CloudSightImageRequestParams.CloudSightImageRequestParamBuilder;
import com.imaginum.cloudsight.cloudsightclient.CloudSightPostResult;
import com.imaginum.cloudsight.cloudsightclient.CloudSightResponseSkippedReason;
import com.imaginum.cloudsight.cloudsightclient.CloudSightResponseStatus;

public class CloudSightClientWrapperImpl implements CloudSightClientWrapper {

	public String getImageDescription(String authKey, String imageUrl) throws CloudSightClientException {
		CloudSightImageRequestParamBuilder builder = CloudSightImageRequestParams.builder();
		CloudSightImageRequestParams params = builder.setRemoteImageUrl(imageUrl).build();
		CloudSightImageRequest request = new CloudSightImageRequest(params);

		CloudSightClient client = new CloudSightClientImpl(authKey);
		CloudSightPostResult postResult = client.post(request);
		return executeGetRequest(client, postResult.getToken());
	}

	private String executeGetRequest(CloudSightClient client, String token) throws CloudSightClientException {
		CloudSightGetResult getResult = client.get(token);
		return analyzeGetRequest(client, getResult);
	}

	private String analyzeGetRequest(CloudSightClient client, CloudSightGetResult getResult)
			throws CloudSightClientException {
		String responseStatus = getResult.getStatus();
		String token = getResult.getToken();

		switch (CloudSightResponseStatus.getCloudSightResponseStatus(responseStatus)) {
		case NOT_COMPLETED:
			// API recommends polling for a response every 1 second after a 4
			// second delay from the initial request
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new CloudSightClientException(e.getMessage());
			}
			
			return executeGetRequest(client, token);

		case COMPLETED:
			break;

		case NOT_FOUND:
			throw new CloudSightClientException("Token supplied on URL does not match an image.");

		case SKIPPED:
			analyzeSkipReason(getResult);
			break;

		case TIMEOUT:
			CloudSightGetResult getResultOnRePost = client.getOnPostTimeout(token);
			analyzeGetRequest(client, getResultOnRePost);
			break;

		default:
			throw new CloudSightClientException("Unknown response status : " + responseStatus);
		}

		return getResult.getName();
	}

	private void analyzeSkipReason(CloudSightGetResult getResult) throws CloudSightClientException {
		String reason = getResult.getReason();

		switch (CloudSightResponseSkippedReason.getCloudSightResponseStatus(reason)) {
		case OFFENSIVE:
			throw new CloudSightClientException("Offensive image content.");

		case BLURRY:
			throw new CloudSightClientException("Image too blurry to identify.");

		case CLOSE:
			throw new CloudSightClientException("Image too close to identify.");

		case DARK:
			throw new CloudSightClientException("Image too dark to identify.");

		case BRIGHT:
			throw new CloudSightClientException("Image too bright to identify.");

		case UNSURE:
			throw new CloudSightClientException("Image content could not be identified.");

		default:
			throw new CloudSightClientException("Unknown reason : " + reason);
		}
	}

	public String getImageDescription(String authKey, File imageFile) throws CloudSightClientException {
		CloudSightImageRequestParamBuilder builder = CloudSightImageRequestParams.builder();
		CloudSightImageRequestParams params = builder.setImageFile(imageFile).build();
		CloudSightImageRequest request = new CloudSightImageRequest(params);

		CloudSightClient client = new CloudSightClientImpl(authKey);
		CloudSightPostResult postResult = client.post(request);
		return executeGetRequest(client, postResult.getToken());
	}
}
