/*
 * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package sys2202.aws.s3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * This sample demonstrates how to make basic requests to Amazon S3 using the
 * AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid Amazon Web Services developer
 * account, and be signed up to use Amazon S3. For more information on Amazon
 * S3, see http://aws.amazon.com/s3.
 * <p>
 * Fill in your AWS access credentials in the provided credentials file
 * template, and be sure to move the file to the default location
 * (~/.aws/credentials) where the sample code will load the credentials from.
 * <p>
 * <b>WARNING:</b> To avoid accidental leakage of your credentials, DO NOT keep
 * the credentials file in your source directory.
 *
 * http://aws.amazon.com/security-credentials
 */
public class Sample {

	public static void main(String[] args) throws Exception {
		
		// create the client we'll use to connect to S3
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		
		// list buckets in our S3 account
		System.out.println("Listing buckets in our S3 account...\n");
		for (Bucket bucket : s3.listBuckets()) {
			System.out.println("\t" + bucket.getName());
		}
		
		System.out.println();

		// create a new bucket to experiment with
		String bucketName = "msg8u-sys2202-bucket"; // set the bucket name -- this must be unique, so you'll want to use your ID instead of msg8u
		System.out.println("Creating bucket " + bucketName + "...\n");
		s3.createBucket(bucketName);

		// list buckets in our S3 account
		System.out.println("Listing buckets in our S3 account...\n");
		for (Bucket bucket : s3.listBuckets()) {
			System.out.println("\t" + bucket.getName());
		}

		System.out.println();

		// create and upload a sample file
		System.out.println("Uploading a new object to S3 from a local file...\n");
		File sampleFile = createSampleFile();
		String objectKey = "my-test-file";
		PutObjectRequest putRequest = new PutObjectRequest(bucketName, objectKey, sampleFile);
		s3.putObject(putRequest);

		// list objects in our new bucket -- notice the new object is now present
		System.out.println("Listing objects in our new bucket...\n");
		ListObjectsRequest listRequest = new ListObjectsRequest().withBucketName(bucketName);
		ObjectListing objectListing = s3.listObjects(listRequest);
		for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
			System.out.println("\t" + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");
		}

		System.out.println();

		// download and display the sample file that we just uploaded
		System.out.println("Downloading the sample file...\n");
		GetObjectRequest getRequest = new GetObjectRequest(bucketName, objectKey);
		S3Object object = s3.getObject(getRequest);
		displayTextInputStream(object.getObjectContent());

		// delete the sample file from S3
		System.out.println("Deleting the sample file...\n");
		s3.deleteObject(bucketName, objectKey);

		// delete the bucket
		System.out.println("Deleting the bucket...\n");
		s3.deleteBucket(bucketName);

		System.out.println("All done!");	
	}

	/**
	 * Creates a temporary file with text data to demonstrate uploading a file
	 * to Amazon S3
	 *
	 * @return A newly created temporary file with text data.
	 *
	 * @throws IOException
	 */
	private static File createSampleFile() throws IOException {
		
		File file = File.createTempFile("test", ".txt");
		file.deleteOnExit();

		Writer writer = new OutputStreamWriter(new FileOutputStream(file));
		writer.write("This is a test file.");
		writer.close();

		return file;
	}

	/**
	 * Displays the contents of the specified input stream as text.
	 *
	 * @param input
	 *            The input stream to display as text.
	 *
	 * @throws IOException
	 */
	private static void displayTextInputStream(InputStream input) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		while (true) {
			
			String line = reader.readLine();
			if (line == null) {
				break;
			}

			System.out.println("\t" + line);
		}
		
		System.out.println();
	}
}
