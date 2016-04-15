package com.imaginum.cloudsight.cloudsighttest;

import java.io.File;

import org.junit.Test;

import com.imaginum.cloudsight.cloudsightclient.CloudSightClientException;
import com.imaginum.cloudsight.cloudsightclient.CloudSightClientWrapper;
import com.imaginum.cloudsight.cloudsightclientimpl.CloudSightClientWrapperImpl;

import junit.framework.TestCase;

public class CloudSightTest extends TestCase {
	// Set this auth key before running the tests.
	String authKey = "";
	
	/*@Test
	public void testImageUrl() throws CloudSightClientException {
		CloudSightClientWrapper clientWrapper = new CloudSightClientWrapperImpl();
		String description = clientWrapper.getImageDescription(authKey, "http://i.ebayimg.com/images/i/271723310731-0-1/s-l1000.jpg");
		System.out.println(description);
	}*/
	
	@Test
	public void testImageFile() throws CloudSightClientException {
		CloudSightClientWrapper clientWrapper = new CloudSightClientWrapperImpl();
		File imageFile = new File("C:/Users/vmehta/Desktop/HACK/testimage2.jpg");
		String description = clientWrapper.getImageDescription(authKey, imageFile);
		System.out.println(description);
	}
}
