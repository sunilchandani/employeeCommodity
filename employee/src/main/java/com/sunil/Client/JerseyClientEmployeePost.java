package com.sunil.Client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sunil.Entities.Employee;

public class JerseyClientEmployeePost {
	public static void main(String[] args) {
		 
		try {
			
			ClientConfig clientConfig = new DefaultClientConfig();
	        clientConfig.getFeatures().put(
	                JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);
	 
			WebResource webResource = client
			   .resource("http://localhost:8080/employee/rest/json/employee/post");
	 
			Employee input = new Employee(5,"SUNIL","MATHS",22,"Forum");
			ClientResponse response = webResource.type("application/json")
			   .post(ClientResponse.class, input);
	 
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
