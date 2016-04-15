package com.imaginum.cloudsight.cloudsightclient;

public interface CloudSightClient {
	/**
	 * 
	 * @param imageRequest
	 * @return
	 * @throws CloudSightClientException
	 */
	public CloudSightPostResult post(CloudSightImageRequest imageRequest) throws CloudSightClientException;
	
	/**
	 * Use this method when initial post request times out.
	 * 
	 * @param token, token received from previous POST request.
	 * @return
	 * @throws CloudSightClientException
	 */
	public CloudSightGetResult getOnPostTimeout(String token) throws CloudSightClientException;
	
	/**
	 * 
	 * @param token, token received from previous POST request
	 * @return
	 * @throws CloudSightClientException
	 */
	public CloudSightGetResult get(String token) throws CloudSightClientException;
	
	/**
	 * 
	 * @param result, result of previous POST request
	 * @return
	 * @throws CloudSightClientException
	 */
	public CloudSightGetResult get(CloudSightPostResult result) throws CloudSightClientException;
}
