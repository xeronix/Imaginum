package com.imaginum.cloudsight.cloudsightclient;

import java.io.File;

/**
 * Wrapper over CloudSightClient for direct usage
 * @author vmehta
 *
 */
public interface CloudSightClientWrapper {
	/**
	 * 
	 * @param authKey CloudSight Auth Key
	 * @param imageUrl
	 * @return
	 */
	public String getImageDescription(String authKey, String imageUrl) throws CloudSightClientException;
	
	/**
	 * 
	 * @param authKey CloudSight Auth Key
	 * @param imageFile
	 * @return
	 */
	public String getImageDescription(String authKey, File imageFile) throws CloudSightClientException;
}
