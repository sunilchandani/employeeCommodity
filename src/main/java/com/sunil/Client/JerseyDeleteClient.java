package com.sunil.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class JerseyDeleteClient {
	public static void main(String[] args) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

			WebResource webResource = null;
			ClientResponse response;

			System.out.println("1. Delete Single Employee.");
			System.out.println("2. Delete All Employee.");
			System.out.print("Enter you choice: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			int choice = Integer.parseInt(br.readLine());
			switch (choice) {
			case 1: {
				System.out.print("Enter Employee ID: ");
				int id = Integer.parseInt(br.readLine());

				webResource = client
						.resource("http://localhost:8080/employee/rest/json/employee/delete/"
								+ id);
				break;
			}
			case 2: {
				webResource = client
						.resource("http://localhost:8080/employee/rest/json/employee/deleteAll");
				break;
			}
			default: {
				System.out.println("Invalid choice.");
				throw new RuntimeException("Failed: invalid Choice.");
			}

			}

			response = webResource.type("application/json").post(
					ClientResponse.class);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
